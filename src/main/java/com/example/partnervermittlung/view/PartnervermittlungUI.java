package com.example.partnervermittlung.view;

import com.example.partnervermittlung.model.Geschlecht;
import com.example.partnervermittlung.model.Profil;
import com.example.partnervermittlung.service.Partnervermittlung;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


/**
 * Konsolen-UI fuer Partnervermittlung
 */
public class PartnervermittlungUI {
    //Service-Objekt erzeugen
    static Partnervermittlung pv = new Partnervermittlung();

    /**
     * Liest Profildaten von der Standardeingabe ein, speichert diese in einem
     * Profil-Objekt und gibt das Objekt zurueck.
     *
     * @return Das erfolgreich erfasste Profil, null im Fehlerfall.
     */
    public static Profil profilErfassen() {

        try {
            //Profildaten von der Konsole einlesen
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("wie ist Ihr Name? ");
            String name = br.readLine();

            System.out.println("Bitte geben Sie Ihr Geburtsdatum im Format TT-MM-JJJJ ein: ");
            String geburtsdatumString = br.readLine();
            // Geburtsdatum-String in ein Datum-Objekt konvertieren
            LocalDate geburtsdatum = LocalDate.parse(geburtsdatumString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            // Abfragen, falls Profil schon gibt
            if (pv.profilExistiert(name, geburtsdatum)) {
                System.out.println("Dieser Profile existiert schon!!");
                return null;
            }
            // Geschlecht für die Suche abfragen
            Geschlecht geschlechtSuche = null;
            // geschlecht abfragen
            Geschlecht geschlecht = null;
            System.out.print("Bitte geben Sie Ihr Geschlecht ein (m/w): ");
            String geschlechtString = br.readLine();
            if (geschlechtString.equalsIgnoreCase("m")) {
                geschlecht = Geschlecht.MAENNLICH;
                geschlechtSuche = Geschlecht.WEIBLICH;
            } else if (geschlechtString.equalsIgnoreCase("w")) {
                geschlecht = Geschlecht.WEIBLICH;
                geschlechtSuche = Geschlecht.MAENNLICH;
            }

            //Interessen
            System.out.print("Geben Sie Ihre Interessen im Format Reisen, Lesen: ");
            String interessenString = br.readLine();

            // Wohnort abfragen
            System.out.print("Bitte geben Sie Ihren Wohnort ein: ");
            String wohnort = br.readLine();

            // Mindestalter für die Suche abfragen
            int minAlter;
            do {
                System.out.print("Bitte geben Sie das Mindestalter für die Suche ein: ");
                minAlter = Integer.parseInt(br.readLine());
            } while (minAlter < 18);


            // Höchstalter für die Suche abfragen
            System.out.print("Bitte geben Sie das Höchstalter für die Suche ein: ");
            int maxAlter = Integer.parseInt(br.readLine());

            // Interessen für die Suche abfragen
            System.out.print("Bitte geben Sie Ihre Interessen für die Suche ein: ");
            String suchInteressen = br.readLine();

            // Wohnort für die Suche abfragen
            System.out.print("Bitte geben Sie den Wohnort für die Suche ein: ");
            String suchWohnort = br.readLine();

            UUID uuid = UUID.randomUUID();        //ID fuer das Profil erzeugen
            return new Profil(uuid, name, geburtsdatum, geschlecht, interessenString,
                    wohnort, geschlechtSuche, minAlter, maxAlter, suchInteressen, suchWohnort);            // ## to do ##
        } catch (IOException e) {
            System.err.println("Fehler bei der Dateneingabe: " + e.getMessage());
            return null;
        } catch (NumberFormatException e) {
            System.err.println("Fehler bei der Zahleneingabe: " + e.getMessage());
            return null;
        }
    }


    /**
     * Zeigt das Hauptmenue und Untermenues an, gibt Eingaben an die Service-Klasse Partnervermittlung
     * weiter, nimmt deren Ausgaben entgegen und zeigt sie an.
     *
     * @param pv Partnervermittlung-Objekt, mit dem kommuniziert wird.
     */
    public static void zeigeMenue(Partnervermittlung pv) {
        boolean ok;                //Rueckgabewert von Lese-/Schreiboperationen
        boolean ende = false;      //Abbruchbedingung fuer die Menueschleife
        int ziffer;                //fuer Menueauswahl
        Profil profil;             //fuer die Zwischenspeicherug von erfassten Profilen
        String uuidString;
        UUID uuid;
        String wohnort;
        String geschlecht;

        if (pv == null) return;    //ohne Service-Objekt geht nichts

        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //alternativ mit java.util.Scanner:
        Scanner sc = new Scanner(System.in);

        //solange nicht Programmende gewaehlt, nach jeder Aktion wieder Menue anzeigen
        while (!ende) {
            System.out.println("\n(1) Profil erfassen");
            System.out.println("(2) Profil suchen");
            System.out.println("(3) Profil loeschen");
            System.out.println("(4) Profile ausgeben");
            System.out.println("(5) Profile suchen mit bestimmten Eigenschaften");
            System.out.println("(6) Für mich Profile zeigen");
            System.out.println("(7) Programm beenden");

            try {
                //ziffer = Integer.parseInt(br.readLine());	//Menueauswahl einlesen
                ziffer = Integer.parseInt(sc.nextLine());    //Menueauswahl einlesen

                switch (ziffer) {
                    case 1: //Profil erfassen
                        profil = profilErfassen();
                        if (profil != null) {
                            pv.profilEintragen(profil);
                            System.out.println("Erfassung erfolgreich.");
                            System.out.println("Ihre ID: " + profil.getUUID().toString());
                            if (pv.profileSpeichern()) {
                                System.out.println("Speicherung erfolgreich.");
                            } else {
                                System.out.println("Speicherung erfolglos.");
                            }
                        }
                        break;

                    case 2: //Profil anhand seiner UUID suchen
                        ok = false;
                        do {
                            System.out.println("Profil-UUID (nur Return -> zurueck): ");
                            //uuidString = br.readLine();
                            uuidString = sc.nextLine();
                            if (uuidString.equals("")) break;    //zurueck zum Menue
                            try {
                                uuid = UUID.fromString(uuidString);
                                ok = true;

                                profil = pv.profilSuchen(uuid);
                                if (profil != null) {
                                    System.out.println(profil.toString());
                                } else {
                                    System.out.println("Nicht gefunden.");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Fehler: " + e.getMessage());
                            }
                        } while (!ok);

                        break;

                    case 3: //Profil anhand seiner UUID loeschen
                        ok = false;
                        do {
                            System.out.println("Profil-UUID (nur Return -> zurueck): ");
                            //uuidString = br.readLine();
                            uuidString = sc.nextLine();
                            if (uuidString.equals("")) break;    //zurueck zum Menue
                            try {
                                uuid = UUID.fromString(uuidString);

                                ok = pv.profilLoeschen(uuid);
                                if (ok) {
                                    System.out.println("Erfolgreich geloescht.");
                                } else {
                                    System.out.println("Loeschen fehlgeschlagen.");
                                }
                            } catch (IllegalArgumentException e) {
                                System.err.println("Fehler: " + e.getMessage());
                            }
                        } while (!ok);

                        break;

                    case 4: //Liste aller Profile ausgeben, die mit min und max Alter übereinstimmen.
                        String profileAlsString = pv.gibProfileAlsString();
                        if (profileAlsString != null) {
                            System.out.println(profileAlsString);
                        } else {
                            System.out.println("Keine Profile gefunden.");
                        }
                        break;

                    case 5: //Liste aller gefilterte Profile ausgeben
                        System.out.println("Geben Sie den Wohnort: ");
                        wohnort = sc.nextLine();
                        System.out.println("Geben Sie das Geschlecht: ");
                        geschlecht = sc.nextLine();
                        if (geschlecht.equalsIgnoreCase("m")) {
                            geschlecht = String.valueOf(Geschlecht.MAENNLICH);
                        } else if (geschlecht.equalsIgnoreCase("w")) {
                            geschlecht = String.valueOf(Geschlecht.WEIBLICH);
                        }

                        List<Profil> olderThan30 = pv.filterProfiles(wohnort, Geschlecht.valueOf(geschlecht));
                        olderThan30.forEach(System.out::println);
                        break;

                    case 6:
                        System.out.println("Profil-UUID (nur Return -> zurueck): ");

                        //uuidString = br.readLine();
                        uuidString = sc.nextLine();
                        if (uuidString.equals("")) break;    //zurueck zum Menue
                        try {
                            uuid = UUID.fromString(uuidString);
                            String s = pv.gibProfileFuerMichAlsString(uuid);
                            System.out.println(s);
                        } catch (IllegalArgumentException e) {
                            System.err.println("Fehler: " + e.getMessage());
                        }
                        break;

                    case 7:
                        System.out.println("Programm wird beendet.");
                        ende = true;
                        break;

                    default:
                        System.out.println("Keine gueltige Eingabe.");
                } //switch
            } catch (NumberFormatException e) {
                System.err.println("Keine gueltige Eingabe.");
            }
        } //while
        //br.close();
        sc.close();
    }

    /**
     * main-Routine zur Menueanzeige fuer die Partnervermittlung.
     *
     * @param args Kommandozeilenparameter, derzeit nicht verwendet
     */
    public static void main(String[] args) {
        //Menue zur User-Interaktion mit der Partnervermittlung anzeigen
        zeigeMenue(pv);
    }
}
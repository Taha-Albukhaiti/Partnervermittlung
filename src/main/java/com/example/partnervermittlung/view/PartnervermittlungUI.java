package com.example.partnervermittlung.view;

import com.example.partnervermittlung.model.Geschlecht;
import com.example.partnervermittlung.model.Profil;
import com.example.partnervermittlung.service.Partnervermittlung;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.Stack;
import java.util.UUID;


/**
 * Konsolen-UI fuer Partnervermittlung
 */
public class PartnervermittlungUI {

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

            System.out.println("Bitte geben Sie Ihr Geburtsdatum im Format TT.MM.JJJJ ein: ");
            String geburtsdatumString = br.readLine();
            // Geburtsdatum-String in ein Datum-Objekt konvertieren
            LocalDate geburtsdatum = LocalDate.parse(geburtsdatumString, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            int alter = geburtsdatum.getYear();

            // geschlecht abfragen
            boolean ok = true;
            Geschlecht geschlecht = null;
            while (ok) {
                System.out.print("Bitte geben Sie Ihr Geschlecht ein (m/w): ");
                String geschlechtString = br.readLine();
                switch (geschlechtString.toUpperCase()) {
                    case "M":
                        geschlecht = Geschlecht.MAENNLICH;
                        break;
                    case "W":
                        geschlecht = Geschlecht.WEIBLICH;
                        break;
                    default:
                        System.out.println("Ungültiges Geschlecht! Nochmal");
                        break;
                }
                if (geschlecht != null) {
                    ok = false;
                }
            }
            //Interessen
            System.out.print("Geben Sie Ihre Interessen im Format Reisen, Lesen: ");
            String interessenString = br.readLine();

            // Wohnort abfragen
            System.out.print("Bitte geben Sie Ihren Wohnort ein: ");
            String wohnort = br.readLine();

            // Geschlecht für die Suche abfragen
            System.out.print("Bitte geben Sie das Geschlecht ein, nach dem Sie suchen (m/w): ");
            String suchGeschlecht = br.readLine();

            // Mindestalter für die Suche abfragen
            System.out.print("Bitte geben Sie das Mindestalter für die Suche ein: ");
            int minAlter = Integer.parseInt(br.readLine());

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
                    wohnort, suchWohnort, minAlter, maxAlter, suchInteressen, suchWohnort);            // ## to do ##
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
            System.out.println("(5) Profile speichern");
            System.out.println("(6) Profile laden");
            System.out.println("(7) Programm beenden");

            try {
                //ziffer = Integer.parseInt(br.readLine());	//Menueauswahl einlesen
                ziffer = Integer.parseInt(sc.nextLine());    //Menueauswahl einlesen

                switch (ziffer) {
                    case 1: //Profil erfassen
                        profil = profilErfassen();
                        if (profil != null) {
                            pv.profilEintragen(profil);
                            System.out.println("Ihre ID: " + profil.getUUID().toString());
                            System.out.println("Erfassung erfolgreich.");
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

                    case 4: //Liste aller Profile ausgeben
                        String profileAlsString = pv.gibProfileAlsString();
                        if (profileAlsString != null) {
                            System.out.println(profileAlsString);
                        } else {
                            System.out.println("Keine Profile gefunden.");
                        }
                        break;

                    case 5: //Profile speichern
                        ok = pv.profileSpeichern();
                        if (ok) {
                            System.out.println("Speicherung erfolgreich.");
                        } else {
                            System.out.println("Fehler beim Speichern.");
                        }
                        break;

                    case 6: //Profile laden
                        pv.alleProfileLoeschen();        //zunaechst Altdaten entfernen
                        ok = pv.profileLaden();
                        if (ok) {
                            System.out.println("Laden erfolgreich.");
                        } else {
                            System.out.println("Fehler beim Laden.");
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
        //Service-Objekt erzeugen
        Partnervermittlung pv = new Partnervermittlung();

        //Menue zur User-Interaktion mit der Partnervermittlung anzeigen
        zeigeMenue(pv);
    }
}
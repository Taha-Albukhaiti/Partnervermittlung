package com.example.partnervermittlung.service;

import com.example.partnervermittlung.model.Geschlecht;
import com.example.partnervermittlung.model.Profil;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Service-Klasse Partnervermittlung zur Verwaltung und zum Matching von Profilen
 */
public class Partnervermittlung {

    //private Profil[] profile = new Profil[MAX_PROFILE];
    private List<Profil> profile = new ArrayList<>();   //ArrayList zum Speichern der Profile
    private int zaehler = 0;                            //zaehler, um neue Profile an der richtigen Stelle abzulegen
    private String profileDatei = "date.txt";

    public Partnervermittlung() {
        profile.clear();
        profileLaden();
    }

    /**
     * Nimmt ein Profil entgegen und traegt es in den Profil-Container ein.
     *
     * @param profil Das zu speichernde Profil.
     */
    public void profilEintragen(Profil profil) {
        if (profil != null) {
            profile.add(profil);

        } else {
            System.out.println("Es gibt keinen Platz mehr für weitere Profile.");
        }
    }


    /**
     * Ermittelt das Profil mit der uebergebenen UUID, sofern vorhanden.
     *
     * @param uuid Die UUID des gesuchten Profils.
     * @return Das Profil mit der uebergebenen UUID. null, falls UUID nicht existent.
     */
    public Profil profilSuchen(UUID uuid) {
        //Profile maximal bis zum Ende durchlaufen
        for (Profil profil : profile) {
            if (profil != null && profil.getUUID().compareTo(uuid) == 0) {    //gefunden
                return profil;                                                //Profil zurueckgeben
            }
        } //for
        return null;    //nicht gefunden
    }


    /**
     * Loescht das Profil mit der uebergebenen UUID, sofern vorhanden.
     *
     * @param uuid Die UUID des zu loeschenden Profils.
     * @return true, falls Loeschen erfolgreich, sonst false.
     */
    public boolean profilLoeschen(UUID uuid) {
        //Profile maximal bis zum Ende durchlaufen
        for (int i = 0; i < profile.size(); i++) {
            if (profile.get(i) != null && profile.get(i).getUUID().compareTo(uuid) == 0) {    //gefunden
                profile.remove(i);    //loeschen
                return true;
            }
        } //for
        return false;    //nichts geloescht
    }

    /**
     * Loescht das Profil mit der uebergebenen UUID, sofern vorhanden.
     *
     * @param name         Der Name des zu loeschenden Profils.
     * @param geburtsdatum Geburtstag des zu loeschenden Profils.
     * @return true, falls Loeschen erfolgreich, sonst false.
     */
    public boolean profilLoeschenPerNameUndGeburtsdatum(String name, LocalDate geburtsdatum) {
        //Profile maximal bis zum Ende durchlaufen
        for (int i = 0; i < profile.size(); i++) {
            if ((profile.get(i) != null) && (profile.get(i).getName().equals(name))
                    && (profile.get(i).getGeburtsdatum().equals(geburtsdatum))) {    //gefunden
                profile.remove(i);    //loeschen
                return true;
            }
        } //for
        return false;    //nichts geloescht
    }


    /**
     * Gibt eine Liste mit allen Profilen als String zurueck.
     *
     * @return Die Profile als String. null, falls keine Profile vorhanden
     */
    public String gibProfileAlsString() {

        String profileText = "";

        //Jedes Profil zum Rueckgabestring hinzufuegen
        //alternative Implementierung der Methode:
        int i = 1;
        for (Profil profil : profile) {
            if (profil != null) {
                profileText += (i++) + ". " + profil + "\n";
            }
        } //for
        return profileText.equals("") ? null : profileText;
    }

    public boolean profilExistiert(String name, LocalDate geburtsdatum) {
        return profile.stream()
                .anyMatch(profil -> profil.getName().equals(name) && profil.getGeburtsdatum().equals(geburtsdatum));
    }

    /**
     * Speichert die Profile in einer Datei.
     *
     * @return true, falls Speicherung erfolgreich, false sonst
     */
    public boolean profileSpeichern() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(profileDatei))) {
            for (Profil profil : profile) {
                writer.write(profil.getUUID() + "\n");
                writer.write(profil.getName() + "\n");
                writer.write(profil.getGeburtsdatum() + "\n");
                writer.write(profil.getGeschlecht() + "\n");
                writer.write(profil.getInteressen() + "\n");
                writer.write(profil.getWohnort() + "\n");
                writer.write(profil.getSuchGeschlecht() + "\n");
                writer.write(profil.getMinAlter() + "\n");
                writer.write(profil.getMaxAlter() + "\n");
                writer.write(profil.getSuchInteressen() + "\n");
                writer.write(profil.getSuchWohnort());
                writer.write("\n");
            }
            return true;
        } catch (IOException e) {
            System.err.println("Fehler beim Speichern: " + e.getMessage());
            return false;
        }
    }

    /**
     * Laedt Profile aus einer Datei.
     *
     * @return true, falls Laden erfolgreich, false sonst
     */
    public boolean profileLaden() {
        try (BufferedReader br = new BufferedReader(new FileReader(profileDatei))) {
            while (br.ready()) {
                profile.add(new Profil(UUID.fromString(br.readLine()), br.readLine(),
                        LocalDate.parse(br.readLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        Geschlecht.valueOf(br.readLine().toUpperCase()), br.readLine(), br.readLine(),
                        Geschlecht.valueOf(br.readLine().toUpperCase()), Integer.parseInt(br.readLine()),
                        Integer.parseInt(br.readLine()), br.readLine(), br.readLine()));
            }
            return true;
        } catch (Exception e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
            return false;
        }
    }


    /*public boolean profileLaden() {
        String line;
        UUID id = null;
        String name = null;
        LocalDate geburstdatum = null;
        Geschlecht geschlecht = null;
        String interessen = null;
        String wohnort = null;
        Geschlecht sucheNachGeschlecht = null;
        int mindestalterSuche = 0;
        int hoechstalterSuche = 0;
        String sucheNachInteressen = null;
        String sucheNachWohnort = null;
        try (BufferedReader br = new BufferedReader(new FileReader(profileDatei))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(": ");
                switch (data[0].toLowerCase()) {
                    case "id" -> id = UUID.fromString(data[1].trim());
                    case "name" -> name = data[1].trim();
                    case "alter" -> geburstdatum = LocalDate.parse(data[1].trim());
                    case "geschlecht" -> geschlecht = Geschlecht.valueOf(data[1].trim().toUpperCase());
                    case "interessen" -> interessen = data[1].trim();
                    case "wohnort" -> wohnort = data[1].trim();
                    case "suche nach geschlecht" ->
                            sucheNachGeschlecht = Geschlecht.valueOf(data[1].trim().toUpperCase());
                    case "mindestalter-suche" -> mindestalterSuche = Integer.parseInt(data[1].trim());
                    case "höchstalter-suche" -> hoechstalterSuche = Integer.parseInt(data[1].trim());
                    case "suche nach interessen" -> sucheNachInteressen = data[1].trim();
                    case "suche nach wohnort" -> sucheNachWohnort = data[1].trim();
                    default -> throw new IllegalArgumentException("Ungültiges Datenformat: " + line);
                }
                Profil profil = new Profil(id, name, geburstdatum, geschlecht, interessen, wohnort,
                        sucheNachGeschlecht, mindestalterSuche, hoechstalterSuche, sucheNachInteressen, sucheNachWohnort);
                profile.add(profil);
            }
        } catch (Exception e) {
            System.err.println("Fehler beim Laden: " + e.getMessage());
            return false;
        }
        return true;
    }

     */

    /**
     * Loescht alle Profile aus dem Datencontainer.
     * Wird vor dem Laden aus einer Datei benoetigt, um Altdaten aus dem Container zu entfernen.
     */
    public void alleProfileLoeschen() {
        profile.clear();
    }


    /**
     * Gibt Informationen zur Datei der Objektserialisierung (sofern vorhanden) als String zur�ck.
     *
     * @return Die Dateiinfos als String
     */
    public String gibDateiInfos(File datei) {

        String dateiInfos = "";
        if (datei != null && datei.exists()) {
            dateiInfos += "\nInformationen zu " + datei.getName() + ":\n\n";
            // ## to do ##
        } else {
            dateiInfos += "Datei nicht gefunden.\n";
        }
        return dateiInfos;
    }


    /**
     * Hilfsmethode zum Schliessen von Streams, die von den Speichern/Laden-Methoden aufgerufen wird.
     * Das Schliessen kann in Ausnahmefaellen eine IOException ausloesen, die aber so unwahrscheinlich ist
     * (Platte voll, ...), dass sie nicht behandelt wird.
     *
     * @param closeable der zu schliessende Stream
     */
    private void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }

}
package com.example.partnervermittlung.service;

import com.example.partnervermittlung.model.Profil;

import java.io.Closeable;
import java.io.IOException;
import java.util.UUID;
import java.io.File;


/** Service-Klasse Partnervermittlung zur Verwaltung und zum Matching von Profilen
 */ 
public class Partnervermittlung {
	
	private static final int MAX_PROFILE = 100;

	private Profil[] profile = new Profil[MAX_PROFILE];	//Array zum Speichern der Profile

	private int zaehler = 0;							//zaehler, um neue Profile an der richtigen Stelle abzulegen

	
	/** Nimmt ein Profil entgegen und traegt es in den Profil-Container ein.
      * @param profil Das zu speichernde Profil.
	 */ 
	public void profilEintragen(Profil profil) {
			
		if (zaehler >= profile.length - 1) 	//noch Platz vorhanden?
			return;							//wenn nein, Methode verlassen

			//Profil eintragen und danach zaehler inkrementieren
			profile[zaehler++] = profil;

	}

	
	/** Ermittelt das Profil mit der uebergebenen UUID, sofern vorhanden.
	  * @param uuid Die UUID des gesuchten Profils.
	  * @return Das Profil mit der uebergebenen UUID. null, falls UUID nicht existent.
	 */ 
	public Profil profilSuchen(UUID uuid) {
		//Profile maximal bis zum Ende durchlaufen
		for (Profil profil : profile) {
			if (profil != null && profil.getUUID().compareTo(uuid) == 0) {	//gefunden
				return profil;												//Profil zurueckgeben
			}
		} //for
		return null;	//nicht gefunden
	}
	

	/** Loescht das Profil mit der uebergebenen UUID, sofern vorhanden.
	  * @param uuid Die UUID des zu loeschenden Profils.
	  * @return true, falls Loeschen erfolgreich, sonst false.
	 */ 
	public boolean profilLoeschen(UUID uuid) {
		//Profile maximal bis zum Ende durchlaufen
		for (int i = 0; i < profile.length; i++) {
			if (profile[i] != null && profile[i].getUUID().compareTo(uuid) == 0) {	//gefunden
				profile[i] = null;		//loeschen
				return true;
			}
		} //for
		return false;	//nichts geloescht
	}
	

	/** Gibt eine Liste mit allen Profilen als String zurueck.
	 * @return Die Profile als String. null, falls keine Profile vorhanden
	 */ 
	public String gibProfileAlsString() {

		String profileText = "";

		//Jedes Profil zum Rueckgabestring hinzufuegen
		//alternative Implementierung der Methode:
		int i = 1;  
		for (Profil profil : profile) {
			if (profil != null) {
				profileText += (i++) + ". " + profil.toString() + "\n";
			}
		} //for
		return profileText.equals("") ? null : profileText;
	}
	
	
	/** Speichert die Profile in einer Datei. 
	 * @return true, falls Speicherung erfolgreich, false sonst
	 */
	public boolean profileSpeichern() {
		return false;	//## dummy ##
	}
	
	
	/** Laedt Profile aus einer Datei. 
	 * @return true, falls Laden erfolgreich, false sonst
	 */
	public boolean profileLaden() {
		return false;	//## dummy ##
	}
	

	/** Loescht alle Profile aus dem Datencontainer.
	 * Wird vor dem Laden aus einer Datei benoetigt, um Altdaten aus dem Container zu entfernen.
	 */
	public void alleProfileLoeschen() {
		for (int i = 0; i < profile.length; i++) {
			profile[i] = null;
		}
	}
	
	
	/** Gibt Informationen zur Datei der Objektserialisierung (sofern vorhanden) als String zur�ck.
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

		
	/** Hilfsmethode zum Schliessen von Streams, die von den Speichern/Laden-Methoden aufgerufen wird.
	 *  Das Schliessen kann in Ausnahmefaellen eine IOException ausloesen, die aber so unwahrscheinlich ist
	 *  (Platte voll, ...), dass sie nicht behandelt wird.
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
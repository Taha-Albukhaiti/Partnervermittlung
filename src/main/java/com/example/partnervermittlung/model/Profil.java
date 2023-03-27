package com.example.partnervermittlung.model;

import java.time.LocalDate;
import java.util.UUID;

public class Profil {

	private UUID uuid;
	private String name;
	private LocalDate geburtsdatum;
	private Geschlecht geschlecht;
	private String interessen;
	private String wohnort;
	private Geschlecht suchGeschlecht;
	private int minAlter;
	private int maxAlter;
	private String suchInteressen;
	private String suchWohnort;
	
	public UUID getUUID() {
		return uuid;
	}

	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public Profil(UUID uuid, String name, LocalDate geburtsdatum, Geschlecht geschlecht,
				  String interessen, String wohnort, Geschlecht suchGeschlecht, int minAlter,
				  int maxAlter, String suchInteressen, String suchWohnort) {
		this.uuid = uuid;
		this.name = name;
		this.geburtsdatum = geburtsdatum;
		this.geschlecht = geschlecht;
		this.interessen = interessen;
		this.wohnort = wohnort;
		this.suchGeschlecht = suchGeschlecht;
		this.minAlter = minAlter;
		this.maxAlter = maxAlter;
		this.suchInteressen = suchInteressen;
		this.suchWohnort = suchWohnort;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}

	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht = geschlecht;
	}

	public String getInteressen() {
		return interessen;
	}

	public void setInteressen(String interessen) {
		this.interessen = interessen;
	}

	public String getWohnort() {
		return wohnort;
	}

	public void setWohnort(String wohnort) {
		this.wohnort = wohnort;
	}

	public Geschlecht getSuchGeschlecht() {
		return suchGeschlecht;
	}

	public void setSuchGeschlecht(Geschlecht suchGeschlecht) {
		this.suchGeschlecht = suchGeschlecht;
	}

	public int getMinAlter() {
		return minAlter;
	}

	public void setMinAlter(int minAlter) {
		this.minAlter = minAlter;
	}

	public int getMaxAlter() {
		return maxAlter;
	}

	public void setMaxAlter(int maxAlter) {
		this.maxAlter = maxAlter;
	}

	public String getSuchInteressen() {
		return suchInteressen;
	}

	public void setSuchInteressen(String suchInteressen) {
		this.suchInteressen = suchInteressen;
	}

	public String getSuchWohnort() {
		return suchWohnort;
	}

	public void setSuchWohnort(String suchWohnort) {
		this.suchWohnort = suchWohnort;
	}

	@Override
	public String toString() {
		return  "ID: " + uuid.toString() + "\n" +
				"Name: " + name + "\n" +
				"Alter: " + geburtsdatum + "\n" +
				"Geschlecht: " + geschlecht + "\n" +
				"Interessen: " + interessen + "\n" +
				"Wohnort: " + wohnort + "\n" +
				"Suche nach Geschlecht: " + suchGeschlecht + "\n" +
				"Mindestalter-Suche: " + minAlter + "\n" +
				"Höchstalter-Suche: " + maxAlter + "\n" +
				"Suche nach Interessen: " + suchInteressen + "\n" +
				"Suche nach Wohnort: " + suchWohnort + "\n";
	}
}


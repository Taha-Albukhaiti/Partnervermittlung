package com.example.partnervermittlung.model;

import javafx.beans.property.*;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public class Profil {
	private UUID uuid;
	private ObjectProperty<Image> bild;
	private StringProperty name;
	private LocalDate geburtsdatum;
	private ObjectProperty<Geschlecht> geschlecht;
	private StringProperty interessen;
	private StringProperty wohnort;

	private ObjectProperty<Geschlecht> suchGeschlecht;
	private IntegerProperty minAlter;
	private IntegerProperty maxAlter;
	private StringProperty suchInteressen;
	private StringProperty suchWohnort;

	public Profil(UUID uuid, String name, LocalDate geburtsdatum, Geschlecht geschlecht,
				  String interessen, String wohnort, Geschlecht suchGeschlecht, int minAlter,
				  int maxAlter, String suchInteressen, String suchWohnort) {
		this.uuid = uuid;
		this.bild = new SimpleObjectProperty<Image>();
		this.name = new SimpleStringProperty(name);
		this.geburtsdatum = geburtsdatum;
		this.geschlecht = new SimpleObjectProperty<Geschlecht>(geschlecht);
		this.interessen = new SimpleStringProperty(interessen);
		this.wohnort = new SimpleStringProperty(wohnort);
		this.suchGeschlecht = new SimpleObjectProperty<Geschlecht>(suchGeschlecht);
		this.minAlter = new SimpleIntegerProperty(minAlter);
		this.maxAlter = new SimpleIntegerProperty(maxAlter);
		this.suchInteressen = new SimpleStringProperty(suchInteressen);
		this.suchWohnort = new SimpleStringProperty(suchWohnort);
	}





	public Image getBild() {
		return bild.get();
	}

	public ObjectProperty<Image> bildProperty() {
		return bild;
	}

	public void setBild(Image bild) {
		this.bild.set(bild);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name.set(name);
	}

	public LocalDate getGeburtsdatum() {
		return geburtsdatum;
	}
	public void setGeburtsdatum(LocalDate geburtsdatum) {
		this.geburtsdatum = geburtsdatum;
	}

	public Geschlecht getGeschlecht() {
		return geschlecht.get();
	}

	public ObjectProperty<Geschlecht> geschlechtProperty() {
		return geschlecht;
	}

	public void setGeschlecht(Geschlecht geschlecht) {
		this.geschlecht.set(geschlecht);
	}

	public String getInteressen() {
		return interessen.get();
	}

	public void setInteressen(String interessen) {
		this.interessen.set(interessen);
	}

	public String getWohnort() {
		return wohnort.get();
	}

	public void setWohnort(String wohnort) {
		this.wohnort.set(wohnort);
	}

	public Geschlecht getSuchGeschlecht() {
		return suchGeschlecht.get();
	}

	public ObjectProperty<Geschlecht> suchGeschlechtProperty() {
		return suchGeschlecht;
	}

	public void setSuchGeschlecht(Geschlecht suchGeschlecht) {
		this.suchGeschlecht.set(suchGeschlecht);
	}

	public int getMinAlter() {
		return minAlter.get();
	}

	public void setMinAlter(int minAlter) {
		this.minAlter.set(minAlter);
	}

	public int getMaxAlter() {
		return maxAlter.get();
	}

	public void setMaxAlter(int maxAlter) {
		this.maxAlter.set(maxAlter);
	}

	public String getSuchInteressen() {
		return suchInteressen.get();
	}

	public void setSuchInteressen(String suchInteressen) {
		this.suchInteressen.set(suchInteressen);
	}

	public String getSuchWohnort() {
		return suchWohnort.get();
	}

	public void setSuchWohnort(String suchWohnort) {
		this.suchWohnort.set(suchWohnort);
	}

	public StringProperty uuidStringProperty() {
		return new SimpleStringProperty(uuid.toString());
	}
	public StringProperty nameProperty() {
		return name;
	}

	public StringProperty interessenProperty() {
		return interessen;
	}

	public StringProperty wohnortProperty() {
		return wohnort;
	}

	public IntegerProperty minAlterProperty() {
		return minAlter;
	}

	public IntegerProperty maxAlterProperty() {
		return maxAlter;
	}

	public StringProperty suchInteressenProperty() {
		return suchInteressen;
	}

	public StringProperty suchWohnortProperty() {
		return suchWohnort;
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


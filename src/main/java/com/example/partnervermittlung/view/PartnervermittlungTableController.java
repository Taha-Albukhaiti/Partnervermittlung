package com.example.partnervermittlung.view;

import com.example.partnervermittlung.PartnervermittlungFX;
import com.example.partnervermittlung.model.Geschlecht;
import com.example.partnervermittlung.model.Profil;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

/**
 * Controller fuer das Hauptfenster der Anwendung, also die Partnerprofile-Tabelle plus Menue.
 *
 * @author Taha
 */
public class PartnervermittlungTableController {
    //die folgenden Annotationen dienen der Verknuepfung der Attribute mit den Controls in der fxml-Datei
    @FXML
    private TableView<Profil> profilTable;
    @FXML
    private TableColumn<Profil, String> IDColumn;
    @FXML
    private TableColumn<Profil, Image> bildColumn;
    @FXML
    private TableColumn<Profil, String> nameColumn;
    @FXML
    private TableColumn<Profil, Integer> alterColumn;
    @FXML
    private TableColumn<Profil, String> geschlechtColumn;
    @FXML
    private TableColumn<Profil, String> interessenColumn;
    @FXML
    private TableColumn<Profil, String> wohnortColumn;
    @FXML
    private TableColumn<Profil, String> suchWohnortColumn;
    @FXML
    private TableColumn<Profil, String> suchGeschlechtColumn;


    //Referenz zur Applikation
    private PartnervermittlungFX mainApp;


    /**
     * Initialisiert das Controller-Objekt. Wird automatisch aufgerufen nach dem Laden der fxml-Datei.
     */
    @FXML
    public void initialize() {
        IDColumn.setCellValueFactory(cellData -> cellData.getValue().uuidStringProperty());
        bildColumn.setCellValueFactory(cellData -> cellData.getValue().bildProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        alterColumn.setCellValueFactory(cellData -> {
            LocalDate geburtsdatum = cellData.getValue().getGeburtsdatum();
            int alter = Period.between(geburtsdatum, LocalDate.now()).getYears();
            return new SimpleIntegerProperty(alter).asObject();
        });
        geschlechtColumn.setCellValueFactory(cellData -> {
            Geschlecht geschlecht = cellData.getValue().getGeschlecht();
            return new SimpleStringProperty(geschlecht.toString());
        });
        suchGeschlechtColumn.setCellValueFactory(cellData ->{
            Geschlecht geschlecht = cellData.getValue().getSuchGeschlecht();
            return new SimpleStringProperty(geschlecht.toString());
        });
        interessenColumn.setCellValueFactory(cellData -> cellData.getValue().interessenProperty());
        wohnortColumn.setCellValueFactory(cellData -> cellData.getValue().wohnortProperty());
        suchWohnortColumn.setCellValueFactory(cellData -> cellData.getValue().suchWohnortProperty());
    }

    /**
     * Wird vom Hauptprogramm aufgerufen, um eine Referenz an sich selbst an den Controller zu uebergeben.
     * Setzt ausserdem die im Backend hinterlegten Webinare in die Tabelle.
     * @param mainApp
     */
    public void setMainApp(PartnervermittlungFX mainApp) {
        this.mainApp = mainApp;

        //ObservableList mit Profile-Objekten in Tabelle eintragen
        profilTable.setItems(mainApp.getPartnerverimttlungsManager().getProfile());
    }

    public void setProfilBild(Profil profil) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Bild auswählen");
        File selectedFile = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            profil.setBild(image);
        }
    }
}

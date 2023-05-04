package com.example.partnervermittlung.view;

import com.example.partnervermittlung.PartnervermittlungFX;
import com.example.partnervermittlung.model.Profil;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 * Controller fuer das Hauptfenster der Anwendung, also die Partnerprofile-Tabelle plus Menue.
 *
 * @author Taha
 */
public class PartnervermittlungTableController {
    //die folgenden Annotationen dienen der Verknuepfung der Attribute mit den Controls in der fxml-Datei
    @FXML
    private TableView<Profil> webinarTable;

    //Referenz zur Applikation
    private PartnervermittlungFX mainApp;

    /**
     * Wird vom Hauptprogramm aufgerufen, um eine Referenz an sich selbst an den Controller zu uebergeben.
     * Setzt ausserdem die im Backend hinterlegten Webinare in die Tabelle.
     * @param mainApp
     */
    public void setMainApp(PartnervermittlungFX mainApp) {
        this.mainApp = mainApp;

        //ObservableList mit Profile-Objekten in Tabelle eintragen
        //webinarTable.setItems(mainApp.getPartnerverimttlungsManager().getProfile());
    }
}

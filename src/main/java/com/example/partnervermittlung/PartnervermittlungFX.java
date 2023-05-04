package com.example.partnervermittlung;
import com.example.partnervermittlung.service.Partnervermittlung;
import com.example.partnervermittlung.view.PartnervermittlungTableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class PartnervermittlungFX extends Application {
    private Stage stage;
    private BorderPane rootLayout;

    //Verknüpfung zum Backend (Service)
    private Partnervermittlung pv = new Partnervermittlung();

    public PartnervermittlungFX(){}
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        this.stage.setTitle("Partnervermittlung");

        //View laden
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PartnervermittlungFX.class.getResource("view/PartnervermittlungTable.fxml"));
            rootLayout = (BorderPane) loader.load();

            //Controller (steht in fxml) holen und mit der MainApp (this) verknuepfen
            PartnervermittlungTableController controller = loader.getController();
            controller.setMainApp(this);

            //Szene mit Root Layout erzeugen, auf die Stage setzen und anzeigen
            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.setTitle("Partnervermittlung");
            stage.show();

            // ### Beginn catch-Block VARIANTE 1: View aus fxml ###
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ### Ende catch-Block VARIANTE 1: View aus fxml ###
    }

    public static void main(String[] args) {
        launch();
    }

    public Partnervermittlung getPartnerverimttlungsManager() {
        return pv;
    }
}
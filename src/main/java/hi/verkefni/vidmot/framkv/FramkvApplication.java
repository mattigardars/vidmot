package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 The main class of the Framkv application that extends the JavaFX Application class.
 */
public class FramkvApplication extends Application {
    /**
     The entry point of the application.
     @param stage the primary stage for this application
     @throws Exception if an error occurs while initializing the application
     */
    @Override
    public void start(Stage stage) throws Exception {
        DataModel dataModel = new DataModel();
        DashboardController.setDataModel(dataModel);
        CreateTaskController.setDataModel(dataModel);

        var scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.LOGIN);

        stage.setScene(scene);
        stage.show();
    }

    /**
     The main method that launches the JavaFX application.
     @param args command line arguments
     */
    public static void main(String[] args) {
        launch();
    }
}
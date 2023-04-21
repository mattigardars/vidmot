package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 The {@code FramkvApplication} class is the main entry point for the application.
 It extends the {@code Application} class and overrides its {@code start()} method
 to initialize the application and display the initial login view. It also sets the
 data model for all controllers and configures the size of the stage to the size
 of the primary screen.
 */
public class FramkvApplication extends Application {

    /**
     Initializes the application and displays the initial login view. Sets the
     data model for all controllers and configures the size of the stage to the size
     of the primary screen.
     @param stage The primary stage of the application.
     @throws Exception If an error occurs during application startup.
     */
    @Override
    public void start(Stage stage) throws Exception {
        DataModel dataModel = new DataModel();
        DashboardController.setDataModel(dataModel);
        CreateTaskController.setDataModel(dataModel);
        EditTaskController.setDataModel(dataModel);

        var scene = new Scene(new Pane());

        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.LOGIN);

        stage.setScene(scene);

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        stage.setX(screenBounds.getMinX());
        stage.setY(screenBounds.getMinY());
        stage.setWidth(screenBounds.getWidth());
        stage.setHeight(screenBounds.getHeight());

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
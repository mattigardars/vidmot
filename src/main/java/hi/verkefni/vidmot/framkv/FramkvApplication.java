package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.DataModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class FramkvApplication extends Application {
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

    public static void main(String[] args) {
        launch();
    }
}
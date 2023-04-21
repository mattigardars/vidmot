package hi.verkefni.vidmot.framkv;

import java.nio.file.Path;

/**
 An enumeration of views used in the application.
 Each view corresponds to an FXML file that defines the UI for that view.
 */
public enum View {
    LOGIN("login.fxml"),
    SIGNUP("signUp.fxml"),
    DASHBOARD("dashboard.fxml"),
    CREATETASK("createTask.fxml"),
    EDITTASK("editTask.fxml");

    private String fileName;

    /**
     Constructs a new View with the specified FXML file name.
     @param fileName the name of the FXML file for the view
     */
    View(String fileName) {
        this.fileName = fileName;
    }

    /**
     Returns the name of the FXML file for this view.
     @return the name of the FXML file for this view
     */
    public String getFileName() {
        return fileName;
    }
}


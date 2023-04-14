package hi.verkefni.vidmot.framkv;

/**

 An enumeration representing the different views of the application.

 Each view is associated with an FXML file name.
 */

public enum View {
    LOGIN("login.fxml"),
    SIGNUP("signUp.fxml"),
    DASHBOARD("dashboard.fxml"),

    CREATETASK("createTask.fxml");
    private String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}


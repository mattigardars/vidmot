package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField fxUsername;

    @FXML
    private PasswordField fxPassword;

    @FXML
    private Button fxLoginButton;

    @FXML
    protected void onLogin() {
        String username = fxUsername.getText();
        String password = fxPassword.getText();

        // Validate username and password
        if (!username.isEmpty() && !password.isEmpty()) {
            // save username to user class
            User user = new User(username, true);
            DashboardController.setUser(user); // set the User object in the DashboardController
            fxPassword.clear();
            // switch to dashboard view
            ViewSwitcher.switchTo(View.DASHBOARD);
        } else {
            // Show error message
        }
    }

    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
    }

    private void updateLoginButton() {
        String username = fxUsername.getText().trim();
        String password = fxPassword.getText().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            fxLoginButton.setDisable(false);
        } else {
            fxLoginButton.setDisable(true);
        }
    }

    public void initialize() {
        fxLoginButton.setDisable(true);
        fxUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });

        fxPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });
    }
}

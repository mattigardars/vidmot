package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


/**
 The LoginController class is a controller for the login view of the application.
 It allows users to log in with a username and password, and provides functionality for switching to the signup view.
 */
public class LoginController {
    @FXML
    private TextField fxUsername;

    @FXML
    private PasswordField fxPassword;

    @FXML
    private Button fxLoginButton;

    /**
     Initializes the LoginController by disabling the login button and adding listeners to the username and password fields.
     The listeners call updateLoginButton() method whenever the text in the fields changes.
     */
    public void initialize() {
        fxLoginButton.setDisable(true);
        fxUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });

        fxPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });
    }

    /**
     Called when the login button is clicked. Validates the username and password and switches to the dashboard view if they are valid.
     If they are not valid, an error message is shown.
     */
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
            fxUsername.clear();
            // switch to dashboard view
            ViewSwitcher.switchTo(View.DASHBOARD);
        } else {
            // Show error message
        }
    }

    /**
     Called when the signup button is clicked. Switches to the signup view.
     */
    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
    }

    /**
     Updates the login button's disabled state based on the text in the username and password fields.
     If both fields have text, the login button is enabled. Otherwise, it is disabled.
     */
    private void updateLoginButton() {
        String username = fxUsername.getText().trim();
        String password = fxPassword.getText().trim();

        if (!username.isEmpty() && !password.isEmpty()) {
            fxLoginButton.setDisable(false);
        } else {
            fxLoginButton.setDisable(true);
        }
    }

}

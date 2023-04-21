package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 The SignUpController class is the controller for the Sign Up view of the application.
 It handles user input for creating a new account.
 */
public class SignUpController {
    @FXML
    private TextField fxUsername;

    @FXML
    private PasswordField fxPassword;

    @FXML
    private PasswordField fxPasswordCheck;
    @FXML
    private Button fxLoginButton;


    /**
     Initializes the controller. Disables the login button until all required fields have input.
     */
    public void initialize() {
        fxLoginButton.setDisable(true);
        fxUsername.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });

        fxPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });
        fxPasswordCheck.textProperty().addListener((observable, oldValue, newValue) -> {
            updateLoginButton();
        });
    }

    /**
     Handles the action when the login button is pressed.
     Validates user input and saves the new user to the system.
     Switches to the dashboard view if successful.
     */
    @FXML
    protected void onLogin() {
        String username = fxUsername.getText();
        String password = fxPassword.getText();
        String passwordCheck = fxPasswordCheck.getText();

        // Validate username and password
        if (!username.isEmpty() && !password.isEmpty() && !passwordCheck.isEmpty()) {
            // save username to user class
            User user = new User(username, true);
            DashboardController.setUser(user); // set the User object in the DashboardController
            fxPassword.clear();
            fxPasswordCheck.clear();
            // switch to dashboard view
            ViewSwitcher.switchTo(View.DASHBOARD);
        } else {
            // Show error message
        }
    }

    /**
     Updates the login button based on the user input.
     */
    private void updateLoginButton() {
        String username = fxUsername.getText().trim();
        String password = fxPassword.getText().trim();
        String passwordCheck = fxPassword.getText().trim();

        if (!username.isEmpty() && !password.isEmpty() && !passwordCheck.isEmpty()) {
            fxLoginButton.setDisable(false);
        } else {
            fxLoginButton.setDisable(true);
        }
    }

}
package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * The LoginController class is a controller for the Login view. It handles the user login functionality
 * and switches to the dashboard view if the login is successful.
 */
public class LoginController {
    @FXML
    private TextField fxUsername;

    @FXML
    private PasswordField fxPassword;

    /**
     * Handles the user login action.
     * If the username and password are not empty, a User object is created and set in the DashboardController.
     * Then, the view is switched to the dashboard.
     * If the fields are empty, an error message is shown.
     */
    @FXML
    private Button fxLoginButton;

    /**
     * Initializes the Login view.
     * Disables the Login button by default and sets listeners on the username and password fields
     * to enable or disable the button based on the fields' values.
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
     * Switches to the Sign Up view.
     */
    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
    }

    /**
     * Enables or disables the Login button based on whether the username and password fields are empty.
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

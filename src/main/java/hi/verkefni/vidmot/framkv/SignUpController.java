package hi.verkefni.vidmot.framkv;

import hi.verkefni.vinnsla.framkv.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 The SignUpController class handles the sign up functionality for the application.
 It contains methods for validating and saving the user's credentials and switching to the dashboard view.
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
     Validates user credentials and switches to the dashboard view.
     If there is a validation error, an error message is displayed.
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
     Switches to the sign-up view.
     */
    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
    }

    /**
     Enables the login button if all text fields have valid input.
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

    /**
     Initializes the sign-up view by disabling the login button and adding listeners to the input text fields.
     The listeners enable or disable the login button based on the validity of the text field inputs.
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
}

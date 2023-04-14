package hi.verkefni.vidmot.framkv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class LoginController {
    @FXML
    protected void onLogin() {
        System.out.print("login");
        ViewSwitcher.switchTo(View.DASHBOARD);
    }

    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
        System.out.print("new user");
    }
}
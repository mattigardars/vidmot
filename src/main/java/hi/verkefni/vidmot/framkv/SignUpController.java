package hi.verkefni.vidmot.framkv;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SignUpController {
    @FXML
    protected void onSignUp() {
        ViewSwitcher.switchTo(View.SIGNUP);
        System.out.print("new user");
    }
}
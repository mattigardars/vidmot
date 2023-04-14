module hi.verkefni.vidmot.framkv {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.verkefni.vidmot.framkv to javafx.fxml;
    exports hi.verkefni.vidmot.framkv;
}
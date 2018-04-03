package controller;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;

public class CreateLawsuitController {
    public static JFXDialog fxd;

    @FXML
    void closeCreateLawsuit() {
        fxd.close();
        System.gc();
    }
}

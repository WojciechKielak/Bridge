package com.example.demo2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class HelloController {

    @FXML
    private TextField MinP;
    @FXML
    private TextField MaxP;
    @FXML
    private TextField MinPr;
    @FXML
    private TextField MaxPr;
    @FXML
    private Button przyc;
    @FXML
    private Button przyc1;
    @FXML
    private Text napis;
    @FXML
    private Text napis1;

    public static int MinPoj, MaxPoj, MinPrze, MaxPrze;


    Thread tL = new Lewy();
    Thread tP = new Prawy();
    Thread Los = new Losowanie();
    Thread poj = new Pojawaienie();

    public void stopprogramik(){
        Losowanie.setofflosowanie();
        Pojawaienie.setoffpojawienie();
        Lewy.setofflewy();
        Prawy.setoffprawy();
        System.exit(0);
    }

    @FXML
    public void start() {
        MinPoj = Integer.parseInt(MinP.getText());
        MaxPoj = Integer.parseInt(MaxP.getText());
        MinPrze = Integer.parseInt(MinPr.getText());
        MaxPrze = Integer.parseInt(MaxPr.getText());
        if ((MinPoj >= 0) && (MinPoj <= 100) && (MinPrze >= 0) && (MinPrze <= 100) && (MaxPoj >= 0) && (MaxPoj <= 100) && (MaxPrze >= 0) && (MaxPrze <= 100) && (MinPrze <= MaxPrze)&& (MinPoj <= MaxPoj)) {

            napis1.setText("");
            przyc1.setDisable(false);
        przyc.setDisable(true);
        MinP.setDisable(true);
        MaxP.setDisable(true);
        MinPr.setDisable(true);
        MaxPr.setDisable(true);

            HelloApplication.semM.release();
            Los.start();
            poj.start();
            tL.start();
            tP.start();

        }
        else {
           napis1.setText("Błędne dane!");
        }
    }
}
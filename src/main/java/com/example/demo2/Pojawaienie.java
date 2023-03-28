package com.example.demo2;

import javafx.application.Platform;
import javafx.scene.text.Text;

import java.util.Random;

import static com.example.demo2.HelloApplication.*;
import static com.example.demo2.HelloController.MaxPoj;
import static com.example.demo2.HelloController.MinPoj;


public class Pojawaienie extends Thread {

    static boolean stanSwiadomosci = true;

    public static void setoffpojawienie() {
        stanSwiadomosci = false;
    }

    Random random = new Random();
    static Text textNL = new Text();
    static Text textNP = new Text();

    public void run(){


        Text textL = new Text();
        textL.setX(10);
        textL.setY(230);
        textL.setText("Ilość oczekujących : ");
        Text textP = new Text();
        textP.setX(920);
        textP.setY(230);
        textP.setText("Ilość oczekujących : ");

        textNL.setX(50);
        textNL.setY(250);
        textNL.setText(ileL+ "");

        textNP.setX(970);
        textNP.setY(250);
        textNP.setText(ileP+ "");

        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(textL);
            HelloApplication.root.getChildren().add(textP);
            HelloApplication.root.getChildren().add(textNL);
            HelloApplication.root.getChildren().add(textNP);
        });

        while (stanSwiadomosci){

            try {
                Thread.sleep((random.nextInt(MaxPoj*1000 - MinPoj*1000 + 1) + MinPoj*1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int a = random.nextInt(2);
            if (a == 0)  {
                HelloApplication.ileP++;
                textNP.setText(HelloApplication.ileP+ "");
            }
            else {
                HelloApplication.ileL++;
                textNL.setText(HelloApplication.ileL+ "");
            }




            semafor.release();


        }

    }
}

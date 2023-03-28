package com.example.demo2;

import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.util.Duration;

import java.util.Random;

import static com.example.demo2.HelloController.MaxPrze;
import static com.example.demo2.HelloController.MinPrze;

public class Prawy extends Thread {

    static boolean stanSwiadomosci = true;

    public static void setoffprawy(){
        stanSwiadomosci = false;
    }
    Random random = new Random();
    public void run() {
        Circle circle = new Circle(1070,350,30);
        circle.setFill(Color.RED);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);

        Platform.runLater(() -> {
            HelloApplication.root.getChildren().add(circle);
        });

        Path path = new Path();
        MoveTo moveToP = new MoveTo();
        moveToP.setX(circle.getCenterX());
        moveToP.setY(circle.getCenterY());
        LineTo lineToP = new LineTo();
        lineToP.setX(-50);
        lineToP.setY(350);
        path.getElements().addAll(moveToP,lineToP);

        while (stanSwiadomosci) {

            try {
                HelloApplication.semP.acquire();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            PathTransition pathTransition= new PathTransition(Duration.millis(random.nextInt(MaxPrze*1000 - MinPrze*1000 + 1) + MinPrze*1000),path,circle);



            pathTransition.setOnFinished( e->{
                synchronized (this){
                    notify();
                }
            });

            Platform.runLater(() -> {
                pathTransition.play();
            });

            synchronized (this){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            HelloApplication.semM.release();
        }

    }
}
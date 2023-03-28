package com.example.demo2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class HelloApplication extends Application {
    static AnchorPane root;

    @Override
    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        root=fxmlLoader.load();
        Scene scene = new Scene( root );
        stage.setTitle("Most");
        stage.setScene(scene);
        stage.show();

    }
    public static Semaphore semM = new Semaphore(0);
    public static Semaphore semL = new Semaphore(0);
    public static Semaphore semP = new Semaphore(0);
    public static Semaphore semafor = new Semaphore(0);

    public  static int ileL=0, ileP=0;

    public static void main(String[] args) throws InterruptedException {
        launch();
    }

}
package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.controller.GameWindowController;
import sample.model.Game;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/MainMenu.fxml"));
        primaryStage.setTitle("Hello World");
        Scene mainMenu = new Scene(root, 600, 300);
        mainMenu.getStylesheets().add(getClass().getResource("style/MainMenu.css").toExternalForm());
        primaryStage.setScene(mainMenu);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

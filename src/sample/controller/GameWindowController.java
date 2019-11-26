package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import resources.ImageBase;
import resources.Map;
import sample.Main;
import sample.model.*;
import sample.model.client.Client;

import javax.swing.text.html.Option;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class GameWindowController implements Initializable {

    public Canvas game_view;
    public Canvas petrol_left;
    public Canvas cash_left;
    public Canvas actual_client;
    public AnchorPane help;
    public Scene gameScene;
    private static Game game;

    public void initScene(Scene scene) {
        gameScene = scene;
        gameScene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            Position oldPosition = game.getPlayer().getPosition();
            boolean updateFields = false;
            if (key.getCode() == KeyCode.UP) {
                System.out.println("Strzałka w góre!");
                updateFields = game.move(game.getPlayer(), Direction.UP);
            } else if (key.getCode() == KeyCode.DOWN) {
                System.out.println("Strzałka w dół!");
                updateFields = game.move(game.getPlayer(), Direction.DOWN);
            } else if (key.getCode() == KeyCode.LEFT) {
                System.out.println("Strzałka w lewo!");
                updateFields = game.move(game.getPlayer(), Direction.LEFT);
            } else if (key.getCode() == KeyCode.RIGHT) {
                System.out.println("Strzałka w prawo!");
                updateFields = game.move(game.getPlayer(), Direction.RIGHT);
            }

            if(game.updateClient()) {
                try {
                    updateClient(game.getClient());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }

            if(updateFields) {
                if(game.getPlayer().getPetrolLeft() == -1) {
                    Alert alert= new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("SKONCZYLO SIE PALIWO :(");
                    alert.setHeaderText(null);
                    alert.setContentText("CZY CHCESZ WYJSC Z GRY ?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if(result.get() == ButtonType.OK) {
                        System.exit(0);
                    }
                }

                try {
                    updateFields(oldPosition, game.getPlayer().getPosition());
                    updateStats();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void updateFields(Position actualPosition, Position newPosition) throws FileNotFoundException {
        GraphicsContext gc = game_view.getGraphicsContext2D();
        Image image = null;

        int x = actualPosition.getX();
        int y = actualPosition.getY();

        gc.setFill(Color.DARKGREY);
        gc.fillRect(x*20, y*20, 20, 20);

        if(x == 1 && y == 2) {
            image = ImageBase.getImage(Field.HOUSE_AGRESOR);
            gc.drawImage(image, x*20, y*20 ,20, 20);
        } else if(x == 24 && y == 16) {
            image = ImageBase.getImage(Field.HOUSE_ANONYMOUS);
            gc.drawImage(image, x*20, y*20 ,20, 20);
        } else if(x == 2 && y == 18) {
            image = ImageBase.getImage(Field.HOUSE_CASUAL);
            gc.drawImage(image, x*20, y*20 ,20, 20);
        } else if(x == 26 && y ==1) {
            image = ImageBase.getImage(Field.HOUSE_REACH);
            gc.drawImage(image, x*20, y*20 ,20, 20);
        } else if(x == 20 && y == 11) {
            image = ImageBase.getImage(Field.PETROL_STATION);
            gc.drawImage(image, x*20, y*20 ,20, 20);
        }

        image = ImageBase.getImage(Field.PLAYER);
        gc.drawImage(image, newPosition.getX()*20, newPosition.getY()*20, 20, 20);
    }

    public void updateStats() {
        GraphicsContext gc = petrol_left.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,80,40);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("", 48));
        gc.fillText(String.valueOf(game.getPlayer().getPetrolLeft()), 0, 40);

        gc = cash_left.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,80,40);
        gc.setFill(Color.BLACK);
        gc.setFont(new Font("", 48));
        gc.fillText(String.valueOf(game.getPlayer().getCashLeft()), 0, 40);
    }

    public void back_to_menu(ActionEvent actionEvent) throws IOException {
        Parent mainMenuParent = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuParent);
        mainMenuScene.getStylesheets().add(getClass().getResource("../style/MainMenu.css").toExternalForm());

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(mainMenuScene);
        window.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        game = new Game();
        prepareNewGame(70, 50);

        GraphicsContext gc = game_view.getGraphicsContext2D();
        gc.setFill(Color.DARKGREY);
        gc.fillRect(0,0,600,400);

        String[] map = Map.generateSuperMap();
        Image image;

        for (int i=0; i < Map.sizeY; i++) {
            for (int j=0; j < Map.sizeX; j++) {
                try {
                    image = ImageBase.getImage(map[i].charAt(j));
                    gc.drawImage(image,j*20, i*20, 20, 20);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void prepareNewGame(int petrol, int cash) {
        game.setClient();
        game.setPlayer(new Player(petrol, cash));
    }

    public void updateClient(Client client) throws FileNotFoundException {
        GraphicsContext gc = game_view.getGraphicsContext2D();
        Image image = ImageBase.getImage((char) (client.getType()+'0'));
        gc.drawImage(image, client.getPosition().getX()*20, client.getPosition().getY()*20, 20, 20);
        gc = actual_client.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0,0,120,65);
        gc.drawImage(image,0, 0, 120, 65);
    }
}

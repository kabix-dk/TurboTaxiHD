package resources;

import javafx.scene.image.Image;
import sample.model.Field;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ImageBase {

    static Image tree = null;
    static Image car = null;
    static Image petrol = null;
    static Image building = null;
    static Image agresor_client = null;
    static Image agresor_house = null;
    static Image anonymous_client = null;
    static Image anonymous_house = null;
    static Image casual_client = null;
    static Image casual_house = null;
    static Image reach_client = null;
    static Image reach_house = null;

    public static Image getImage(char fieldType) throws FileNotFoundException {
        building = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\building.png"));
        petrol = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\petrol.png"));
        tree = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\tree.png"));
        car = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\car.png"));
        agresor_client = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\agresor_client.png"));
        agresor_house = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\agresor_house.png"));
        anonymous_client = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\anonymous_client.png"));
        anonymous_house = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\anonymous_house.png"));
        casual_client = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\casual_client.png"));
        casual_house = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\casual_house.png"));
        reach_client = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\reach_client.png"));
        reach_house = new Image(new FileInputStream("C:\\Users\\Kabiszon\\IdeaProjects\\TurboTaxiHD\\src\\resources\\reach_house.png"));

        Image image = null;
        switch (fieldType) {
            case Field.TREE:
                return tree;
            case Field.PLAYER:
                return car;
            case Field.ROAD:
                return image;
            case Field.BUILDING:
                return building;
            case Field.HOUSE_AGRESOR:
                return agresor_house;
            case Field.HOUSE_ANONYMOUS:
                return anonymous_house;
            case Field.HOUSE_CASUAL:
                return casual_house;
            case Field.HOUSE_REACH:
                return reach_house;
            case Field.CLIENT_AGRESOR:
                return agresor_client;
            case Field.CLIENT_ANONYMOUS:
                return anonymous_client;
            case Field.CLIENT_CASUAL:
                return casual_client;
            case Field.CLIENT_REACH:
                return reach_client;
            case Field.PETROL_STATION:
                return petrol;
            default:
                break;
        }
        return image;
    }
}

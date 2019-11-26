package sample.model;

import resources.Map;
import sample.model.client.*;

import java.util.Random;

public class Game {
    private final static int MAX_PETROL_LEFT = 70;

    private Player player;
    private Client client;
    private String[] mapCopy = Map.generateSuperMap();

    public Game() {
    }

    public boolean canMove(Position newPosition, Player player) {
        if(newPosition.getX() >= Map.sizeX || newPosition.getX() < 0 ) {
            return false;
        }
        if(newPosition.getY() >= Map.sizeY || newPosition.getY() < 0) {
            return false;
        }
        if(mapCopy[newPosition.getY()].charAt(newPosition.getX()) == Field.BUILDING){
            return false;
        }
        if(mapCopy[newPosition.getY()].charAt(newPosition.getX()) == Field.TREE) {
            return false;
        }
        return true;
    }

    public boolean updateClient(){
        if (client.isTaken())  return false;
        return true;
    }

    public boolean move(Player player, int direction) {
        if(player == null){
            return false;
        }

        Position newPosition;

        switch (direction) {
            case Direction.UP:
                newPosition = new Position(player.getPosition().getX(), player.getPosition().getY()-1);
                break;
            case Direction.DOWN:
                newPosition = new Position(player.getPosition().getX(), player.getPosition().getY()+1);
                break;
            case Direction.LEFT:
                newPosition = new Position(player.getPosition().getX()-1, player.getPosition().getY());
                break;
            case Direction.RIGHT:
                newPosition = new Position(player.getPosition().getX()+1, player.getPosition().getY());
                break;
            default:
                return false;
        }

        if(!canMove(newPosition, player)){
            return false;
        }

        if(client.isTaken()){
            client.increaseCost();
            int x = newPosition.getX();
            int y = newPosition.getY();
            switch(client.getType()) {
                case 1:
                    if(x == 1 && y == 2) {
                        player.setCashLeft((int) (player.getCashLeft() + client.getTip() + (client.getCost()*1.7)));
                        setClient();
                    }
                    break;
                case 2:
                    if(x == 24 && y == 16) {
                        player.setCashLeft((int) (player.getCashLeft() + client.getTip() + (client.getCost()*1.7)));
                        setClient();
                    }
                    break;
                case 3:
                    if(x == 2 && y == 18) {
                        player.setCashLeft((int) (player.getCashLeft() + client.getTip() + (client.getCost()*1.7)));
                        setClient();
                    }
                    break;
                case 4:
                    if(x == 26 && y == 1) {
                        player.setCashLeft((int) (player.getCashLeft() + client.getTip() + (client.getCost()*1.7)));
                        setClient();
                    }
                    break;
            }
        }

        if(newPosition.equals(client.getPosition()) && !client.isTaken()){
            client.setTaken(true);
        }

        player.setPetrolLeft(player.getPetrolLeft() - 1);
        visitPetrolStation(player);
        player.setPosition(newPosition);
        return true;
    }

    private void visitPetrolStation(Player player) {
        if (mapCopy[player.getPosition().getY()].charAt(player.getPosition().getX()) == Field.PETROL_STATION){
            int dif = Math.min(player.getCashLeft(), MAX_PETROL_LEFT-player.getPetrolLeft());
            player.setPetrolLeft(player.getPetrolLeft() + dif);
            player.setCashLeft(player.getCashLeft() - dif);
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Client getClient() {
        return client;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int generateTypeOfClient () {
        int type;
        Random generator = new Random();
        type = generator.nextInt(4) + 1;
        return type;
    }

    public Position generateCorrectPosition () {
        Position position = new Position();
        Random generator = new Random();
        int x;
        int y;
        while(true){
            x = generator.nextInt(30);
            y = generator.nextInt(20);
            if(mapCopy[y].charAt(x) == Field.ROAD) {
                position.setX(x);
                position.setY(y);
                break;
            }
        }
        return position;
    }

    public void setClient() {
        Position position = new Position();
        position = generateCorrectPosition();
        int type = generateTypeOfClient();
        switch(type) {
            case 1:
                this.client = new AgresorClient(position, type);
                break;
            case 2:
                this.client = new AnonymousClient(position, type);
                break;
            case 3:
                this.client = new CasualClient(position, type);
                break;
            case 4:
                this.client = new ReachClient(position, type);
                break;
            default:
                break;
        }
    }
}

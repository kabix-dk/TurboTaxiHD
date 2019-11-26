package sample.model;

public class Player {
    private int petrolLeft;
    private int cashLeft;
    private Position position;

    public Player(int petrol, int cash) {
        this.petrolLeft = petrol;
        this.cashLeft = cash;
        this.position = new Position(1,9);
    }

    public int getPetrolLeft() {
        return petrolLeft;
    }

    public void setPetrolLeft(int petrolLeft) {
        this.petrolLeft = petrolLeft;
    }

    public int getCashLeft() {
        return cashLeft;
    }

    public void setCashLeft(int cashLeft) {
        this.cashLeft = cashLeft;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

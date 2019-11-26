package sample.model.client;

import sample.model.Position;

import java.util.Random;

public class AnonymousClient extends Client{
    private int tip;

    public AnonymousClient(Position position, int type) {
        super(position, type);
        this.tip = 7;
    }

    @Override
    public int getTip() {
        Random generator = new Random();
        tip = generator.nextInt(3) + tip;
        return tip;
    }

    @Override
    public void setTip(int tip) {
        this.tip = tip;
    }
}

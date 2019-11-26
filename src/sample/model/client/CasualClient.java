package sample.model.client;

import sample.model.Position;

import java.util.Random;

public class CasualClient extends Client{
    private int tip;

    public CasualClient(Position position, int type) {
        super(position, type);
        this.tip = 3;
    }

    @Override
    public int getTip() {
        Random generator = new Random();
        tip = generator.nextInt(4) + tip;
        return tip;
    }

    @Override
    public void setTip(int tip) {
        this.tip = tip;
    }
}

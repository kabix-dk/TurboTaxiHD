package sample.model.client;

import sample.model.Position;

import java.util.Random;

public class AgresorClient extends Client{
    private int tip;

    public AgresorClient(Position position, int type) {
        super(position, type);
        this.tip = 2;
    }

    @Override
    public int getTip() {
        Random generator = new Random();
        tip = generator.nextInt(5) - tip;
        return tip;
    }

    @Override
    public void setTip(int tip) {
        this.tip = tip;
    }
}

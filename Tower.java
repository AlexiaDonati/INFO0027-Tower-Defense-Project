import javax.swing.*;

abstract class Tower extends ArmedEntity{
    protected int cost;
    protected int unlock;
    protected int decay;

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return (Tower) super.clone();
    }

    public int get_cost(){ return cost; }

    public int get_unlock(){ return unlock; }

    abstract void power();
}

class Tower1 extends Tower {
    Tower1() {
        sprite = new ImageIcon("../resources/towers/tower1.png");

        cost = 15;
        unlock = 0;
        // TODO set variables to the right start values
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void power(){
        // TODO implement power
    }
}

class Tower2 extends Tower {
    Tower2() {
        sprite = new ImageIcon("../resources/towers/tower2.png");

        cost = 110;
        unlock = 1;
        // TODO set variables to the right start values
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void power(){
        // TODO implement power
    }
}

class Tower3 extends Tower {
    Tower3() {
        sprite = new ImageIcon("../resources/towers/tower3.png");
        // TODO set variables to the right start values
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void power(){
        // TODO implement power
    }
}

class Tower4 extends Tower {
    Tower4() {
        sprite = new ImageIcon("../resources/towers/tower4.png");
        // TODO set variables to the right start values
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public void power(){
        // TODO implement power
    }
}
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

abstract class Tower extends ArmedEntity implements Observer{
    protected int cost;
    protected int unlock;
    protected int decay;

    protected List<Cell> inRange = new ArrayList<>();

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return (Tower) super.clone();
    }

    public int get_cost(){ return cost; }

    public int get_unlock(){ return unlock; }

    @Override
    public void set_position(int x, int y){
        super.set_position(x, y);

        for(int i = Map.get_maxDistance() ; i >= 0 ; i--){
            Cell c = Map.get_Cell(i);
            if(can_reach(c.get_x(), c.get_y())){
                inRange.add(c);
                c.attach_Observer(this);
            }
        }
    }

    public void action(){
        for(Cell c : inRange){
            List<ArmedEntity> enemies = c.give_update();
            if(!enemies.isEmpty()){
                enemies.get(0).hit(damage);
                break;
            }
        }
    }

    public boolean check_for_decay(){
        decay--;
        if(decay <= 0){
            remove();
            return true;
        }
        else{
            return false;
        }
    }

    private void remove(){
        for(Cell c : inRange){
            c.detach_Observer(this);
        }

    }
    abstract void power();
}

class Tower1 extends Tower {
    Tower1() {
        sprite = new ImageIcon("../resources/towers/tower1.png");
        range = 4;
        damage = 5;
        rate = 1;

        cost = 15;
        unlock = 0;
        decay = 1;
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
        range = 3;
        damage = 10;
        rate = 2;

        cost = 35;
        unlock = 2;
        decay = 3;
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
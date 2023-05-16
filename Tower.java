import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

interface Observer {}

abstract class Tower extends ArmedEntity implements Observer{
    protected int cost;
    protected int unlock;
    protected int decay;
    protected Power ability;
    private boolean hitAnEnemy;

    protected List<Cell> inRange = new ArrayList<>();

    Tower(){
        reset_hitAnEnemy();
    }

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
                enemies.get(0).apply_ability((this.ability).toString());
                hitAnEnemy = true;
                break;
            }
        }
    }

    public void apply_ability(String ability){}

    protected void reset_hitAnEnemy(){
        hitAnEnemy = false;
    }

    public boolean check_for_decay(){
        if(hitAnEnemy == false){
            decay--;
        }
        else{
            reset_hitAnEnemy();
        }

        if(decay <= 0){
            remove();
            return true;
        }
        else{
            return false;
        }
    }

    private void remove(){
        Map.remove_tower(get_X(), get_Y());
        for(Cell c : inRange){
            c.detach_Observer(this);
        }

    }
}

enum Power{
    NORMAL,
    STUN,
    POISON,
    SLOW;
}

class Tower1 extends Tower {
    Tower1() {
        sprite = new ImageIcon("../resources/towers/tower1.png");
        range = 2;
        damage = 5;
        rate = 1;

        cost = 15;
        unlock = 0;
        ability = Power.NORMAL;

        decay = 1;
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Tower2 extends Tower {
    Tower2() {
        sprite = new ImageIcon("../resources/towers/tower2.png");
        range = 2;
        damage = 10;
        rate = 2;

        cost = 35;
        unlock = 1;
        ability = Power.SLOW;

        decay = 1;
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Tower3 extends Tower {
    Tower3() {
        sprite = new ImageIcon("../resources/towers/tower3.png");
        range = 3;
        damage = 5;
        rate = 3;

        cost = 50;
        unlock = 2;
        ability = Power.POISON;

        decay = 1;
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }

}

class Tower4 extends Tower {
    Tower4() {
        sprite = new ImageIcon("../resources/towers/tower4.png");
        range = 2;
        damage = 20;
        rate = 1;

        cost = 75;
        unlock = 3;
        ability = Power.STUN;

        decay = 1;
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
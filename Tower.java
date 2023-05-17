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
    private List<Cell> inRange;

    Tower(){
        inRange = new ArrayList<>();
        reset_hitAnEnemy();
    }

    @Override
    public Tower clone() throws CloneNotSupportedException {
        Tower clone = (Tower) super.clone();
        clone.inRange = new ArrayList<>(inRange);
        return clone;
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
                Enemy enemy = (Enemy) enemies.get(0);

                enemy.get_hit(damage);
                enemy.apply_effect(this.ability);
                hitAnEnemy = true;

                double hypotenuse = get_position().distance(enemy.get_position());
                double adjacent = enemy.get_X() - get_X();
                double theta = Math.toDegrees(Math.acos(adjacent/hypotenuse));
                if(enemy.get_Y() < get_Y()){
                    angle = (float) (360 - theta);
                }
                else{
                    angle = (float) (theta);
                }

                break;
            }
        }
    }

    protected void reset_hitAnEnemy(){
        hitAnEnemy = false;
    }

    public boolean check_for_decay(){
        if(hitAnEnemy == false){
            decay--;
            if(decay <= 0){
                remove();
                return true;
            }
        }
        reset_hitAnEnemy();
        return false;
    }

    private void remove(){
        Map.remove_tower(get_X(), get_Y());
        for(Cell c : inRange){
            c.detach_Observer(this);
        }

    }
}

class Tower1 extends Tower {
    Tower1() {
        super();
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
        super();
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
        super();
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
        super();
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
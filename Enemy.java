import javax.swing.*;
import java.util.*;

abstract class Enemy extends ArmedEntity{
    protected int reward;
    protected double speed;
    protected double distance;
    protected HashMap<Power, Integer> effectDuration = new HashMap<>();

    Enemy(){
        distance = 0;
        angle = 90;

        effectDuration.put(Power.SLOW, 0);
        effectDuration.put(Power.STUN, 0);
        effectDuration.put(Power.POISON, 0);
        set_position(1, 0);
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return (Enemy) super.clone();
    }

    public int get_health(){ return health; }

    public int get_reward(){ return reward; }

    public double get_speed(){ 
        int stunTimer = effectDuration.get(Power.STUN);
        if(stunTimer > 0){
            return 0;
        }

        int slowTimer = effectDuration.get(Power.SLOW);
        if(slowTimer > 0){
            return speed/2;
        }

        return speed;
    }

    public void advance(){
        int currentX = get_X(), currentY = get_Y();
        Cell currCell = Map.get_Cell((int) distance);

        double speedWithEffect = get_speed();

        int maxDistance = Map.get_maxDistance();
        if(distance >= maxDistance){ return; } // The enemy is already at his maximum distance.
        else if(distance + speedWithEffect >= maxDistance){ // The enemy is almost at his maximum distance.
            currCell.remove_Entity(this);
            distance = maxDistance;
        }
        else{ // The enemy is not at or almost at his maximum distance.
            currCell.remove_Entity(this);
            distance += speedWithEffect;
        }

        int newX = Map.get_Cell((int) distance).get_x();
        int newY = Map.get_Cell((int) distance).get_y();
        set_position(newX, newY);

        Map.get_Cell((int) distance).add_Entity(this);

        if(newX > currentX){ angle = 0; }
        else if(newX < currentX){ angle = 180; }
        else{
            if(newY > currentY){ angle = 90; }
            else if(newY < currentY){ angle = 270; }
        }
    }

    public void remove(){
        Cell currCell = Map.get_Cell((int) distance);
        currCell.remove_Entity(this);
    }

    public void apply_effect(Power effect){
        switch(effect){
            case NORMAL:
                break;
            case SLOW:
                this.effectDuration.put(Power.SLOW, 2);
                break;
            case STUN:
                this.effectDuration.put(Power.STUN, 1);
                break;
            case POISON:
                this.effectDuration.put(Power.POISON, 4);
                break;
        }
    }

    public void handle_effect(){
        for (HashMap.Entry<Power, Integer> effect : effectDuration.entrySet()) {
            effect_handler(effect.getKey());
        }
    }

    private void effect_handler(Power effect){
        Integer timer = effectDuration.get(effect);
        if(timer != 0){
            effectDuration.put(effect, timer-1);
            if(effect == Power.POISON){
                this.health -= 2;
            }
        }
    }

}

class Enemy1 extends Enemy {
    Enemy1() {
        super();
        sprite = new ImageIcon("../resources/attackers/attacker1.png");

        range = 5;
        damage = 5;
        rate = 1;
        reward = 5;
        health = 5;
        speed = 2;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy2 extends Enemy {
    Enemy2() {
        super();
        sprite = new ImageIcon("../resources/attackers/attacker2.png");

        range = 4;
        damage = 15;
        rate = 2;
        reward = 10;
        health = 15;
        speed = 1;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy3 extends Enemy {
    Enemy3() {
        super();
        sprite = new ImageIcon("../resources/attackers/attacker3.png");

        range = 4;
        damage = 25;
        rate = 3;
        reward = 15;
        health = 20;
        speed = 1;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy4 extends Enemy {
    Enemy4() {
        super();
        sprite = new ImageIcon("../resources/attackers/attacker4.png");

        range = 3;
        damage = 50;
        rate = 5;
        reward = 25;
        health = 50;
        speed = 0.5;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
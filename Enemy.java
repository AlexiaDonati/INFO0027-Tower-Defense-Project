import javax.swing.*;
import java.util.*;

abstract class Enemy extends ArmedEntity{
    protected int reward;
    protected double speed;
    protected double distance;
    protected HashMap<State, Integer> effectDuration = new HashMap<>();

    Enemy(){
        effectDuration.put(State.SLOWED, 0);
        effectDuration.put(State.STUNNED, 0);
        effectDuration.put(State.POISONED, 0);
        distance = 0;
        set_position(1, 0);
        angle = 90;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return (Enemy) super.clone();
    }

    public int get_health(){ return health; }

    public int get_reward(){ return reward; }

    public double get_speed(){ return speed; }

    public void advance(){
        Cell currCell = Map.get_Cell((int) distance);

        int maxDistance = Map.get_maxDistance();
        if(distance >= maxDistance){ return; } // The enemy is already at his maximum distance.
        else if(distance + speed >= maxDistance){ // The enemy is almost at his maximum distance.
            currCell.remove_Entity(this);
            distance = maxDistance;
        }
        else{ // The enemy is not at or almost at his maximum distance.
            currCell.remove_Entity(this);
            distance += speed;
        }

        int newX = Map.get_Cell((int) distance).get_x();
        int newY = Map.get_Cell((int) distance).get_y();
        set_position(newX, newY);

        Map.get_Cell((int) distance).add_Entity(this);

        if(newX > get_X()){ angle = 0; }
        else if(newX < get_X()){ angle = 180; }
        else{
            if(newY > get_Y()){ angle = 90; }
            else if(newY < get_Y()){ angle = 270; }
        }
    }

    public void remove(){
        Cell currCell = Map.get_Cell((int) distance);
        currCell.remove_Entity(this);
    }

    public void apply_ability(String ability){
        switch(ability){
            case "NORMAL":
                break;
            case "SLOW":
                this.effectDuration.put(State.SLOWED, 2);
                break;
            case "STUN":
                this.effectDuration.put(State.STUNNED, 1);
                break;
            case "POISON":
                this.effectDuration.put(State.POISONED, 4);
                break;
        }
    }

    //I had to do the two following function because I had an issue where I couldn't
    //loop through my hashmap
    public void handle_effect(){
        effect_handler(State.STUNNED);
        effect_handler(State.SLOWED);
        effect_handler(State.POISONED);
    }

    private void effect_handler(State penalty){
        Integer timer = effectDuration.get(penalty);
        if(timer != 0){
            effectDuration.put(penalty, timer-1);
            switch(penalty){
                case SLOWED:
                    this.speed /= 2;
                    break;
                case STUNNED:
                    this.speed = 0;
                    break;
                case POISONED:
                    this.health -= 2;
                    break;
            }
        }
    }

}

enum State{
    STUNNED,
    POISONED,
    SLOWED;
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
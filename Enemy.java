import javax.swing.*;

abstract class Enemy extends ArmedEntity{
    protected int reward;
    protected double speed;
    protected double distance;

    Enemy(){
        distance = 0;
        set_position(1, 0);
        angle = 90;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return (Enemy) super.clone();
    }

    public int get_health(){ return health; }

    public void advance(int[][] path){
        int[] currPosition = {(int) get_position().getX(), (int) get_position().getY()};

        int maxDistance = path.length-1;
        if(distance >= maxDistance){ return; } // The enemy is already at his maximum distance.
        else if(distance + speed >= maxDistance){ distance = maxDistance; } // The enemy is almost at his maximum distance.
        else{ distance += speed; } // The enemy is not at or almost at his maximum distance.

        int[] newPosition = path[(int) distance];
        if(distance != (int) distance){
            newPosition[0] += path[(int) distance + 1][0] - newPosition[0];
            newPosition[1] += path[(int) distance + 1][1] - newPosition[1];
        }
        set_position(newPosition[0], newPosition[1]);

        if(newPosition[0] > currPosition[0]){ angle = 0; }
        else if(newPosition[0] < currPosition[0]){ angle = 180; }
        else{
            if(newPosition[1] > currPosition[1]){ angle = 90; }
            else if(newPosition[1] < currPosition[1]){ angle = 270; }
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

        reward = 10;
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

        reward = 20;
        health = 15;
        speed = 1;
        // TODO set variables to the right start values
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
        damage = 20;
        rate = 3;

        reward = 30;
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

        reward = 50;
        health = 30;
        speed = 0.5;
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
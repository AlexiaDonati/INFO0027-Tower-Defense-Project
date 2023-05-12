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

    public void advance(){
        int[] currPosition = {(int) get_position().getX(), (int) get_position().getY()};
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

        if(newX > currPosition[0]){ angle = 0; }
        else if(newX < currPosition[0]){ angle = 180; }
        else{
            if(newY > currPosition[1]){ angle = 90; }
            else if(newY < currPosition[1]){ angle = 270; }
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
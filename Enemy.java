abstract class Enemy extends ArmedEntity{
    private int reward;
    private int health;
    private int speed;

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return (Enemy) super.clone();
    }
}

class Enemy1 extends Enemy {
    Enemy1() {
        // TODO set variables to the right start values
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy2 extends Enemy {
    Enemy2() {
        // TODO set variables to the right start values
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy3 extends Enemy {
    Enemy3() {
        // TODO set variables to the right start values
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Enemy4 extends Enemy {
    Enemy4() {
        // TODO set variables to the right start values
    }

    @Override
    public Enemy clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
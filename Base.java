import graphics.TowerDefenseView;
import graphics.exceptions.*;

import javax.swing.*;

public class Base {
    private static final int x = 10;
    private static final int y = 1;

    private int health;
    private static final int healingRate = 5;

    private static final ImageIcon sprite = new ImageIcon("../resources/base/base.png");

    private Base() {
        reset();
    }

    private static Base INSTANCE = null;

    public static Base get_Base() {
        if (INSTANCE == null) {
            INSTANCE = new Base();
        }
        return INSTANCE;
    }

    public int getX(){ return x; }

    public int getY(){ return y; }

    public void display(TowerDefenseView view){
        if(view == null){
            throw new IllegalArgumentException("Invalid argument");
        }

        try{
            view.updateBase(x, y, health, sprite);
        } catch(WrongBasePositionException | EmptySpriteException e){
            e.printStackTrace();
        }
    }

    public void get_hit(int damage){
        health -= damage;
    }

    public boolean is_dead(){ return health <= 0; }

    public void update(int currTime){
        if(currTime % healingRate == 0){
            health += 10;
            if(health >= 100){
                health = 100;
            }

        }
    }

    public void reset(){
        health = 100;
    }
}

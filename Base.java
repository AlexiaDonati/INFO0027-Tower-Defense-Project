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
        health = 100;
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

    public void update(TowerDefenseView view){
        if(view == null){
            throw new IllegalArgumentException("Invalid argument");
        }
        
        try{
            view.updateBase(x, y, health, sprite);
        } catch(WrongBasePositionException | EmptySpriteException e){
            e.printStackTrace();
        }
    }

    public boolean get_hit(int damage){
        health -= damage;
        if(health > 0){
            return true;
        }
        return false;
    }

    public void action(int currTime){
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

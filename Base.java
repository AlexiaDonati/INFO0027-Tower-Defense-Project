import graphics.TowerDefenseView;
import graphics.exceptions.*;

import javax.print.attribute.standard.SheetCollate;
import javax.swing.*;

public class Base {
    private static final int x = 10;
    private static final int y = 1;

    private int health;
    private int healingRate;

    private ImageIcon sprite;

    private Base() {
        sprite = new ImageIcon("../resources/base/base.png");

        health = 1000;
        healingRate = 10;
    }

    private static Base INSTANCE = null;

    public static Base get_Base() {
        if (INSTANCE == null) {
            INSTANCE = new Base();
        }
        return INSTANCE;
    }

    public void update(TowerDefenseView view){
        try{
            view.updateBase(x, y, health, sprite);
        } catch(WrongBasePositionException | EmptySpriteException e){
            e.printStackTrace();
        }
    }

    public void play_turn(int currTime){
        if(currTime % healingRate == 0){
            health += 10;
        }
    }
}

import graphics.TowerDefenseView;
import graphics.exceptions.*;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private TowerDefenseView view;

    static final int nbrType = 4;
    private Tower[] towerType = {new Tower1(), new Tower2(), new Tower3(), new Tower4()};

    private int nbrTower;
    private List<Tower> listTower;

    TowerManager(TowerDefenseView view) {
        this.view = view;

        nbrTower = 0;
        listTower = new ArrayList<Tower>();
    }

    public int get_cost(int towerIndex){ return towerType[towerIndex].get_cost(); }
    public ImageIcon get_sprite(int towerIndex){ return towerType[towerIndex].get_sprite(); }

    void unlock(int currLevel) throws UnknownTowerException {
        for(int i = 0 ; i < nbrType ; i++){
            if(currLevel == towerType[i].get_unlock()){
                view.unlockTower(i);
            }
        }
    }
    void add_Tower(int x, int y, int towerIndex){
        try {
            Tower newTower = towerType[towerIndex].clone();
            newTower.set_position(x, y);

            listTower.add(newTower);
            nbrTower++;
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        try {
            view.updateTowerField(x, y, get_sprite(towerIndex), 0);
        } catch (WrongTowerPositionException | EmptySpriteException e) {
            e.printStackTrace();
        }
    }

    public void update(){
        for(Tower t : listTower){
            Point2D pos = t.get_position();
            try {
                view.updateTowerField((int) pos.getX(), (int) pos.getY(), t.get_sprite(), 0);
            } catch (WrongTowerPositionException | EmptySpriteException e) {
                e.printStackTrace();
            }
        }
    }
}

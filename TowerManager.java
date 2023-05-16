import graphics.TowerDefenseView;
import graphics.exceptions.*;

import javax.swing.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private TowerDefenseView view;

    private Tower[] towerType = {new Tower1(), new Tower2(), new Tower3(), new Tower4()};
    private List<Tower> listTower;

    TowerManager(TowerDefenseView view) {
        this.view = view;

        listTower = new ArrayList<Tower>();
    }

    public int get_cost(int towerIndex){ return towerType[towerIndex].get_cost(); }

    public ImageIcon get_sprite(int towerIndex){ return towerType[towerIndex].get_sprite(); }

    void unlock(int currLevel) throws UnknownTowerException {
        for(int i = 0 ; i < towerType.length ; i++){
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
        } catch (CloneNotSupportedException e){
            e.printStackTrace();
        }

        try {
            view.updateTowerField(x, y, get_sprite(towerIndex), 0);
        } catch (WrongTowerPositionException | EmptySpriteException e) {
            e.printStackTrace();
        }
    }
    public void update(int currTime){
        for(Tower tower : listTower){
            if(tower.can_fire(currTime)){
                tower.action();
            }

            Point2D pos = tower.get_position();
            try {
                view.updateTowerField((int) pos.getX(), (int) pos.getY(), tower.get_sprite(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void check_for_decay(){
        List<Tower> listTemp = new ArrayList<Tower>(listTower);
        for(Tower tower : listTemp){
            if(tower.check_for_decay()){
                listTower.remove(tower);
            }
        }
    }

    public void reset(){
        listTower.clear();
    }
}

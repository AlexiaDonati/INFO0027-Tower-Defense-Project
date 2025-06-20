import graphics.TowerDefenseView;

import java.util.ArrayList;
import java.util.List;

public class TowerManager {
    private Tower[] towerType = {new Tower1(), new Tower2(), new Tower3(), new Tower4()};
    private List<Tower> listTower;

    public TowerManager() {
        listTower = new ArrayList<Tower>();
    }

    public int get_cost(int towerIndex){ 
        if(towerIndex < 0 || towerIndex >= towerType.length){
            throw new IllegalArgumentException("Invalid tower index");
        }
        return towerType[towerIndex].get_cost(); 
    }

    public List<Integer> can_be_unlocked(int currLevel){
        List<Integer> canBeUnlocked = new ArrayList<>(towerType.length);

        for(int i = 0 ; i < towerType.length ; i++){
            if(currLevel == towerType[i].get_unlock()){
                canBeUnlocked.add(i);
            }
        }

        return canBeUnlocked;
    }

    public void add_Tower(int x, int y, int towerIndex){
        if(towerIndex < 0 || towerIndex >= towerType.length){
            throw new IllegalArgumentException("Invalid tower index.");
        }

        try {
            Tower newTower = towerType[towerIndex].clone();
            newTower.set_position(x, y);
            listTower.add(newTower);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public void update(int currTime){
        for(Tower tower : listTower){
            if(tower.can_fire(currTime)){
                tower.action();
            }
        }
    }

    public void display(TowerDefenseView view){
        if(view == null){
            throw new IllegalArgumentException("Invalid argument");
        }
        
        for(Tower tower : listTower){
            try {
                view.updateTowerField(tower.get_X(), tower.get_Y(), tower.get_sprite(), tower.get_angle());
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

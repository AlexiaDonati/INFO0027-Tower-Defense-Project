// java -cp ".;./../graphics.jar" Game
// dans le dossier bin pour execute

import graphics.TowerDefenseEventsHandlerInterface;
import graphics.TowerDefenseView;
import graphics.exceptions.*;
import java.io.IOException;

public class Game implements TowerDefenseEventsHandlerInterface {
    private TowerDefenseView view;
    private TowerManager towerManager;

    int currLevel;
    int currTime;

    static final float startBudget = 50;
    private float budget;

    public Game(){

        try {
            view = new TowerDefenseView(this);
        } catch (IOException e){
            e.printStackTrace();
        }

        towerManager = new TowerManager(view);

        try{
            view.unlockTower(0);
        } catch (UnknownTowerException e) {
            e.printStackTrace();
        }
        
        startNewGame();
    }

    @Override
    public void startNewGame() {
        currLevel = 0;

        budget = startBudget;
        view.updateMoney(budget);
    }

    @Override
    public void moveTowerToField(int x, int y, int towerIndex) {
        if(budget - towerManager.get_cost(towerIndex-1) >= 0){
            towerManager.add_Tower(x, y, towerIndex-1);
            budget -= towerManager.get_cost(towerIndex-1);
            view.updateMoney(budget);
        }
    }

    @Override
    public void launchWave() {

        view.refreshWindow();
        towerManager.update();

        currLevel++;
        currTime = 0;
    }

    public static void main(String[] args) {
        Game game = new Game();
    }
}

// java -cp ".;./../graphics.jar" Game
// dans le dossier bin pour execute

import graphics.TowerDefenseEventsHandlerInterface;
import graphics.TowerDefenseView;
import graphics.exceptions.*;
import java.io.IOException;

public class Game implements TowerDefenseEventsHandlerInterface {
    private GameState state;
    private TowerDefenseView view;

    private Base base;
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

        base = base.get_Base();
        base.update(view);

        towerManager = new TowerManager(view);

        try{
            view.unlockTower(0);
        } catch (UnknownTowerException e) {
            e.printStackTrace();
        }
        
        startNewGame();
    }

    public void set_state(GameState state){
        this.state = state;
    }
    @Override
    public void startNewGame() {
        state = new PlacingState();
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
        state.launchWave(this);
    }

    public void initWave(){
        currLevel++;

        try {
            towerManager.unlock(currLevel);
        } catch (UnknownTowerException e) {
            throw new RuntimeException(e);
        }

        currTime = 0;
    }
    public void update(){
        view.refreshWindow();
        base.update(view);
        towerManager.update();
    }

    private void play(){
        state.play(this);
    }

    public static void main(String[] args) {
        Game game = new Game();
        while (true){
            game.play();
        }
    }
}

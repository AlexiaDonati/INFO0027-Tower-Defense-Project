// java -cp ".;./../graphics.jar" Game
// dans le dossier bin pour execute

import graphics.TowerDefenseEventsHandlerInterface;
import graphics.TowerDefenseView;
import graphics.exceptions.*;
import java.io.IOException;

public class Game implements TowerDefenseEventsHandlerInterface {
    static final int fps = 30;
    private GameState state;
    private TowerDefenseView view;

    private Base base;
    private TowerManager towerManager;

    int currLevel;
    int currFrame;

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
        state.moveTowerToField(x, y, towerIndex, this);
    }

    public void add_Tower(int x, int y, int towerIndex) {
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

    public void init_Wave(){
        currLevel++;

        try {
            towerManager.unlock(currLevel);
        } catch (UnknownTowerException e) {
            throw new RuntimeException(e);
        }

        currFrame = 0;
    }

    public void update(){
        if(currFrame % 30 == 0){
            int currTime = currFrame/30;

            view.refreshWindow();

            towerManager.update();

            base.action(currTime);
            base.update(view);
        }
    }

    public static void main(String[] args) {
        Game game = new Game();

        long time_before, time_after;
        int	 ms_wait;
        while (true){
            time_before = System.currentTimeMillis();

            game.currFrame++;
            game.state.play(game);

            time_after = System.currentTimeMillis();
            ms_wait = (1000/fps) - (int) (time_after - time_before);
            try {
                if (ms_wait > 0) {
                    Thread.sleep(ms_wait);
                }
            } catch(InterruptedException e) { throw new RuntimeException(e); }

        }
    }
}

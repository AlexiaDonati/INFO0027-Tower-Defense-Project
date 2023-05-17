// java -cp ".;./../graphics.jar" Game
// dans le dossier bin pour execute

import graphics.TowerDefenseEventsHandlerInterface;
import graphics.TowerDefenseView;
import graphics.exceptions.*;
import java.io.IOException;

public class Game implements TowerDefenseEventsHandlerInterface {
    private static final int fps = 30;
    private GameState state;
    private TowerDefenseView view;

    private static final Base base = Base.get_Base();
    private TowerManager towerManager;
    private EnemyManager enemyManager;

    private int currLevel;
    private int currFrame;

    static final float startBudget = 50;
    private float budget;

    public Game(){
        Map.init_Map();

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

        enemyManager = new EnemyManager();

        startNewGame();
    }

    public void set_state(GameState state){ this.state = state; }

    public void go_to_next_level(){ currLevel++; }

    public int get_level(){ return currLevel; }

    public void deduct_from_budget(float loss){ budget -= loss; }

    public float get_budget(){ return budget; }

    @Override
    public void startNewGame() {
        state = new PlacingState();

        currLevel = 1;

        budget = startBudget;
        view.updateMoney(budget);

        Map.reset();
        base.reset();
        towerManager.reset();
        enemyManager.reset();

        display();
    }

    @Override
    public void moveTowerToField(int x, int y, int towerIndex) {
        state.moveTowerToField(x, y, towerIndex, towerManager, this);
    }

    @Override
    public void launchWave() {
        state.launchWave(enemyManager, this);
    }

    public void display(){
        view.refreshWindow();
        enemyManager.display(view);
        towerManager.display(view);
        base.display(view);
        view.updateMoney(budget);
    }

    public void update(){
        if(currFrame % 30 == 0){
            int currTime = currFrame/30;

            enemyManager.update();
            towerManager.update(currTime);
            base.update(currTime);

            display();

            budget += enemyManager.remove();

            if(enemyManager.checkForWin()){
                state.stopWave(towerManager, this);
            }

            int damage = enemyManager.try_to_hit(base.getX(), base.getY(), currTime);
            base.get_hit(damage);
            if(base.is_dead()){
                gameOver();
            }
        }
    }

    public void gameOver(){
        view.promptNewGame();
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

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

        base.update(view);

        towerManager = new TowerManager(view);
        try{
            view.unlockTower(0);
        } catch (UnknownTowerException e) {
            e.printStackTrace();
        }

        enemyManager = new EnemyManager();

        startNewGame();
    }

    public void set_state(GameState state){
        this.state = state;
    }

    public void next_level(){ currLevel++; }

    public int get_level(){ return currLevel; }

    @Override
    public void startNewGame() {
        state = new PlacingState();

        currLevel = 1;

        view.refreshWindow();

        base.reset();
        base.update(view);

        budget = startBudget;
        view.updateMoney(budget);

        towerManager.reset();
        enemyManager.reset();
        Map.reset();
    }

    @Override
    public void moveTowerToField(int x, int y, int towerIndex) {
        state.moveTowerToField(x, y, towerIndex, this);
    }

    public void add_Tower(int x, int y, int towerIndex) {
        if(!Map.is_cell_empty(x, y)){
            System.out.print("You can't add a new tower there.\n");
            return;
        }

        if(budget - towerManager.get_cost(towerIndex-1) >= 0){
            towerManager.add_Tower(x, y, towerIndex-1);
            Map.add_tower(x, y);

            budget -= towerManager.get_cost(towerIndex-1);
            view.updateMoney(budget);
        }
        else{
            System.out.print("You don't have enough money to add that tower.\n");
        }
    }

    @Override
    public void launchWave() {
        state.launchWave(enemyManager, this);
    }

    public void update(){
        if(currFrame % 30 == 0){
            int currTime = currFrame/30;

            view.refreshWindow();

            enemyManager.update();
            towerManager.update(currTime);

            enemyManager.display(view);
            budget += enemyManager.remove();

            view.updateMoney(budget);
            if(enemyManager.checkForWin()){
                enemyManager.display(view);
                state.stopWave(towerManager, this);
            }

            int damage = enemyManager.try_to_hit(base.getX(), base.getY(), currTime);
            if(!base.get_hit(damage)){
                gameOver();
            }
            base.action(currTime);
            base.update(view);
        }
    }

    public void gameOver(){
        base.update(view); // Show the empty health of the base to the player
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

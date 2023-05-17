import graphics.exceptions.UnknownTowerException;

public interface GameState {
    void launchWave(EnemyManager enemyManager, Game game);
    void stopWave(TowerManager towerManager, Game game);
    void play(Game game);
    void moveTowerToField(int x, int y, int towerIndex, Game game);
}

class PlayingState implements GameState {

    public void launchWave(EnemyManager enemyManager, Game game) {
        System.out.print("You can't launch a new wave while one is still ongoing.\n");
    }

    public void stopWave(TowerManager towerManager, Game game){
        game.display();
        game.set_state(new PlacingState());

        towerManager.check_for_decay();
        try {
            towerManager.unlock(game.get_level());
            game.next_level();
        } catch (UnknownTowerException e) {
            e.printStackTrace();
        }
    }

    public void play(Game game){
        game.update();
    }

    public void moveTowerToField(int x, int y, int towerIndex, Game game){
        System.out.print("You can't add a new tower to the field while a wave is still ongoing.\n");
    }
}

class PlacingState implements GameState {

    public void launchWave(EnemyManager enemyManager, Game game){
        game.set_state(new PlayingState());
        enemyManager.launch_wave(game.get_level());
    }

    public void stopWave(TowerManager towerManager, Game game){ }

    public void play(Game game){ }

    public void moveTowerToField(int x, int y, int towerIndex, Game game){
        game.add_Tower(x, y, towerIndex);
    }
}


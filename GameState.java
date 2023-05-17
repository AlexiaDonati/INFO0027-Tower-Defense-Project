import java.util.List;

public interface GameState {
    void launchWave(EnemyManager enemyManager, Game game);
    void stopWave(TowerManager towerManager, Game game);
    void play(Game game);
    void moveTowerToField(int x, int y, int towerIndex, TowerManager towerManager, Game game);
}

class PlayingState implements GameState {

    public void launchWave(EnemyManager enemyManager, Game game) {
        System.out.print("You can't launch a new wave while one is still ongoing.\n");
    }

    public void stopWave(TowerManager towerManager, Game game){
        game.display();
        game.set_state(new PlacingState());

        towerManager.check_for_decay();

        List<Integer> canBeUnlocked = towerManager.can_be_unlocked(game.get_level());
        for(Integer towerIndex : canBeUnlocked){
            game.unlock_Tower(towerIndex);
        }
        game.go_to_next_level();
    }

    public void play(Game game){
        game.update();
    }

    public void moveTowerToField(int x, int y, int towerIndex, TowerManager towerManager, Game game){
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

    public void moveTowerToField(int x, int y, int towerIndex, TowerManager towerManager, Game game){
        Map map = Map.get_Map();
        if(!map.is_cell_empty(x, y)){
            System.out.print("You can't add a new tower there.\n");
            return;
        }

        if(game.get_budget() - towerManager.get_cost(towerIndex-1) >= 0){
            towerManager.add_Tower(x, y, towerIndex-1);
            map.add_tower(x, y);

            game.deduct_from_budget(towerManager.get_cost(towerIndex-1));
        }
        else{
            System.out.print("You don't have enough money to add that tower.\n");
        }

        game.display();
    }
}


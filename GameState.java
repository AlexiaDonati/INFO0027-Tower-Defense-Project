public interface GameState {
    void launchWave(Game game);
    void play(Game game);
    void moveTowerToField(int x, int y, int towerIndex, Game game);
}

class PlayingState implements GameState {

    public void launchWave(Game game) {
        System.out.print("You can't launch a new wave while one is still ongoing.\n");
    }
    public void play(Game game){
        game.update();
    }

    public void moveTowerToField(int x, int y, int towerIndex, Game game){
        System.out.print("You can't add a new tower to the field while a wave is still ongoing.\n");
    }
}

class PlacingState implements GameState {
    public void launchWave(Game game){
        game.set_state(new PlayingState());
        game.init_Wave();
    }

    public void play(Game game){ }

    public void moveTowerToField(int x, int y, int towerIndex, Game game){
        game.add_Tower(x, y, towerIndex);
    }
}


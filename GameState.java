abstract public class GameState {
    abstract void launchWave(Game game);

    abstract void play(Game game);
}

class PlayingState extends GameState {
    public void launchWave(Game game) {
        System.out.print("You can't launch a new wave while one is still ongoing\n");
    }
    void play(Game game){
        game.update();
    }
}

class PlacingState extends GameState {

    public void launchWave(Game game){
        game.initWave();
        game.set_state(new PlayingState());
    }

    void play(Game game){
        return;
    }
}


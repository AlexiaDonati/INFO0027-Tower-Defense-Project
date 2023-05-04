abstract public interface GameState {
    abstract void launchWave(Game game);

    abstract void play(Game game);
}

class PlayingState implements GameState {

    public void launchWave(Game game) {
        System.out.print("You can't launch a new wave while one is still ongoing\n");
    }

    public void play(Game game){
        game.update();
    }
}

class PlacingState implements GameState {

    public void launchWave(Game game){
        game.initWave();
        game.set_state(new PlayingState());
    }

    public void play(Game game){
        return;
    }
}


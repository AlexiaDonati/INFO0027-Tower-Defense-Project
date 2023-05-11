import java.util.*;

public class Cell {
    private Set<Tower> towers;
    private List<Enemy> enemies;

    public Cell(){
        towers = new HashSet<>();
        enemies = new ArrayList<>();
    }

    public void attach_Tower(Tower tower){
        towers.add(tower);
    }

    public void detach_Tower(Tower tower){
        towers.remove(tower);
    }

    public void add_Enemy(Enemy enemy){
        enemies.add(enemy);

    }

    public void remove_Enemy(Enemy enemy){
        enemies.remove(enemy);
    }
    /*
    public Enemy give_Update(){
        Enemy enemy;
        //do smthg
        return enemy;
    }*/
}

import graphics.TowerDefenseView;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class EnemyManager {
    private final TowerDefenseView view;

    private static final Enemy[] enemyType = {new Enemy1(), new Enemy2(), new Enemy3(), new Enemy4()};
    private List<Enemy> listEnemy;

    private int enemyToAdd;

    EnemyManager(TowerDefenseView view) {
        if(view == null){
            throw new IllegalArgumentException("Invalid argument");
        }
        this.view = view;

        listEnemy = new ArrayList<Enemy>();
    }

    void launch_wave(int level){
        enemyToAdd = level * 5;
    }

    void add_Enemy(){
        int randomType = ThreadLocalRandom.current().nextInt(0, enemyType.length);

        try {
            Enemy enemy = enemyType[randomType].clone();
            listEnemy.add(enemy);

            view.updateAttackerField(enemy.get_position(), enemy.get_health(), enemy.get_sprite(), enemy.get_angle());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int try_to_hit(int x, int y, int currTime){
        int damageSum = 0;
        for (Enemy enemy : listEnemy){
            damageSum += enemy.try_to_hit(x, y, currTime);
        }
        return damageSum;
    }

    public boolean checkForWin(){
        return (listEnemy.size() == 0) && (enemyToAdd == 0);
    }

    public float update(){
        if(enemyToAdd > 0){
            add_Enemy();
            enemyToAdd--;
        }

        float money = 0;

        List<Enemy> listTemp = new ArrayList<Enemy>(listEnemy);
        for(Enemy enemy : listTemp){
            if(enemy.get_health() <= 0){
                money += enemy.get_reward();
                enemy.remove();
                listEnemy.remove(enemy);
            }
            else{
                enemy.advance();
                enemy.handle_effect();
                try {
                    view.updateAttackerField(enemy.get_position(), enemy.get_health(), enemy.get_sprite(), enemy.get_angle());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return money;
    }

    public void reset(){
        listEnemy.clear();
    }
}
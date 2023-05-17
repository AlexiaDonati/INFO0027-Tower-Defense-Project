import graphics.TowerDefenseView;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class EnemyManager {
    private static final Enemy[] enemyType = {new Enemy1(), new Enemy2(), new Enemy3(), new Enemy4()};
    private List<Enemy> listEnemy;

    private int enemyToAdd;

    EnemyManager() {
        listEnemy = new ArrayList<Enemy>();
    }

    public void launch_wave(int level){
        enemyToAdd = level * 5;
    }

    public void add_Enemy(){
        int randomType = ThreadLocalRandom.current().nextInt(0, enemyType.length);

        try {
            Enemy enemy = enemyType[randomType].clone();
            listEnemy.add(enemy); 
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

    public boolean check_for_win(){
        return (listEnemy.size() == 0) && (enemyToAdd == 0);
    }

    public float remove(){
        float money = 0;
        List<Enemy> listTemp = new ArrayList<Enemy>(listEnemy);
        for(Enemy enemy : listTemp){
            if(enemy.get_health() <= 0){
                money += enemy.get_reward();
                enemy.remove();
                listEnemy.remove(enemy);
            }
        }
        return money;
    }

    public void update(){
        for(Enemy enemy : listEnemy){
            enemy.advance();
            enemy.handle_effect();  
        }

        if(enemyToAdd > 0){
            add_Enemy();
            enemyToAdd--;
        }
    }

    public void display(TowerDefenseView view){
        if(view == null){
            throw new IllegalArgumentException("Invalid argument");
        }
        
        for(Enemy enemy : listEnemy){
            try {
                view.updateAttackerField(enemy.get_position(), enemy.get_health(), enemy.get_sprite(), enemy.get_angle());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void reset(){
        listEnemy.clear();
    }
}
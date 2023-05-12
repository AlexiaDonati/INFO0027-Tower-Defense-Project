import graphics.TowerDefenseView;
import graphics.exceptions.*;

import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class EnemyManager {
    private final TowerDefenseView view;

    private static final Enemy[] enemyType = {new Enemy1(), new Enemy2(), new Enemy3(), new Enemy4()};
    private List<Enemy> listEnemy;

    private int enemyToAdd;



    EnemyManager(TowerDefenseView view) {
        this.view = view;

        listEnemy = new ArrayList<Enemy>();
    }

    void launch_wave(int level){
        enemyToAdd = level * 5;
    }

    void add_Enemy(){
        int randomType = ThreadLocalRandom.current().nextInt(0, enemyType.length);

        Enemy enemy;
        try {
            enemy = enemyType[randomType].clone();
            listEnemy.add(enemy);
        } catch (CloneNotSupportedException e) { throw new RuntimeException(e); }

        try {
            view.updateAttackerField(enemy.get_position(), enemy.get_health(), enemy.get_sprite(), enemy.get_angle());
        } catch (WrongAttackerPositionException | EmptySpriteException e) {
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

    public void update(){
        listEnemy.removeIf(enemy -> enemy.get_health() <= 0);

        if(enemyToAdd > 0){
            add_Enemy();
            enemyToAdd--;
        }

        for(Enemy a : listEnemy) {
            a.advance();
            try {
                view.updateAttackerField(a.get_position(), a.get_health(), a.get_sprite(), a.get_angle());
            } catch (WrongAttackerPositionException | EmptySpriteException e) {
                e.printStackTrace();
            }
        }
    }

    public void reset(){
        listEnemy.clear();
    }
}
import graphics.TowerDefenseView;
import graphics.exceptions.*;

import java.awt.geom.Point2D;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class EnemyManager {
    private final TowerDefenseView view;

    static final int nbrType = 4;
    private static final Enemy[] enemyType = {new Enemy1(), new Enemy2(), new Enemy3(), new Enemy4()};

    private int nbrEnemy = 0;
    private List<Enemy> listEnemy;

    private int enemyToAdd;

    private static final int[][] path = {{1, 0},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
            {2, 7}, {3, 7}, {4, 7},
            {4, 6}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {4, 1},
            {5, 1}, {6, 1}, {7, 1},
            {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
            {8, 7}, {9, 7}, {10, 7},
            {10, 6}, {10, 5}, {10, 4}, {10, 3}, {10, 2}};

    EnemyManager(TowerDefenseView view) {
        this.view = view;

        nbrEnemy = 0;
        listEnemy = new ArrayList<Enemy>();
    }

    void launch_wave(int level){
        enemyToAdd = level * 5;
    }

    void add_Enemy(){
        int randomType = ThreadLocalRandom.current().nextInt(0, nbrType);

        Enemy enemy;
        try {
            enemy = enemyType[randomType].clone();
            listEnemy.add(enemy);
            nbrEnemy++;
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
    public boolean checkForWin(){ return (nbrEnemy == 0) && (enemyToAdd == 0); }

    public void update(){
        if(enemyToAdd > 0){
            add_Enemy();
            enemyToAdd--;
        }

        for(Enemy a : listEnemy){
            a.advance(path);
            try {
                view.updateAttackerField(a.get_position(), a.get_health(), a.get_sprite(), a.get_angle());
            } catch (WrongAttackerPositionException | EmptySpriteException e) {
                e.printStackTrace();
            }
        }
    }
}
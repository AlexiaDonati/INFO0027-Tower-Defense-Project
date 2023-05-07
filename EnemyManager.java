import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class EnemyManager {
    private Game game;
    static final int nbrType = 4;
    private Enemy[] enemyType;

    private int nbrEnemy;
    private List<Enemy> listEnemy;

    private static final int[][] path = {{1, 0},
            {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
            {2, 7}, {3, 7}, {4, 7},
            {4, 6}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {4, 1},
            {5, 1}, {6, 1}, {7, 1},
            {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
            {8, 7}, {9, 7}, {10, 7},
            {10, 6}, {10, 5}, {10, 4}, {10, 3}, {10, 2}, {10, 1}};

    EnemyManager(Game game) {
        enemyType = new Enemy[nbrType];

        enemyType[0] = new Enemy1();
        enemyType[1] = new Enemy2();
        enemyType[2] = new Enemy3();
        enemyType[3] = new Enemy4();

        nbrEnemy = 0;
        listEnemy = new ArrayList<Enemy>();
    }

    void add_Enemy(){
        int randomType = ThreadLocalRandom.current().nextInt(0, nbrType);
        try {
            listEnemy.add(enemyType[randomType].clone());
            nbrEnemy++;
        } catch (CloneNotSupportedException e){
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
}
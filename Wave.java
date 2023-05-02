import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

class Wave {
    private int nbrType = 4;
    private Enemy[] enemyType;

    private int nbrEnemy;
    private List<Enemy> listEnemy;

    Wave() {
        nbrType = 4;
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
}
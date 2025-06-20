import java.util.List;
import java.util.ArrayList;

public class Map {
    private static final int width = 12;
    private static final int height = 9;

    private int[][] map = {{0,1,0,0,0,0,0,0,0,0,0,0},
                            {0,1,0,0,1,1,1,1,0,0,2,0},
                            {0,1,0,0,1,0,0,1,0,0,1,0},
                            {0,1,0,0,1,0,0,1,0,0,1,0},
                            {0,1,0,0,1,0,0,1,0,0,1,0},
                            {0,1,0,0,1,0,0,1,0,0,1,0},
                            {0,1,0,0,1,0,0,1,0,0,1,0},
                            {0,1,1,1,1,0,0,1,1,1,1,0},
                            {0,0,0,0,0,0,0,0,0,0,0,0}};

    private static final int[][] pathPosition = {{1, 0},
                                                 {1, 1}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {1, 6}, {1, 7},
                                                 {2, 7}, {3, 7}, {4, 7},
                                                 {4, 6}, {4, 5}, {4, 4}, {4, 3}, {4, 2}, {4, 1},
                                                 {5, 1}, {6, 1}, {7, 1},
                                                 {7, 2}, {7, 3}, {7, 4}, {7, 5}, {7, 6}, {7, 7},
                                                 {8, 7}, {9, 7}, {10, 7},
                                                 {10, 6}, {10, 5}, {10, 4}, {10, 3}, {10, 2}};

    private static final List<Cell> path = new ArrayList<>(pathPosition.length);

    private Map(){
        for(int[] pos : pathPosition) {
            path.add(new Cell(pos[0], pos[1]));
        }
    }

    private static Map INSTANCE = null;

    private static void init_Map(){
        if (INSTANCE == null) {
            INSTANCE = new Map();
        }
    }
    
    public static Map get_Map() {
        init_Map();
        return INSTANCE;
    }

    public boolean is_cell_empty(int x, int y){ return (map[y][x] == 0); }

    public void add_tower(int x, int y){ map[y][x] = 2; }

    public void remove_tower(int x, int y){ map[y][x] = 0; }

    public static int get_width(){ return width; }

    public static int get_height(){ return height; }

    public static Cell get_Cell(int distance){ 
        init_Map();
        return path.get(distance); 
    }

    public static int get_maxDistance(){ 
        init_Map();
        return path.size() - 1; 
    }

    public void reset(){
        for(Cell c : path){
            c.reset();
        }
    }
}

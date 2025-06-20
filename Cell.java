import java.util.List;
import java.util.ArrayList;

abstract class CellSubject{
    private List<Observer> observers = new ArrayList<>();

    public void attach_Observer(Observer o){
        observers.add(o);
    }

    public void detach_Observer(Observer o){
        observers.remove(o);
    }

    protected void reset(){ observers.clear(); }
}

public class Cell extends CellSubject{
    private List<ArmedEntity> entities;
    private int x, y;

    public Cell(int x, int y){
        entities = new ArrayList<>();
        this.x = x;
        this.y = y;
    }

    public int get_x(){ return x; }

    public int get_y(){ return y; }

    public void add_Entity(ArmedEntity e){
        entities.add(e);
    }

    public void remove_Entity(ArmedEntity e){
        entities.remove(e);
    }

    public List<ArmedEntity> give_update(){ return entities; }

    public void reset(){
        super.reset();
        entities.clear();
    }
}

import java.util.List;
import java.util.ArrayList;

abstract class CellSubject{
    protected List<Observer> observers = new ArrayList<>();

    public void attach_Observer(Observer o){
        observers.add(o);
    }

    public void detach_Observer(Observer o){
        observers.remove(o);
    }

    public void notify_observers(){
        for(Observer o : observers){
            o.update();
        }
    }
}

public class Cell{
    private List<ArmedEntity> entities;

    public Cell(){
        entities = new ArrayList<>();
    }

    public void add_Entity(ArmedEntity e){
        entities.add(e);
    }

    public void remove_Entity(ArmedEntity e){
        entities.remove(e);
    }

    public List<ArmedEntity> give_update(){
        return entities;
    }
}

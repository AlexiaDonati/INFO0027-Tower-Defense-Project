import javax.swing.*;
import java.awt.geom.Point2D;

public class ArmedEntity implements Cloneable{
    protected ImageIcon sprite;

    private Point2D position;
    protected int angle;

    protected int range;
    protected int damage;
    protected int rate;

    ArmedEntity(){
        position = new Point2D.Double(0, 0);
    }

    @Override
    public ArmedEntity clone() throws CloneNotSupportedException {
        ArmedEntity clone = (ArmedEntity) super.clone();
        clone.position = (Point2D) position.clone();
        return clone;
    }

    public ImageIcon get_sprite(){ return sprite; }

    public void set_position(int x, int y){
        position.setLocation(x, y);
    }

    public Point2D get_position(){
        return position;
    }

    public int get_angle(){
        return angle;
    }

    private boolean can_reach(int x, int y){
        return range >= position.distance(x, y);
    }

    private boolean can_fire(int currTime){ return currTime % rate == 0; }

    public int try_to_hit(int x, int y, int currTime){
        if(can_reach(x, y) && can_fire(currTime)){
            return damage;
        }
        return 0;
    }
}

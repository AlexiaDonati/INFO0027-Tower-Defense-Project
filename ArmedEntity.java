import javax.swing.*;
import java.awt.geom.Point2D;

public abstract class ArmedEntity implements Cloneable{
    enum Power{
        NORMAL,
        STUN,
        POISON,
        SLOW;
    }
    
    protected int health;
    protected int range;
    protected int damage;
    protected int rate;

    protected ImageIcon sprite;

    protected float angle;
    private Point2D position;

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
        if(x < 0 || x >= Map.get_width() || y < 0 || y >= Map.get_height()){
            throw new IllegalArgumentException("Position not inside the map boundary");
        }

        position.setLocation(x, y);
    }

    public Point2D get_position(){
        return position;
    }

    public int get_X(){
        return (int) position.getX();
    }

    public int get_Y(){
        return (int) position.getY();
    }

    public float get_angle(){
        return angle;
    }

    public int get_damage(){
        return damage;
    }

    public boolean can_reach(int x, int y){
        return range >= position.distance(x, y);
    }

    public boolean can_fire(int currTime){ 
        return currTime % rate == 0; 
    }

    public int try_to_hit(int x, int y, int currTime){
        if(can_reach(x, y) && can_fire(currTime)){
            return damage;
        }
        return 0;
    }

    public void get_hit(int damage){
        health -= damage;
    }

}

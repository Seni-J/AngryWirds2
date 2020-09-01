package Models;

import com.badlogic.gdx.math.Vector2;

public abstract class MovingObject extends PhysicalObject {

    public final static float G = 10f;

    protected Vector2 speed;

    protected boolean freeze;

    public MovingObject(float positionX, float positionY,  float width, float height, String picname, Vector2 speed){
        super(positionX,positionY,width,height,picname);
        this.speed = speed;
    }

    public void setFreeze(){
        this.freeze = true;
    }

    public void unFreeze(){
        this.freeze = false;
    }

    public boolean isFrozen(){ return freeze; }

    public abstract void accelerate(float dt);

    public final void move(float dt){
        if(!freeze) {
            translate(speed.x * dt, speed.y * dt);
        }
    }
}

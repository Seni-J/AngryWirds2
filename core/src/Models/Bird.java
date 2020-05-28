package Models;

import com.badlogic.gdx.math.Vector2;

public class Bird extends MovingObject {
    private static final String PICNAME = "bird.png";
    private static final float WIDTH = 60;
    private static final float HEIGHT = 60;

    public Bird(float positionX,float positionY, Vector2 speed){
        super(positionX,positionY,WIDTH,HEIGHT,PICNAME,speed);
    }

    @Override
    public void accelerate(float dt){
        speed.y -= MovingObject.G * dt;
    }

    public void setSpeed(float x, float y){
        this.speed = new Vector2(x,y);
    }
}

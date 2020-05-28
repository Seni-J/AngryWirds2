package Models;

import com.badlogic.gdx.math.Vector2;
import java.util.Random;

public class Wasp extends MovingObject {
    private static final String PICNAME = "wasp.png";
    private static final float WIDTH = 60;
    private static final float HEIGHT = 60;

    public Wasp(float positionX,float positionY, Vector2 speed){
        super(positionX,positionY,WIDTH,HEIGHT,PICNAME,speed);
    }

    @Override
    public void accelerate(float dt){
        int x = 1600/2;
        int y = 900/2;
        Random randx = new Random();
        Random randy = new Random();
        int ax;
        int ay;
        int i = 10;

        if (this.getX() > x){
            if (this.getY() > y) {
                ax = -randx.nextInt(i);
                ay = -randy.nextInt(i);
            }else {
                ax = -randx.nextInt(i);
                ay = randy.nextInt(i);
            }
        }else if (this.getY() > y) {
            ax = randx.nextInt(i);
            ay = -randy.nextInt(i);
        }
        else {
            ax = randx.nextInt(i);
            ay = randy.nextInt(i);
        }

        speed.x += ax;
        speed.y += ay;


    }
}

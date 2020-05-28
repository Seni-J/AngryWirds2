package Models;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class Scenary {
    ArrayList<PhysicalObject> scene = new ArrayList<PhysicalObject>();

    public Scenary() {
        scene = new ArrayList<PhysicalObject>();
    }

    public void addElement(PhysicalObject el) throws Exception {
        Rectangle elRectangle = new Rectangle(el.getX(), el.getY(), el.getWidth(), el.getHeight());
        for (PhysicalObject obj : scene) {
            if (elRectangle.overlaps(new Rectangle(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight()))) {
                throw new Exception("Already an object on it");
            }
        }
        scene.add(el);
    }

    public void draw(Batch batch) {
        for (PhysicalObject obj : scene) {
            obj.draw(batch);
        }
    }
    public PhysicalObject collidesWith(PhysicalObject o){
        for(PhysicalObject el : scene){
            if(el.collidesWith(o)){
                return el;
            }
        }
        return null;
    }
}

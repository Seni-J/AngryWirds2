package Models;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class PhysicalObject extends Sprite {
    public PhysicalObject(float pX,float pY,float width, float height,String picname){
        super(new Texture(picname));
        setBounds(pX,pY,width,height);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        return new Rectangle(this.getX(),this.getY(),this.getWidth(),this.getHeight());
    }
    public boolean collidesWith(PhysicalObject o){
        return this.getBoundingRectangle().overlaps(o.getBoundingRectangle());
    }
}

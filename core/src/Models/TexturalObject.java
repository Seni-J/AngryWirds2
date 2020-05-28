package Models;

public class TexturalObject extends PhysicalObject {
    protected String word;

    public TexturalObject(float pX, float pY, float width, float height, String picname) {
        super(pX, pY, width, height, picname);
    }

    protected String getWord() {
        return word;
    }
}

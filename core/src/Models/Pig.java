package Models;

public class Pig extends TexturalObject {
    private static final String PICNAME = "pig.png";
    private static final float WIDTH = 60;
    private static final float HEIGHT = 60;

    public Pig(float pX, float pY) {
        super(pX, pY, WIDTH, HEIGHT, PICNAME);
    }

    private String sayWord(){
        return word;
    }
}

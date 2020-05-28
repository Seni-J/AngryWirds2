package Models;

public class Tnt extends PhysicalObject {
    private static final String PICNAME = "tnt.png";
    private static final float WIDTH = 80;
    private static final float HEIGHT = 80;

    private int negPts;

    public Tnt(float pX, float pY, int negPts) {
        super(pX, pY, WIDTH, HEIGHT, PICNAME);
        this.negPts = negPts;
    }
}

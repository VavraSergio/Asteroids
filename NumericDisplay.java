/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class NumericDisplay implements Drawable {
    private String prefix;
    private int value;
    private Point location;

    /**
     * Constructor for class.
     * 
     * @param xPos a param x
     * @param yPos a param
     * @param prefix a param
     * @param value a param
     */
    public NumericDisplay(int xPos, int yPos, String prefix, int value) {
        this.value = value;
        this.prefix = prefix;
        this.location = new Point(xPos, yPos);
    }

    public Point getLocation() {
        return this.location;

    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    /**
     * Draws the display.
     */
    public void draw() {
        StdDraw.textLeft(location.getX(), location.getY(), (prefix + value));
    }
}

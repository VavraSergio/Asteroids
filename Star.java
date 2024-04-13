/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class Star implements Drawable {
    public static final int STAR_RADIUS = 1;
    private Point location;
    
    /**
     * Constructor for star.
     * 
     * @param x a param x
     * @param y a param y
     */
    public Star(double x, double y) {
        this.location = new Point(x, y);
    }

    /**
     * Returns the location.
     * 
     * @return a point
     */
    public Point getLocation() {
        return this.location;
    }

    /**
     * Draws the star.
     */
    public void draw() {
        StdDraw.filledCircle(location.getX(), location.getY(), STAR_RADIUS);
    }

}

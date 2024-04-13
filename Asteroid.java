import java.awt.Color;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class Asteroid extends Enemy {
    public static final int ASTEROID_SPEED = 1;

    /**
     * Constructor for asteroid.
     * 
     * @param size a size
     */
    public Asteroid(AsteroidSize size) {
        super(ASTEROID_SPEED, size.getRadius(), size.getPoints());
    }

    /**
     * Draw method.
     */
    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.circle(pose.getX(), pose.getY(), radius);
    }

    /**
     * Update method. 
     */
    public void update() {
        if (!isDestroyed())
            super.update();
    }

}

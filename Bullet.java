import java.awt.Color;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class Bullet extends GameElement {

    public static final double BULLET_RADIUS = 1.5;
    public static final int BULLET_SPEED = 20;
    public static final int BULLET_DURATION = 20;
    private int counter;

    /**
     * The constructor for bullet.
     * 
     * @param pose a pose
     */
    public Bullet(Pose pose) {
        super(pose, new Vector2D(pose.getHeading(), BULLET_SPEED), BULLET_RADIUS);
        this.counter = 0;
    }

    /**
     * Updates bullet.
     */
    public void update() {
        if (counter < BULLET_DURATION) {
            super.update();
            counter += 1;
        } else {
            super.setDestroyed(true);
            super.update();
        }

    }

    /**
     * Draws the ship.
     */
    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledCircle(this.pose.getX(), this.pose.getY(), BULLET_RADIUS);
    }
    
    public int getCounter() {
        return this.counter;
    }
}

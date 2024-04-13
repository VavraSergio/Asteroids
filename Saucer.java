import java.util.Random;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class Saucer extends Enemy {
    
    public static final int HALF_WIDTH = 10;
    public static final int HALF_HEIGHT = 5;
    public static final double SAUCER_SPEED = 2.0;
    public static final int SAUCER_POINTS = 400;
    public static final double SPAWN_PROB = .002;
    public static final double TURN_PROB = .05;

    /**
     * Constructor for saucer.
     */
    public Saucer() {
        super(SAUCER_SPEED, HALF_WIDTH, SAUCER_POINTS);
        
    }

    /**
     * Draw method.
     */
    public void draw() {
        StdDraw.rectangle(super.pose.getX(), super.pose.getY(), HALF_WIDTH, HALF_HEIGHT);
    }
    
    /**
     * Update method.
     */
    public void update() {
        Random rand = new Random();
        if (rand.nextInt(20) == 0) {
            super.velocity = super.velocity.newHeading(AsteroidsGame.randomHeading());
        }
        
        this.pose = pose.move(velocity);

        if (pose.getX() > GameDriver.SCREEN_WIDTH) {
            setDestroyed(true);

        } else if (pose.getX() < 0) {
            setDestroyed(true);
        }
        if (pose.getY() > GameDriver.SCREEN_HEIGHT) {
            setDestroyed(true);
        } else if (pose.getY() < 0) {
            setDestroyed(true);
        }
    }
}

import java.awt.Color;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class Ship extends GameElement {
    public static final int SHIP_WIDTH = 10;
    public static final int SHIP_HEIGHT = 20;
    public static final double SHIP_TURN = 0.1;
    public static final double SHIP_MOVE = 0.1;
    public static final double FRICTION = .02;

    /**
     * Constructor for ship.
     */
    public Ship() {
        super(new Pose(GameDriver.SCREEN_WIDTH / 2, GameDriver.SCREEN_HEIGHT / 2, Math.PI / 2),
                new Vector2D(Math.PI / 2, 0), SHIP_HEIGHT / 2);

    }
    
    /**
     * Turns the ship left.
     */
    public void turnLeft() {
        super.pose = super.pose.newHeading(super.pose.getHeading() + SHIP_TURN);

    }

    /**
     * Turns the ship right.
     */
    public void turnRight() {
        super.pose = super.pose.newHeading(super.pose.getHeading() - SHIP_TURN);

    }
    
    /**
     * Moves the ship forward.
     */
    public void thrust() {
        super.velocity = super.velocity.add(new Vector2D(pose.getHeading(), SHIP_MOVE));

    }

    /**
     * Updates the ship.
     */
    public void update() {
        super.update();
        if (super.velocity.getMagnitude() > FRICTION) {
            super.velocity = super.velocity.newMagnitude(super.velocity.getMagnitude() - FRICTION);
            if (super.velocity.getMagnitude() > 0 && super.velocity.getMagnitude() < FRICTION) {
                super.velocity = super.velocity.newMagnitude(0);
            }
        }
        
    }
    
    /**
     * Draws the ship.
     */
    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        GameUtils.drawPoseAsTriangle(super.getPose(), SHIP_WIDTH, SHIP_HEIGHT);
    }

}

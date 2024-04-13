/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public abstract class GameElement implements Updatable, Drawable {
    protected Pose pose;
    protected Vector2D velocity;
    protected double radius;
    protected boolean destroyed;

    /**
     * Constructor for class.
     * 
     * @param pose a param pose
     * @param velocity a param pose
     * @param radius a param pose
     */
    public GameElement(Pose pose, Vector2D velocity, double radius) {
        this.pose = pose;
        this.radius = radius;
        this.velocity = velocity;

    }

    public Pose getPose() {
        return this.pose;
    }

    public Vector2D getVelocity() {
        return this.velocity;
    }

    public double getRadius() {
        return this.radius;
    }

    public boolean isDestroyed() {
        return this.destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    /**
     * Updates the class.
     */
    public void update() {
        this.pose = pose.move(velocity);

        if (pose.getX() > GameDriver.SCREEN_WIDTH) {
            this.pose = pose.newX(0);

        } else if (pose.getX() < 0) {
            this.pose = pose.newX(GameDriver.SCREEN_WIDTH);
        }
        if (pose.getY() > GameDriver.SCREEN_HEIGHT) {
            this.pose = pose.newY(0);
        } else if (pose.getY() < 0) {
            this.pose = pose.newY(GameDriver.SCREEN_HEIGHT);
        }
    }

    /**
     * Checks for collision.
     * 
     * @param other another param
     * @return boolean a boolean
     */
    public boolean checkCollision(GameElement other) {
        double xCollision;
        double yCollision;
        double x1 = this.pose.getX();
        double y1 = this.pose.getY();
        double x2 = other.pose.getX();
        double y2 = other.pose.getY();
        if (x1 > x2) {
            xCollision = x1 - x2;
        } else {
            xCollision = x2 - x1;
        }

        if (y1 > y2) {
            yCollision = y1 - y2;
        } else {
            yCollision = y2 - y1;
        }

        if ((xCollision <= (other.getRadius() + this.getRadius()))
                && (yCollision <= (other.getRadius() + this.getRadius()))) {
            this.setDestroyed(true);
            other.setDestroyed(true);
            return true;
        } else {
            return false;
        }

    }
}

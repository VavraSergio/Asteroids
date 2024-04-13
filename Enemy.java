/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public abstract class Enemy extends GameElement {

    protected int points;

    /**
     * Constructor for enemy.
     * 
     * @param speed a speed
     * @param radius a radius
     * @param points a point
     */
    public Enemy(double speed, double radius, int points) {
        super(new Pose(AsteroidsGame.randomXPosition(), AsteroidsGame.randomYPosition(), AsteroidsGame.randomHeading()),
                new Vector2D(AsteroidsGame.randomHeading(), speed), radius);
        
        this.points = points;
    }
    
    public int getPoints() {
        return points;
    }

}

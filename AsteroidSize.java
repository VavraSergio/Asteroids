import java.util.Random;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public enum AsteroidSize {

    SMALL(10, 200),
    MEDIUM(20, 100),
    LARGE(30, 50);
    
    private int radius;
    private int points;
    
    /**
     * Constructor for asteroid.
     * 
     * @param radius a radius
     * @param points a point
     */
    AsteroidSize(int radius, int points) {
        this.points = points;
        this.radius = radius;
    }
    
    public int getRadius() {
        return this.radius;
    }
    
    public int getPoints() {
        return this.points;
    }
    
    /**
     * Asteroid size. 
     * 
     * @return an asteroid size
     */
    public static AsteroidSize randomSize() {
        Random rand = new Random();
        int nextInt = rand.nextInt(4);
        if (nextInt == 0 || nextInt == 1) {
            return AsteroidSize.LARGE;
        }
        if (nextInt == 2) {
            return AsteroidSize.MEDIUM;
        }
        return AsteroidSize.SMALL;
    }
    

}

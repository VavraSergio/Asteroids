import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 * Main class for Asteroids.
 * 
 * @author CS159 Instructors
 * @version s23
 */
public class AsteroidsGame implements Playable {

    public static final int LIVES = 3;
    private ArrayList<Drawable> drawElements;
    private ArrayList<Updatable> updateElements;
    private ArrayList<GameElement> shipAndBullets;
    private ArrayList<Enemy> enemies;

    private NumericDisplay score;
    private NumericDisplay lives;
    private Ship ship;

    /**
     * Constructs all game elements.
     */
    public AsteroidsGame() {
        StdDraw.setTitle("Generic Space Rock Shooter");

        drawElements = new ArrayList<>();
        updateElements = new ArrayList<>();
        shipAndBullets = new ArrayList<>();
        enemies = new ArrayList<>();

        // add background elements
        final int TOP = GameDriver.SCREEN_HEIGHT;
        score = new NumericDisplay(10, TOP - 20, "Score: ", 0);
        lives = new NumericDisplay(10, TOP - 40, "Lives: ", LIVES);
        drawElements.add(score);
        drawElements.add(lives);

    }

    /**
     * Creates and adds 100 stars with random locations.
     */
    private void newStars() {
        for (int i = 0; i < 100; i++) {
            Star star = new Star(randomXPosition(), randomYPosition());
            drawElements.add(star);
        }

    }

    /**
     * Creates new ship.
     */
    private void newShip() {
        ship = new Ship();
        drawElements.add(ship);
        updateElements.add(ship);
        shipAndBullets.add(ship);
    }

    /**
     * Adds enemy.
     * 
     * @param e a new param
     */
    public void addEnemy(Enemy e) {
        drawElements.add(e);
        updateElements.add(e);
        enemies.add(e);
    }

    /**
     * Starts a new game with 10 asteroids.
     */
    public void startGame() {
        newStars();
        newShip();
        newEnemies();
    }

    /**
     * New enemies.
     */
    private void newEnemies() {
        for (int i = 0; i < 10; i++) {
            Asteroid asteroid = new Asteroid(AsteroidSize.randomSize());
            drawElements.add(asteroid);
            updateElements.add(asteroid);
            enemies.add(asteroid);
        }

    }

    /**
     * Handle keyboard input from the game and move the ship and shoot bullets if the corresponding keys are pressed.
     */
    private void handleKeyboardInput() {
        if (GameDriver.spacePressed()) {
            Bullet bullet = new Bullet(ship.pose);
            drawElements.add(bullet);
            updateElements.add(bullet);
            shipAndBullets.add(bullet);
        }

        if (GameDriver.leftPressed()) {
            this.ship.turnLeft();
        }

        if (GameDriver.rightPressed()) {
            this.ship.turnRight();
        }

        if (GameDriver.upPressed()) {
            this.ship.thrust();
        }
    }

    @Override
    public void update() {

        // freeze simulation if game is over
        if (lives.getValue() <= 0) {
            return;
        }

        int count = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) instanceof Asteroid) {
                count += 1;
            }
        }
        if (count == 0) {
            newEnemies();
        }
        // update everything (including newest bullet)

        handleKeyboardInput();
        for (Updatable item : updateElements) {
            item.update();

        }

        Random rand = new Random();
        if (rand.nextInt(500) == 0) {
            Saucer saucer = new Saucer();
            drawElements.add(saucer);
            updateElements.add(saucer);
            enemies.add(saucer);
        }

        removeDestroyedBullets();
        removeDestroyedEnemies();

    }

    /**
     * Destroyed enemies.
     */
    public void removeDestroyedBullets() {
        for (int i = 0; i < shipAndBullets.size(); i++) {
            if (shipAndBullets.get(i).isDestroyed()) {
                updateElements.remove(shipAndBullets.get(i));
                drawElements.remove(shipAndBullets.get(i));
                shipAndBullets.remove(shipAndBullets.get(i));

            }
        }

    }

    /**
     * Removes destroyed.
     */
    public void removeDestroyedEnemies() {
        if (enemies.size() > 0 && shipAndBullets.size() > 0) {
            for (int i = 0; i < enemies.size(); i++) {
                for (int k = 0; k < shipAndBullets.size(); k++) {
                    if (k < shipAndBullets.size() && i < enemies.size()) {

                        if (shipAndBullets.get(k).checkCollision(enemies.get(i))) {
                            if (shipAndBullets.get(k) instanceof Ship) {
                                updateElements.remove(shipAndBullets.get(k));
                                drawElements.remove(shipAndBullets.get(k));
                                shipAndBullets.remove(shipAndBullets.get(k));
                                newShip();
                                lives.setValue(lives.getValue() - 1);
                                score.setValue(score.getValue() + enemies.get(i).getPoints());
                                updateElements.remove(enemies.get(i));
                                drawElements.remove(enemies.get(i));
                                enemies.remove(enemies.get(i));

                            } else {
                                updateElements.remove(shipAndBullets.get(k));
                                drawElements.remove(shipAndBullets.get(k));
                                shipAndBullets.remove(shipAndBullets.get(k));
                                score.setValue(score.getValue() + enemies.get(i).getPoints());
                                updateElements.remove(enemies.get(i));
                                drawElements.remove(enemies.get(i));
                                enemies.remove(enemies.get(i));

                            }

                        }

                    }
                }

            }
        }

    }

    @Override
    public void draw() {
        StdDraw.setPenColor(Color.WHITE);
        for (Drawable item : drawElements) {
            item.draw();
        }
    }

    /**
     * Generates a random X position on the screen.
     * 
     * @return the x position
     */
    protected static double randomXPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_WIDTH;
    }

    /**
     * Generates a random Y position on the screen.
     * 
     * @return the y position
     */
    protected static double randomYPosition() {
        return GameDriver.GENERATOR.nextDouble() * GameDriver.SCREEN_HEIGHT;
    }

    /**
     * Generates a random heading from the interval [0, 2*PI).
     * 
     * @return the heading
     */
    protected static double randomHeading() {
        return GameDriver.GENERATOR.nextDouble() * 2 * Math.PI;
    }

}

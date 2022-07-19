package pk.pl;

/*  Imported libraries / classes
 *  java.util.ArrayList - provide possibility to creating Array Lists
 *  javax.awt.Color - provide ability to define color and assigned them to background of the labels and window
 *  java.util.Random - enables the formation of pseudo-random numbers
 */
import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/*  Point class - class representing points in Snake game
 *  Its child class and inherits from the Square class
 */
public class Point extends Square {
    // random is an object type Random, which is used to generate randomise x and y position of points
    private final Random random = new Random();

    //  Constructor for Point class
    //  It takes two arguments - int width and int height - which define the limits of the randomization
    //  of the position of the point
    public Point(int width, int height) {
        super();                                                    //  Call the parent class constructor
        this.x = randomXPosition(width);                            //  Set random X position of the point
        this.y = randomYPosition(height);                           //  Set random Y position of the point
        this.setBounds(this.x, this.y,  this.width, this.height);   //  Set bounds for point object (label)
        this.setBackground(Color.RED);                              //  Set background color to red
    }

    //  Methods randomXPosition and randomYPosition set the new random x and y position of the point
    //  They take by one argument (width - width of GamePanel panel and height - height of GamePanel panel)
    //  Arguments set up "barriers" in which point should appear
    //  random.nextInt return random integer number
    //  "Barriers" are defined in equations: (width/this.width - 1) * this.width
    //  For example for GamePanel width = 500, this.width = 10 (point width) from 0 to 490. If point spawn
    //  on position 500 it will be not visible on GamePanel panel.
    public int randomXPosition(int width) {return random.nextInt(1,(width/this.width) - 1)*this.width; }

    public int randomYPosition(int height) {return random.nextInt(1,(height/this.height) - 1)*this.height; }

    //  Method setPosition sets position of the point
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBounds(this.x, this.y,  this.width, this.height);
    }

    //  Method assignNewPosition generate new position for the point
    //  First it generates new point positions and then check if point will not spawn on obstacle or on snake
    //  If not it set new point position
    public void assignNewPosition(ArrayList<Snake> snakeParts, ArrayList<Obstacle> obstacles, int width, int height) {
            do {
                this.x = randomXPosition(width);
                this.y = randomYPosition(height);
            } while (!isOnSnakeParts(snakeParts) || !isOnObstacles(obstacles));

            setPosition(this.x, this.y);
        }

    //  Method isOnSnakeParts check if point will spawn on Snake object
    //  It takes one argument - snakeParts ArrayList, which contains snake objects. This list is defined in Game class
    //  Method checks if new positions of the point are different from each snake object positions
    //  If yes - it returns true
    //  If no - it returns false
    //  Function is used in assignNewPosition method to prevent from spawning point on Snake body
    public boolean isOnSnakeParts(ArrayList<Snake> snakeParts) {
        for (int i = 0; i <= snakeParts.size() - 1; i++){
            if (snakeParts.get(i).getY() == this.y && snakeParts.get(i).getX() == this.x) {
                return false;
            }
        }
        return true;
    }

    //  Method isOnObstacles check if point will spawn on Obstacle object
    //  Method works analogously to the method isOnSnakeParts
    public boolean isOnObstacles(ArrayList<Obstacle> obstacles) {
        for (int i = 0; i <= obstacles.size() - 1; i++) {
            if (obstacles.get(i).getY() == this.y && obstacles.get(i).getX() == this.x) {
                return false;
            }
        }
        return true;
    }
    }


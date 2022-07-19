package pk.pl;

/*  Imported libraries / classes
 *  javax.awt.Color - provide ability to define color and assigned them to background of the labels and window
 */
import java.awt.Color;

/*  Obstacle class - class representing obstacles in Snake game
 *  Its child class and inherits from the Square class
 */
public class Obstacle extends Square{

    // Constructor for Obstacle class objects
    public Obstacle(int x, int y) {
        super();                                        //  Call parent class constructor
        this.x = x;                                     //  Assigned x position of obstacle
        this.y = y;                                     //  Assigned y position of obstacle
        this.setBounds(x,y,this.width,this.height);     //  Set obstacle bounds
        this.setBackground(Color.decode("#006bb3"));    //  Set obstacle color
    }
}

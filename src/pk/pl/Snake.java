package pk.pl;

 /*     Snake class represent each snake part. Of course the most important is snake "head".
 *      The head of the snake will be the first Snake object created in Game class (it will be defined in Game constructor).
 *      Head will be the first element in objects ArrayList "snakeParts", which will be defined in Game class.
 *      Snake class is a child class from Square class. Mainly uses the functionality of JLabel class.
 */
public class Snake extends Square {

    // Boolean variable vertical and horizontal are responsible for setting snake move direction.
    // More in snakeMove() method
    private boolean vertical = true;
    private boolean horizontal = true;

    // Constructor for Snake class
    public Snake(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    // Method setPosition is responsible for changing position of Snake object.
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
        this.setBounds(this.x, this.y,  this.width, this.height);
    }

    /*  Method "snakeMovement" is responsible for changes snake "head" (main part) position.
    *               |   Horizontal
    *   Vertical    |   0       1
    *   -   -   -   |   -   -   -   -
    *       0       |   Up      Right
    *       1       |   Down    Left
    *
    *   Method take two arguments: width and height (int type). They are used to adjust a method to a different
    *   sizes of the game area.
    *   Position settings:
    *   Position is incremented/decremented by 10 each time the function is called.
    *   Conditions (this.x + this.width or this.y + this.height) are to prevent snake from displacing behind
    *   the game area. This.x/this.y is a snake head position and this.width and this.height are the snake parts sizes.
    */
    public void snakeMovement (int width, int height){
        int increment = 10;                                             // position increment
        // Horizontal moves
        if (horizontal) {
            // Move left
            if (vertical) {
                this.setPosition(this.x - increment, this.y);
                if (this.x <  0) {this.setPosition(width - this.width, this.y);}
            // Move right
            } else {
                this.setPosition(this.x + increment, this.y);
                if (this.x + this.width > width) { this.setPosition(0, this.y); }
            }
        }

        // Vertical moves
        if (!horizontal) {
            // Downward movement
            if (vertical) {
                this.setPosition(this.x, this.y + increment);
                if (this.y + this.height > height) { this.setPosition(this.x, 0); }
            // Move up
            } else {
                this.setPosition(this.x, this.y - increment);
                if (this.y < 0) { this.setPosition(this.x, height - this.height); }
            }
        }
    }

    // Setters for boolean vertical and horizontal variables
    public void setVertical(boolean vertical) { this.vertical = vertical; }

    public void setHorizontal(boolean horizontal) { this.horizontal = horizontal; }

    // Getters for boolean vertical and horizontal variables
    public boolean isVertical() { return this.vertical; }

    public boolean isHorizontal() { return this.horizontal; }
}

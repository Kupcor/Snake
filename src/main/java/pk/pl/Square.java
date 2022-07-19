package pk.pl;

/*  Imported libraries / classes
 *  javax.swing.JLabel - allow creating labels (Snake body parts, points and obstacles)
 *  javax.swing.BorderFactory - add possibility to creating border style for labels
 *  java.awt.Color - provide ability to define color and assigned them to background of the labels and window
 *  javax.swing.border.Border - allow creating object from class Border to which will be assigned border style
 */
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.border.Border;

/*  Square class - main class responsible for creating Snake body parts, points and obstacle
 *  Its child class and inherits from the Swing class - JLabel
 *  Square class represents simple square (with its style.positions and dimensions)
 *  Square class is parent class for:
 *          Snake class
 *          Point class
 *          Score class
 */
public class Square extends JLabel{
    protected int x;                                    //  X-position of label
    protected int y;                                    //  Y-position of label
    protected int width             = 10;               //  Label width
    protected int height            = 10;               //  Label height
    protected Color color           = Color.GREEN;      //  Label color
    protected final Border border   = BorderFactory.createLineBorder(Color.BLACK);  //  Label border style

    // Constructor for Square class
    protected Square() {
        this.setBounds(this.x, this.y, this.width, this.height);    //  Sets label bounds (x-dimension and y-dimension)
        this.setOpaque(true);                                       //  Allow changing label background color
        this.setBackground(this.color);                             //  Set background color of label
        this.setBorder(this.border);                                //  Set label border style
        this.setHorizontalAlignment(CENTER);                        //  Set horizontal and vertical text alignment
        this.setVerticalAlignment(CENTER);                          //  (used in ScorePanel)
    }
}

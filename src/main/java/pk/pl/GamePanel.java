package pk.pl;

/*  Imported libraries / classes
 *  javax.swing.JPanel from SWING directory - provide ability to create main game area
 *  javax.awt.Color - provide ability to define color and assigned them to background of the labels and window
 */
import javax.swing.JPanel;
import java.awt.Color;

/*  GamePanel class - class responsible for creating game area in which snake will move.
 *  Its child class and inherits from the Swing class - JPanel
 */
public class GamePanel extends JPanel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    /*  Constructor for GamePanel class
    *   It takes four arguments - x,y are position coordinates of panel, width and height - panel sizes
    */
    public GamePanel(int x, int y, int width, int height) {
        this.x = x;                                 //  x start position of panel
        this.y = y;                                 //  y start position of panel
        this.width = width;                         //  panel width
        this.height = height;                       //  panel height

        this.setBackground(Color.BLACK);                        //  Set game area background on black
        this.setBounds(this.x,this.y,this.width,this.height);   //  Set game area bounds
        this.setLayout(null);                                   //  Set Layout to null (manual positioning element in panel)
        this.setOpaque(true);                                   //  Allow changing background color of panel
    }
}

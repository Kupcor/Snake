package pk.pl;

/*  Imported libraries / classes
 *  javax.swing.JFrame from SWING directory - provide ability to create main window for the game
 *  javax.awt.Color - provide ability to define color and assigned them to background of the labels and window
 */
import com.formdev.flatlaf.FlatDarculaLaf;

import javax.swing.*;
import java.awt.*;

/*  Window class - main class responsible for creating the main window/frame for Snake Game
 *  Its child class and inherits from the Swing class - JFrame
 *  Window class is parent class for:
 *      Game class
 *      Menu class
 */
public class Window extends JFrame{
    protected int width = 500;                                      //  Width variable represents width of main frame
    protected int height = 500;                                     //  Height variable represents height of main frame
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();    //  Dim variable contains screen dimensions

    /*  Constructor for Window class
    *   Decorations of frame are unset due to get accurate frame sizes and get accurate snake position change.
    */
    protected Window() {
        try {
            UIManager.setLookAndFeel(new FlatDarculaLaf());
        } catch( Exception ex ) {
            System.err.println("Failed to initialize LaF");
        }
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            //  Terminate program when main frame is closed
        this.setPreferredSize(new Dimension(this.width, this.height));  //  Set frame sizes (x-dimension and y-dimension)
        this.setResizable(false);                                       //  Prevent from resize main frame (window)
        this.getContentPane().setBackground(Color.BLACK);               //  Set background color on black
        this.setLayout(null);                                           //  Each component will be placed manually
        this.setUndecorated(true);                                      //  Unset frame decoration

        //  Set the window locations to the center of the monitor
        this.setLocation(   dim.width/2-this.getSize().width/2 - this.width/2,
                            dim.height/2-this.getSize().height/2 - this.height/2);

        this.setVisible(true);                                          //  Make changes visible
    }
}

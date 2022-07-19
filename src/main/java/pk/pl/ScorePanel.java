package pk.pl;

/*  Imported libraries / classes
 *  javax.swing.JFrame from SWING directory - provide ability to create main window for the game
 *  javax.swing.SwingConstants.LEFT - set text position in "scoreInfo" panel to LEFT
 */
import java.awt.*;
import static javax.swing.SwingConstants.LEFT;

/*     ScorePanel class represents part of the window game, that represents most important information during game.
 *     These pieces of information are: Player Name, Snake caption and total score.
 *     ScorePanel is child class from GamePanel class.
 *     ScorePanel class also use Square class.
 */
public class ScorePanel extends GamePanel{
    private int score;                                      //  Total amount of point acquire during one game session
    private final Square scoreInfo;                         //  scoreInfo represents label in which will be displayed score info

    //  Constructor for ScorePanel class
    //  The constructor takes 4 arguments (x , y - x/y position of panel, width/height - sizes of ScoreInfo panel
    //  Taken attributes override attributes from GamePanel class
    public ScorePanel(int x, int y, int width, int height, String playerName) {
        super(x, y, width, height);                             //  Call the parent class constructor
        this.score = 0;                                         //  Assigned 0 value to score variable
        this.setBounds(this.x,this.y,this.width,this.height);   //  Set panel bounds

        //  Definition of playerNameLabel label, which will contain information about player
        Square playerNameLabel = new Square();                                    // Create playerNameLabel object (Square type)
        playerNameLabel.setBounds(0,0,this.width / 5,this.height);    //  Set playerNameLabel bounds
        playerNameLabel.setText(playerName);                                  //  Set label text
        playerNameLabel.setBackground(Color.decode("#0b3d61"));                  //  Set background color
        playerNameLabel.setForeground(Color.decode("#d1eeff"));                  //  Set text color
        this.add(playerNameLabel);                                               //  Add object playerNameLabel to ScorePanel object

        //  Definition of gameInfo label, which will contain "Snake" caption
        Square gameInfo = new Square();
        gameInfo.setBounds(playerNameLabel.getWidth(),0,(this.width / 5)*3,this.height);
        gameInfo.setText("Snake Game");
        gameInfo.setBackground(Color.decode("#0b3d61"));
        gameInfo.setForeground(Color.decode("#d1eeff"));
        this.add(gameInfo);

        //  Definition of scoreInfo label, which will contain information about player score during one game session
        scoreInfo = new Square();
        scoreInfo.setBounds(playerNameLabel.getWidth() + gameInfo.getWidth(),0,this.width / 5,this.height);
        scoreInfo.setText("Score: "+Integer.toString(this.score));
        scoreInfo.setHorizontalAlignment(LEFT);
        scoreInfo.setBackground(Color.decode("#0b3d61"));
        scoreInfo.setForeground(Color.decode("#d1eeff"));
        this.add(scoreInfo);
    }

    // Method addScore is responsible for increasing score variable by 1 every time when Snake "eat" red point
    public void addScore() {
        this.score = this.score + 1;
        scoreInfo.setText("Score: "+Integer.toString(this.score));
    }

    public int getScore() {
        return score;
    }
}

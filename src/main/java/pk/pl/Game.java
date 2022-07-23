package pk.pl;

/*  Imported libraries / classes */
import javax.swing.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import pk.structures.ReadStructure;

/*  Game class - main class for the Snake game project
*   It is child class and inherits from the Window Klass
*   It implements KeyListener class in order to allow the player to move (up, down, left, right)
*   Game class contain main functions responsible for main game mechanics
*/
public class Game extends Window implements KeyListener {

    private final ArrayList<Snake> snakeParts = new ArrayList<>();          //   Array List to contain Snake objects
    private final ArrayList<Obstacle> obstacles = new ArrayList<>();        //   Array List to contain Obstacle objects
    private final Snake snake;                                              //   Sanke object
    private final Point point;                                              //   Point object
    private final ScorePanel infoPanel;                                     //   infoPanel panel
    private final GamePanel gamePanel;                                      //   gamePanel panel
    public String playerName;                                               //   Player name

    // Constructor for Game class
    public Game(String playerName, String mapName) throws InterruptedException, FileNotFoundException {
        super();                                                                //  Call parent class constructor
        this.addKeyListener(this);                                            //  Add key listener to window
        this.playerName = playerName;

        infoPanel = new ScorePanel(0, 0 , this.width, 20, playerName);                  //  InfoPanel definition
        gamePanel = new GamePanel (0, 20, this.width, this.height - 20);    //  GamePanel definition

        this.createFrame();                                                             //  Create obstacle frame
        this.createObstacleStructure(mapName);

        //  Create snake object (snake "head"). Starting position is set on the middle of the game area
        //  Starting position should be rounded to the full value (snake size)
        snake = new Snake(  gamePanel.getWidth()/2 - (gamePanel.getWidth()/2) % 10,
                            gamePanel.getHeight()/2 - (gamePanel.getHeight()/2) % 10);

        this.snakeParts.add(snake);                 //  Add "snake head" to the beggining of the snakeParts list
        gamePanel.add(snakeParts.get(0));

        point = new Point(gamePanel.getWidth(), gamePanel.getHeight());     //  Create point object
        gamePanel.add(point);

        this.getContentPane().add(infoPanel);           //  Add infoPanel to main game window
        this.getContentPane().add(gamePanel);           //  Add gamePanel to main game window

        //  Call method snakeMoves() - method resposible for the executions of the most important game mechanisms
        this.pack();                                    //  Pack added objects/items

        //  Main function of the Game class - initilize snake game
        this.snakeMoves();
    }

    //  Method addSnakePart adds another part of snake body (increases his tail) when snake get point
    //  It creates new snake object, which takes over the previous position of the preccessor snake object form
    //  the snakeParts Array List
    //  Then it adds new snake object to game area
    private void addSnakePart() {
        Snake snake = new Snake(snakeParts.get(snakeParts.size()-1).getX(), snakeParts.get(snakeParts.size()-1).getY());
        this.snakeParts.add(snake);
        gamePanel.add(snake);
    }

    //  Method snakePositionActualization sets new position to each snake object in snakeParts list (except head, which
    //  that sets new positions
    private void snakePositionActualization() {
        for (int i = snakeParts.size() - 1; i >= 1; i--) {
            snakeParts.get(i).setPosition(snakeParts.get(i-1).getX(), snakeParts.get(i-1).getY());
        }
    }

    //  Method snakeMoves() is main method is Game Class. It is responsible for infinite snake moves.
    //  Main loop of the application
    //  It contains method:
    //      addPoint
    //      snakePosoitionActualization
    //      snakeMovement from Snake class
    //      lostGame
    private void snakeMoves() {
        Timer timer = new Timer(30, e -> {
            if (point.getX() == snake.getX() && point.getY() == snake.getY()) {     //  Check if player get a point
                addPoint();                                                    //  If yes add point
            }

            snakePositionActualization();                                      //  Snake objects position actualization
            snake.snakeMovement(gamePanel.getWidth(), gamePanel.getHeight());       //  Move snake head

            if(lostGame()) {   //  Check if player has not lost
                DataBase dataBase = new DataBase();
                try {
                    dataBase.saveData(playerName, infoPanel.getScore());
                } catch (SQLException ex) {
                    System.err.println(ex.getMessage());
                }
                dispose();
                ((Timer)e.getSource()).stop();
                try {
                    Menu menu = new Menu();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        timer.start();
    }

    //  Method addPoint adds points to score panel and increase snake body every time, when player get a point
    //  It contains:
    //      assignNewPosition method from Point class
    //      addSnakePart method
    //      addScore method from ScorePanel class
    private void addPoint() {
        point.assignNewPosition(snakeParts, obstacles, gamePanel.getWidth(), gamePanel.getHeight());
        this.addSnakePart();
        infoPanel.addScore();
    }

    //  Method listGame() checks checks that the snake has not bitten itself or hit an obstacle. The loops compare
    //  the current positions of the snake head with the positions of all its parts and the positions of all the obstacles
    //  If any positions of snake parts and obstacles is equal to snake head position game is lost
    private boolean lostGame() {
        for (int i = 1; i <= snakeParts.size() - 1; i++){
            if (snakeParts.get(i).getY() == snake.getY() && snakeParts.get(i).getX() == snake.getX()) {
                return true;

            }
        }
        for (int i = 0; i <= obstacles.size() - 1; i++) {
            if (obstacles.get(i).getY() == snake.getY() && obstacles.get(i).getX() == snake.getX()) {
                this.dispose();
                return true;
            }
        }

        return false;
    }

    //  Method createFrame() creates a obstacles frame around the perimeter of the playing area.
    private void createFrame() {
        int frameBrake = gamePanel.getWidth() / 50;

        for (int i = 0; i < gamePanel.getHeight(); i = i + 10) {
            if (i > gamePanel.getHeight() / 2 - frameBrake*10 && i < gamePanel.getHeight() / 2 + frameBrake*10) { continue; }
            this.addObstacle(0, i);
            this.addObstacle(gamePanel.getWidth() - 10, i);
        }

        for (int i = 0; i < gamePanel.getWidth(); i = i + 10) {
            if (i > gamePanel.getWidth() / 2 - frameBrake*10 && i < gamePanel.getWidth()/2 + frameBrake*10) { continue; }
            this.addObstacle(i, gamePanel.getHeight() - 10);
            this.addObstacle(i, 0);
        }
    }

    //  Method createObstacleStructure creates an additional, symetrical, piramidal shapes obstacles structures
    private void createObstacleStructure(String mapName) throws FileNotFoundException {
        switch (mapName) {
            case "Egipt map": {
                this.loadStructure("src\\main\\java\\pk\\structures\\piramids.txt",100 , 100);
                break;
            }
            case "Lakes map": {
                this.loadStructure("src\\main\\java\\pk\\structures\\cubes.txt",100 , 100);
                break;
            }
            case "Blocks map": {
                this.loadStructure("src\\main\\java\\pk\\structures\\squares.txt",100 , 100);
                break;
            }
            case "Simple map": {
                this.loadStructure("src\\main\\java\\pk\\structures\\clear.txt",100 , 100);
                break;
            }
            case "Labyrinth map": {
                this.loadStructure("src\\main\\java\\pk\\structures\\labirynth.txt",100 , 100);
                break;
            }
        }
    }

    private void loadStructure(String filePath, int xStructurePosition, int yStructurePosition) throws FileNotFoundException {
        ReadStructure readStructure = new ReadStructure(filePath);
        int xStartStructurePosition = xStructurePosition;
        for (int i = 0; i < readStructure.getVerticalLength(); i += 1) {
            for (int j = 0; j < readStructure.getHorizontalLength(); j += 1) {
                if(readStructure.getStructurePartStatus(i, j)) {
                    this.addObstacle(xStructurePosition, yStructurePosition);
                }
                xStructurePosition += 10;
            }
            yStructurePosition += 10;
            xStructurePosition = xStartStructurePosition;
        }
    }

    //  Method addObstacle() is a simple method. It creates and adds Obstacle objects to game area.
    //  It was created to decrease amount of reduntant code
    private void addObstacle(int xPosition, int yPosition) {
        Obstacle obstacle = new Obstacle(xPosition,yPosition);
        gamePanel.add(obstacle);
        obstacles.add(obstacle);
    }

    //  keyPressed method is called on any click.
    //  By switch statement it reacts only for up/down/left/right key
    //  Depending on the button clicked, they set the variables vertical and horizontal that are responsible for
    //  the appropriate snake movement in the method snakeMovement in Snake class.
    //  Conditions prevent the snake from falling backwards
    //  For example, when a snake is going to the left it is not possible for it to suddenly start moving to the right
    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case 40 -> {
                if (!(!snake.isHorizontal() && !snake.isVertical())) {
                    snake.setVertical(true);
                    snake.setHorizontal(false);
                }
            }
            case 38 -> {
                if (!(!snake.isHorizontal() && snake.isVertical())) {
                    snake.setVertical(false);
                    snake.setHorizontal(false);
                }
            }
            case 39 -> {
                if (!(snake.isHorizontal() && snake.isVertical())) {
                    snake.setVertical(false);
                    snake.setHorizontal(true);
                }
            }
            case 37 -> {
                if (!(snake.isHorizontal() && !snake.isVertical())) {
                    snake.setVertical(true);
                    snake.setHorizontal(true);
                }
            }
        }
    }

    /* Unused KeyListener methods */
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}

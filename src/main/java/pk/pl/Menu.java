package pk.pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Main menu for the Snake game */
public class Menu extends Window implements MouseListener {
    JButton playButton = new JButton();
    JButton statisticButton = new JButton();
    JButton exitButton = new JButton();
    JButton gameSettings = new JButton();

    public Menu() throws InterruptedException {
        super();
        this.width = 300;
        this.height = 100;
        this.setPreferredSize(new Dimension(this.width, this.height));

        playButton.setText("Play");
        playButton.addMouseListener(this);
        this.add(playButton);

        statisticButton.setText("Statistics");
        statisticButton.addMouseListener(this);
        this.add(statisticButton);

        exitButton.setText("Exit");
        exitButton.addMouseListener(this);
        this.add(exitButton);


        this.setLayout(new GridLayout(3,1));
        this.pack();
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == playButton) {
            this.dispose();
            PlayerName playerName = new PlayerName();
        }

        if (e.getSource() == statisticButton) {
            this.dispose();
            DataBase dataBase = new DataBase();
            dataBase.readData();
            ScoreTable score = new ScoreTable(dataBase.getResultsArrayList());
        }

        if (e.getSource() == exitButton) {
            System.exit(0);
        }
        }


    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
}

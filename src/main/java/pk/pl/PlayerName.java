package pk.pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PlayerName extends Window implements MouseListener {
    JButton enterNameButton = new JButton("Submit");
    JButton backToMenuButton = new JButton("Back");
    JTextField playerNameTextField = new JTextField(10);

    public PlayerName(){
        super();
        this.setSize(200,100);
        this.setLayout(new GridLayout(2,0));
        JPanel bottomPanel = new JPanel(new GridLayout(2,0));
        JPanel upperPanel = new JPanel(new GridLayout(1,2));
        JLabel playerNameLabel = new JLabel("Player name:");


        this.enterNameButton.addMouseListener(this);
        this.backToMenuButton.addMouseListener(this);

        upperPanel.add(playerNameLabel);
        upperPanel.add(this.playerNameTextField);
        bottomPanel.add(this.enterNameButton);
        bottomPanel.add(this.backToMenuButton);

        this.add(upperPanel);
        this.add(bottomPanel);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == backToMenuButton) {
            this.dispose();
            try {
                Menu menu = new Menu();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == this.enterNameButton) {
            try {
                Game game = new Game(this.playerNameTextField.getText());
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

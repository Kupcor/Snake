package pk.pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.util.Objects;

public class PlayerName extends Window implements MouseListener {
    JButton enterNameButton = new JButton("Submit");
    JButton backToMenuButton = new JButton("Back");
    JTextField playerNameTextField = new JTextField(10);
    private final String[] optionJComboBoxList = {"Simple map", "Egipt map",
            "Lakes map", "Blocks map", "Labyrinth map"};
    private final JComboBox mapsList = new JComboBox(this.optionJComboBoxList);

    public PlayerName(){
        super();
        this.setSize(200,150);
        this.setLayout(new GridLayout(2,0));
        JPanel bottomPanel = new JPanel(new GridLayout(3,0));
        JPanel upperPanel = new JPanel(new GridLayout(1,2));
        JLabel playerNameLabel = new JLabel("Player name:", SwingConstants.CENTER);


        this.enterNameButton.addMouseListener(this);
        this.backToMenuButton.addMouseListener(this);
        this.playerNameTextField.setHorizontalAlignment(SwingConstants.CENTER);

        upperPanel.add(playerNameLabel);
        upperPanel.add(this.playerNameTextField);
        bottomPanel.add(this.enterNameButton);
        bottomPanel.add(this.mapsList);
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

        if (e.getSource() == this.enterNameButton && !Objects.equals(this.playerNameTextField.getText(), "")) {
            try {
                Game game = new Game(this.playerNameTextField.getText(), String.valueOf(mapsList.getSelectedItem()));
            } catch (InterruptedException | FileNotFoundException ex) {
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

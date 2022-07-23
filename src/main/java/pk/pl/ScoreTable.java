package pk.pl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ScoreTable extends Window implements MouseListener {
    JButton exitButton = new JButton("Exit");
    JButton backToMenuButton = new JButton("Back to menu");
    ArrayList<ArrayList<Object>> resultsArrayList;


    public ScoreTable(ArrayList<ArrayList<Object>> results) {
        super();
        this.resultsArrayList = results;
        sortResultsArrayList();

        this.setSize(400,300);
        this.setLayout(new GridLayout(0,1));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,2));
        this.exitButton.addMouseListener(this);
        this.backToMenuButton.addMouseListener(this);

        for (int iterator = 0; iterator < this.resultsArrayList.size(); iterator++) {
            if (iterator > 10) { break;}
            mainPanel.add(new JLabel(String.valueOf(this.resultsArrayList.get(iterator).get(0)), SwingConstants.CENTER));
            mainPanel.add(new JLabel(String.valueOf(this.resultsArrayList.get(iterator).get(1)), SwingConstants.CENTER));
        }

        mainPanel.add(this.exitButton);
        mainPanel.add(this.backToMenuButton);

        this.add(mainPanel);
        this.setVisible(true);
    }

    //  Bubble sort
    private void sortResultsArrayList() {
        for (int iterator = 0; iterator < this.resultsArrayList.size(); iterator++){
            for (int subIterator = 0; subIterator < this.resultsArrayList.size(); subIterator++) {
                if (Integer.parseInt(String.valueOf(this.resultsArrayList.get(iterator).get(1))) > Integer.parseInt(String.valueOf(this.resultsArrayList.get(subIterator).get(1)))) {
                    ArrayList<Object> temp;
                    temp = this.resultsArrayList.get(iterator);
                    this.resultsArrayList.set(iterator, this.resultsArrayList.get(subIterator));
                    this.resultsArrayList.set(subIterator, temp);
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.backToMenuButton) {
            this.dispose();
            try {
                Menu menu = new Menu();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == this.exitButton) {
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

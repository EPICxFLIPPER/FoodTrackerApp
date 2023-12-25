package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//Represents the Menu Screens used by the GUI
//The screens are using a border layout with a grid layout center
public abstract class MenuGUI extends JPanel implements ActionListener {

    protected GIManager gim;



    protected static final int FONT_SIZE = 20;
    protected static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);

    protected JPanel centerPanel;
    protected JPanel northPanel;
    protected JPanel southPanel;
    protected JPanel eastPanel;
    protected JPanel westPanel;

    protected JLabel titleLabel;

    protected boolean active;


    protected String[] listInit = new String[0];

    //Modifies: This
    //Effects: Creates the title label for given menu
    protected void createTitleLabel(String title) {
        northPanel.setBackground(Color.green);

        titleLabel = new JLabel(title);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setFont(COOPER_BLACK);

        northPanel.add(titleLabel);

        repaint();
    }

    //Effects: Refreshes the side list(s) of the menu
    protected abstract void updateSideList();

    //Effects: Initializes the menu
    protected abstract void init();

    //Effects: Creates the features for the center main frame.
    protected abstract void createCenterFrame();

    //Effects: Makes the blank filler labels for the center grid frame:
    protected void createBlankLabels(int times) {
        for (int i = 0; i < times; i++) {
            centerPanel.add(new JLabel());
        }
    }

    //Modifies: This
    //Effects: Sets the action listeners for the features on the menu
    protected abstract void setActionListeners();

}

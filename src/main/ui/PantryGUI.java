package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantryGUI extends MenuGUI {

    private static final String TITLE = "Pantry Menu";

    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 5;


    public PantryGUI(GIManager gim) {
        super("Food Tracker");
        this.gim = gim;
        active = false;

        init();
        createMenuBar();
        createTitleLabel(TITLE);

        pack();
        repaint();
    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    protected void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setVisible(true);
        setSize(X_DIM,Y_DIM);
        setResizable(true);
        setTitle(TITLE);

        setMinimumSize(new Dimension(X_DIM,Y_DIM));
        setLayout(new BorderLayout());

        initPanels();
    }

    @Override
    protected void createButtons() {

    }

    //Modifies: this
    //Effects: Initializes the Panels that go in the grid
    private void initPanels() {
        centerPanel = new JPanel();
        northPanel = new JPanel();
        southPanel = new JPanel();
        eastPanel = new JPanel();
        westPanel = new JPanel();

        centerPanel.setLayout(new GridLayout(GRID_ROWS,GRID_COLS));
        northPanel.setLayout(new FlowLayout());

        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
        this.add(westPanel,BorderLayout.WEST);
        this.add(eastPanel,BorderLayout.EAST);
        this.add(southPanel,BorderLayout.SOUTH);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }




}

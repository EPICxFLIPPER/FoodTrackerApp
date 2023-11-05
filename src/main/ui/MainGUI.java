package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

//Represents the GUI for the Food Tracker application
public class MainGUI extends MenuGUI {



    private static final String TITLE = "Food Tracker";


    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 5;

    private JButton recipeMenuButton = new JButton("Recipe Menu");
    private JButton pantryMenuButton = new JButton("Pantry Menu");
    private JButton canCookButton = new JButton("Can Cook?");


    public MainGUI(GIManager gim) {
        super(TITLE);
        this.gim = gim;
        active = false;

        init();
        createCenterFrame();
        createMenuBar();
        createTitleLabel(TITLE);

        pack();
        repaint();
    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    protected void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(X_DIM,Y_DIM);
        setResizable(true);
        setTitle(TITLE);

        setMinimumSize(new Dimension(X_DIM,Y_DIM));
        setLayout(new BorderLayout());

        initPanels();
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



    //Modifes: This
    //Effects: Creates the buttons for the Jframe
    protected void createCenterFrame() {
        recipeMenuButton.addActionListener(this);
        canCookButton.addActionListener(this);
        pantryMenuButton.addActionListener(this);

        centerPanel.add(new JLabel());

        centerPanel.add(recipeMenuButton);


        centerPanel.add(new JLabel());

        centerPanel.add(pantryMenuButton);


        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        centerPanel.add(canCookButton);


        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());


        repaint();
    }





    @Override
    //Effects: Specifies the actions preformed by each button;
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recipeMenuButton) {
            gim.active = gim.RECIPE_ACTIVE;
            gim.activateFrame();
        } else if (e.getSource() == pantryMenuButton) {
            System.out.println("pantry");
            gim.active = gim.PANTRY_ACTIVE;
            gim.activateFrame();
        } else if (e.getSource() == canCookButton) {
            System.out.println("cook");
        } else {
            menuBarActions(e);
        }
    }



}

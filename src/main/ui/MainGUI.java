package ui;

import model.CookBook;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import static ui.FoodTrackerApp.JSON_STORE;

//Represents the GUI for the Food Tracker application
public class MainGUI extends MenuGUI {



    private static final String TITLE = "Food Tracker";


    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 5;

    private JButton saveButton = new JButton("Save");
    private JButton loadButton = new JButton("Load");
//    private JButton canCookButton = new JButton("Can Cook?");


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


    @Override
    public void updateSideList() {

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



    //Modifies: This
    //Effects: Creates the buttons for the Jframe
    protected void createCenterFrame() {
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);

        createBlankLabels(1);
        centerPanel.add(saveButton);
        createBlankLabels(1);
        centerPanel.add(loadButton);
        createBlankLabels(11);
    }

    private void createBlankLabels(int times) {
        for (int i = 0; i < times; i++) {
            centerPanel.add(new JLabel());
        }
    }






    @Override
    //Effects: Specifies the actions preformed by each button;
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            saveCookBook();
        } else if (e.getSource() == loadButton) {
            loadCookBook();
        }
    }

    // EFFECTS: saves the cookBook to JSON file
    private void saveCookBook() {
        try {
            gim.jsonWriter.open();
            gim.jsonWriter.write(gim.cookBook);
            gim.jsonWriter.close();
            System.out.println("Saved CookBook to" + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: gim.Cookbook
    // EFFECTS: loads CookBook from file, and apply it to all menus and food tracker app
    private void loadCookBook() {
        try {
            CookBook tempBook = gim.jsonReader.read();
            gim.setCookBook(tempBook);
            System.out.println("Loaded CookBook from:" + JSON_STORE);

        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}

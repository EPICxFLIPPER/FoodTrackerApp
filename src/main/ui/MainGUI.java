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

    private static final String burgerImageLoc = "./Images/cartoonBurger.jpg";
    private ImageIcon burgerImage;
    private JLabel burgerImageLabel;



    public MainGUI(GIManager gim) {

        this.gim = gim;
        active = false;

        init();
        setImages();
        createCenterFrame();

        createTitleLabel(TITLE);
        setActionListeners();

        setVisible(true);
        repaint();
    }


    @Override
    public void updateSideList() {

    }

    private void setImages() {
        try {
            burgerImage = new ImageIcon(burgerImageLoc);
        } catch (Exception e) {
            System.out.println("could not find image");
        }

        burgerImageLabel = new JLabel();
        burgerImageLabel.setIcon(burgerImage);

        southPanel.add(burgerImageLabel);
    }

    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    protected void init() {

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

    @Override
    //Modifies: This
    //Effects: Sets the action listeners for the features on the menu
    protected void setActionListeners() {
        saveButton.addActionListener(this);
        loadButton.addActionListener(this);
    }

    //Modifies: This
    //Effects: Creates the buttons for the Jframe
    protected void createCenterFrame() {
        createBlankLabels(1);
        centerPanel.add(saveButton);

        createBlankLabels(1);
        centerPanel.add(loadButton);
        createBlankLabels(11);
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

package ui;

import model.CookBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;

//Combines all JFrame aspects with cookbook to create GUI
public class GIManager extends JFrame {

    private MenuGUI homeGUI;
    protected MenuGUI pantryGUI;
    protected MenuGUI recipeGUI;

    protected CookBook cookBook;
    protected JsonReader jsonReader;
    protected JsonWriter jsonWriter;
    protected static final String JSON_STORE = "./data/cookbook.json";

    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel();

    private JMenuBar menuBar;


    protected static final int X_DIM = 800;
    protected static final int Y_DIM = 800;


    //Effects: Starts the GUI
    public GIManager() {
        super("Food Tracker App");
        init();
        pack();
        repaint();
    }

    //Modifies: This
    //Effects: preforms all the default operations necessary to create the GUI
    private void init() {
        setDefaults();
        initPanels();
        initContentPanel();
        createMenuBar();
        linkCookBook();
        setImageLogo();
    }

    //Modifies: This
    //Effects: Sets the image logo for the frame
    private void setImageLogo() {
        ImageIcon image = new ImageIcon("./Images/cartoonBurger.jpg");
        this.setIconImage(image.getImage());
    }

    //Modifies: this
    //Effects: adds the different frames to the content panel
    private void initContentPanel() {
        contentPanel.setLayout(cardLayout);
        contentPanel.add(homeGUI,"home");
        contentPanel.add(pantryGUI,"pantry");
        contentPanel.add(recipeGUI,"recipe");
        cardLayout.show(contentPanel,"home");
        add(contentPanel);
    }

    //Modifies: This
    //Effects: Constructs the Jpanels
    private void initPanels() {
        this.homeGUI = new HomeGUI(this);
        this.pantryGUI = new PantryGUI(this);
        this.recipeGUI = new RecipeGUI(this);
    }

    //Modifies: This
    //Effects: Sets the defaults for the JFrame
    private void setDefaults() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(X_DIM,Y_DIM);
        setResizable(true);
        setTitle("Food Tracker");
        setMinimumSize(new Dimension(X_DIM,Y_DIM));
        setVisible(true);
    }

    //Modifies: This
    //Effects: sets the cookbook and save and load features
    private void linkCookBook() {
        this.cookBook = new CookBook();
        this.jsonReader = new JsonReader(JSON_STORE);
        this.jsonWriter = new JsonWriter(JSON_STORE);
    }

    //Modifies: This
    //Effects: Sets this.cookbook to the given cookbook
    public void setCookBook(CookBook cookBook) {
        this.cookBook = cookBook;
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    protected void createMenuBar() {
        menuBar = new MenuBar(this);
        setJMenuBar(menuBar);
    }

    public void changeMenu(String menu) {
        cardLayout.show(contentPanel,menu);
        refresh();
    }

    //refreshes the sideLists on each of the Panels
    private void refresh() {
        recipeGUI.updateSideList();
        pantryGUI.updateSideList();
        homeGUI.updateSideList();
    }









}

package ui;

import model.CookBook;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Combines all JFrame aspects with cookbook to create GUI
public class GIManager extends JFrame implements MenuListener {

    private MenuGUI mainGUI;
    protected MenuGUI pantryGUI;
    protected MenuGUI recipeGUI;

    protected int active = 0;
    protected static final int MAIN_ACTIVE = 0;
    protected static final int PANTRY_ACTIVE = 1;
    protected static final int RECIPE_ACTIVE = 2;

    protected CookBook cookBook;
    protected JsonReader jsonReader;
    protected JsonWriter jsonWriter;
    protected static final String JSON_STORE = "./data/cookbook.json";

    private CardLayout cardLayout = new CardLayout();
    private JPanel contentPanel = new JPanel();

    protected JMenuBar menuBar = new JMenuBar();
    protected JMenu pantryMenu = new JMenu("Pantry");
    protected JMenu recipeMenu = new JMenu("Recipe");
    protected JMenu mainMenu = new JMenu("Main");

    protected static final int X_DIM = 800;
    protected static final int Y_DIM = 800;


    //Effects: Starts the GUI
    public GIManager() {
        super("Food Tracker App");
        init();
        add(contentPanel);
        setVisible(true);
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
        contentPanel.add(mainGUI,"main");
        contentPanel.add(pantryGUI,"pantry");
        contentPanel.add(recipeGUI,"recipe");
        cardLayout.show(contentPanel,"main");
    }

    //Modifies: This
    //Effects: Constructs the Jpanels
    private void initPanels() {
        this.mainGUI = new MainGUI(this);
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
        menuBar.add(pantryMenu);
        menuBar.add(recipeMenu);
        menuBar.add(mainMenu);

        setJMenuBar(menuBar);

        pantryMenu.addMenuListener(this);
        recipeMenu.addMenuListener(this);
        mainMenu.addMenuListener(this);



        pantryMenu.setMnemonic('P');
        recipeMenu.setMnemonic('R');
        mainMenu.setMnemonic('M');
    }

    @Override
    //Modifies: GIManager
    //Effects: Provides the actions for each individual selection from the menuBar
    public void menuSelected(MenuEvent e) {
        if (e.getSource() == mainMenu) {
            cardLayout.show(contentPanel,"main");
        } else if (e.getSource() == pantryMenu) {
            cardLayout.show(contentPanel,"pantry");
        } else if (e.getSource() == recipeMenu) {
            cardLayout.show(contentPanel,"recipe");
        }
        refresh();
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("deselect");
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("canceled");
    }

    //refreshes the sideLists on each of the Panels
    private void refresh() {
        recipeGUI.updateSideList();
        pantryGUI.updateSideList();
        mainGUI.updateSideList();
    }









}

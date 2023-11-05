package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class RecipeGUI extends MenuGUI {

    private static final String TITLE = "Recipe Menu";

    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 4;

    private JButton addRecipeButton = new JButton("Add Recipe");
    private JButton addIngredientButton = new JButton("Add Ingredient");
    private JButton removeRecipeButton = new JButton("Remove Recipe");


    private JTextField recipeName = new JTextField("Recipe Name");
    private JTextField recipeSelect = new JTextField("Recipe Name");
    private JTextField ingName = new JTextField("Name");
    private JTextField ingQty = new JTextField("Quantity (int)");
    private JTextField ingUnit = new JTextField("Units");

    private ArrayList<String> recipesList = new ArrayList<>();
    private JList<String> sideList = new JList<>();
    JScrollPane scrollPane = new JScrollPane(sideList);



    public RecipeGUI(GIManager gim) {
        super("Food Tracker");
        this.gim = gim;
        active = false;


        init();
        createMenuBar();
        createTitleLabel(TITLE);
        createCenterFrame();
        createSideBar();


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

    private void createSideBar() {
        sideList.setListData(recipesList.toArray(listInit));
        westPanel.add(scrollPane);
    }


    @Override
    protected void createCenterFrame() {
        addIngredientButton.addActionListener(this);
        addRecipeButton.addActionListener(this);
        removeRecipeButton.addActionListener(this);

        recipeSelect.addActionListener(this);

        centerPanel.add(new JLabel("Recipe Select:"));
        centerPanel.add(recipeSelect);
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        centerPanel.add(addRecipeButton);
        centerPanel.add(recipeName);
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        centerPanel.add(addIngredientButton);
        centerPanel.add(ingName);
        centerPanel.add(ingQty);
        centerPanel.add(ingUnit);

        centerPanel.add(removeRecipeButton);
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        menuBarActions(e);
    }

}

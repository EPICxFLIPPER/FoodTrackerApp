package ui;

import model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class PantryGUI extends MenuGUI {

    private static final String TITLE = "Pantry Menu";

    private static final int GRID_ROWS = 3;
    private static final int GRID_COLS = 4;

    private JButton addIngredientButton = new JButton("Add Ingredient");
    private JButton removeIngredientButton = new JButton("Remove Ingredient");


    private JTextField ingSelect = new JTextField("Ingredient Name");
    private JTextField ingName = new JTextField("Ingredient Name");
    private JTextField ingQty = new JTextField("Quantity (int)");
    private JTextField ingUnit = new JTextField("Units");

    private ArrayList<String> ingredientsList = new ArrayList<>();
    private JList<String> sideList = new JList<>();
    private JScrollPane scrollPane = new JScrollPane(sideList);




    public PantryGUI(GIManager gim) {
        super("Food Tracker");
        this.gim = gim;
        active = false;

        init();
        createMenuBar();
        createTitleLabel(TITLE);
        createCenterFrame();
        createSidebar();

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
    protected void createCenterFrame() {
        addIngredientButton.addActionListener(this);
        removeIngredientButton.addActionListener(this);


        centerPanel.add(new JLabel("Items Select:"));

        centerPanel.add(ingSelect);

        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        centerPanel.add(addIngredientButton);
        centerPanel.add(ingName);
        centerPanel.add(ingQty);
        centerPanel.add(ingUnit);

        centerPanel.add(removeIngredientButton);

        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());
        centerPanel.add(new JLabel());

        repaint();
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

    private void createSidebar() {
        sideList.setListData(ingredientsList.toArray(listInit));
        westPanel.add(scrollPane);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIngredientButton) {
            addIngredient();
        } else if (e.getSource() == removeIngredientButton) {
            removeIngredient();
        }
    }


    @Override
    public void updateSideList() {
        ArrayList<Ingredient> ingredients = gim.cookBook.getPantry().getIngredients();
        ingredientsList.clear();
        for (Ingredient i : ingredients) {
            ingredientsList.add(i.getName());
        }
        sideList.setListData(ingredientsList.toArray(listInit));
    }

    private void addIngredient() {
        String name = ingName.getText();
        int quantity = Integer.valueOf(ingQty.getText());
        String units = ingUnit.getText();
        Ingredient ingredient = new Ingredient(name,quantity,units);
        gim.cookBook.addToPantry(ingredient);
        updateSideList();
        resetTexts();
    }

    private void removeIngredient() {
        gim.cookBook.removeFromPantry(ingSelect.getText());
        updateSideList();
        resetTexts();
    }

    private void resetTexts() {
        ingName.setText("Ingredient Name");
        ingQty.setText("Quantity (int)");
        ingUnit.setText("Units");
        ingSelect.setText("Ingredient Name");
    }




}

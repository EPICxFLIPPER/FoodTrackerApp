package ui.gui;

import conversions.Unit;
import model.Ingredient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;

//Represents the Pantry Screen for the GUI
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

    private JComboBox units = new JComboBox<>();

    private Ingredient activeIngredient;
    private JButton activateIngredientButton = new JButton("Set Active Inredient");
    private JButton ingredientNameEdit = new JButton("Set Ingredient Name");
    private JButton ingredientQuantityEdit = new JButton("Set Ingredient Quantity");
    private JButton ingredientUnitsEdit = new JButton("Set Ingredient Units");



    //Effects: Constructs a Pantry screen with a title, buttons,
    //         and text fields to add user input
    public PantryGUI(GIManager gim) {

        this.gim = gim;
        active = false;
        this.activeIngredient = null;

        init();

        createTitleLabel(TITLE);
        createCenterFrame();
        createSidebar();
        setActionListeners();


        repaint();
    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    protected void init() {



        setLayout(new BorderLayout());

        initPanels();
        initUnitComboBox();
    }

    @Override
    //Modifies: this
    //Effects: Creates the center grid frame for this
    protected void createCenterFrame() {
        centerPanel.add(activateIngredientButton);
        centerPanel.add(ingSelect);
        createBlankLabels(2);
        centerPanel.add(addIngredientButton);
        centerPanel.add(ingName);
        centerPanel.add(ingQty);
        centerPanel.add(units);
        centerPanel.add(removeIngredientButton);
        centerPanel.add(ingredientNameEdit);
        centerPanel.add(ingredientQuantityEdit);
        centerPanel.add(ingredientUnitsEdit);
    }

    @Override
    //Modifies: this
    //Effects: Sets the action listeners for the buttons
    protected void setActionListeners() {
        addIngredientButton.addActionListener(this);
        removeIngredientButton.addActionListener(this);
        ingredientNameEdit.addActionListener(this);
        ingredientQuantityEdit.addActionListener(this);
        ingredientUnitsEdit.addActionListener(this);
        activateIngredientButton.addActionListener(this);
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
    //Effects: Creates the side bar for this
    private void createSidebar() {
        sideList.setListData(ingredientsList.toArray(listInit));
        westPanel.add(scrollPane);
    }


    @Override
    //Effects: Provides response for user events
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIngredientButton) {
            addIngredient();
        } else if (e.getSource() == removeIngredientButton) {
            removeIngredient();
        } else if (e.getSource() == activateIngredientButton) {
            activeIngredient = gim.cookBook.getPantry().getIngredients().get(ingSelect.getText());
        } else if (e.getSource() == ingredientNameEdit) {
            activeIngredient.setName(ingName.getText());
        } else if (e.getSource() == ingredientQuantityEdit) {
            activeIngredient.setQuantity(Integer.valueOf(ingQty.getText()));
        } else if (e.getSource() == ingredientUnitsEdit) {
            activeIngredient.setUnits((String) units.getSelectedItem());
        }
        updateSideList();
    }


    @Override
    //Modifies: this
    //Effects: updates the side list with the most current ingrident list
    public void updateSideList() {
        Collection<Ingredient> ingredients = gim.cookBook.getPantry().getIngredients().values();
        ingredientsList.clear();
        for (Ingredient i : ingredients) {
            ingredientsList.add(printIngredient(i));
        }
        sideList.setListData(ingredientsList.toArray(listInit));
    }

    private String printIngredient(Ingredient ingredient) {
        return ingredient.getName() + ": " + ingredient.getQuantity() + ", " + ingredient.getUnits();
    }

    //Modifies: Cookbook
    //Effects: Adds the ingredient to the cookbook based on user input
    private void addIngredient() {
        String name = ingName.getText();
        int quantity = Integer.valueOf(ingQty.getText());
        String unit = (String) units.getSelectedItem();
        Ingredient ingredient = new Ingredient(name,quantity,unit);
        gim.cookBook.addToPantry(ingredient);
        updateSideList();
        resetTexts();
    }

    //Modifies: CookBook
    //Effects: Removes the ingrident from the cookbook based on user input
    private void removeIngredient() {
        gim.cookBook.removeFromPantry(ingSelect.getText());
        updateSideList();
        resetTexts();
    }

    //Modifies: this
    //Effects: Resets the text fields to their initial state
    private void resetTexts() {
        ingName.setText("Ingredient Name");
        ingQty.setText("Quantity (int)");
        ingUnit.setText("Units");
        ingSelect.setText("Ingredient Name");
    }

    //Effects: Creates the combo box that stores units
    private void initUnitComboBox() {
        for (Unit u : Unit.values()) {
            units.addItem(u.toString());
        }
    }




}

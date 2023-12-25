package ui.gui;

import conversions.Unit;
import model.Ingredient;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

public class RecipeEditor extends MenuGUI implements ActionListener {

    private static final int GRID_ROWS = 4;
    private static final int GRID_COLS = 4;

    private GIManager gim;

    Ingredient activeIngredient;
    Recipe activeRecipe;

    private JList<String> westList = new JList<>();
    private JScrollPane recipesList = new JScrollPane(westList);

    private JList<String> eastList = new JList<>();
    private JScrollPane ingredientsList = new JScrollPane(eastList);

    private JButton activateRecipe = new JButton("Set Selected Recipe");
    private JTextField activeRecipeTextBox = new JTextField("active Recipe");

    private JButton activateIngredient = new JButton("Set Selected Ingredient");
    private JTextField activeIngreidnetTextBox = new JTextField("activeIngredient");

    private JButton removeIngredient = new JButton("Remove Ingredient");
    private JButton ingredientName = new JButton("Set Ingredient Name");
    private JButton ingredientQuantity = new JButton("Set Ingredient Quantity");
    private JButton ingredientUnits = new JButton("Set Ingredient Units");

    private JTextField ingName = new JTextField("Name");
    private JTextField ingQty = new JTextField("Quantity (int)");
    private JComboBox units = new JComboBox<>();




    public RecipeEditor(GIManager gim) {
        this.activeIngredient = null;
        this.activeRecipe = null;
        this.gim = gim;
        init();
        setVisible(true);
        repaint();
    }



    @Override
    protected void updateSideList() {
        Collection<Recipe> recipes = gim.cookBook.getRecipes().values();
        ArrayList<String> recipeNames = new ArrayList<>();

        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }

        westList.setListData(recipeNames.toArray(listInit));

        if (activeRecipe != null) {
            Collection<Ingredient> ingredients = activeRecipe.getIngredients().values();
            ArrayList<String> ingredientPrintOuts = new ArrayList<>();

            for (Ingredient i : ingredients) {
                recipeNames.add(printIngredient(i));
            }

            eastList.setListData(recipeNames.toArray(listInit));
        }
    }



    private String printIngredient(Ingredient ingredient) {
        return ingredient.getName() + ": " + ingredient.getQuantity() + ", " + ingredient.getUnits();
    }

    @Override
    protected void init() {
        setLayout(new BorderLayout());
        initPanels();
        initUnitComboBox();
        createCenterFrame();
        setActionListeners();
    }

    @Override
    protected void createCenterFrame() {
        centerPanel.add(activateRecipe);
        centerPanel.add(activeRecipeTextBox);
        createBlankLabels(2);

        centerPanel.add(activateIngredient);
        centerPanel.add(activeIngreidnetTextBox);
        createBlankLabels(3);

        centerPanel.add(ingName);
        centerPanel.add(ingQty);
        centerPanel.add(units);

        centerPanel.add(removeIngredient);
        centerPanel.add(ingredientName);
        centerPanel.add(ingredientQuantity);
        centerPanel.add(ingredientUnits);
    }

    @Override
    protected void setActionListeners() {
        activateRecipe.addActionListener(this);
        activeRecipeTextBox.addActionListener(this);
        activateIngredient.addActionListener(this);
        activeIngreidnetTextBox.addActionListener(this);
        removeIngredient.addActionListener(this);
        ingredientName.addActionListener(this);
        ingredientQuantity.addActionListener(this);
        ingredientUnits.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

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

    //Effects: Creates the combo box that stores units
    private void initUnitComboBox() {
        for (Unit u : Unit.values()) {
            units.addItem(u.toString());
        }
    }
}

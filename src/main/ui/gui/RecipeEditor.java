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

    private ArrayList<String> recipes = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();

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
        recipes.clear();
        ingredients.clear();

        Collection<Recipe> recipesFromBook = gim.cookBook.getRecipes().values();


        for (Recipe r : recipesFromBook) {
            recipes.add(r.getName());
        }

        westList.setListData(recipes.toArray(listInit));

        if (activeRecipe != null) {
            Collection<Ingredient> ingredientsFromBook = activeRecipe.getIngredients().values();

            for (Ingredient i : ingredientsFromBook) {
                ingredients.add(printIngredient(i));
            }

            eastList.setListData(ingredients.toArray(listInit));
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
        westPanel.add(recipesList);

        eastPanel.add(ingredientsList);
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

        activateIngredient.addActionListener(this);

        removeIngredient.addActionListener(this);
        ingredientName.addActionListener(this);
        ingredientQuantity.addActionListener(this);
        ingredientUnits.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == activateRecipe) {
            activeRecipe = gim.cookBook.getRecipe(activeRecipeTextBox.getText());
        } else if (e.getSource() == activateIngredient) {
            activeIngredient = activeRecipe.getIngredients().get(activeIngreidnetTextBox.getText());
        } else if (e.getSource() == removeIngredient) {
            activeRecipe.removeIngredient(activeIngredient.getName());

        } else if (e.getSource() == ingredientName) {
            activeIngredient.setName(ingName.getText());

        } else if (e.getSource() == ingredientQuantity) {
            activeIngredient.setQuantity(Integer.valueOf(ingQty.getText()));

        } else if (e.getSource() == ingredientUnits) {
            activeIngredient.setUnits((String) units.getSelectedItem());

        }
        updateSideList();

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

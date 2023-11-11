package ui;

import model.Ingredient;
import model.Recipe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

//Represents the Recipe Screen for the GUI
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
    private JList<String> westList = new JList<>();
    private JScrollPane scrollPaneWest = new JScrollPane(westList);

    private JList<String> eastList = new JList<>();
    private JScrollPane scrollPaneEast = new JScrollPane(eastList);

    private JButton canCookButton = new JButton("Can Cook?");
    private JLabel cookResultLabel = new JLabel("No Input Yet!");





    public RecipeGUI(GIManager gim) {

        this.gim = gim;
        active = false;



        init();

        createTitleLabel(TITLE);
        createCenterFrame();
        createSideBars();
        setActionListeners();



        repaint();
    }

    @Override
    public void updateSideList() {
        ArrayList<Recipe> recipes = gim.cookBook.getRecipes();
        ArrayList<String> recipeNames = new ArrayList<>();
        //recipesList.clear();
        for (Recipe r : recipes) {
            recipeNames.add(r.getName());
        }
        westList.setListData(recipeNames.toArray(listInit));
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

    private void createSideBars() {
        westList.setListData(recipesList.toArray(listInit));
        westPanel.add(scrollPaneWest);

        eastPanel.add(scrollPaneEast);
    }


    @Override
    protected void createCenterFrame() {


        centerPanel.add(new JLabel("Recipe Select:"));
        centerPanel.add(recipeSelect);
        centerPanel.add(canCookButton);
        centerPanel.add(cookResultLabel);

        centerPanel.add(addRecipeButton);
        centerPanel.add(recipeName);
        createBlankLabels(2);

        centerPanel.add(addIngredientButton);
        centerPanel.add(ingName);
        centerPanel.add(ingQty);
        centerPanel.add(ingUnit);

        centerPanel.add(removeRecipeButton);
        createBlankLabels(3);


    }

    @Override
    //Modifies: This
    //Effects: Sets the action listeners for the features on the menu
    protected void setActionListeners() {
        addIngredientButton.addActionListener(this);
        addRecipeButton.addActionListener(this);
        removeRecipeButton.addActionListener(this);
        canCookButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addIngredientButton) {
            addIngredientToRecipe();
        } else if (e.getSource() == addRecipeButton) {
            addRecipe();
        } else if (e.getSource() == removeRecipeButton) {
            removeRecipe();
        } else if (e.getSource() == canCookButton) {
            canCook();
        }
        updateSideList();
    }

    private void addRecipe() {
        String name = recipeName.getText();
        Recipe recipe = new Recipe(name);
        gim.cookBook.addRecipe(recipe);
    }

    private void addIngredientToRecipe() {
        String name = ingName.getText();
        int quantity = Integer.valueOf(ingQty.getText());
        String units = ingUnit.getText();

        Ingredient ingredient = new Ingredient(name,quantity,units);

        String recipeName = recipeSelect.getText();
        ArrayList<Recipe> recipes = gim.cookBook.getRecipes();

        for (Recipe r : recipes) {
            if (r.getName().equals(recipeName)) {
                r.addIngredient(ingredient);
            }
        }

    }

    private void removeRecipe() {
        String recipeName = recipeSelect.getText();
        gim.cookBook.removeRecipe(recipeName);
    }

    private void canCook() {
        ArrayList<String> items = gim.cookBook.itemsToCook(recipeSelect.getText());
        if (items.size() == 0) {
            cookResultLabel.setText("You can cook this!");
        } else {
            cookResultLabel.setText("Missing Ingredients!");
        }

        eastList.setListData(items.toArray(listInit));
    }


}

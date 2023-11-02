package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//Represents the GUI for the Food Tracker application
public class FoodTrackerGUI extends JFrame implements ActionListener {

    private static final String TITLE = "Food Tracker";
    private static final int X_DIM = 800;
    private static final int Y_DIM = 800;
    private static final int FONT_SIZE = 20;
    private static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);



    private JMenuBar menuBar;
    private JLabel titleLabel;
    private JButton recipeMenuButton = new JButton();
    private JButton pantryMenuButton = new JButton();
    private JButton canCookButton = new JButton();




    public FoodTrackerGUI() {
        super(TITLE);

        init();
        createMenuBar();
        createTitleLabel();
        createButtons();

        pack();
        repaint();

    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(X_DIM,Y_DIM);
        setResizable(false);
        setTitle(TITLE);

        setMinimumSize(new Dimension(X_DIM,Y_DIM));
        setLayout(null);
    }

    //Modifies: This
    //Effects: Creates the menu bar for the JFrame
    private void createMenuBar() {
        menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File v");
        JMenu pantryMenu = new JMenu("Pantry");
        JMenu recipeMenu = new JMenu("Recipe");
        JMenu mainMenu = new JMenu("Main");

        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem loadItem = new JMenuItem("Load");

        fileMenu.add(saveItem);
        fileMenu.add(loadItem);

        menuBar.add(fileMenu);
        menuBar.add(pantryMenu);
        menuBar.add(recipeMenu);
        menuBar.add(mainMenu);

        setJMenuBar(menuBar);
    }

    //Modifies: This
    //Effects: Creates the title label
    private void createTitleLabel() {
        titleLabel = new JLabel(TITLE);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setFont(COOPER_BLACK);


        this.add(titleLabel);
    }

    //Modifes: This
    //Efffects: Creates the buttons for the Jframe
    private void createButtons() {
        createButton(canCookButton,"Can Cook?",100,200,200,200);
        createButton(recipeMenuButton,"Recipe Menu",X_DIM * 3 / 4,Y_DIM / 2,200,200);
        createButton(pantryMenuButton,"Pantry Menu",X_DIM / 4,Y_DIM / 2,200,400);


    }

    //Effects: sets the button defaults to the given button
    private void createButton(JButton button, String label,int x,int y,int width, int height) {




        button.setSize(x,y);
        button.setText(label);
        button.addActionListener(this);
        button.setFocusable(false);


        JPanel panel = new JPanel();
        panel.setBounds(x,y,x,y);
        panel.setBackground(Color.green);


        panel.add(button);
        panel.setLayout(null);

        this.add(panel);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}

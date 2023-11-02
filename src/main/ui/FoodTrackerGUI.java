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


    private JPanel centerPanel;
    private JPanel northPanel;

    private JMenuBar menuBar;
    private JLabel titleLabel;
    private JButton recipeMenuButton = new JButton();
    private JButton pantryMenuButton = new JButton();
    private JButton canCookButton = new JButton();




    public FoodTrackerGUI() {
        super(TITLE);


        init();
        //createButtons();
        createMenuBar();
        createTitleLabel();



        pack();
        repaint();
        setVisible(true);

    }


    //Modifies: This
    //Effects: Sets the initial settings of the JFrame
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
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

        centerPanel.setLayout(new GridLayout(2,5));
        northPanel.setLayout(new FlowLayout());

        this.add(northPanel,BorderLayout.NORTH);
        this.add(centerPanel,BorderLayout.CENTER);
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
        northPanel.setBackground(Color.green);

        titleLabel = new JLabel(TITLE);
        titleLabel.setHorizontalTextPosition(JLabel.CENTER);
        titleLabel.setVerticalAlignment(JLabel.TOP);
        titleLabel.setFont(COOPER_BLACK);

        northPanel.add(titleLabel);

        repaint();
    }

    //Modifes: This
    //Effects: Creates the buttons for the Jframe
    private void createButtons() {
        createButton(canCookButton,"Can Cook?",100,200,200,200);
//        createButton(recipeMenuButton,"Recipe Menu",X_DIM * 3 / 4,Y_DIM / 2,200,200);
//        createButton(pantryMenuButton,"Pantry Menu",X_DIM / 4,Y_DIM / 2,200,400);
        repaint();

    }

    //Effects: sets the button defaults to the given button
    private void createButton(JButton button, String label,int x,int y,int width, int height) {

        button.setBounds(x,y,width,height);
        button.addActionListener(this);
        button.setText(label);
        button.setFocusable(false);
        this.add(button);



    }

    @Override
    //Effects: Specifies the actions preformed by each button;
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == recipeMenuButton) {
            System.out.println("recipe");
        } else if (e.getSource() == pantryMenuButton) {
            System.out.println("pantry");
        } else if (e.getSource() == canCookButton) {
            System.out.println("cook");
        }
    }

}

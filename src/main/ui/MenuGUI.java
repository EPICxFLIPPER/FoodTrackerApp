package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class MenuGUI extends JFrame implements ActionListener {

    protected GIManager gim;

    protected static final int X_DIM = 800;
    protected static final int Y_DIM = 800;

    protected static final int FONT_SIZE = 20;
    protected static final Font COOPER_BLACK = new Font("Cooper Black",Font.PLAIN,FONT_SIZE);

    protected JPanel centerPanel;
    protected JPanel northPanel;
    protected JPanel southPanel;
    protected JPanel eastPanel;
    protected JPanel westPanel;

    protected JMenuBar menuBar;
    protected JLabel titleLabel;


    public MenuGUI(String title) {
        super(title);
    }

    public abstract void activate();

    public abstract void deactivate();



}

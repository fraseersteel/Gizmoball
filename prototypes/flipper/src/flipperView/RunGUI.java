
package flipperView;

import flipperController.RunListener;
import flipperController.BoardListener;
import flipperModel.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public class RunGUI {

    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);
    private Model model;
    private JFrame jFrame;
    private ActionListener listener;
    private KeyListener keyListener;
    private Board board;

    private JPanel playZone;
    private Container cp;
    private GridLayout controlButtons;
    private FlowLayout flow;

    private GridLayout runGrid;

    private final int cellDimension = 25;
    private final int cellAmount = 20;

    public RunGUI(Model model){

        this.model = model;
        listener = new RunListener(model);
        keyListener = new BoardListener(model);

        initialise();
        rightZone();
        menu();

        jFrame.setVisible(true);
        //jFrame.pack();

        //board.paintComponent(Graphics2D.g);
        jFrame.pack();

    }

    private void initialise() {
        jFrame = new JFrame();
        flow = new FlowLayout();
        jFrame.setLayout(flow);

        jFrame.setTitle("Gizmoball");

        jFrame.setSize(WINDOW_SIZE);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        cp = new Container();
        //playZone = new JPanel();

        int totalRes = cellDimension*cellAmount;

        playZone = new Board(model, cellDimension);
        playZone.addKeyListener(keyListener);
        jFrame.addKeyListener(keyListener);
        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(totalRes,totalRes);
        playZone.setPreferredSize(runDimension);

        cp.setLayout(new GridLayout( 0,1));
        //playZone.setLayout(new GridLayout(21,21));
        cp.setPreferredSize(new Dimension(100,550));



        Container pane = jFrame.getContentPane();

        pane.add(cp,BorderLayout.LINE_START);
        pane.add(playZone,BorderLayout.CENTER);


        jFrame.pack();
        jFrame.setResizable(false);

    }




    private void rightZone() {
        //JButton but = new JButton();
        //playZone.add(but);




    }

    private void menu() {
        //Create menu bar then populate it
        JMenuBar menuBar = new JMenuBar();

        //Create menu
        JMenu menu = new JMenu("File");

        //Create all menu items
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(listener);

        JMenuItem load = new JMenuItem("Load");

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(listener);

        JMenuItem reset = new JMenuItem("Reset");
        reset.addActionListener(listener);

        //Construct menu bar
        jFrame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(save);
        menu.add(load);
        menu.add(reset);
        menu.addSeparator();
        menu.add(exit);
    }

}
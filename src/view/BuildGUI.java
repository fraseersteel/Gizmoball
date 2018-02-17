
package view;

import controller.LoadListener;
import controller.RunListener;
import model.Model;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class BuildGUI {

    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);
    private Model model;
    private JFrame jFrame;
    private ActionListener listener;
    private Board board;

    private JPanel playZone;
    private Container cp;
    private Container cp2;
    private GridLayout controlButtons;
    private FlowLayout flow;

    private GridLayout runGrid;

    public BuildGUI(Model model){

        this.model = model;
        listener = new RunListener(model);

        initialise();
        leftZone();
        rightZone();
        editZone();
        menu();

        jFrame.setVisible(true);
        //jFrame.pack();

        //board.paintComponent(Graphics2D.g);

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

        cp2 = new Container();

        playZone = new Board(500,500,model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(playZone.getWidth(),playZone.getHeight());
        playZone.setPreferredSize(runDimension);

        cp.setLayout(new GridLayout( 0,1));
        cp2.setLayout(new GridLayout(1,0));
        //playZone.setLayout(new GridLayout(21,21));
        cp.setPreferredSize(new Dimension(100,550));



        Container pane = jFrame.getContentPane();

        pane.add(cp,BorderLayout.LINE_START);
        pane.add(playZone,BorderLayout.CENTER);
        pane.add(cp2, BorderLayout.AFTER_LINE_ENDS);

        jFrame.pack();
        jFrame.setResizable(true);

    }

    public void editZone() {

        //Create grid and populate with buttons
        controlButtons = new GridLayout(1,4);
        JButton move = new JButton("Move");
        JButton rotate = new JButton("Rotate");
        JButton delete = new JButton("Delete");
        JButton connect = new JButton("Connect");


        move.addActionListener(listener);
        rotate.addActionListener(listener);
        delete.addActionListener(listener);
        connect.addActionListener(listener);

        cp2 = new Container();

        cp2.add(move);
        cp2.add(rotate);
        cp2.add(delete);
        cp2.add(connect);

    }


    private void leftZone() {

        //Create grid and populate with buttons
        controlButtons = new GridLayout(5,1);
        JButton square = new JButton("Square");
        JButton stop = new JButton("Stop");
        JButton circle = new JButton("Circle");
        JButton leftFlipper = new JButton("Left Flipper");
        JButton rightFlipper = new JButton("Right Flipper");


        square.addActionListener(listener);
        stop.addActionListener(listener);
        circle.addActionListener(listener);
        leftFlipper.addActionListener(listener);
        rightFlipper.addActionListener(listener);

        cp.add(square);
        cp.add(stop);
        cp.add(circle);
        cp.add(leftFlipper);
        cp.add(rightFlipper);

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
        load.addActionListener(new LoadListener());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(listener);

        //Construct menu bar
        jFrame.setJMenuBar(menuBar);
        menuBar.add(menu);
        menu.add(save);
        menu.add(load);
        menu.addSeparator();
        menu.add(exit);
    }

    public static void main(String[] args) {
        Model m = new Model();
        BuildGUI gui = new BuildGUI(m);
    }

}
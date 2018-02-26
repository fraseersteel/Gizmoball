package view;

import controller.EditListener;
import controller.GizmoListener;
import controller.LoadListener;
import controller.RunListener;
import model.Model;

import javax.swing.*;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI {

    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);
    private Model model;
    private JFrame runFrame;
    private JFrame buildFrame;
    private Board board;

    private RunListener runListener;
    private JPanel playZone;
    private JPanel playZoneBoard;
    private Container runButtons;
    private Container gizmoButtons;
    private Container editButtons;
    private FlowLayout flow;


    public GUI(Model model) {

        this.model = model;
        runListener = new RunListener(model,this);

        initialiseRun();
        initialiseBuild();
        runZoneButtons();
        buildMenuBar();
        runMenuBar();
        leftZone();
        buildEditButtons();
        runFrame.setVisible(true);
        buildFrame.setVisible(false);
        runFrame.pack();
        buildFrame.pack();

        //board.paintComponent(Graphics2D.g);

    }

    private void initialiseBuild() {

        buildFrame = new JFrame();
        flow = new FlowLayout();
        buildFrame.setLayout(flow);

        buildFrame.setTitle("Gizmoball - BUILD MODE");

        buildFrame.setSize(WINDOW_SIZE);
        buildFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gizmoButtons = new Container();
        //playZone = new JPanel();

        editButtons = new Container();

        playZoneBoard = new BuildBoard(500, 500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(playZoneBoard.getWidth(), playZoneBoard.getHeight());
        playZoneBoard.setPreferredSize(runDimension);

        gizmoButtons.setLayout(new GridLayout(0, 1));
        editButtons.setLayout(new GridLayout(0, 1));
        //playZone.setLayout(new GridLayout(21,21));
        editButtons.setPreferredSize(new Dimension(100, 580));


        Container pane1 = buildFrame.getContentPane();

        pane1.add(gizmoButtons, BorderLayout.LINE_START);
        pane1.add(playZoneBoard, BorderLayout.CENTER);
        pane1.add(editButtons, BorderLayout.PAGE_END);

        buildFrame.pack();
        buildFrame.setResizable(false);


    }

    private void runZoneButtons() {
        //Create grid and populate with buttons
        GridLayout grid = new GridLayout(4, 1);
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton tick = new JButton("Tick");
        JButton quit = new JButton("Quit");

        start.addActionListener(runListener);
        stop.addActionListener(runListener);
        tick.addActionListener(runListener);
        quit.addActionListener(runListener);

        runButtons.add(start);
        runButtons.add(stop);
        runButtons.add(tick);
        runButtons.add(quit);

        grid.addLayoutComponent("buttons",runButtons);

    }


    public void buildEditButtons() {

        //Create grid and populate with buttons
        JButton move = new JButton("Move");
        JButton rotate = new JButton("Rotate");
        JButton delete = new JButton("Delete");
        JButton connect = new JButton("Connect");

        EditListener editListener = new EditListener(model);

        move.addActionListener(editListener);
        rotate.addActionListener(editListener);
        delete.addActionListener(editListener);
        connect.addActionListener(editListener);


        editButtons.add(move);
        editButtons.add(rotate);
        editButtons.add(delete);
        editButtons.add(connect);

        editButtons.setPreferredSize(new Dimension(100, 400));

    }

    private void leftZone() {

        //Create grid and populate with buttons
        JButton square = new JButton("SquareGizmo");
        JButton triangle = new JButton("TriangleGizmo");
        JButton circle = new JButton("Circle");
        JButton leftFlipper = new JButton("Left Flipper");
        JButton rightFlipper = new JButton("Right Flipper");


        GizmoListener gizmoListener = new GizmoListener(model);

        square.addActionListener(gizmoListener);
        triangle.addActionListener(gizmoListener);
        circle.addActionListener(gizmoListener);
        leftFlipper.addActionListener(gizmoListener);
        rightFlipper.addActionListener(gizmoListener);

        gizmoButtons.add(square);
        gizmoButtons.add(triangle);
        gizmoButtons.add(circle);
        gizmoButtons.add(leftFlipper);
        gizmoButtons.add(rightFlipper);


        gizmoButtons.setPreferredSize(new Dimension(100, 400));

    }

    private void runMenuBar() {



        //Create menu bar then populate it
        JMenuBar runMenu = new JMenuBar();

        //Create menu
        JMenu menu = new JMenu("File");

        //Create all menu items
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(runListener);

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoadListener());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(runListener);

        //Construct menu bar
        runFrame.setJMenuBar(runMenu);

        runMenu.add(menu);
        menu.add(save);
        menu.add(load);
        menu.addSeparator();

        menu.add(exit);

        JMenu switch_mode = new JMenu("Switch Mode");
        runMenu.add(switch_mode);
        JMenuItem buildMode = new JMenuItem("Build Mode");
        switch_mode.add(buildMode);
        buildMode.addActionListener(runListener);
    }

    private void buildMenuBar(){

        JMenuBar buildMenu = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(runListener);

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoadListener());

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(runListener);

        buildFrame.setJMenuBar(buildMenu);

        buildMenu.add(menu);
        menu.add(save);
        menu.add(load);
        menu.addSeparator();

        menu.add(exit);

        JMenu switch_mode = new JMenu("Switch Mode");
        buildMenu.add(switch_mode);
        JMenuItem runMode = new JMenuItem("Run Mode");
        switch_mode.add(runMode);
        runMode.addActionListener(runListener);
    }


    private void initialiseRun() {
        runFrame = new JFrame();
        flow = new FlowLayout();
        runFrame.setLayout(flow);

        runFrame.setTitle("Gizmoball");

        runFrame.setSize(WINDOW_SIZE);
        runFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        runButtons = new Container();
        //playZone = new JPanel();

        playZone = new Board(500, 500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(playZone.getWidth(), playZone.getHeight());
        playZone.setPreferredSize(runDimension);

        runButtons.setLayout(new GridLayout(0, 1));
        //playZone.setLayout(new GridLayout(21,21));
        runButtons.setPreferredSize(new Dimension(100, 550));

        Container pane2 = runFrame.getContentPane();

        pane2.add(runButtons, BorderLayout.LINE_START);
        pane2.add(playZone, BorderLayout.CENTER);

        runFrame.pack();
        runFrame.setResizable(false);

    }


    public void switchToRunMode(){

        runFrame.setVisible(true);
        buildFrame.setVisible(false);
    }

    public void switchToBuildMode(){

        runFrame.setVisible(false);
        buildFrame.setVisible(true);
    }


}

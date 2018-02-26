//
//package view;
//
//import controller.EditListener;
//import controller.GizmoListener;
//import controller.LoadListener;
//import controller.RunListener;
//import model.Model;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//
//public class BuildGUI {
//
//    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);
//    private Model model;
//    private JFrame jFrame;
//    private Board board;
//
//    private JPanel playZone;
//    private Container cp;
//    private Container cp2;
//    private GridLayout controlButtons;
//    private FlowLayout flow;
//    private RunGUI runGUI;
//
//    private GridLayout runGrid;
//
//    public BuildGUI(Model model) {
//
//        this.model = model;
//
//
//        initialise();
//        leftZone();
//        editZone();
//        menu();
//        jFrame.setVisible(false);
//        jFrame.pack();
//
//        //board.paintComponent(Graphics2D.g);
//
//    }
//
//    private void initialise() {
//        jFrame = new JFrame();
//        flow = new FlowLayout();
//        jFrame.setLayout(flow);
//
//        jFrame.setTitle("Gizmoball - BUILD MODE");
//
//        jFrame.setSize(WINDOW_SIZE);
//        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        cp = new Container();
//        //playZone = new JPanel();
//
//        cp2 = new Container();
//
//        playZone = new Board(500, 500, model);
//
//        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
//        Dimension runDimension = new Dimension(playZone.getWidth(), playZone.getHeight());
//        playZone.setPreferredSize(runDimension);
//
//        cp.setLayout(new GridLayout(0, 1));
//        cp2.setLayout(new GridLayout(0, 1));
//        //playZone.setLayout(new GridLayout(21,21));
//        cp.setPreferredSize(new Dimension(100, 580));
//
//
//        Container pane = jFrame.getContentPane();
//
//        pane.add(cp, BorderLayout.LINE_START);
//        pane.add(playZone, BorderLayout.CENTER);
//        pane.add(cp2, BorderLayout.PAGE_END);
//
//        jFrame.pack();
//        jFrame.setResizable(false);
//
//    }
//
//    public void editZone() {
//
//        //Create grid and populate with buttons
//        JButton move = new JButton("Move");
//        JButton rotate = new JButton("Rotate");
//        JButton delete = new JButton("Delete");
//        JButton connect = new JButton("Connect");
//
//        EditListener editListener = new EditListener(model);
//
//        move.addActionListener(editListener);
//        rotate.addActionListener(editListener);
//        delete.addActionListener(editListener);
//        connect.addActionListener(editListener);
//
//
//        cp2.add(move);
//        cp2.add(rotate);
//        cp2.add(delete);
//        cp2.add(connect);
//
//        cp2.setPreferredSize(new Dimension(100, 400));
//
//    }
//
//
//    private void leftZone() {
//
//        //Create grid and populate with buttons
//        JButton square = new JButton("SquareGizmo");
//        JButton triangle = new JButton("TriangleGizmo");
//        JButton circle = new JButton("Circle");
//        JButton leftFlipper = new JButton("Left Flipper");
//        JButton rightFlipper = new JButton("Right Flipper");
//
//
//        GizmoListener gizmoListener = new GizmoListener(model);
//
//        square.addActionListener(gizmoListener);
//        triangle.addActionListener(gizmoListener);
//        circle.addActionListener(gizmoListener);
//        leftFlipper.addActionListener(gizmoListener);
//        rightFlipper.addActionListener(gizmoListener);
//
//        cp.add(square);
//        cp.add(triangle);
//        cp.add(circle);
//        cp.add(leftFlipper);
//        cp.add(rightFlipper);
//
//
//        cp.setPreferredSize(new Dimension(100, 400));
//
//    }
//
//
//    private void menu() {
//        //Create menu bar then populate it
//        JMenuBar menuBar = new JMenuBar();
//
//        //Create menu buttons
//        JMenu menu = new JMenu("File");
//        JMenuItem save = new JMenuItem("Save");
//        JMenuItem load = new JMenuItem("Load");
//        JMenuItem exit = new JMenuItem("Exit");
//        menu.add(save);
//        menu.add(load);
//        menu.add(exit);
//        //Create all menu items
//
//
//        LoadListener loadListener = new LoadListener();
//
//        save.addActionListener(runListener);
//        load.addActionListener(loadListener);
//        exit.addActionListener(runListener);
//
//
//        //Construct menu bar
//        jFrame.setJMenuBar(menuBar);
//        menuBar.add(menu);
//        menu.add(save);
//        menu.add(load);
//        menu.addSeparator();
//        menu.add(exit);
//
//
//
//        JMenu switch_mode = new JMenu("Switch Mode");
//        menuBar.add(switch_mode);
//        JMenuItem buildMode = new JMenuItem("Run Mode");
//        switch_mode.add(buildMode);
//        buildMode.addActionListener(runListener);
//    }
//
//
//    public void setVisible(){
//        jFrame.setVisible(true);
//    }
//
//    public void hideFrame(){
//        jFrame.setVisible(false);
//    }
//
//}
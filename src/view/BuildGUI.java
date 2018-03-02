package view;

import controller.EditListener;
import controller.GizmoListener;
import controller.LoadListener;
import controller.RunListener;
import model.Model;

import javax.swing.*;
import java.awt.*;

class BuildGUI extends GUI {

    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);

    private JPanel buildBoard;
    private Container gizmoButtons;
    private Container editButtons;
    private FlowLayout flow;

    private RunListener runListener;

    public BuildGUI(Model model) {
        super(model);
        runListener = new RunListener(model,this);

        initilise();
        buildMenuBar();
        leftZone();
        buildEditButtons();

        jframe.pack();
        jframe.setVisible(true);
    }

    private void initilise() {

        jframe = new JFrame();
        flow = new FlowLayout();
        jframe.setLayout(flow);

        jframe.setTitle("Gizmoball - BUILD MODE");

        jframe.setSize(WINDOW_SIZE);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gizmoButtons = new Container();
        editButtons = new Container();

        buildBoard = new BuildBoard(500, 500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(buildBoard.getWidth(), buildBoard.getHeight());
        buildBoard.setPreferredSize(runDimension);

        gizmoButtons.setLayout(new GridLayout(0, 1));
        editButtons.setLayout(new GridLayout(0, 1));
        //playZone.setLayout(new GridLayout(21,21));
        editButtons.setPreferredSize(new Dimension(100, 580));

        Container pane1 = jframe.getContentPane();

        pane1.add(gizmoButtons, BorderLayout.LINE_START);
        pane1.add(buildBoard, BorderLayout.CENTER);
        pane1.add(editButtons, BorderLayout.PAGE_END);

        jframe.pack();
        jframe.setResizable(false);

    }

    private void buildEditButtons() {

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

    private void buildMenuBar(){

        JMenuBar buildMenu = new JMenuBar();
        JMenu menu = new JMenu("File");

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(runListener);

        JMenuItem load = new JMenuItem("Load");
        load.addActionListener(new LoadListener(model, buildBoard));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(runListener);

        jframe.setJMenuBar(buildMenu);

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

}


package view;

import controller.*;
import model.Model;

import javax.swing.*;
import java.awt.*;

public class RunGUI extends GUI {

    private final static Dimension WINDOW_SIZE = new Dimension(800, 700);

    private RunListener runListener;
    private JPanel playZone;
    private Container runButtons;
    private FlowLayout flow;

    public RunGUI(Model model) {
        super(model);

        runListener = new RunListener(model,this);

        initialise();
        runZoneButtons();
        runMenuBar();

        jframe.setVisible(true);
        jframe.pack();

    }

    private void initialise() {
        jframe = new JFrame();
        flow = new FlowLayout();
        jframe.setLayout(flow);

        jframe.addKeyListener(new RunKeyListener(model));

        jframe.setTitle("Gizmoball");

        jframe.setSize(WINDOW_SIZE);
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        runButtons = new Container();
        //playZone = new JPanel();

        playZone = new Board(500, 500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(playZone.getWidth(), playZone.getHeight());
        playZone.setPreferredSize(runDimension);

        runButtons.setLayout(new GridLayout(0, 1));
        //playZone.setLayout(new GridLayout(21,21));
        runButtons.setPreferredSize(new Dimension(100, 550));

        Container pane2 = jframe.getContentPane();

        pane2.add(runButtons, BorderLayout.LINE_START);
        pane2.add(playZone, BorderLayout.CENTER);

        jframe.pack();
        jframe.setResizable(false);

    }

    private void runZoneButtons() {
        //Create grid and populate with buttons
        GridLayout grid = new GridLayout(4, 1);
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton tick = new JButton("Tick");
        JButton quit = new JButton("Quit");


        start.addActionListener(runListener);
        start.setFocusable(false);
        stop.addActionListener(runListener);
        stop.setFocusable(false);
        tick.addActionListener(runListener);
        tick.setFocusable(false);
        quit.addActionListener(runListener);
        quit.setFocusable(false);

        runButtons.add(start);
        runButtons.add(stop);
        runButtons.add(tick);
        runButtons.add(quit);

        grid.addLayoutComponent("buttons",runButtons);

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
        load.addActionListener(new LoadListener(model, playZone));

        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(runListener);

        //Construct menu bar
        jframe.setJMenuBar(runMenu);

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


}

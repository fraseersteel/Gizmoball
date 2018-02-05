package view;

import controller.RunListener;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class RunGUI {

    private Model model;
    private JFrame jFrame;
    private ActionListener listener;
    private Board board;

    private JPanel controlZone, playZone;
    private GridLayout controlButtons;
    private FlowLayout flow;

    private GridLayout runGrid;

    public RunGUI(Model model){

        this.model = model;
        listener = new RunListener(model);

        initialise();
        leftZone();
        rightZone();

        controlZone.setAlignmentX(100);
        jFrame.setVisible(true);
        //jFrame.pack();

        //board.paintComponent(Graphics2D.g);

    }

    private void initialise() {
        jFrame = new JFrame();
        flow = new FlowLayout();
        jFrame.setLayout(flow);

        jFrame.setTitle("Gizmoball");

        jFrame.setSize(800, 600);

        controlZone = new JPanel();
        //playZone = new JPanel();

        playZone = new Board(500,500, model);

        // Gizmo board is 25x25 pixels each zone (doesnt work atm)
        Dimension runDimension = new Dimension(500,500);
        playZone.setPreferredSize(runDimension);

        controlZone.setLayout(new GridLayout( 0,1));
        //playZone.setLayout(new GridLayout(21,21));

        playZone.setBackground(Color.BLACK);

        jFrame.add(controlZone);
        jFrame.add(playZone);
    }


    private void leftZone() {
        controlButtons = new GridLayout(4,1);
        JButton start = new JButton("Start");
        JButton stop = new JButton("Stop");
        JButton tick = new JButton("Tick");
        JButton quit = new JButton("Quit");

        start.addActionListener(listener);
        stop.addActionListener(listener);
        tick.addActionListener(listener);
        quit.addActionListener(listener);

        controlZone.add(start);
        controlZone.add(stop);
        controlZone.add(tick);
        controlZone.add(quit);

    }

    private void rightZone() {
        //JButton but = new JButton();
        //playZone.add(but);




    }

    private void menu() {


    }

}

package controller;

import model.Ball;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GizmoListener implements ActionListener{

    private Model model;
    private BuildBoard board;
    private KeyListener keyboardListener;
    private GizmoPlaceListener gizmoPlaceListener;
    private BuildGUI buildGUI;

    public GizmoListener(Model m, BuildBoard board,BuildGUI gui){
        this.model = m;
        this.board = board;
        this.gizmoPlaceListener = null;
        this.buildGUI = gui;
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        switch(e.getActionCommand()){
            case "Ball":
                updatePlaceListener(0);
                buildGUI.getLabel().setText("Adding ball");
                break;
            case "SquareGizmo":
                updatePlaceListener(1);
                buildGUI.getLabel().setText("Adding Square");
                break;
            case "Circle":
                updatePlaceListener(2);
                System.out.println("pressed circle");
                buildGUI.getLabel().setText("Adding Circle");
                break;
            case "TriangleGizmo":
                updatePlaceListener(3);
                System.out.println("pressed triangle");
                buildGUI.getLabel().setText("Adding Triangle");
                break;
            case "Left Flipper":
                updatePlaceListener(4);
                System.out.println("pressed left flipper");
                buildGUI.getLabel().setText("Adding Left Flipper");
                break;
            case "Right Flipper":
                updatePlaceListener(5);
                System.out.println("pressed right flipper");
                buildGUI.getLabel().setText("Adding Right Flipper");
                break;
        }

    }

    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }

    private void updatePlaceListener(int type) {
        board.removeMouseListener(gizmoPlaceListener);
        gizmoPlaceListener = new GizmoPlaceListener(model, board, 25, type,buildGUI);
        board.addMouseListener(gizmoPlaceListener);
    }
}

package controller;

import model.Ball;
import model.Model;
import view.BuildBoard;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GizmoListener implements ActionListener{

    private Model model;
    private BuildBoard board;
    private KeyListener keyboardListener;
    private GizmoPlaceListener gizmoPlaceListener;

    public GizmoListener(Model m, BuildBoard board){
        this.model = m;
        this.board = board;
        this.gizmoPlaceListener = null;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ball":
                updatePlaceListener(0);
                System.out.println("pressed ball");
                break;
            case "SquareGizmo":
                updatePlaceListener(1);
                System.out.println("pressed square");
                break;
            case "Circle":
                updatePlaceListener(2);
                System.out.println("pressed circle");
                break;
            case "TriangleGizmo":
                updatePlaceListener(3);
                System.out.println("pressed triangle");
                break;
            case "Left Flipper":
                updatePlaceListener(4);
                System.out.println("pressed left flipper");
                break;
            case "Right Flipper":
                updatePlaceListener(5);
                System.out.println("pressed right flipper");
                break;
        }

    }

    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }

    private void updatePlaceListener(int type) {
        board.removeMouseListener(gizmoPlaceListener);
        gizmoPlaceListener = new GizmoPlaceListener(model, board, 25, type);
        board.addMouseListener(gizmoPlaceListener);
    }
}

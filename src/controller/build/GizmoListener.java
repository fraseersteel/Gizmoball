package controller.build;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.*;
import java.awt.event.KeyListener;

public class GizmoListener implements ActionListener{

    private Model model;
    private BuildBoard board;
    private KeyListener keyboardListener;
    private MouseListener listener;
    private BuildGUI buildGUI;

    public GizmoListener(Model m, BuildBoard board,BuildGUI gui){
        this.model = m;
        this.board = board;
        this.listener = null;
        this.buildGUI = gui;
    }



    @Override
    public void actionPerformed(ActionEvent e) {


        switch(e.getActionCommand()){
            case "Ball":
                setGizmoPlaceListener(0);
                buildGUI.getLabel().setText("Adding ball");
                break;
            case "SquareGizmo":
                setGizmoPlaceListener(1);
                buildGUI.getLabel().setText("Adding Square");
                break;
            case "Circle":
                setGizmoPlaceListener(2);
                System.out.println("pressed circle");
                buildGUI.getLabel().setText("Adding Circle");
                break;
            case "TriangleGizmo":
                setGizmoPlaceListener(3);
                System.out.println("pressed triangle");
                buildGUI.getLabel().setText("Adding Triangle");
                break;
            case "Left Flipper":
                setGizmoPlaceListener(4);
                System.out.println("pressed left flipper");
                buildGUI.getLabel().setText("Adding Left Flipper");
                break;
            case "Right Flipper":
                setGizmoPlaceListener(5);
                System.out.println("pressed right flipper");
                buildGUI.getLabel().setText("Adding Right Flipper");
                break;
            case "Absorber":
                setAbsorberPlaceListener();
                System.out.println("pressed absorber");
                buildGUI.getLabel().setText("Drag over area to place absorber");
                break;
        }

    }

    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }

    private void setGizmoPlaceListener(int type) {
        board.removeMouseListener(listener);
        listener = new GizmoPlaceListener(model, board, type,buildGUI);
        board.addMouseListener(listener);
    }

    private void setAbsorberPlaceListener() {
        board.removeMouseListener(listener);
        listener = new AbsorberPlaceListener(model, board);
        board.addMouseListener(listener);
    }
}

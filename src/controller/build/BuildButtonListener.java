package controller.build;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.*;
import java.awt.event.KeyListener;

public class BuildButtonListener implements ActionListener{

    private Model model;
    private BuildBoard board;
    private MouseListener listener;
    private BuildGUI buildGUI;

    public BuildButtonListener(Model m, BuildBoard board, BuildGUI gui){
        this.model = m;
        this.board = board;
        this.listener = null;
        this.buildGUI = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        switch(e.getActionCommand()){
            case "Ball":
                changePlaceListener(new BallPlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding ball");
                break;
            case "SquareGizmo":
                changePlaceListener(new SquarePlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding Square");
                break;
            case "Circle":
                changePlaceListener(new CirclePlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding Circle");
                break;
            case "TriangleGizmo":
                changePlaceListener(new TrianglePlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding Triangle");
                break;
            case "Left Flipper":
                changePlaceListener(new LeftFlipperPlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding Left Flipper");
                break;
            case "Right Flipper":
                changePlaceListener(new RightFlipperPlaceListener(model, board, buildGUI));
                buildGUI.getLabel().setText("Adding Right Flipper");
                break;
            case "Absorber":
                changePlaceListener(new AbsorberPlaceListener(model, board));
                buildGUI.getLabel().setText("Drag over area to place absorber");
                break;
        }

    }

    public void keyPressed(KeyEvent e){
    }

    private void changePlaceListener(MouseListener newListener) {
        board.removeMouseListener(listener);
        listener = newListener;
        board.addMouseListener(listener);
    }
}

package controller.build.gizmo;

import controller.build.edit.*;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.*;

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
                changePlaceListener(new AbsorberPlaceListener(model, board,buildGUI));
                buildGUI.getLabel().setText("Drag over area to place absorber");
                break;
            case "Move":

                changePlaceListener(new MoveListener(model,board,buildGUI));
                buildGUI.getLabel().setText("Moving Gizmo");
                break;
            case "Rotate":

                changePlaceListener(new RotateListener(model,board,buildGUI));
                buildGUI.getLabel().setText("Rotating Gizmo");
                break;
            case "Connect":

                changePlaceListener(new ConnectListener(model,board,buildGUI));
                buildGUI.getLabel().setText("Connecting Gizmo");
                break;

            case "Disconnect":

                changePlaceListener(new DisconnectListener(model,board,buildGUI));
                buildGUI.getLabel().setText("Disconnecting Gizmo");
                break;
            case "Delete":

                changePlaceListener(new DeleteListener(model,board,buildGUI));
                buildGUI.getLabel().setText("Deleting Gizmo");
                break;
        }

        board.repaint();
    }

    public void keyPressed(KeyEvent e){
    }

    private void changePlaceListener(MouseListener newListener) {
        board.removeMouseListener(listener);
        listener = newListener;
        board.addMouseListener(listener);
    }
}

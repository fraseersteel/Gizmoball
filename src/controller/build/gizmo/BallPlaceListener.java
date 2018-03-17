package controller.build.gizmo;

import exceptions.InvalidGizmoException;
import model.Ball;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BallPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private BuildButtonListener buttonListener;

    public BallPlaceListener(Model model, BuildBoard board,  BuildGUI gui, BuildButtonListener buttonListener) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
        this.buttonListener = buttonListener;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        Ball newBall = new Ball("ball", xCoord, yCoord, 0, 0);
        try {
            if (model.checkLegalPlace(newBall, xCoord, yCoord)) {
                model.setBall(newBall);
                buildGUI.getLabel().setText("Added Ball");

                buttonListener.changePlaceListener(null);
                board.updateMouseHoverType("");

            } else {
                buildGUI.getLabel().setText("Ball can't be placed here!");
            }
        } catch (InvalidGizmoException ex) {
            System.err.println("Error: " + ex);
        }
        board.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

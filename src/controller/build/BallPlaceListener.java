package controller.build;

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

    public BallPlaceListener(Model model, BuildBoard board,  BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        Ball newBall = new Ball("ball", xCoord, yCoord, 3, 3);
        try {
            if (model.checkLegalPlace(newBall, xCoord, yCoord)) {
                model.setBall(newBall);
                buildGUI.getLabel().setText("Added Ball");
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

package controller.build.gizmo;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RightFlipperPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;

    public RightFlipperPlaceListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        RightFlipper newFlipper = new RightFlipper("RightFlipper"+ model.getGizmos().size(), xCoord, yCoord);
        try {
            if (model.checkLegalPlace(newFlipper, xCoord, yCoord)) {
                model.addGizmo(newFlipper);
                buildGUI.getLabel().setText("Added Right Flipper");
            } else {
                buildGUI.getLabel().setText("Right Flipper can't be placed here!");
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

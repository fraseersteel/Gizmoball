package controller.build.gizmo;

import exceptions.InvalidGizmoException;
import model.Model;
import model.SquareGizmo;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.UUID;

public class SquarePlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;

    public SquarePlaceListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        // TODO - all new gizmo objects are the same ID, must increment per object?

        SquareGizmo newSquare = new SquareGizmo("newSquare", xCoord, yCoord);
        try {
            if (model.checkLegalPlace(newSquare, xCoord, yCoord)) {
                model.addGizmo(newSquare);
                buildGUI.getLabel().setText("Added Square");
            } else {
                buildGUI.getLabel().setText("Square can't be placed here!");
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

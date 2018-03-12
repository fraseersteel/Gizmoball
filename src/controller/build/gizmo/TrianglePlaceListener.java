package controller.build.gizmo;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TrianglePlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;

    public TrianglePlaceListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        TriangleGizmo newTriangle = new TriangleGizmo("Triangle"+ model.getGizmos().size(), xCoord, yCoord);
        try {
            if (model.checkLegalPlace(newTriangle, xCoord, yCoord)) {
                model.addGizmo(newTriangle);
                buildGUI.getLabel().setText("Added Triangle");
            } else {
                buildGUI.getLabel().setText("Triangle can't be placed here!");
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

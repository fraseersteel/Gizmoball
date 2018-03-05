package controller;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GizmoPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private int cellDimension;

    private int gizmoType;
    // gizmoType is a reference to each different gizmo, as follows:
    // 0 = Ball
    // 1 = Square
    // 2 = Circle
    // 3 = Triangle
    // 4 = Left Flipper
    // 5 = Right Flipper


    public GizmoPlaceListener(Model model, BuildBoard board, int cellDimension, int gizmoType) {
        this.model = model;
        this.board = board;
        this.cellDimension = cellDimension;
        this.gizmoType = gizmoType;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/cellDimension;
        int yCoord = e.getY()/cellDimension;

        IGizmo newGizmo = null;

        switch (gizmoType) {
            case 1:
                newGizmo = new SquareGizmo("hey", xCoord, yCoord);
                break;
            case 2:
                newGizmo = new CircleGizmo("hey", xCoord, yCoord);
                break;
            case 3:
                newGizmo = new TriangleGizmo("hey", xCoord, yCoord);
                break;
            case 4:
                newGizmo = new LeftFlipper("hey", xCoord, yCoord);
                break;
            case 5:
                newGizmo = new RightFlipper("hey", xCoord, yCoord);
                break;
        }

        if (checkLegalPlace(xCoord, yCoord)) {
            try {
                if (gizmoType == 0) {
                    model.setBall(new Ball("ball", xCoord, yCoord, 5, 0));
                }
                else {
                    model.addGizmo(newGizmo);
                }
            } catch (InvalidGizmoException ex) { }
        }
        else
        {
            // TODO: Update UI here.
            System.out.println("Cannot place gizmo here.");
        }
        board.repaint();

    }

    /**
     * Checks to see if corresponding gizmo type can be placed in this cell location
     * Worth noting that Flippers AND Ball occupy 4x4 cells.
     * @return true if gizmo can be legally placed here, false otherwise.
     */
    private boolean checkLegalPlace(int x, int y) {
        if (model.findGizmoByCoords(x,y) != null)
            return false;

        // Check no ball in vicinity
        if (model.getBall() != null) {
            Ball ball = model.getBall();
            if (ball.getXPos() == x && ball.getYPos() == y)
                return false;
            if (ball.getXPos()-1 == x && ball.getYPos() == y)
                return false;
            if (ball.getXPos()-1 == x && ball.getYPos()-1 == y)
                return false;
            if (ball.getXPos() == x && ball.getYPos()-1 == y)
                return false;
        }

        // BALL
        // Note: Due to way balls are drawn, assume [x,y] location is bottom right of ball coords.
        if (gizmoType == 0) {
            if (model.findGizmoByCoords(x,y-1) != null)
                return false;
            if (model.findGizmoByCoords(x-1,y-1) != null)
                return false;
            if (model.findGizmoByCoords(x-1,y) != null)
                return false;
        }

        // FLIPPERS
        if (gizmoType == 4 || gizmoType == 5) {
            if (model.findGizmoByCoords(x+1,y) != null)
                return false;
            if (model.findGizmoByCoords(x,y+1) != null)
                return false;
            if (model.findGizmoByCoords(x+1,y+1) != null)
                return false;
        }

        return true;
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

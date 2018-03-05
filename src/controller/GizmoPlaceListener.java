package controller;

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
            case 0:
                model.setBall(new Ball("ball", xCoord, yCoord, 5, 0));
                break;
            case 1:
                newGizmo = new SquareGizmo("hey", xCoord, yCoord);
                model.addSquare((SquareGizmo) newGizmo);
                break;
            case 2:
                newGizmo = new CircleGizmo("hey", xCoord, yCoord);
                model.addCircle((CircleGizmo) newGizmo);
                break;
            case 3:
                newGizmo = new TriangleGizmo("hey", xCoord, yCoord);
                model.addTriangle((TriangleGizmo) newGizmo);
                break;
            case 4:
                newGizmo = new LeftFlipper("hey", xCoord, yCoord);
                model.addLeftFlipper((LeftFlipper) newGizmo);
                break;
            case 5:
                newGizmo = new RightFlipper("hey", xCoord, yCoord);
                model.addRightFlipper((RightFlipper) newGizmo);
                break;
        }

        board.repaint();


        System.out.println("X:" + xCoord + " Y:" + yCoord);

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

package controller;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GizmoPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;

    private int gizmoType;
    // gizmoType is a reference to each different gizmo, as follows:
    // 0 = Ball
    // 1 = Square
    // 2 = Circle
    // 3 = Triangle
    // 4 = Left Flipper
    // 5 = Right Flipper


    public GizmoPlaceListener(Model model, BuildBoard board, int gizmoType, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.gizmoType = gizmoType;
        this.buildGUI = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        IGizmo newGizmo = null;

        switch (gizmoType) {
            case 1:
                newGizmo = new SquareGizmo("hey", xCoord, yCoord);
                buildGUI.getLabel().setText("Added Square");
                break;
            case 2:
                newGizmo = new CircleGizmo("hey", xCoord, yCoord);
                buildGUI.getLabel().setText("Added Circle");
                break;
            case 3:
                newGizmo = new TriangleGizmo("hey", xCoord, yCoord);
                buildGUI.getLabel().setText("Added Triangle");
                break;
            case 4:
                newGizmo = new LeftFlipper("hey", xCoord, yCoord);
                buildGUI.getLabel().setText("Added Left Flipper");
                break;
            case 5:
                newGizmo = new RightFlipper("hey", xCoord, yCoord);
                buildGUI.getLabel().setText("Added Right Flipper");
                break;
        }

        if (model.checkLegalPlace(gizmoType, xCoord, yCoord)) {
            try {
                if (gizmoType == 0) {
                    model.setBall(new Ball("ball", xCoord, yCoord, 3, 3));
                    buildGUI.getLabel().setText("Added Ball");
                }
                else {
                    model.addGizmo(newGizmo);
                }
            } catch (InvalidGizmoException ex) { }
        }
        else
        {
            buildGUI.getLabel().setText("Warning: Cannot Place Gizmo Here!");
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
        System.out.println("Mouse Entered");

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

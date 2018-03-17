package controller.build.edit;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoveListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private Object selectedItem;


    public MoveListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX() / board.getCellWidth();
        int yCoord = e.getY() / board.getCellWidth();

        if (selectedItem == null) {
            Object selection = model.findItemByCoords(xCoord, yCoord);
            if (selection != null) {
                selectedItem = selection;
                buildGUI.getLabel().setText("Selected. Choose location to move item.");

                if (selection instanceof SquareGizmo)
                    board.updateMouseHoverType("Square");
                else if (selection instanceof TriangleGizmo)
                    board.updateMouseHoverType("Triangle");
                else if (selection instanceof CircleGizmo)
                    board.updateMouseHoverType("Circle");
                else if (selection instanceof LeftFlipper)
                    board.updateMouseHoverType("Left Flipper");
                else if (selection instanceof RightFlipper)
                    board.updateMouseHoverType("Right Flipper");
                else if (selection instanceof Ball)
                    board.updateMouseHoverType("Ball");
            }

        }
        else {
            try {
                boolean legal = model.checkLegalPlace(selectedItem, xCoord, yCoord);
                if (selectedItem instanceof IGizmo) {
                    if (legal) {
                        ((IGizmo) selectedItem).setXPos(xCoord);
                        ((IGizmo) selectedItem).setYPos(yCoord);
                        ((IGizmo) selectedItem).resetPhysics();

                        selectedItem = null;
                        buildGUI.getLabel().setText("Gizmo moved.");
                        board.updateMouseHoverType("");
                    }
                    else
                        buildGUI.getLabel().setText("This gizmo can't be moved here.");

                }
                else if(selectedItem instanceof Ball) {
                    if (legal) {
                        ((Ball) selectedItem).setXPos(xCoord);
                        ((Ball) selectedItem).setYPos(yCoord);
                        selectedItem = null;
                        buildGUI.getLabel().setText("Gizmo moved.");
                        board.updateMouseHoverType("");
                    }
                    else
                        buildGUI.getLabel().setText("This gizmo can't be moved here.");
                }
            } catch (InvalidGizmoException ex) { }
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

package controller.build.edit;

import exceptions.InvalidGizmoException;
import model.Absorber;
import model.Ball;
import model.IGizmo;
import model.Model;
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

            }

        }
        else {
            try {
                boolean legal = model.checkLegalPlace(selectedItem, xCoord, yCoord);
                if (selectedItem instanceof IGizmo) {
                    if (legal) {
                        ((IGizmo) selectedItem).setXPos(xCoord);
                        ((IGizmo) selectedItem).setYPos(yCoord);
                        selectedItem = null;
                        buildGUI.getLabel().setText("Gizmo moved.");
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
                    }
                    else
                        buildGUI.getLabel().setText("This gizmo can't be moved here.");
                }
            } catch (InvalidGizmoException ex) { }
        }
        board.repaint();

    }

    /*
    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        if (selectedItem == null){
            System.out.println("No Gizmo selected: selecting clicked Gizmo");
            selectedItem = model.findGizmoByCoords(xCoord, yCoord);
            if (selectedItem == null){
                System.out.println("No Gizmo was clicked");
            }else {
                String name = model.getGizmoTypeName(selectedItem.getxPos(), selectedItem.getyPos());
                buildGUI.getLabel().setText(name + " at " + xCoord + "," + yCoord + " selected");
            }
        } else {
            String name = model.getGizmoTypeName(selectedItem.getxPos(), selectedItem.getyPos());
            try {
                if (model.checkLegalPlace(selectedItem, xCoord, yCoord)) {
                    selectedItem.setXPos(xCoord);
                    selectedItem.setYPos(yCoord);
                    selectedItem = null; //deselects the selected gizmo
                    System.out.println(name + " was moved");
                } else {
                    System.out.println("Not a legal placement of " + name);
                }
            } catch (InvalidGizmoException ex) {
                System.err.println("Error: " + ex);
            }
        }


        board.repaint();



    }
    */

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

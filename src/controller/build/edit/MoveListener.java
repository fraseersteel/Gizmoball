package controller.build.edit;

import exceptions.InvalidGizmoException;
import model.IGizmo;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import javax.jws.WebParam;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MoveListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private IGizmo selectedGizmo;


    public MoveListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        if (selectedGizmo == null){
            System.out.println("No Gizmo selected: selecting clicked Gizmo");
            selectedGizmo = model.findGizmoByCoords(xCoord, yCoord);
            if (selectedGizmo == null){
                System.out.println("No Gizmo was clicked");
            }else {
                String name = model.getGizmoTypeName(selectedGizmo.getxPos(), selectedGizmo.getyPos());
                System.out.println(name + " was selected");
            }
        } else {
            String name = model.getGizmoTypeName(selectedGizmo.getxPos(), selectedGizmo.getyPos());
            try {
                if (model.checkLegalPlace(selectedGizmo, xCoord, yCoord)) {
                    selectedGizmo.setXPos(xCoord);
                    selectedGizmo.setYPos(yCoord);
                    selectedGizmo = null; //deselects the selected gizmo
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

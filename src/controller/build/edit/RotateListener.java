package controller.build.edit;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RotateListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;


    public RotateListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        if(model.findGizmoByCoords(xCoord,yCoord) != null){
            model.findGizmoByCoords(xCoord,yCoord).rotate();
            buildGUI.getLabel().setText(model.getGizmoTypeName(xCoord,yCoord) + " rotated");
        }else{
            buildGUI.getLabel().setText("Could not find Gizmo to rotate");
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
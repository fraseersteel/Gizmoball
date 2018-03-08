package controller.build.edit;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import javax.jws.WebParam;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DeleteListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;


    public DeleteListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        String name = model.getGizmoTypeName(xCoord,yCoord);

        if(model.findGizmoByCoords(xCoord,yCoord) != null){
            model.removeGizmoByCoords(xCoord,yCoord);
            buildGUI.getLabel().setText(name + " deleted.");
        }else{
            buildGUI.getLabel().setText("No gizmo to Delete");
        }

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
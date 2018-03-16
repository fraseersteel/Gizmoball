package controller.build.edit;

import model.IGizmo;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DisconnectListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private boolean chosenConnector = false;
    private IGizmo connectorGizmo;
    private IGizmo connectedGizmo;

    public DisconnectListener(Model model, BuildBoard board, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/board.getCellWidth();
        int yCoord = e.getY()/board.getCellWidth();

        if(model.findItemByCoords(xCoord,yCoord) != null){
            if(!chosenConnector) {
                connectorGizmo = model.findGizmoByCoords(xCoord, yCoord);
                chosenConnector = true;
                buildGUI.getLabel().setText(String.format("Disconnecting %s", connectorGizmo.getId()));
            } else {
                connectedGizmo = model.findGizmoByCoords(xCoord, yCoord);
                if(connectorGizmo.getConnections().contains(connectedGizmo)) {
                    buildGUI.getLabel().setText(String.format("Disconnected %s from %s", connectorGizmo.getId(), connectedGizmo.getId()));
                    model.disconnect(connectorGizmo, connectedGizmo);
                } else {
                    buildGUI.getLabel().setText(String.format("%s and %s are not connected", connectorGizmo.getId(), connectedGizmo.getId()));
                }
                connectorGizmo = null;
                connectedGizmo = null;
                chosenConnector = false;
            }

        } else {

            buildGUI.getLabel().setText("No gizmo to connect");
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
package controller.build.edit;

import model.IGizmo;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ConnectListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private boolean chosenConnector = false;
    private Object connectorItem;
    private Object connectedItem;

    public ConnectListener(Model model, BuildBoard board, BuildGUI gui) {
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
                connectorItem = model.findItemByCoords(xCoord, yCoord);
                chosenConnector = true;
                buildGUI.getLabel().setText(String.format("Connecting Item"));

                new ConnectKeyListener(model, connectorItem);
            } else {
                connectedItem = model.findItemByCoords(xCoord, yCoord);
                buildGUI.getLabel().setText(String.format("Connected"));
                model.connect(connectorItem, connectedItem);
                connectorItem = null;
                connectedItem = null;
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
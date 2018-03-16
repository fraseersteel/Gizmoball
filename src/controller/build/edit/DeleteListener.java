package controller.build.edit;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

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

        if (model.removeItemByCoords(xCoord, yCoord))
            buildGUI.getLabel().setText("Deleted item from board");
        else
            buildGUI.getLabel().setText("This cell is already empty!");

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
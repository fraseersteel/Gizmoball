package controller;

import model.Model;
import model.SquareGizmo;
import view.BuildBoard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GizmoPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private int cellDimension;

    public GizmoPlaceListener(Model model, BuildBoard board, int cellDimension) {
        this.model = model;
        this.board = board;
        this.cellDimension = cellDimension;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX()/cellDimension;
        int yCoord = e.getY()/cellDimension;

        model.addSquare(new SquareGizmo("hey", xCoord, yCoord));
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

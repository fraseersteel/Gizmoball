package controller.build;

import model.Model;
import model.SquareGizmo;
import view.BuildBoard;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BuildMouseHoverListener implements MouseMotionListener{

    private BuildBoard board;
    private int cellWidth;
    private Model model;

    public BuildMouseHoverListener(BuildBoard board,Model model) {
        this.board = board;
        this.model = model;
        cellWidth = board.getCellWidth();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int xCoord = e.getX()/cellWidth;
        int yCoord = e.getY()/cellWidth;

        board.updateMouseHoverCoords(xCoord, yCoord);

        System.out.println("[MOUSE] x:" + xCoord + " y:" + yCoord);


        board.drawSquare(xCoord,yCoord, Color.BLUE);
        board.repaint();

    }
}

package controller.build;

import view.BuildBoard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class BuildMouseHoverListener implements MouseMotionListener{

    private BuildBoard board;
    private int cellWidth;

    public BuildMouseHoverListener(BuildBoard board) {
        this.board = board;
        cellWidth = board.getCellWidth();
    }


    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int xCoord = e.getX()/cellWidth;
        int yCoord = e.getY()/cellWidth;

        System.out.println("[MOUSE] x:" + xCoord + " y:" + yCoord);


    }
}

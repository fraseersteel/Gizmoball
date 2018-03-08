package controller.build.gizmo;

import model.Absorber;
import model.Model;
import view.BuildBoard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AbsorberPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private int cellWidth;

    private int absorberStartX, absorberStartY, absorberEndX, absorberEndY;
    private boolean absorberProgress;

    public AbsorberPlaceListener(Model model, BuildBoard board) {
        this.model = model;
        this.board = board;

        this.cellWidth = board.getCellWidth();
        this.absorberProgress = false;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int xCoord = e.getX()/cellWidth;
        int yCoord = e.getY()/cellWidth;

        System.out.println("[Press] x:" + xCoord + " y:" + yCoord);

        absorberStartX = xCoord;
        absorberStartY = yCoord;

        absorberProgress = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xCoord = (e.getX()/cellWidth)+1;
        int yCoord = (e.getY()/cellWidth)+1;

        System.out.println("[Release] x:" + xCoord + " y:" + yCoord);

        absorberEndX = xCoord;
        absorberEndY = yCoord;

        Absorber absorber = new Absorber("absorber", absorberStartX, absorberStartY, absorberEndX, absorberEndY);
        model.setAbsorber(absorber);
        board.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

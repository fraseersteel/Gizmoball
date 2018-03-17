package controller.build.gizmo;

import exceptions.InvalidGizmoException;
import model.Absorber;
import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AbsorberPlaceListener implements MouseListener {

    private BuildGUI gui;
    private Model model;
    private BuildBoard board;
    private int cellWidth;

    private boolean absorberProgress;

    private int absorberStartX, absorberStartY, absorberEndX, absorberEndY;
    private BuildButtonListener buttonListener;

    public AbsorberPlaceListener(Model model, BuildBoard board, BuildGUI gui, BuildButtonListener buttonListener) {
        this.model = model;
        this.board = board;
        this.gui = gui;
        this.buttonListener = buttonListener;

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

        gui.getLabel().setText("[Press] x:" + xCoord + " y:" + yCoord);

        absorberStartX = xCoord;
        absorberStartY = yCoord;

        absorberProgress = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int xCoord = (e.getX()/cellWidth)+1;
        int yCoord = (e.getY()/cellWidth)+1;

        System.out.println("x: " + xCoord + "y: " + yCoord);

    gui.getLabel().setText("[Release] x:" + xCoord + " y:" + yCoord);

        if(xCoord<=20&&yCoord<=20) {
            absorberEndX = xCoord;
            absorberEndY = yCoord;


            Absorber absorber = new Absorber("absorber", absorberStartX, absorberStartY, absorberEndX, absorberEndY);

            try {
                if (model.checkLegalPlace(absorber, absorberStartX, absorberStartY)) {
                    model.setAbsorber(absorber);
                    gui.getLabel().setText("Added Absorber");

                    buttonListener.changePlaceListener(null);
                    board.updateMouseHoverType("");
                } else {
                    gui.getLabel().setText("Absorber can't be placed here!");
                }
            } catch (InvalidGizmoException ex) {}


        }else{
            gui.getLabel().setText("Absorber larger than the map: Make sure it is within boundaries");
        }
        board.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

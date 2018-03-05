package controller;

import exceptions.InvalidGizmoException;
import model.*;
import view.BuildBoard;
import view.BuildGUI;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EditPlaceListener implements MouseListener {

    private Model model;
    private BuildBoard board;
    private int cellDimension;
    private BuildGUI buildGUI;
    private int editType;


    public EditPlaceListener(Model model, BuildBoard board, int cellDimension, BuildGUI gui, int type) {
        this.model = model;
        this.board = board;
        this.cellDimension = cellDimension;
        this.buildGUI = gui;
        this.editType = type;
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        int xCoord = e.getX() / cellDimension;
        int yCoord = e.getY() / cellDimension;

        /* 1 - remove
           2 - rotate
           3 - connect
           4 - delete
         */

        switch (editType) {
            case 1:

                buildGUI.getLabel().setText("Moved Gizmo");
                break;
            case 2:

                buildGUI.getLabel().setText("Rotated Gizmo");
                break;
            case 3:

                //guess it may be slightly different for this and move gizmo and its not just one click
                buildGUI.getLabel().setText("Connected Gizmo");
                break;
            case 4:
                System.out.println(xCoord + "," + yCoord);
               model.removeGizmoByCoords(xCoord,yCoord);

                buildGUI.getLabel().setText("Deleted Gizmo");
                System.out.println("working !!!!");
                break;
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

package controller;

import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import java.awt.event.*;
import java.awt.event.KeyListener;

public class EditListener implements ActionListener {

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;
    private KeyListener keyboardListener;
    private EditPlaceListener editPlaceListener;

    public EditListener(Model m, BuildBoard board,BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
        this.editPlaceListener = null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Move":

                updatePlaceListener(1);
                buildGUI.getLabel().setText("Moving Gizmo");
                break;
            case "Rotate":

                updatePlaceListener(2);
                buildGUI.getLabel().setText("Rotating Gizmo");
                break;

            case "Connect":

                updatePlaceListener(3);
                buildGUI.getLabel().setText("Connecting Gizmo");
                break;
            case "Delete":

                updatePlaceListener(4);
                buildGUI.getLabel().setText("Deleting Gizmo");
                break;
        }
    }


    private void updatePlaceListener(int type) {
        board.removeMouseListener(editPlaceListener);
        editPlaceListener = new EditPlaceListener(model, board, 25,buildGUI,type);
        board.addMouseListener(editPlaceListener);
    }

}

package controller.build.edit;

import model.Model;
import view.BuildBoard;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearListener implements ActionListener {

    Model model;
    JPanel board;


    public ClearListener(Model model, BuildBoard buildBoard) {
    this.model = model;
    this.board = buildBoard;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        model.removeAllGizmo();


        board.repaint();
    }
}

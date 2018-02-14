package collisionsController;

import collisionsModel.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadListener implements ActionListener {

    Model model;
    JPanel board;

    public LoadListener(Model m, JPanel board) {
        model = m;
        this.board = board;
    }

    public void actionPerformed() {



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPerformed();
    }
}

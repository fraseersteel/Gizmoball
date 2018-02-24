package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditListener implements ActionListener {

    private Model model;

    public EditListener(Model m) {
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Move":
                System.out.println("pressed move");
                break;
            case "Rotate":
                System.out.println("pressed rotate");
                break;

            case "Connect":
                System.out.println("pressed connect");
                break;
            case "Delete":
                System.out.println("pressed delete");
                break;
        }
    }
}

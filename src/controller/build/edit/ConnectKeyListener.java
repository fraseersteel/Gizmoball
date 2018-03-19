package controller.build.edit;

import exceptions.InvalidGizmoException;
import model.Model;
import view.BuildGUI;
import view.GUI;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectKeyListener implements KeyListener {

    private BuildGUI gui;
    private Model model;
    private Object item;

    private boolean status;

    public ConnectKeyListener(BuildGUI gui, Model model, Object item) {
        this.gui = gui;
        this.model = model;
        this.item = item;
        this.status = false;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (status == false) {
            try {
                System.out.println("Adding key connection");
                model.addKeyConnect(item, String.valueOf(e.getKeyCode()));
                status = true;
                gui.getLabel().setText("Key Connected");
            } catch (InvalidGizmoException ex) {}
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

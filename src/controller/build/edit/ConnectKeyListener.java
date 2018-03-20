package controller.build.edit;

import exceptions.InvalidGizmoException;
import model.Absorber;
import model.IGizmo;
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
        String keyPress = String.valueOf(e.getKeyCode());

        if (status == false) {
            if (item instanceof IGizmo) {
                ((IGizmo) item).addKeyConnection(keyPress);
            }
            else if (item instanceof Absorber) {
                ((Absorber) item).addKeyConnection(keyPress);
            }
            status = true;
            gui.getLabel().setText("Key Connected");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

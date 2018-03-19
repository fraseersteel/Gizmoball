package controller.build.edit;

import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConnectKeyListener implements KeyListener {

    private Model model;
    private Object item;

    public ConnectKeyListener(Model model, Object item) {
        this.model = model;
        this.item = item;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

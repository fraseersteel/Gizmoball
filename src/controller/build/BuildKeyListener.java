package controller.build;

import model.Model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class BuildKeyListener implements KeyListener {

    private Model model;

    public BuildKeyListener(Model model) {
        this.model = model;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed: " + e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

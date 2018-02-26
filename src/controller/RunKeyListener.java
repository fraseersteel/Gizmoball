package controller;

import model.Model;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunKeyListener implements KeyListener{

    Model model;
    public RunKeyListener(Model m) {
        model = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            System.out.println("SPACE");
            model.shootAbsorber();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

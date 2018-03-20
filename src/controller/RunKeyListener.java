package controller;

import model.Absorber;
import model.IGizmo;
import model.Model;
import view.Board;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

public class RunKeyListener implements KeyListener{

    private Model model;
    private Board board;

    public RunKeyListener(Model m, Board board) {
        this.model = m;
        this.board = board;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Pressed " + e.getKeyCode());


        String keyPress = String.valueOf(e.getKeyCode());

        for (IGizmo gizmo : model.getGizmos()) {
            if (gizmo.getKeyConnections().contains(keyPress)) {
                gizmo.trigger();
            }
        }

        if (model.getAbsorber() != null) {
            if (model.getAbsorber().getKeyConnections().contains(keyPress)) {
                model.shootAbsorber();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

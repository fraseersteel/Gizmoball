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

        Map<Object, String> keyConnects = model.getKeyConnects();
        String keyPress = String.valueOf(e.getKeyCode());

        for (Object key : keyConnects.keySet()) {
            if (keyConnects.get(key).equals(keyPress)) {
                if (key instanceof IGizmo) {
                    ((IGizmo) key).trigger();

                    board.repaint();
                } else if (key instanceof Absorber) {
                    model.shootAbsorber();
                }
            }
        }
        /*
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            model.shootAbsorber();
        } */
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

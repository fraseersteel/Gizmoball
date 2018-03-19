package controller;

import model.Absorber;
import model.IGizmo;
import model.Model;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

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
        Map<Object, String> keyConnects = model.getKeyConnects();
        String keyPress = String.valueOf(e.getKeyCode());

        for (Object key : keyConnects.keySet()) {
            if (keyConnects.get(key).equals(keyPress)) {
                if (key instanceof IGizmo) {
                    ((IGizmo) key).trigger();
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

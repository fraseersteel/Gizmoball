package collisionsController;

import collisionsModel.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class AbsorberListener implements java.awt.event.KeyListener{

    Model model;
    public AbsorberListener(Model m) {
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

package flipperController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import flipperModel.Model;

public class BoardListener implements KeyListener{
    private Model model;

    public BoardListener(Model model){
        this.model = model;
    }

    public void keyPressed(KeyEvent keyEvent){
        System.out.println("Pressed key to rotate flipper");
        model.rotateFlippers();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public void keyTyped(KeyEvent keyEvent){

    }
}

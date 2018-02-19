package flipperController;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import flipperModel.Model;

public class BoardListener implements KeyListener{
    private Model model;
    private ArrayList<Integer> keyPresses = new ArrayList<Integer>();

    public BoardListener(Model model){
        this.model = model;
    }

    public void keyPressed(KeyEvent keyEvent){
        int code = keyEvent.getKeyCode();
        Integer val = Integer.valueOf(code);
        if (keyPresses.contains(val)){
            return;
        } else {
            System.out.println("Pressed key to rotate flipper");
            model.rotateFlippers();
        }
        keyPresses.add(val);
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        model.rotateFlippers();
        keyPresses.clear();
    }

    public void keyTyped(KeyEvent keyEvent){

    }
}

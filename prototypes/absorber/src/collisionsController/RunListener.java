package collisionsController;

import collisionsModel.Model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunListener implements ActionListener{

    private Model model;
    private KeyListener keyboardListener;
    private Timer timer;

    public RunListener(Model m){
        model = m;
        timer = new Timer(50,this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //case statement for which action to perform
        // cases include "Start","Stop","Tick","Quit"

        if (e.getSource() == timer) {
            model.moveBall();
        }else {
            switch (e.getActionCommand()) {
                case "Start":
                    timer.start();
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    model.moveBall();
                    break;
                case "Quit":
                    int reply = JOptionPane.showConfirmDialog(null, "Are you sure you would like to quit?", "Warning", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_NO_CANCEL_OPTION) {
                        JOptionPane.showMessageDialog(null, "Resuming game");
                    } else {
                        System.exit(0);
                    }
                    break;
            }
        }

    }


    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }
}

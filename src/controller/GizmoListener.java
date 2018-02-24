package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GizmoListener implements ActionListener{

    private Model model;
    private KeyListener keyboardListener;

    public GizmoListener(Model m){
        model = m;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Square":
                System.out.println("pressed square");
                break;
            case "Circle":
                System.out.println("pressed circle");
                break;

            case "Triangle":
                System.out.println("pressed triangle");
                break;
            case "Right Flipper":
                System.out.println("pressed right flipper");
                break;
            case "Left Flipper":
                System.out.println("pressed left flipper");
                break;
        }
    }

    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }
}

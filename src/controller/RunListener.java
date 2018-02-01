package controller;

import model.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunListener implements ActionListener{

    private Model model;
    private KeyListener keyboardListener;

    public RunListener(Model m){
        model = m;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //case statement for which action to perform
        // cases include "Start","Stop","Tick","Quit"

    }


    public void keyPressed(KeyEvent e){
        keyboardListener.keyPressed(e);
    }
}

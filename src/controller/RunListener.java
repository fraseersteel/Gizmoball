package controller;

import model.Model;
import view.GUI;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class RunListener implements ActionListener {

    private Model model;
    private KeyListener keyboardListener;
    private Timer timer;
    private GUI gui;
    private int timerCount;

    public RunListener(Model m, GUI runGui) {
        model = m;
        timer = new Timer(50, this);
        this.gui = runGui;
        timerCount =0;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //case statement for which action to perform
        // cases include "Start","Stop","Tick","Quit"


        if (e.getSource() == timer) {
            model.moveBall();
        } else {
            switch (e.getActionCommand()) {
                case "Start":
                    timer.start();
                    break;
                case "Stop":
                    timer.stop();
                    break;
                case "Tick":
                    timer.stop();
                    model.moveBall();
                    timerCount++;
                    break;
                case "Build Mode":
                    System.out.println("switching");
                    gui.switchToBuildMode();
                    break;
                case "Run Mode":
                    System.out.println("switching to run");
                    gui.switchToRunMode();
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


    public void keyPressed(KeyEvent e) {
        keyboardListener.keyPressed(e);
    }
}

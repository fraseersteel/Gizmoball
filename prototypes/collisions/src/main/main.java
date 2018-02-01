package main;

import collisionsView.Board;
import collisionsModel.Model;
import collisionsController.RunListener;

import javax.swing.*;
import java.awt.event.ActionListener;

public class main {

    private Model model;
    private JFrame frame;
    private ActionListener listener;
    private Board board;

    public main(Model m){
        model = m;
        listener = new RunListener(m);
    }

}

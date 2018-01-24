package view;

import controller.RunListener;
import model.Model;

import javax.swing.*;
import java.awt.event.ActionListener;

public class GUI {

    private Model model;
    private JFrame jFrame;
    private ActionListener listener;
    private Board board;

    public GUI(Model model){

        this.model = model;
        listener = new RunListener(model);

    }

}

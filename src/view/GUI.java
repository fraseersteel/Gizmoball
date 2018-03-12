package view;

import model.Model;

import javax.swing.*;

public class GUI {

    protected Model model;
    protected JFrame jframe;

    public GUI(Model model) {
        this.model = model;
    }

    public void switchToBuildMode(){
        jframe.setVisible(false);
        model.getBall().stop();
        new BuildGUI(model);
    }

    public void switchToRunMode(){
        jframe.setVisible(false);
        new RunGUI(model);
    }


}

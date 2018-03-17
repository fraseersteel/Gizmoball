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
        if (model.getBall() != null) {
            model.getBall().stop();
            model.getBall().reset();
        }

        jframe.setVisible(false);
        new BuildGUI(model);
    }

    public void switchToRunMode(){
        jframe.setVisible(false);
        new RunGUI(model);
    }


}

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
        model = new Model(); // Create new model when we go to build.

        jframe.setVisible(false);
        new BuildGUI(new Model());
    }

    public void switchToRunMode(){
        jframe.setVisible(false);
        new RunGUI(model);
    }


}

package controller.build.edit;


import model.Model;
import view.BuildBoard;
import view.BuildGUI;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SliderListener implements ChangeListener{

    private Model model;
    private BuildBoard board;
    private BuildGUI buildGUI;


    public SliderListener(Model model, BuildGUI gui) {
        this.model = model;
        this.board = board;
        this.buildGUI = gui;
    }


    @Override
    public void stateChanged(ChangeEvent e) {

        JSlider source = (JSlider)e.getSource();

        String name = source.getName();

        if(!source.getValueIsAdjusting()){
            if("gravity".equals(name)) {
                int gravity = (int) source.getValue();
                model.setGravity(gravity);
                buildGUI.getLabel().setText("Gravity set to " + gravity);
            }else if("friction".equals(name)){
                int friction = (int) source.getValue();
                model.setFriction(friction);
                buildGUI.getLabel().setText("Friction set to " + friction);
            }
        }
    }
}

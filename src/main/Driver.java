package main;


import model.*;
import view.RunGUI;


public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        model.setBall(new Ball("Ball", 9, 1, 5, 0));

        model.setAbsorber(new Absorber("abs", 0, 19, 20, 20));


        RunGUI runGui = new RunGUI(model);
    }
}

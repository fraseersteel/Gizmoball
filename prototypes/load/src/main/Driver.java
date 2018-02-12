package main;


import model.GizmoLoader;
import model.Model;
import view.RunGUI;

public class Driver {
    Model model;

    public static void main(String[] args) {
        Model model = new Model();
        RunGUI gui = new RunGUI(model);

    }
}

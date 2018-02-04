package main;


import model.Model;
import view.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        RunGUI gui = new RunGUI(model);
    }
}

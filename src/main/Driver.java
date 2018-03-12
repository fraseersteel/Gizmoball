package main;


import model.*;
import view.RunGUI;


public class Driver {


    public static void main(String[] args) {
        Model model = new Model();

        RunGUI runGui = new RunGUI(model);
    }
}

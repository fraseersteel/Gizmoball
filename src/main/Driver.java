package main;


import model.Model;
import view.GUI;


public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        GUI Gui = new GUI(model);
    }
}

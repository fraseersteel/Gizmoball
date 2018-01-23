package main;

import view.GUI;
import model.Model;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        GUI gui = new GUI(model);
    }
}

package main;


import model.*;
import view.RunGUI;


public class Driver {


    public static void main(String[] args) {
        Model model = new Model();

        model.addSquare(new SquareGizmo("hey", 4, 5));
        model.addSquare(new SquareGizmo("hey", 10, 5));
        model.addSquare(new SquareGizmo("hey", 12, 7));
        model.addSquare(new SquareGizmo("hey", 17, 17));
        model.addSquare(new SquareGizmo("hey", 4, 17));
        model.addSquare(new SquareGizmo("hey", 10, 1));
        model.addCircle(new CircleGizmo("hey", 8, 5));

        model.addCircle(new CircleGizmo("hey", 8, 7));

        model.addTriangle(new TriangleGizmo("hey", 4, 15));

        model.setBall(new Ball("Ball", 9, 1, 5, 0));

        model.setAbsorber(new Absorber("abs", 0, 19, 20, 20));


        RunGUI runGui = new RunGUI(model);
    }
}

package main;


import model.*;
import view.GUI;


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

        model.setBall(new Ball("Ball", 8, 10, 14, 12.5));

        GUI Gui = new GUI(model);
    }
}

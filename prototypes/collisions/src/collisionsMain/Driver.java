package collisionsMain;


import collisionsModel.CircleGizmo;
import collisionsModel.Model;
import collisionsModel.Square;
import collisionsModel.Triangle;
import collisionsView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();

        //model.addSquare(new Square("heee", 10, 13));


        Triangle triangle = new Triangle("hey", 10, 4);
        triangle.rotate();
        triangle.rotate();

        model.addTriangle(triangle);

        model.addSquare(new Square("heee", 1, 1));
        model.addSquare(new Square("heee", 2, 2));
        model.addSquare(new Square("heee", 10, 17));

        model.addSquare(new Square("heee", 12, 13));
        model.addSquare(new Square("heee", 2, 13));
        model.addSquare(new Square("heee", 17, 5));
        model.addSquare(new Square("heee", 17, 10));
        model.addSquare(new Square("heee", 4, 3));
        model.addSquare(new Square("heee", 17, 17));
        model.addSquare(new Square("heee", 2, 17));


        /*
        model.addCircle(new CircleGizmo("sdsdsd", 3, 19));
        model.addCircle(new CircleGizmo("sdsdsd", 4, 15));
        model.addCircle(new CircleGizmo("sdsdsd", 6, 19));
        model.addCircle(new CircleGizmo("sdsdsd", 8, 14));
        model.addCircle(new CircleGizmo("sdsdsd", 12, 19));

        model.addTriangle(triangle);
        */

        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(0, 300);

    }
}

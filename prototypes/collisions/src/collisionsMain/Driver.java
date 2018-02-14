package collisionsMain;


import collisionsModel.Model;
import collisionsModel.Square;
import collisionsModel.Triangle;
import collisionsView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();

        Triangle triangle = new Triangle("hey", 10, 4);
        triangle.rotate();
        triangle.rotate();

        model.addSquare(new Square("heee", 10, 17));

        model.addSquare(new Square("heee", 12, 13));
        model.addSquare(new Square("heee", 2, 13));
        model.addSquare(new Square("heee", 17, 5));
        model.addTriangle(triangle);

        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(0, 300);

    }
}

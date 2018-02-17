package collisionsMain;


import collisionsModel.Model;
import collisionsModel.Square;
import collisionsView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        //model.addSquare(new Square("hey", 10, 3));

        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(0, 500);

    }
}

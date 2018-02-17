package collisionsMain;


import collisionsModel.Absorber;
import collisionsModel.Model;
import collisionsModel.Square;
import collisionsView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        //model.addSquare(new Square("hey", 10, 3));
        Absorber absorber = new Absorber("absorber1", 0, 19, 20, 20);
        model.addAbsorber(absorber);

        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(0, 300);

    }
}

package absorberMain;


import absorberModel.Model;
import absorberModel.Square;
import absorberView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        model.addSquare(new Square("hey", 3, 3));

        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(0, 300);

    }
}

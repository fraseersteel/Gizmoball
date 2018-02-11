package collisionsMain;


import collisionsModel.Model;
import collisionsView.RunGUI;

public class Driver {


    public static void main(String[] args) {
        Model model = new Model();
        RunGUI gui = new RunGUI(model);
        model.setBallVeloctiy(300, 150);

    }
}

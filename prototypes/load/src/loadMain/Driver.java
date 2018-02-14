package loadMain;


import loadModel.GizmoLoader;
import loadModel.Model;
import loadView.RunGUI;

public class Driver {
    Model model;

    public static void main(String[] args) {
        Model model = new Model();
        RunGUI gui = new RunGUI(model);

    }
}

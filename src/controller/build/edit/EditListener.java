//package controller.build.edit;
//
//import model.Model;
//import view.BuildBoard;
//import view.BuildGUI;
//
//import java.awt.event.*;
//import java.awt.event.KeyListener;
//
//public class EditListener implements ActionListener {
//
//    private Model model;
//    private BuildBoard board;
//    private BuildGUI buildGUI;
//    private KeyListener keyboardListener;
//    private MouseListener listener;
//
//    public EditListener(Model model, BuildBoard board,BuildGUI gui) {
//        this.model = model;
//        this.board = board;
//        this.buildGUI = gui;
//        this.listener = null;
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//        switch (e.getActionCommand()) {
//            case "Move":
//
//                changePlaceListener(new MoveListener(model,board,buildGUI));
//                buildGUI.getLabel().setText("Moving Gizmo");
//                break;
//            case "Rotate":
//
//                changePlaceListener(new RotateListener(model,board,buildGUI));
//                buildGUI.getLabel().setText("Rotating Gizmo");
//                break;
//            case "Connect":
//
//                changePlaceListener(new ConnectListener(model,board,buildGUI));
//                buildGUI.getLabel().setText("Connecting Gizmo");
//                break;
//            case "Delete":
//
//                changePlaceListener(new DeleteListener(model,board,buildGUI));
//                buildGUI.getLabel().setText("Deleting Gizmo");
//                break;
//        }
//    }
//
//
//    private void changePlaceListener(MouseListener newListener) {
//        board.removeMouseListener(listener);
//        listener = newListener;
//        board.addMouseListener(listener);
//    }
//
//}

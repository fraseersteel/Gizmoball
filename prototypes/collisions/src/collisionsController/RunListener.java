package collisionsController;

import collisionsModel.Model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RunListener implements ActionListener {

    private Model model;
    private Timer timer;

    public RunListener(Model m){
        model = m;
        timer = new Timer(20,this);
    }



    @Override
    public void actionPerformed(ActionEvent e) {

        switch(e.getActionCommand()){
            case "Start":
                timer.start();
            case "Stop":
                timer.stop();
            case "Tick":
                model.moveBall();
            case "Quit":
                int reply = JOptionPane.showConfirmDialog(null,"Are you sure you would like to quit","Warning",JOptionPane.YES_NO_OPTION);
                if(reply==JOptionPane.YES_NO_CANCEL_OPTION) {
                    System.exit(0);
                }else{
                    JOptionPane.showMessageDialog(null,"Resuming game");
                }
        }

    }
}

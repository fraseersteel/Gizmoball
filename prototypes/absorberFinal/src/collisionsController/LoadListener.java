package collisionsController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class LoadListener implements ActionListener {


    public void actionPerformed() {

        FileInputStream fileInputStream;
        String msg = "Success";

        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();

            fileInputStream = new FileInputStream(file);
            ObjectInputStream oInputStream = new ObjectInputStream(fileInputStream);
            //
            oInputStream.close();

        } catch (FileNotFoundException e) {
            msg = "File not selected or doesn't exist!";
        } catch(InvalidClassException e){
            msg = "This file type is incompatible!";
        } catch (IOException e) {
            msg = "Problem occurred while reading the file, it may have been moved!";
        } catch(NullPointerException ee){
            return;
        }
        JOptionPane.showMessageDialog(null, msg);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPerformed();
    }
}

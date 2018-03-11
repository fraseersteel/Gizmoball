package controller;

import model.IGizmo;
import model.Model;
import model.SaveFile;
import view.BuildGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveListener implements ActionListener {

    Model model;
    JPanel board;
    JFileChooser fc;
    BuildGUI buildGUI;


    public SaveListener(Model m, JPanel board,BuildGUI gui){
        this.model = m;
        this.board = board;
        fc = new JFileChooser();
        buildGUI = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        fc.showSaveDialog(board);
            try {
                String withExt = fc.getSelectedFile().getAbsolutePath() + ".giz";
                File file = new File(withExt);

                SaveFile saveFile = new SaveFile(model, file);
                saveFile.saveToFile();

                buildGUI.getLabel().setText("File saved to: " + file);

            }
            catch (Exception ex)
            {
                buildGUI.getLabel().setText("Cancel selected: nothing saved.");
            }
        }
    }


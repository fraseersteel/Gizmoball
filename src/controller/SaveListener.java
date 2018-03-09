package controller;

import model.IGizmo;
import model.Model;
import model.SaveFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveListener implements ActionListener {

    Model model;
    JPanel board;
    JFileChooser fc;

    public SaveListener(Model m, JPanel board){
        this.model = m;
        this.board = board;
        fc = new JFileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        FileNameExtensionFilter filter = new FileNameExtensionFilter("(.giz) Gizo Saves","giz");
        fc.addChoosableFileFilter(filter);

        fc.showSaveDialog(board);

        SaveFile saveFile = new SaveFile(model,fc.getName());
        System.out.println(model.getGizmos() + " is in the model");
        saveFile.save();

    }
}

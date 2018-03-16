package controller;

import model.GizmoLoader;
import model.Model;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class LoadListener implements ActionListener {

    Model model;
    JPanel board;

    public LoadListener(Model m, JPanel board) {
        this.model = m;
        this.board = board;
    }

    public void actionPerformed() {


        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setDialogTitle("Select Gizmo map to Load");
        jfc.setAcceptAllFileFilterUsed(false);

        FileNameExtensionFilter filter = new FileNameExtensionFilter("(.giz) Gizmo Saves", "giz");
        jfc.addChoosableFileFilter(filter);

        int returnValue = jfc.showOpenDialog(null);

        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();

            GizmoLoader loader = new GizmoLoader(model, selectedFile.getAbsolutePath());

            if (!loader.loadSave()) {
                JOptionPane.showMessageDialog(board, "Save file is corrupt in some way.  Ensure you are only loading saves created within the build mode!",
                        "Error with load", JOptionPane.ERROR_MESSAGE);
                model.reset();
            }


            board.repaint();
            System.out.println("Repainted");

            System.out.println(selectedFile.getAbsolutePath());
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPerformed();
    }
}


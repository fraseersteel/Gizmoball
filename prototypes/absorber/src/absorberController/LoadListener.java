package absorberController;

import absorberModel.Model;

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
        model = m;
        this.board = board;
    }

    public void actionPerformed() {



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        actionPerformed();
    }
}

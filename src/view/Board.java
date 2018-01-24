package view;

import model.Model;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;
import java.util.jar.JarEntry;

public class Board extends JPanel implements Observer{

    private int width;
    private int height;

    public Board(int x, int y, Model M){
        width = x;
        height = x;
    }


    @Override
    public void update(Observable o, Object arg) {

    }
}

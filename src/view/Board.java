package view;

import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.jar.JarEntry;

public class Board extends JPanel implements Observer{

    private Model model;
    private int width;
    private int height;

    public Board(int x, int y, Model m){
        width = x;
        height = x;
        model = m;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D g2 = (Graphics2D) graphic;

        //for (VerticalLine )

        g2.setColor(Color.white);
        g2.fillOval(30,10, 20, 20);
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

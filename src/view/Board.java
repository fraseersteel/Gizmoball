package view;

import model.Ball;
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
        m.addObserver(this);
        width = x;
        height = x;
        model = m;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D g2d = (Graphics2D) graphic;

        Ball ball = model.getBall();

        if(ball!=null){
            g2d.setColor(Color.BLACK);
            int x = (int) (ball.getX() - ball.getRadius());
            int y = (int) (ball.getX() - ball.getRadius());
            int ballWidth = (int) (2* ball.getRadius());
            g2d.fillOval(x,y,ballWidth,ballWidth);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

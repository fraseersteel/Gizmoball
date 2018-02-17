package view;

import model.Ball;
import model.Circle;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.jar.JarEntry;

public class Board extends JPanel implements Observer {

    private Model model;
    private int width;
    private int height;

    public Board(int x, int y,Model m) {
        width = x;
        height = y;
        m.addObserver(this);
        model = m;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D g2d = (Graphics2D) graphic;

        Ball ball = model.getBall();

        if (ball != null) {
            g2d.setColor(Color.BLACK);
            int x = (int) (ball.getXPos() - ball.getRadius());
            int y = (int) (ball.getYPos() - ball.getRadius());
            int ballWidth = (int) (2 * ball.getRadius());
            g2d.fillOval(x, y, ballWidth, ballWidth);
        } else {
            System.out.println("Error with ball");
        }

        for(int i =0; i<width;i+=25){
            g2d.drawLine(i,0,i,height);
        }
        for(int i=0;i<height;i+=25){
            g2d.drawLine(0,i,width,i);
        }

        //for (Circle circle : )
    }

    public int getWidth() {
        return width;
    }

    public int getHeight(){
        return height;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

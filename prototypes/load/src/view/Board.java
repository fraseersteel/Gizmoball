package view;

import model.*;

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
            int x = (int) ball.getXPos()*20;
            int y = (int) ball.getYPos()*20;
            int ballWidth = (int) (2* ball.getRadius());
            g2d.fillOval(x,y,ballWidth,ballWidth);
        }


        for (IGizmo gizmo : model.getGizmos()) {
            int x = (int) gizmo.getxLocation()*20;
            int y = (int) gizmo.getyLocation()*20;

            if (gizmo instanceof Triangle) {
            }
            else if(gizmo instanceof Square) {
                g2d.fillRect(x, y, 25, 25);
            }
            else if(gizmo instanceof Circle) {
                g2d.fillOval(x, y, 25, 25);
            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

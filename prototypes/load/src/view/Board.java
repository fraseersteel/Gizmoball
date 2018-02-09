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
        this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D g2d = (Graphics2D) graphic;

        Ball ball = model.getBall();

        if(ball!=null){
            int x = (int) ball.getXPos()*25;
            int y = (int) ball.getYPos()*25;
            int ballWidth = (int) (2* ball.getRadius());
            g2d.setColor(Color.BLUE);
            g2d.fillOval(x,y,ballWidth,ballWidth);
        }


        for (IGizmo gizmo : model.getGizmos()) {
            int x = (int) gizmo.getxLocation()*25;
            int y = (int) gizmo.getyLocation()*25;

            if (gizmo instanceof Triangle) {

            }
            else if(gizmo instanceof Square) {
                g2d.setColor(Color.RED);
                g2d.fillRect(x, y, 25, 25);
            }
            else if(gizmo instanceof Circle) {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x, y, 25, 25);
            }
            else if(gizmo instanceof LeftFlipper) {
                g2d.setColor(Color.ORANGE);
                g2d.fillRoundRect(x, y, 12, 50, 13, 13);
            }
            else if(gizmo instanceof RightFlipper) {
                g2d.setColor(Color.ORANGE);
                g2d.fillRoundRect(x, y, 12, 50, 13, 13);
            }
        }

        for (Absorber absorber : model.getAbsorbers()) {
            int startX = (int) (absorber.getStartX() * 25);
            int startY = (int) (absorber.getStartY() * 25);
            int width = (int) ((absorber.getEndX()-absorber.getStartX()) * 25);
            int height = (int) ((absorber.getEndY()-absorber.getStartY()) * 25);

            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(startX, startY, width, height);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

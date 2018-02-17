package flipperView;

import flipperModel.*;
import flipperPhysics.LineSegment;


import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;


public class Board extends JPanel implements Observer{

    private Model model;
    private int dim;


    public Board(Model m, int cellDimension){
        m.addObserver(this);
        model = m;
        this.dim = cellDimension;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);

        Graphics2D g2d = (Graphics2D) graphic;

        Ball ball = model.getBall();

        if(ball!=null){
            int x = (int) ball.getXPos();
            int y = (int) ball.getYPos();
            int ballWidth = (int) (2* ball.getRadius());
            g2d.setColor(Color.BLACK);
            g2d.fillOval(x,y,(int)ball.getRadius(),(int)ball.getRadius());
        }


        for (RightFlipper rFlippers : model.getFlippers()) {
            int x = (int) rFlippers.getXPos() * 25;
            int y = (int) rFlippers.getYPos() * 25;
            ArrayList<LineSegment> lines = rFlippers.getLines();
            LineSegment l1 = lines.get(0);
            LineSegment l2 = lines.get(1);

            g2d.drawLine((int) l1.p1().x(), (int) l1.p1().y(),
                    (int) l1.p2().x(), (int) l1.p2().y());

            g2d.drawLine((int) l2.p1().x(), (int) l2.p1().y(),
                    (int) l2.p2().x(), (int) l2.p2().y());
        }



        for (Absorber absorber : model.getAbsorbers()) {
            int startX = (int) (absorber.getStartX() * dim);
            int startY = (int) (absorber.getStartY() * dim);
            int width = (int) ((absorber.getEndX()-absorber.getStartX()) * dim);
            int height = (int) ((absorber.getEndY()-absorber.getStartY()) * dim);

            g2d.setColor(Color.MAGENTA);
            g2d.fillRect(startX, startY, width, height);
        }
    }


    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

package absorberView;

import absorberModel.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Board extends JPanel implements Observer {

    private Model model;
    private int width;
    private int height;

    public Board(int x, int y, Model m) {
        width = x;
        height = y;
        m.addObserver(this);
        model = m;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }


    public void paintBall(Graphics graphic){
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
    }

    private void paintLines(Graphics g){

        g.setColor(Color.gray);

        for (int i = 0; i <= getSize().width; i += 25)
        {
            g.drawLine(i, 0, i, getSize().height);
        }

        for (int i = 0; i <= getHeight(); i += 25)
        {
            g.drawLine(0,i, getSize().width, i);
        }
    }


    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int gizmoWidth = 25;

        Graphics2D g2d = (Graphics2D) graphic;

        paintBall(graphic);
        paintLines(graphic);

        for (IGizmo gizmo : model.getGizmo()) {
            int x = (int) gizmo.getxPos();
            int y = (int) gizmo.getyPos();

            switch (gizmo.getClass().getName()) {
                case "absorberModel.Square":
                    g2d.setColor(Color.RED);
                    g2d.fillRect(x, y, gizmoWidth, gizmoWidth);
                    break;
                case "absorberModel.Circle":
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(x, y, gizmoWidth, gizmoWidth);
                    break;
                case "absorberModel.LeftFlipper":
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRoundRect(x, y, 12, 50, 13, 13);
                case "absorberModel.RightFlipper":
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRoundRect(x, y, 12, 50, 13, 13);
                    break;
            }
        }

        for(VerticalLines vertL : model.getVertlines()){
            g2d.fillRect(vertL.getxPos(),vertL.getyPos(),1,vertL.getHeight());
        }
        for(HorizontalLines hortL: model.getHorzLines()){
            g2d.fillRect(hortL.getxPos(),hortL.getyPos(),hortL.getWidth(),1);
        }

    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

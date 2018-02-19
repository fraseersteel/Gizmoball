package collisionsView;

import collisionsModel.*;
import collisionsPhysics.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.GZIPInputStream;

public class Board extends JPanel implements Observer {

    private Model model;
    private int width;
    private int height;
    private final int cellWidth = 25;

    public Board(int x, int y, Model m) {
        width = x;
        height = y;
        m.addObserver(this);
        model = m;
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int gizmoWidth = cellWidth;

        Graphics2D g2d = (Graphics2D) graphic;

        Ball ball = model.getBall();

        if (ball != null) {
            g2d.setColor(Color.BLACK);
            int x = (int) ((ball.getXPos() - ball.getRadius())*cellWidth);
            int y = (int) ((ball.getYPos() - ball.getRadius())*cellWidth);
            int ballWidth = (int) (2 * ball.getRadius())*cellWidth;
            g2d.fillOval(x, y, ballWidth, ballWidth);
        } else {
            System.out.println("Error with ball");
        }

        for (IGizmo gizmo : model.getGizmo()) {
            int x = (int) gizmo.getxPos()*cellWidth;
            int y = (int) gizmo.getyPos()*cellWidth;


            for (Circle c : model.getCircles()) {
                g2d.setColor(Color.GREEN);
                g2d.fill(c.toEllipse2D());
            }

            switch (gizmo.getClass().getName()) {
                case "collisionsModel.Square":
                    g2d.setColor(Color.RED);
                    g2d.fillRect(x, y, gizmoWidth, gizmoWidth);
                    break;
                case "collisionsModel.Circle":
                    g2d.setColor(Color.GREEN);
                    g2d.fillOval(x, y, gizmoWidth, gizmoWidth);
                    break;
                case "collisionsModel.Triangle":
                    Polygon triangle = null;
                    g2d.setColor(Color.BLUE);
                    int dim = cellWidth;
                    switch (gizmo.getRotationAngle()) {
                        case 0:
                            triangle = new Polygon(new int[]{x, x, x + dim}, new int[]{y, y + dim, y}, 3);
                            break;
                        case 90:
                            triangle = new Polygon(new int[]{x, x + dim, x + dim}, new int[]{y, y, y + dim}, 3);
                            break;
                        case 180:
                            triangle = new Polygon(new int[]{x + dim, x + dim, x}, new int[]{y, y + dim, y + dim}, 3);
                            break;
                        case 270:
                            triangle = new Polygon(new int[]{x, x, x + dim}, new int[]{y, y + dim, y + dim}, 3);
                            break;
                    }
                    g2d.fillPolygon(triangle);
                    g2d.setColor(Color.BLUE);
                    break;
                case "collisionsModel.LeftFlipper":
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRoundRect(x, y, 12, 50, 13, 13);
                case "collisionsModel.RightFlipper":
                    g2d.setColor(Color.ORANGE);
                    g2d.fillRoundRect(x, y, 12, 50, 13, 13);
                    break;
            }
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

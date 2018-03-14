package view;

import controller.RunKeyListener;
import model.*;
import physics.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.Observable;
import java.util.Observer;

public class Board extends JPanel implements Observer {

    private Model model;
    private int width;
    private int height;
    private final int cellWidth = 25;

    private Graphics2D g2d;

    public Board(int x, int y, Model m) {
        width = x;
        height = y;
        model = m;
        g2d = null;

        this.setBackground(Color.BLACK);
        this.addKeyListener(new RunKeyListener(model));
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        m.addObserver(this);
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        g2d = (Graphics2D) graphic;

        Absorber absorber = model.getAbsorber();
        if (absorber != null) {
            int startX = (int) (absorber.getStartX() * cellWidth);
            int startY = (int) (absorber.getStartY() * cellWidth);
            int width = (int) ((absorber.getEndX() - absorber.getStartX()) * cellWidth);
            int height = (int) ((absorber.getEndY() - absorber.getStartY()) * cellWidth);

            drawAbsorber(startX, startY, width, height);
        }

        Ball ball = model.getBall();
        if (ball != null) {
            int x = (int) ((ball.getXPos() - ball.getRadius())*cellWidth);
            int y = (int) ((ball.getYPos() - ball.getRadius())*cellWidth);
            drawBall(x, y, ball.getRadius());
        } else {
            System.out.println("Error with ball");
        }

        for (IGizmo gizmo : model.getGizmos()) {
            int x = gizmo.getxPos()*cellWidth;
            int y = gizmo.getyPos()*cellWidth;

            if (gizmo instanceof SquareGizmo) {
                if(!gizmo.isTrigger()){
                    drawSquare(x, y,Color.RED);
                }
                else{
                    drawSquare(x, y,gizmo.getColour());
                }

            }
            else if (gizmo instanceof CircleGizmo) {
                if(!gizmo.isTrigger()){
                    drawCircle(x, y,Color.GREEN);
                }
                else{
                    drawCircle(x, y,gizmo.getColour());
                }
            }
            else if (gizmo instanceof TriangleGizmo) {
                if(!gizmo.isTrigger()) {
                    drawTriangle(x, y, gizmo.getRotationAngle(), Color.BLUE);
                } else{
                    drawTriangle(x, y, gizmo.getRotationAngle(), gizmo.getColour());
                }
            }
            else if (gizmo instanceof LeftFlipper) {
                drawLeftFlipper(x, y,gizmo.getColour());
            }
            else if(gizmo instanceof RightFlipper) {
                drawRightFlipper(x, y,gizmo.getColour());
            }
        }

    }

    private void drawBall(int x, int y, double radius) {
        int ballWidth = (int) (2 * radius*cellWidth);

        g2d.setColor(Color.WHITE);
        g2d.fillOval(x, y, ballWidth, ballWidth);
    }

    private void drawSquare(int x, int y,Color color) {
        g2d.setColor(color);
        g2d.fillRect(x, y, cellWidth, cellWidth);
    }

    private void drawCircle(int x, int y,Color color) {
        g2d.setColor(color);
        g2d.fillOval(x, y, cellWidth, cellWidth);
    }

    private void drawLeftFlipper(int x, int y,Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x, y, 12, 50, 13, 13);
    }

    private void drawRightFlipper(int x, int y,Color color) {
        g2d.setColor(color);
        g2d.fillRoundRect(x+cellWidth+13, y, 12, 50, 13, 13);


        /*AffineTransform transform = new AffineTransform();
            g2d.setColor(Color.ORANGE);
            Shape flipper;
            if (rFlippers.getOrientation() == 0) {
                flipper = new RoundRectangle2D.Double((int) l1.p1().x(), (int) l1.p1().y(), 20, 50, 20, 25);
                g2d.fill(flipper);
            } else if (rFlippers.getOrientation() != 0) {
                flipper = new RoundRectangle2D.Double((int) l1.p1().x(), (int) l1.p1().y(), 20, 50, 20, 25);
                transform.rotate(Math.toRadians(rFlippers.getOrientation()), (int) l1.p1().x() + 20, (int) l1.p1().y() + 50);
                Shape newFlipper = transform.createTransformedShape(flipper);
                flipper = newFlipper;
                g2d.fill(flipper);
            }*/
    }

    private void drawTriangle(int x, int y, int rotationAngle,Color color) {
        Polygon triangle = null;
        switch (rotationAngle) {
            case 0:
                triangle = new Polygon(new int[]{x, x, x + cellWidth}, new int[]{y, y + cellWidth, y}, 3);
                break;
            case 90:
                triangle = new Polygon(new int[]{x, x + cellWidth, x + cellWidth}, new int[]{y, y, y + cellWidth}, 3);
                break;
            case 180:
                triangle = new Polygon(new int[]{x + cellWidth, x + cellWidth, x}, new int[]{y, y + cellWidth, y + cellWidth}, 3);
                break;
            case 270:
                triangle = new Polygon(new int[]{x, x, x + cellWidth}, new int[]{y, y + cellWidth, y + cellWidth}, 3);
                break;
        }

        g2d.fillPolygon(triangle);
        g2d.setColor(color);
    }

    private void drawAbsorber(int startX, int startY, int width, int height) {
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect(startX, startY, width, height);
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

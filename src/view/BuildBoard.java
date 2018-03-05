package view;

import controller.GizmoPlaceListener;
import model.*;
import physics.Circle;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.zip.GZIPInputStream;

public class BuildBoard extends JPanel implements Observer {

    private Model model;
    private int width;
    private int height;
    private final int cellWidth = 25;
    private Graphics2D g2d;



    public BuildBoard(int x, int y, Model m) {
        this.width = x;
        this.height = y;
        this.model = m;

        m.addObserver(this);
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        int gizmoWidth = cellWidth;

        g2d = (Graphics2D) graphic;

        // drawing grid lines
        for(int i =0; i<width;i+=25){
            g2d.drawLine(i,0,i,height);
        }
        for(int i=0;i<height;i+=25){
            g2d.drawLine(0,i,width,i);
        }

        Ball ball = model.getBall();
        if (ball != null) {
            int x = (int) ((ball.getXPos() - ball.getRadius()) * cellWidth);
            int y = (int) ((ball.getYPos() - ball.getRadius()) * cellWidth);
            drawBall(x, y, ball.getRadius());
        }

        for (IGizmo gizmo : model.getGizmo()) {
            int x = gizmo.getxPos()*cellWidth;
            int y = gizmo.getyPos()*cellWidth;

            if (gizmo instanceof SquareGizmo) {
                drawSquare(x, y,gizmo.getColour());
            }
            else if (gizmo instanceof CircleGizmo) {
                drawCircle(x, y,gizmo.getColour());
            }
            else if (gizmo instanceof TriangleGizmo) {
                drawTriangle(x, y, gizmo.getRotationAngle(),gizmo.getColour());
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

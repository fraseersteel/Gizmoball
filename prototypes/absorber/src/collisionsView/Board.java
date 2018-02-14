package collisionsView;

import collisionsModel.*;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Board extends JPanel implements Observer{

    private Model model;
    private int dim;


    public Board(Model m, int cellDimension){
        m.addObserver(this);
        model = m;
        this.dim = cellDimension;
        //this.setBackground(Color.BLACK);
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE));
    }

    public void paintBall(Graphics graphic){
        super.paintComponent(graphic);

        Graphics2D g2d = (Graphics2D) graphic;


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

        for (IGizmo gizmo : model.getGizmos()) {
            int x = (int) gizmo.getXPos()*dim;
            int y = (int) gizmo.getYPos()*dim;

            if (gizmo instanceof Triangle) {
                g2d.setColor(Color.BLUE);
                switch (gizmo.getRotationAngle()) {
                    case 0:
                        g2d.drawPolygon(new int[] {x, x, x+ dim}, new int[] {y, y+dim, y}, 3);
                        break;
                    case 90:
                        g2d.drawPolygon(new int[] {x, x+dim, x+dim}, new int[] {y, y, y+dim}, 3);
                        break;
                    case 180:
                        g2d.drawPolygon(new int[] {x+dim, x+dim, x}, new int[] {y, y+dim, y+dim}, 3);
                        break;
                    case 270:
                        g2d.drawPolygon(new int[] {x, x, x+dim}, new int[] {y, y+dim, y+dim}, 3);
                        break;
                }
            }
            else if(gizmo instanceof Square) {
                g2d.setColor(Color.RED);
                g2d.fillRect(x, y, dim, dim);
            }
            else if(gizmo instanceof Circle) {
                g2d.setColor(Color.GREEN);
                g2d.fillOval(x, y, dim, dim);
            }
            else if(gizmo instanceof LeftFlipper) {
                g2d.setColor(Color.ORANGE);
                g2d.fillRoundRect(x, y, dim/2, dim*2, dim/2, dim/2);
            }
            else if(gizmo instanceof RightFlipper) {
                g2d.setColor(Color.ORANGE);
                g2d.fillRoundRect(x+dim/2, y, dim/2, dim*2, dim/2, dim/2);
            }
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

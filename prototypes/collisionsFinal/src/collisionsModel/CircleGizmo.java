package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class CircleGizmo implements IGizmo {

    private ArrayList<collisionsPhysics.Circle> circles = new ArrayList<collisionsPhysics.Circle>();
    private int xPos = 0;
    private int yPos = 0;
    private int width = 1;
    private String ID = "";
    private double diameter = 1;


    public CircleGizmo(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        createCircles();
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void setId(String id) {
        this.ID = id;
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }

    @Override
    public void setYPos(int y) {
        this.yPos = y;
    }

    @Override
    public void setXPos(int x) {
        this.xPos = x;
    }

    @Override
    public ArrayList<LineSegment> getLines() {
        return null;
    }

    public double getRadius() {
        return (diameter/2);
    }

    @Override
    public ArrayList<collisionsPhysics.Circle> getCircles() {
        return circles;
    }

    public void createCircles() {
        circles.clear();
        Circle mid = new Circle(xPos + 0.5, yPos + 0.5, diameter / 2);
        circles.add(mid);
    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    @Override
    public collisionsModel.CircleGizmo getCircle() {
        return this;
    }

}

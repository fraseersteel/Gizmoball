package model;

import model.IGizmo;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;

public class CircleGizmo implements IGizmo {

    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private int xPos = 0;
    private int yPos = 0;
    private int width = 1;
    private String ID = "";
    private int rotationAngle;
    private double diameter = 1;


    public CircleGizmo(String id, int x, int y) {
        rotationAngle =0;
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
        return (diameter / 2);
    }

    @Override
    public void addTrigger(IGizmo gizmo) {
        //
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle + 90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }

    }

    @Override
    public ArrayList<Circle> getCircles() {
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
    public CircleGizmo getCircle() {
        return this;
    }

}

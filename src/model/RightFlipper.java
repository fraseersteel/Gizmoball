package model;

import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;


public class RightFlipper implements IGizmo {

    private String ID;
    private double xPos;
    private double yPos;

    public RightFlipper(String id, double x, double y) {

        ID = id;
        xPos = x;
        yPos = y;

    }

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    //easy way to get the corners of any given object to easily add the zero-radius circles?
    private ArrayList<String> connections = new ArrayList<String>();
    private ArrayList<String> keyConnections = new ArrayList<String>();

    @Override
    public String getId() {
        return null;
    }

    @Override
    public void setId(String i) {

    }

    @Override
    public int getxPos() {
        return 0;
    }

    @Override
    public int getyPos() {
        return 0;
    }

    @Override
    public void setYPos(int y) {

    }

    @Override
    public void setXPos(int x) {

    }

    @Override
    public ArrayList<LineSegment> getLines() {
        return null;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        return null;
    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    @Override
    public CircleGizmo getCircle() {
        return null;
    }

    @Override
    public double getRadius() {
        return 0;
    }

    @Override
    public void addTrigger(IGizmo gizmo) {

    }

    @Override
    public void rotate() {

    }
}
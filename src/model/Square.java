package model;

import physics.Circle;
import physics.LineSegment;
import physics.*;

import java.util.ArrayList;

public class Square implements IGizmo {

    private int rotationAngle;
    private ArrayList<physics.Circle> circles = new ArrayList<physics.Circle>();
    private ArrayList<physics.LineSegment> lines = new ArrayList<LineSegment>();
    private int xPos = 0;
    private int yPos = 0;
    private int width = 1;
    private String ID = "";

    public Square(String id,int x,int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        addCircles();
        addLines();
        rotationAngle = 0;
    }


    private void addCircles(){

        circles.add(new physics.Circle(xPos,yPos,0.0));
        circles.add(new physics.Circle(xPos,yPos+1,0.0));
        circles.add(new physics.Circle(xPos+1,yPos,0.0));
        circles.add(new physics.Circle(xPos+1,yPos+1,0.0));
    }

    private void addLines(){

        lines.add(new LineSegment(xPos,yPos,xPos+1,yPos));
        lines.add(new LineSegment(xPos+1,yPos,xPos+1,yPos+1));
        lines.add(new LineSegment(xPos+1,yPos+1,xPos,yPos+1));
        lines.add(new LineSegment(xPos,yPos+1,xPos,yPos));
    }

    @Override
    public String getId() {
        return this.ID;
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
    public ArrayList<physics.LineSegment> getLines() {
        return lines;
    }

    @Override
    public ArrayList<physics.Circle> getCircles() {
        return circles;
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
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
    }

}
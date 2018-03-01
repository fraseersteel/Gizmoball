package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class SquareGizmo implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private Color colour;

    public SquareGizmo(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;
        colour = Color.RED;
        circles = new ArrayList<>();
        lines = new ArrayList<>();

        addCircles();
        addLines();
    }

    @Override
    public void setColour(Color color) {
        this.colour = color;
    }

    @Override
    public Color getColour() {
        return colour;
    }



    private void addCircles(){

        circles.add(new Circle(xPos,yPos,0.0));
        circles.add(new Circle(xPos,yPos+1,0.0));
        circles.add(new Circle(xPos+1,yPos,0.0));
        circles.add(new Circle(xPos+1,yPos+1,0.0));
    }

    private void addLines(){

        lines.add(new LineSegment(xPos,yPos,xPos+1,yPos));
        lines.add(new LineSegment(xPos+1,yPos,xPos+1,yPos+1));
        lines.add(new LineSegment(xPos+1,yPos+1,xPos,yPos+1));
        lines.add(new LineSegment(xPos,yPos+1,xPos,yPos));
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
    }


    @Override
    public String getId() {
        return this.ID;
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
    public ArrayList<LineSegment> getLines() {
        return lines;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        return circles;
    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    @Override
    public void setId(String id) {
        this.ID = id;
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
    public void addTrigger(IGizmo gizmo) {

    }

}
package model;

import java.awt.*;
import java.util.ArrayList;

import physics.*;


public class LeftFlipper implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;

    private Color colour;
    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private boolean isTriggered;

    private int height = 50;
    private int width = 20;
    private int radius = 10; //using same values from prototype

    private boolean isFlipped; //if is in the flipped position
    private boolean isStopped = true; //for checking if at rest - need to apply

    private Vect lineCor;
    private Vect circleCor;

    private Angle angle;

    public LeftFlipper(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;
        colour = Color.ORANGE;
        isTriggered = false;

        circles = new ArrayList<>();
        lines = new ArrayList<>();
        lineCor = new Vect ( xPos, yPos-height);
        circleCor = new Vect ( xPos, yPos-height); //still not sure about these

        addCircles();
        addLines();
    }

    private void addCircles(){
        //circles for rounded ends of flipper
        Circle c1 = new Circle(xPos+radius, yPos-radius, radius);
        Circle c2 = new Circle (xPos+radius, (yPos-height)+radius, radius);
        circles.add(c1);
        circles.add(c2);
        //zero-radius circles for ends of lines
        Circle z1 = new Circle(xPos, yPos, 0);
        Circle z2 = new Circle(xPos, yPos-height, 0);
        Circle z3 = new Circle(xPos+width, yPos, 0);
        Circle z4 = new Circle(xPos+width, yPos-height, 0);
        circles.add(z1);
        circles.add(z2);
        circles.add(z3);
        circles.add(z4);
    }

    private void addLines(){
        LineSegment l1 = new LineSegment(xPos, yPos, xPos, yPos-height);
        LineSegment l2 = new LineSegment(xPos+width, yPos, xPos+width, yPos-height);
        lines.add(l1);
        lines.add(l2);
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
    }

    public void flipFlipper(){
        if (!isFlipped) {
            angle = new Angle(-1.57); //think these should just be opposite of right
        } else {
            angle = new Angle(1.57); //roughly 90 degrees
        }

        circles.set(0, Geometry.rotateAround(circles.get(0), circleCor, angle));
        circles.set(1, Geometry.rotateAround(circles.get(1), circleCor, angle));
        circles.set(2, Geometry.rotateAround(circles.get(2), circleCor, angle));
        circles.set(3, Geometry.rotateAround(circles.get(3), circleCor, angle));
        circles.set(4, Geometry.rotateAround(circles.get(4), circleCor, angle));
        circles.set(5, Geometry.rotateAround(circles.get(5), circleCor, angle));

        lines.set(0, Geometry.rotateAround(lines.get(0), lineCor, angle));
        lines.set(1, Geometry.rotateAround(lines.get(1), lineCor, angle));
        isFlipped = !isFlipped;
    }

    @Override
    public void setColour(Color color) {
        this.colour = color;
    }

    @Override
    public Color getColour() {
        return colour;
    }

    @Override
    public boolean isTrigger() {
        return isTriggered;
    }

    @Override
    public void trigger() {
        isTriggered = true;
    }


    public Vect getCircleCor() {
        return circleCor;
    }

    public Vect getLineCor() {
        return lineCor;
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

    public boolean getIsStopped() {
        return isStopped;
    }

    public boolean getIsFlipped(){
        return isFlipped;
    }

    public double getAngle(){
        return angle.radians();
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
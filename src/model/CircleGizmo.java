package model;

import physics.Circle;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public class CircleGizmo implements IGizmo {

    private ArrayList<Circle> circles;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private Color colour;
    private boolean isTriggered;


    public CircleGizmo(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;
        isTriggered = false;

        circles = new ArrayList<>();

        createCircles();
    }

    public void createCircles() {
        circles.clear();
        Circle mid = new Circle(xPos + 0.5, yPos + 0.5, 0.5);
        circles.add(mid);
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle + 90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }

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
    public String getId() {
        return ID;
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
        return null;
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
        //
    }


    @Override
    public boolean isTrigger(){
        return isTriggered;
    }

    @Override
    public void trigger(){
        if(isTriggered==false){
            isTriggered = true;
        } else if(isTriggered ==true){
            isTriggered = false;
        }
    }


}

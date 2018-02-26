package model;

import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;


public class LeftFlipper implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;

    public LeftFlipper(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;

        circles = new ArrayList<>();
        lines = new ArrayList<>();

        addCircles();
        addLines();

    }

    private void addCircles(){
        //todo
    }

    private void addLines(){
        //todo
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
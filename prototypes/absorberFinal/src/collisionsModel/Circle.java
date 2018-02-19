package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Circle implements IGizmo {

    private ArrayList<collisionsPhysics.Circle> circles = new ArrayList<collisionsPhysics.Circle>();
    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private int xPos = 0;
    private int yPos = 0;
    private int width = 1;
    private String ID = "";


    public Circle(String id,int x, int y){
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
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
        return lines;
    }

    @Override
    public ArrayList<collisionsPhysics.Circle> getCircles() {
        return circles;
    }

    @Override
    public int getRotationAngle() {
        return 0;
    }

    @Override
    public collisionsPhysics.Circle getCircle() {
        return new collisionsPhysics.Circle(xPos,yPos,12.5);
    }

}

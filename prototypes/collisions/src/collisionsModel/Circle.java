package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Circle implements IGizmo {

    private ArrayList<Circle> circles = new ArrayList<Circle>();
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
    public ArrayList<Circle> getCircles() {
        return circles;
    }

    @Override
    public double[] to2D() {
        return new double[0];
    }
}

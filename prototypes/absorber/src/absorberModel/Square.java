package absorberModel;

import absorberPhysics.LineSegment;

import java.util.ArrayList;

public class Square implements IGizmo {

    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private int xPos = 0;
    private int yPos = 0;
    private int width = 1;
    private String ID = "";

    public Square(String id,int x,int y) {
       this.ID = id;
       this.xPos = x;
       this.yPos = y;
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
    public ArrayList<LineSegment> getLines() {
        return lines;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        return circles;
    }

    @Override
    public double[] to2D() {
        double[] coords;

        coords = new double[] {xPos,yPos, width};

        return coords;
    }
}
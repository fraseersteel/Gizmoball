package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.LineSegment;
import collisionsPhysics.*;

import java.util.ArrayList;

public class Square implements IGizmo {

    private ArrayList<collisionsPhysics.Circle> circles = new ArrayList<collisionsPhysics.Circle>();
    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
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
    }


    private void addCircles(){

        circles.add(new collisionsPhysics.Circle(xPos,yPos,0.0));
        circles.add(new collisionsPhysics.Circle(xPos,yPos+1,0.0));
        circles.add(new collisionsPhysics.Circle(xPos+1,yPos,0.0));
        circles.add(new collisionsPhysics.Circle(xPos+1,yPos+1,0.0));
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
    public Circle getCircle() {
        return null;
    }


}
package model;

import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;

public class TriangleGizmo implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;

    public TriangleGizmo(String id, int x, int y){
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;

        circles = new ArrayList<>();
        lines = new ArrayList<>();

        addLines();
        addCircles();
    }

    private void addLines() {

        int x = xPos;
        int y = yPos;

        switch (rotationAngle) {
            case 0:
                // | /
                // |/
                lines.add(new LineSegment(x, y, x+1, y));
                lines.add(new LineSegment(x+1, y, x, y+1));
                lines.add(new LineSegment(x, y+1, x, y));
                break;
            case 90:
                // \ |
                //  \|
                lines.add(new LineSegment(x, y, x+1, y));
                lines.add(new LineSegment(x+1, y, x+1, y+1));
                lines.add(new LineSegment(x+1, y+1, x, y));
                break;
            case 180:

                //  /|
                // /_|
                //lines.add(new LineSegment(x, y+20, x+1, y+20));
                lines.add(new LineSegment(x+1, y, x+1, y+1));
                lines.add(new LineSegment(x+1, y+1, x, y+1));
                lines.add(new LineSegment(x, y+1, x+1, y));
                break;
            case 270:
                // |\
                // |_\
                lines.add(new LineSegment(x, y, x+1, y+1));
                lines.add(new LineSegment(x+1, y+1, x, y+1));
                lines.add(new LineSegment(x, y+1, x, y));
                break;
        }
    }

    private void addCircles() {
        int x = xPos;
        int y = yPos;

        switch (rotationAngle) {
            case 0:
                // | /
                // |/
                circles.add(new Circle(x, y,0));
                circles.add(new Circle(x+1, y, 0));
                circles.add(new Circle(x, y+1, 0));
                break;
            case 90:
                // \ |
                //  \|
                circles.add(new Circle(x, y, 0));
                circles.add(new Circle(x+1, y, 0));
                circles.add(new Circle(x+1, y+1, 0));
                break;
            case 180:

                //  /|
                // /_|
                //lines.add(new LineSegment(x, y+20, x+1, y+20));
                circles.add(new Circle(x+1, y, 0));
                circles.add(new Circle(x+1, y+1, 0));
                circles.add(new Circle(x, y+1, 0));
                break;
            case 270:
                // |\
                // |_\
                circles.add(new Circle(x, y, 0));
                circles.add(new Circle(x+1, y+1, 0));
                circles.add(new Circle(x, y+1, 0));
                break;
        }
    }

    public void rotate() {
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
        updateLines();
    }

    private void updateLines() {
        lines.clear();
        circles.clear();
        addLines();
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
    public ArrayList<LineSegment>getLines() {
        return lines;
    }

    @Override
    public ArrayList<Circle> getCircles() {
        return circles;
    }

    @Override
    public int getRotationAngle() {
        return rotationAngle;
    }


    @Override
    public void setId(String id) {
        this.ID= id;
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

}

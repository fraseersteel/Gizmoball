package model;

import java.awt.*;
import java.util.ArrayList;

import physics.*;


public class RightFlipper implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;
    private ArrayList<IGizmo> connections;
    private ArrayList<String> keyConnections;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private Color colour;
    private boolean isTriggered;

    private boolean isFlipped; //if is in the flipped position
    private boolean isStopped = true; //for checking if at rest - need to apply

    private Vect lineCor;
    private Vect circleCor;

    private Angle angle;


    public RightFlipper(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;
        colour = Color.ORANGE;
        isTriggered = false;

        colour = Color.ORANGE;

        keyConnections = new ArrayList<>();
        connections = new ArrayList<>();
        circles = new ArrayList<>();
        lines = new ArrayList<>();
        //lineCor = new Vect ( xPos, yPos-height);
        //circleCor = new Vect ( xPos, yPos-height);


        addCircles();
        addLines();
    }

    @Override
    public void resetPhysics() {
        addCircles();
        addLines();
    }

    private void addCircles(){
        circles.clear();

        if (!isFlipped) {
            //circles for rounded ends of flipper
            Circle c1 = new Circle(xPos + 1.75, yPos, 0.25);
            Circle c2 = new Circle(xPos + 1.75, yPos + 2, 0.25);
            circles.add(c1);
            circles.add(c2);
            //zero-radius circles for ends of lines
            Circle z1 = new Circle(xPos + 1.5, yPos, 0);
            Circle z2 = new Circle(xPos + 1.5, yPos + 2, 0);
            Circle z3 = new Circle(xPos + 2, yPos, 0);
            Circle z4 = new Circle(xPos + 2, yPos + 2, 0);
            circles.add(z1);
            circles.add(z2);
            circles.add(z3);
            circles.add(z4);
        }
        else
        {
            //circles for rounded ends of flipper
            Circle c1 = new Circle(xPos + 0.25, yPos, 0.25);
            Circle c2 = new Circle(xPos + 2, yPos + 0.25, 0.25);
            circles.add(c1);
            circles.add(c2);
            //zero-radius circles for ends of lines
            Circle z1 = new Circle(xPos, yPos, 0);
            Circle z2 = new Circle(xPos + 2, yPos + 0.5, 0);
            Circle z3 = new Circle(xPos, yPos+0.5, 0);
            Circle z4 = new Circle(xPos + 2, yPos + 0.5, 0);
            circles.add(z1);
            circles.add(z2);
            circles.add(z3);
            circles.add(z4);
        }
    }

    private void addLines(){
        lines.clear();

        if (!isFlipped) {
            lines.add(new LineSegment(xPos + 1.5, yPos, xPos + 1.5, yPos + 2));
            lines.add(new LineSegment(xPos + 2, yPos, xPos + 2, yPos + 2));
        }
        else
        {
            lines.add(new LineSegment(xPos, yPos, xPos +2, yPos + 0.5));
            lines.add(new LineSegment(xPos, yPos + 0.5, xPos + 2, yPos + 0.5));
        }
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
        resetPhysics();
    }

    public void flipFlipper(){
        if (!isFlipped) {
            angle = new Angle(1.57);
        } else {
            angle = new Angle(-1.57); //roughly 90 degrees
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
    public void setID(String id) {
        this.ID = id;
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
        flip();
        if (!isTriggered)
            isTriggered = true;
        else
            isTriggered = false;
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

    public boolean isFlipped(){
        return isFlipped;
    }

    public void flip() {
        if (!isFlipped)
            isFlipped = true;
        else
            isFlipped = false;

        resetPhysics();
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

    // Note: we need to be able to trigger on absorber as well (not sure why)
    @Override
    public void addTrigger(IGizmo gizmo){
        //
    }


    @Override
    public ArrayList<IGizmo> getConnections() {
        return connections;
    }

    @Override
    public boolean hasConnections() {
        if(connections.size()==0){
            return false;
        }else{
            return true;
        }
    }

    public void addConnection(IGizmo connected){
        System.out.println(String.format("Connecting gizmo %s to %s", this.ID, connected.getId()));
        connections.add(connected);
    }

    @Override
    public ArrayList<String> getKeyConnections() {
        return keyConnections;
    }

    @Override
    public void addKeyConnection(String key) {
        keyConnections.add(key);
    }

    @Override
    public ArrayList<String> saveSignature(){
        ArrayList<String> gizLines = new ArrayList<>();
        gizLines.add("RightFlipper " + ID + " " + xPos + " " + yPos);

        return gizLines;
    }
}

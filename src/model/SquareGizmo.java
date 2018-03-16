package model;

import physics.Circle;
import physics.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SquareGizmo implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;
    private ArrayList<IGizmo> connections;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private Color colour;
    private boolean isTriggered;

    public SquareGizmo(String id, int x, int y) {
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;

        connections = new ArrayList<>();
        circles = new ArrayList<>();
        lines = new ArrayList<>();
        isTriggered = false;

        colour = Color.GREEN;

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


    private void addCircles() {

        circles.add(new Circle(xPos, yPos, 0.0));
        circles.add(new Circle(xPos, yPos + 1, 0.0));
        circles.add(new Circle(xPos + 1, yPos, 0.0));
        circles.add(new Circle(xPos + 1, yPos + 1, 0.0));
    }

    private void addLines() {

        lines.add(new LineSegment(xPos, yPos, xPos + 1, yPos));
        lines.add(new LineSegment(xPos + 1, yPos, xPos + 1, yPos + 1));
        lines.add(new LineSegment(xPos + 1, yPos + 1, xPos, yPos + 1));
        lines.add(new LineSegment(xPos, yPos + 1, xPos, yPos));
    }

    @Override
    public void rotate() {
        rotationAngle = rotationAngle + 90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
    }

    @Override
    public void setID(String id) {
        this.ID = id;
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

    @Override
    public boolean isTrigger() {
        return isTriggered;
    }

    @Override
    public void trigger() {
        if (isTriggered == false) {
            isTriggered = true;
            if (hasConnections()){
                for(int i = 0; i < getConnections().size(); i++){
                    getConnections().get(i).trigger();
                }
            }

            colour = Color.GREEN;

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    colour = Color.RED;
                    isTriggered = false;
                }
            });

            timer.setRepeats(false);
            timer.start();

        }
    }


    @Override
    public ArrayList<IGizmo> getConnections() {
        return connections;
    }

    @Override
    public boolean hasConnections() {
        if (connections.size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    public void addConnection(IGizmo connected){
        System.out.println(String.format("Connecting gizmo %s to %s", this.ID, connected.getId()));
        connections.add(connected);
    }

    @Override
    public ArrayList<String> saveSignature(){
        ArrayList<String> gizLines = new ArrayList<>();
        gizLines.add("Square " + ID + " " + xPos + " " + yPos);

        return gizLines;
    }

}

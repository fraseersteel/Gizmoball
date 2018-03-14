package model;


import physics.Circle;
import physics.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CircleGizmo implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<IGizmo> connections;

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

        colour = Color.GREEN;

        connections = new ArrayList<>();
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

    public void setXYPos(double x, double y){
        xPos = (int) x;
        yPos = (int) y;
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
    public void trigger() {
        if (isTriggered == false) {
            System.out.println("Trigger circle");
            isTriggered = true;

            colour = Color.YELLOW;

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    colour = Color.GREEN;
                }
            });

            timer.setRepeats(false);
            timer.start();

        } else if (isTriggered == true) {
            isTriggered = false;
        }
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
    public ArrayList<String> saveSignature(){
        ArrayList<String> gizLines = new ArrayList<>();
        gizLines.add("Circle " + ID + " " + xPos + " " + yPos);

        return gizLines;
    }


}

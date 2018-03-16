package model;

import physics.Circle;
import physics.LineSegment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TriangleGizmo implements IGizmo {

    private ArrayList<Circle> circles;
    private ArrayList<LineSegment> lines;
    private ArrayList<IGizmo> connections;

    private String ID;
    private int xPos;
    private int yPos;
    private int rotationAngle;
    private Color colour;
    private boolean isTriggered;

    public TriangleGizmo(String id, int x, int y){
        this.ID = id;
        this.xPos = x;
        this.yPos = y;
        this.rotationAngle = 0;
        //starts in initial colour scheme
        isTriggered = false;

        colour = Color.BLUE;

        connections = new ArrayList<>();
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
        if (isTriggered == false) {
            isTriggered = true;
            if (hasConnections()){
                for(int i = 0; i < getConnections().size(); i++){
                    getConnections().get(i).trigger();
                }
            }

            colour = Color.CYAN;

            Timer timer = new Timer(3000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    colour = Color.BLUE;
                    isTriggered = false;
                }
            });

            timer.setRepeats(false);
            timer.start();

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
        gizLines.add("Triangle " + ID + " " + xPos + " " + yPos);

        int rotation = rotationAngle;
        while (rotation != 0) {
            rotation = rotation-90;
            gizLines.add("Rotate " + ID);
        }

        return gizLines;
    }
}



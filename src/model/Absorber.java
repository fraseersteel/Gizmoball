package model;


import physics.LineSegment;

import java.util.ArrayList;

public class Absorber {

    private String id;
    private int startX, startY;
    private int endX, endY;

    private ArrayList<String> keyConnections;
    private ArrayList<IGizmo> gizmoConnections;

    private boolean connectedItself;

    private ArrayList<LineSegment> edges;

    public Absorber(String name, int startX, int startY, int endX, int endY) {
        this.id = name;

        keyConnections = new ArrayList<>();
        gizmoConnections = new ArrayList<>();
        connectedItself = false;

        // These if statements ensure that startX isnt higher than endX etc (this causes problems)
        if (endX < startX) {
            this.startX = endX;
            this.endX = startX;
        }
        else
        {
            this.startX = startX;
            this.endX = endX;
        }
        if (endY < startY) {
            this.startY = endY;
            this.endY = startY;
        }
        else
        {
            this.startY = startY;
            this.endY = endY;
        }

        int length = endX - startX;
        int width = endY - endX;
        edges = new ArrayList<>();
        edges.add(new LineSegment(startX, startY, startX+length, startY+width));
    }

    public String getId() {
        return id;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public boolean occupies(int x, int y) {
        if (x >= startX && x <= endX-1 &&
                y >= startY && y <= endY-1)
            return true;
        return false;
    }

    public ArrayList<LineSegment> getEdges() {
        return edges;
    }

    public ArrayList<String> saveSignature(){
        ArrayList<String> absorber = new ArrayList<>();
        absorber.add("Absorber " + "A " + getStartX() + " "  + getStartY() + " " + getEndX() + " " + getEndY());

        return absorber;
    }

    public ArrayList<String> getKeyConnections() {
        return keyConnections;
    }

    public void addKeyConnection(String key) {
        keyConnections.add(key);
    }

    public void setConnectedItself(boolean itself) {
        connectedItself = itself;
    }

    public void addGizmoConnection(IGizmo gizmo) {
        gizmoConnections.add(gizmo);
    }

    public void removeGizmoConnection(IGizmo gizmo) {
        gizmoConnections.remove(gizmo);
    }

    public boolean isConnectedItself() {
        return connectedItself;
    }

    public ArrayList<IGizmo> getGizmoConnections() {
        return gizmoConnections;
    }
}

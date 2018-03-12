package model;


import physics.LineSegment;

import java.util.ArrayList;

public class Absorber {

    private String id;
    private int startX, startY;
    private int endX, endY;

    private ArrayList<LineSegment> edges;

    public Absorber(String name, int startX, int startY, int endX, int endY) {
        this.id = name;

        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;

        int length = (int) (endX - startX);
        int width = (int) (endY - endX);
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

    public ArrayList<LineSegment> getEdges() {
        return edges;
    }

    public ArrayList<String> saveSignature(){
        ArrayList<String> absorber = new ArrayList<>();
        absorber.add("Absorber " + "A " + getStartX() + " "  + getStartY() + " " + getEndX() + " " + getEndY());

        return absorber;
    }
}

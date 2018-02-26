package model;


import physics.LineSegment;

import java.util.ArrayList;

public class Absorber {

    private String id;
    private double startX, startY;
    private double endX, endY;

    private ArrayList<LineSegment> edges;

    public Absorber(String name, double startX, double startY, double endX, double endY) {
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

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }

    public ArrayList<LineSegment> getEdges() {
        return edges;
    }
}

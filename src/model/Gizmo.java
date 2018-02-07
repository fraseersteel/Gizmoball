package model;

public class Gizmo {

    private String id;
    private double xLocation;
    private double yLocation;


    public Gizmo(String id, double x, double y) {
        this.xLocation = x;
        this.yLocation = y;
        this.id = id;
    }

    public double getxLocation() {
        return xLocation;
    }

    public double getyLocation() { return yLocation; }
}

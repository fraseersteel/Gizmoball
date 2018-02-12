package model;

import java.util.ArrayList;

public class Gizmo implements IGizmo {

    private String id;
    private double xLocation;
    private double yLocation;
    private int angle;

    private ArrayList<Gizmo> triggers;


    public Gizmo(String id, double x, double y) {
        this.xLocation = x;
        this.yLocation = y;
        this.id = id;
        this.angle = 0;

        this.triggers = new ArrayList<>();
    }

    public String getId(){
        return id;
    }

    public void setId(String i){
        id = i;
    }

    public double getXPos() {

        return xLocation;
    }

    public void setXPos(double x){
        xLocation = x;
    }

    public double getYPos() {
        return yLocation;
    }

    public void setYPos(double y){
        yLocation = y;
    }

    public int getAngle() {
        return angle;
    }

    public void rotate() {
        angle = angle+90;

        if (angle == 360) {
            angle = 0;
        }
    }

    public ArrayList<Gizmo> getTriggers() {
        return triggers;
    }

    public void addTrigger(Gizmo gizmo) {
        triggers.add(gizmo);
    }
}

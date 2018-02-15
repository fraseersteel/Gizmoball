package flipperModel;


import flipperPhysics.LineSegment;

import java.util.ArrayList;

public class Gizmo {

    private String id;
    protected int xLocation;
    protected int yLocation;
    protected int rotationAngle;

    private ArrayList<Gizmo> triggers;


    public Gizmo(String id, int x, int y) {
        this.xLocation = x;
        this.yLocation = y;
        this.id = id;
        this.rotationAngle = 0;

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

    public void setXPos(int x){
        xLocation = x;
    }

    public double getYPos() {
        return yLocation;
    }

    public void setYPos(int y){
        yLocation = y;
    }

    public int getRotationAngle() {
        return rotationAngle;
    }

    public void rotate() {
        rotationAngle = rotationAngle +90;

        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
    }

    public ArrayList<Gizmo> getTriggers() {
        return triggers;
    }

    public void addTrigger(Gizmo gizmo) {
        triggers.add(gizmo);
    }

    public ArrayList<LineSegment> getLines() {
        return null;
    }

    public flipperPhysics.Circle getPhysicsCircle() {
        return null;
    }

}

package model;

import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public interface IGizmo {

    String getId();

    void setId(String i);

    int getxPos();

    int getyPos();

    void setID(String id);

    void setYPos(int y);

    void setXPos(int x);

    void resetPhysics();

    ArrayList<physics.LineSegment> getLines();

    ArrayList<physics.Circle> getCircles();

    int getRotationAngle();

    void addTrigger(IGizmo gizmo);

    void rotate();

    void setColour(Color color);

    Color getColour();

    boolean isTrigger();

    void trigger();

    ArrayList<IGizmo> getConnections();

    boolean hasConnections();

    void addConnection(IGizmo gizmo);

    ArrayList<String> saveSignature();

}
package model;

import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

public interface IGizmo {

    String getId();

    void setId(String i);

    int getxPos();

    int getyPos();

    void setYPos(int y);

    void setXPos(int x);

    ArrayList<physics.LineSegment> getLines();

    ArrayList<physics.Circle> getCircles();

    int getRotationAngle();

    void addTrigger(IGizmo gizmo);

    void rotate();

    void setColour(Color color);

    Color getColour();


}
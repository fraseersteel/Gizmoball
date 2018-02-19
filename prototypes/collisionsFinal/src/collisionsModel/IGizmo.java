package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public interface IGizmo {

    String getId();

    void setId(String i);

    int getxPos();

    int getyPos();

    void setYPos(int y);

    void setXPos(int x);

    ArrayList<LineSegment> getLines();

    ArrayList<collisionsPhysics.Circle> getCircles();

    int getRotationAngle();

    collisionsModel.CircleGizmo getCircle();

    double getRadius();



}
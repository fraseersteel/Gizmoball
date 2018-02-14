package absorberModel;

import absorberPhysics.LineSegment;

import java.util.ArrayList;

public interface IGizmo {

    String getId();

    void setId(String i);

    int getxPos();

    int getyPos();

    void setYPos(int y);

    void setXPos(int x);

    ArrayList<LineSegment> getLines();

    ArrayList<Circle> getCircles();

    public double[] to2D();


}
package model;

import java.util.ArrayList;

import physics.Circle;
import physics.LineSegment;

public class RightFlipper extends Gizmo {

    public RightFlipper(String id, double x, double y) {

        super(id, x, y);
    }

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    //easy way to get the corners of any given object to easily add the zero-radius circles?
    private ArrayList<String> connections = new ArrayList<String>();
    private ArrayList<String> keyConnections = new ArrayList<String>();
}
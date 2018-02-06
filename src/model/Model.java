package model;

import physics.Geometry;
import physics.LineSegment;

import java.util.ArrayList;

public class Model {

    private ArrayList<LineSegment> lines;
    private Ball ball;
    private ArrayList<Circle> circles;
    private ArrayList<Triangle> triangles;
    private ArrayList<Square> squares;

    // do we really need each of these individually? could have an interface and
    // a single ArrayList for all gizmos?
    // private ArrayList<IGizmo> gizmos; ???
    // and then for each item in the list get its name and act accordingly


    public Model() {

        circles = new ArrayList<Circle>();
        triangles = new ArrayList<Triangle>();
        squares = new ArrayList<Square>();
    }


    public void addCircle(Circle c) {
        circles.add(c);
    }

    public void addTriangle(Triangle t) {
        triangles.add(t);
    }

    public void addSquare(Square s) {
        squares.add(s);
    }

}
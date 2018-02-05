package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;

import java.util.ArrayList;

public class Model {

    private Ball ball;
    private ArrayList<Circle> circles;
    private ArrayList<Triangle> triangles;
    private ArrayList<Square> squares;


    public Model(){

        circles = new ArrayList<Circle>();
        triangles = new ArrayList<Triangle>();
        squares = new ArrayList<Square>();
    }
}

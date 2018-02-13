package loadModel;

import java.util.ArrayList;

import loadPhysics.Circle;
import loadPhysics.LineSegment;
import loadPhysics.Vect;

public class RightFlipper extends Gizmo {

    private Vect lineCor; //center of rotation for lines
    private Vect circleCor //center of rotation for circles

    public RightFlipper(String id, double x, double y) {

        super(id, x, y);
        //lineCor = new Vect something something
        //LineSegment l1 = new LineSegment something something
        //lines.add(l1);
        //LineSegment l2 = new LineSegment something something
        //lines.add(l2);

        //figure out exact coordinates
        //what's the relationship between the backend structure of a gizmo i.e. the lines and circles
        //and the frontend painting thereof?
        //like when you perform a rotational operation on the backend how does that translate?

        //so when the actual rotation happens, in whatever method that takes place in, it goes like
        //lines.set(0, Geometry.rotateAround(lines.get(0), center of rotation, angle of rotation));
        //and repeat for everything that needs rotating
    }

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    //easy way to get the corners of any given object to easily add the zero-radius circles?
    private ArrayList<String> connections = new ArrayList<String>();
    private ArrayList<String> keyConnections = new ArrayList<String>();
}
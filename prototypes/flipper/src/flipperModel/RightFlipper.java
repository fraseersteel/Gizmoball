package flipperModel;

import java.util.ArrayList;

import flipperPhysics.Circle;
import flipperPhysics.LineSegment;
import flipperPhysics.Vect;

//was wondering if we really needed two flipper classes or could have just one
//with a variable indicating its direction

public class RightFlipper extends Gizmo {

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private ArrayList<String> connections = new ArrayList<String>();
    private ArrayList<String> keyConnections = new ArrayList<String>();

    private Vect lineCor; //center of rotation for lines
    private Vect circleCor; //center of rotation for circles
    //actually could probably be just the same value not sure

    private int height; //height and width of the flippers
    private int width; //there is no actual rectangle being created with these dimensions
    //they're just sort of references for positioning things
    //also need actual values for these
    private int radius;

    public RightFlipper(String id, int xPos, int yPos) {

        super(id, xPos, yPos);
        //lineCor = new Vect something something
        LineSegment l1 = new LineSegment(xPos, yPos, xPos, yPos-height);
        LineSegment l2 = new LineSegment(xPos+width, yPos, xPos+width, yPos-height);
        lines.add(l1);
        lines.add(l2);
        //circles for rounded ends of flipper
        Circle c1 = new Circle(xPos+(width/2), yPos-radius, radius);
        Circle c2 = new Circle (xPos+(width/2), (yPos-height)+radius, radius);
        circles.add(c1);
        circles.add(c2);
        //zero-radius circles for ends of lines
        Circle z1 = new Circle(xPos, yPos, 0);
        Circle z2 = new Circle(xPos, yPos-height, 0);
        Circle z3 = new Circle(xPos+width, yPos, 0);
        Circle z4 = new Circle(xPos+width, yPos-height, 0);

        //so when the actual rotation happens, in whatever method that takes place in, it goes like
        //lines.set(0, Geometry.rotateAround(lines.get(0), center of rotation, angle of rotation));
        //and repeat for everything that needs rotating
    }



    public ArrayList<LineSegment> getLines() {

        return lines;
    }

    public ArrayList<Circle> getCircles() {

        return circles;
    }

    public Vect getCircleCor() {

        return circleCor;
    }

    public Vect getLineCor() {
        return lineCor;
    }
}
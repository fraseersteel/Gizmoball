package flipperModel;

import java.util.ArrayList;

import flipperPhysics.*;

//was wondering if we really needed two flipper classes or could have just one
//with a variable indicating its direction

public class RightFlipper extends Gizmo {

    private ArrayList<LineSegment> lines = new ArrayList<LineSegment>();
    private ArrayList<Circle> circles = new ArrayList<Circle>();
    private ArrayList<String> connections = new ArrayList<String>();
    private ArrayList<String> keyConnections = new ArrayList<String>();

   //private boolean isStopped = false;
    private boolean isFlipped = false;

    //will probably prove useful for actual flippers

    private Vect lineCor; //center of rotation for lines
    private Vect circleCor; //center of rotation for circles
    //actually could probably be just the same value not sure

    private int height = 50; //height and width of the flippers
    private int width = 20;//there is no actual rectangle being created with these dimensions
    //they're just sort of references for positioning things
    //also need actual values for these
    private int radius = 10 ;

    public RightFlipper(String id, int xPos, int yPos) {

        super(id, xPos, yPos);
        lineCor = new Vect ( xPos, yPos-height);
        LineSegment l1 = new LineSegment(xPos, yPos, xPos, yPos-height);
        LineSegment l2 = new LineSegment(xPos+width, yPos, xPos+width, yPos-height);
        lines.add(l1);
        lines.add(l2);
        //circles for rounded ends of flipper
        Circle c1 = new Circle(xPos+radius, yPos-radius, radius);
        Circle c2 = new Circle (xPos+radius, (yPos-height)+radius, radius);
        circles.add(c1);
        circles.add(c2);
        //zero-radius circles for ends of lines
        Circle z1 = new Circle(xPos, yPos, 0);
        Circle z2 = new Circle(xPos, yPos-height, 0);
        Circle z3 = new Circle(xPos+width, yPos, 0);
        Circle z4 = new Circle(xPos+width, yPos-height, 0);
        circles.add(z1);
        circles.add(z2);
        circles.add(z3);
        circles.add(z4);

    }

    public void rotate() {

        Angle angle;
        if (!isFlipped) {
            angle = new Angle(1.57);
        } else {
            angle = new Angle(-1.57); //cannot get the lines straight no matter how many
                                            //decimal points deep I go
        }
        System.out.print("executed RightFlipper.rotate()");

        circles.set(0, Geometry.rotateAround(circles.get(0), lineCor, angle));
        circles.set(1, Geometry.rotateAround(circles.get(1), lineCor, angle));
        circles.set(2, Geometry.rotateAround(circles.get(2), lineCor, angle));
        circles.set(3, Geometry.rotateAround(circles.get(3), lineCor, angle));
        circles.set(4, Geometry.rotateAround(circles.get(4), lineCor, angle));
        circles.set(5, Geometry.rotateAround(circles.get(5), lineCor, angle));

        lines.set(0, Geometry.rotateAround(lines.get(0), lineCor, angle));
        lines.set(1, Geometry.rotateAround(lines.get(1), lineCor, angle));
        isFlipped = !isFlipped;
    }

   /* public void flip(){
        isFlipped = !isFlipped;
        //isStopped = false;
        System.out.print("Executed RightFlipper.flip()");

        gone but not forgotten
    } */


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
package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Triangle extends Gizmo {

    public Triangle(String id, int x, int y) {
        super(id,x,y);
    }

    public ArrayList<LineSegment> getLines() {
        ArrayList<LineSegment> lines = new ArrayList<>();



        int x = xLocation*25;
        int y = yLocation*25;

        switch (rotationAngle) {
            case 0:
                // | /
                // |/
                lines.add(new LineSegment(x, y, x+25, y));
                lines.add(new LineSegment(x+25, y, x, y+25));
                lines.add(new LineSegment(x, y+25, x, y));
                break;
            case 90:
                // \ |
                //  \|
                lines.add(new LineSegment(x, y, x+25, y));
                lines.add(new LineSegment(x+25, y, x+25, y+25));
                lines.add(new LineSegment(x+25, y+25, x, y));
                break;
            case 180:
                //  /|
                // /_|
                //lines.add(new LineSegment(x, y+20, x+1, y+20));
                lines.add(new LineSegment(x+25, y, x+25, y+25));
                lines.add(new LineSegment(x+25, y+25, x, y+25));
                lines.add(new LineSegment(x, y+25, x+25, y));
                break;
            case 270:
                // |\
                // |_\
                lines.add(new LineSegment(x, y, x+25, y+25));
                lines.add(new LineSegment(x+25, y+25, x, y+25));
                lines.add(new LineSegment(x, y+25, x, y));
                break;
        }


        return lines;

    }
}

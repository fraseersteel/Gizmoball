package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Square extends Gizmo {

    public Square(String id, int x, int y) {
        super(id,x,y);
    }

    @Override
    public ArrayList<LineSegment> getLines() {
        ArrayList<LineSegment> lines = new ArrayList<>();

        int x = xLocation*25;
        int y = yLocation*25;

        //top
        lines.add(new LineSegment(x, y, x+25, y));
        lines.add(new LineSegment(x+25, y, x+25, y+25));
        lines.add(new LineSegment(x+25, y+25, x, y+25));
        lines.add(new LineSegment(x, y+25, x, y));

        return lines;
    }
}
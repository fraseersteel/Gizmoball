package collisionsModel;

import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Square extends Gizmo {

    public Square(String id,double x, double y) {
        super(id,x,y);
    }

    @Override
    public ArrayList<LineSegment> getLines() {
        ArrayList<LineSegment> lines = new ArrayList<>();
        // basically top line of a square
        lines.add(new LineSegment((int)xLocation*25, (int)yLocation*25, ((int)xLocation+1)*25, (int)yLocation*25));

        return lines;

    }
}
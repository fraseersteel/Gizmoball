package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.LineSegment;

import java.util.ArrayList;

public class Square extends Gizmo {

    public Square(String id, int x, int y) {
        super(id,x,y);

        addCircles();
        addLines();
    }

    private void addCircles() {
        int x = xLocation*25;
        int y = yLocation*25;

        physicsCircles.add(new Circle(x,y,0));
        physicsCircles.add(new Circle(x+25,y,0));
        physicsCircles.add(new Circle(x+25,y,y+25));
        physicsCircles.add(new Circle(x,y+25,0));

    }

    private void addLines() {
        int x = xLocation*25;
        int y = yLocation*25;

        physicsLines.add(new LineSegment(x, y, x+25, y));
        physicsLines.add(new LineSegment(x+25, y, x+25, y+25));
        physicsLines.add(new LineSegment(x+25, y+25, x, y+25));
        physicsLines.add(new LineSegment(x, y+25, x, y));
    }

}
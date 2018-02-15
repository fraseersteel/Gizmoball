package collisionsModel;

import collisionsPhysics.LineSegment;
import collisionsPhysics.Circle;

import java.util.ArrayList;

public class Triangle extends Gizmo {

    public Triangle(String id, int x, int y) {
        super(id,x,y);

        System.out.println("new triangle");

        addLines();
        addCircles();
    }

    private void addLines() {

        int x = xLocation*25;
        int y = yLocation*25;

        System.out.println(rotationAngle);
        switch (rotationAngle) {
            case 0:
                // | /
                // |/
                physicsLines.add(new LineSegment(x, y, x+25, y));
                physicsLines.add(new LineSegment(x+25, y, x, y+25));
                physicsLines.add(new LineSegment(x, y+25, x, y));
                break;
            case 90:
                // \ |
                //  \|
                physicsLines.add(new LineSegment(x, y, x+25, y));
                physicsLines.add(new LineSegment(x+25, y, x+25, y+25));
                physicsLines.add(new LineSegment(x+25, y+25, x, y));
                break;
            case 180:

                //  /|
                // /_|
                //lines.add(new LineSegment(x, y+20, x+1, y+20));
                physicsLines.add(new LineSegment(x+25, y, x+25, y+25));
                physicsLines.add(new LineSegment(x+25, y+25, x, y+25));
                physicsLines.add(new LineSegment(x, y+25, x+25, y));
                break;
            case 270:
                // |\
                // |_\
                physicsLines.add(new LineSegment(x, y, x+25, y+25));
                physicsLines.add(new LineSegment(x+25, y+25, x, y+25));
                physicsLines.add(new LineSegment(x, y+25, x, y));
                break;
        }
    }

    private void addCircles() {
        int x = xLocation*25;
        int y = yLocation*25;

        switch (rotationAngle) {
            case 0:
                // | /
                // |/
                physicsCircles.add(new Circle(x, y,0));
                physicsCircles.add(new Circle(x+25, y, 0));
                physicsCircles.add(new Circle(x, y+25, 0));
                break;
            case 90:
                // \ |
                //  \|
                physicsCircles.add(new Circle(x, y, 0));
                physicsCircles.add(new Circle(x+25, y, 0));
                physicsCircles.add(new Circle(x+25, y+25, 0));
                break;
            case 180:

                //  /|
                // /_|
                //lines.add(new LineSegment(x, y+20, x+1, y+20));
                physicsCircles.add(new Circle(x+25, y, 0));
                physicsCircles.add(new Circle(x+25, y+25, 0));
                physicsCircles.add(new Circle(x, y+25, 0));
                break;
            case 270:
                // |\
                // |_\
                physicsCircles.add(new Circle(x, y, 0));
                physicsCircles.add(new Circle(x+25, y+25, 0));
                physicsCircles.add(new Circle(x, y+25, 0));
                break;
        }
    }

    /**
     * Overrides parent rotate since triangles need new physics lines on rotation.
     */
    @Override
    public void rotate() {
        rotationAngle = rotationAngle +90;
        if (rotationAngle == 360) {
            rotationAngle = 0;
        }
        updateLines();
    }

    private void updateLines() {
        physicsLines.clear();
        addLines();
    }
}

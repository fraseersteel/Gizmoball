package collisionsModel;

import collisionsPhysics.Circle;

import java.util.ArrayList;

public class CircleGizmo extends Gizmo {

    public CircleGizmo(String id, int x, int y) {
        super(id,x,y);

        circle = new Circle(x*25, y*25, 12.5);
    }


    public Circle getCircle() {

        int x = xLocation*25;
        int y = yLocation*25;

        return new collisionsPhysics.Circle(x, y, 12.5);
    }
}

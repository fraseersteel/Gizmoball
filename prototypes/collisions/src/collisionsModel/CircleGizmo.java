package collisionsModel;

public class CircleGizmo extends Gizmo {

    public CircleGizmo(String id, int x, int y) {
        super(id,x,y);

        circle = new collisionsPhysics.Circle(x*25, y*25, 12);
    }

    /*
    @Override
    public collisionsPhysics.Circle getPhysicsCircle() {

        int x = xLocation*25;
        int y = yLocation*25;

        return new collisionsPhysics.Circle(x, y, 12);
    }
    */
}

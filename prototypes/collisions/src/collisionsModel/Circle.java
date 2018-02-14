package collisionsModel;

public class Circle extends Gizmo {

    public Circle(String id, int x, int y) {
        super(id,x,y);
    }

    @Override
    public collisionsPhysics.Circle getPhysicsCircle() {

        int x = xLocation*25;
        int y = yLocation*25;

        return new collisionsPhysics.Circle(x, y, 12);
    }
}

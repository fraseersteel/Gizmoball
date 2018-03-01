import model.Ball;
import model.CircleGizmo;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test.*;

import static org.junit.Assert.*;


public class CircleGizmoTest {

    CircleGizmo circleGizmo;

    @Before
    public void setUP(){
        circleGizmo = new CircleGizmo("test",10,15);
    }

    @Test
    public void testXPos(){
        assertEquals(10,circleGizmo.getxPos());
    }

    @Test
    public void testYPos(){
        assertEquals(15,circleGizmo.getyPos());
    }
}

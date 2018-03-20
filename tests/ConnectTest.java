import model.CircleGizmo;
import model.SquareGizmo;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConnectTest {

    private Model m = new Model();
    private CircleGizmo circleGizmo;
    private SquareGizmo squareGizmo;

    @Before
    public void setUP() {
        circleGizmo = new CircleGizmo("C1", 10, 15);
        squareGizmo = new SquareGizmo("S1", 15, 15);
    }

    //Test to check initial connections are empty
    @Test
    public void testHasConnections1() {
        assertEquals(0, circleGizmo.getConnections().size());
    }

    //Test to connect two gizmos
    @Test
    public void testConnect() {
        m.connect(circleGizmo, squareGizmo);
        assertEquals(squareGizmo, circleGizmo.getConnections().get(0));
    }


    //Test to check that triggering a gizmo also triggers connected gizmos
    @Test
    public void testTrigger(){
        m.connect(circleGizmo, squareGizmo);
        circleGizmo.trigger();
        assertEquals(true, squareGizmo.isTrigger());
    }

    //Test to disconnect gizmos
    @Test
    public void testDisconnect(){
        m.connect(circleGizmo, squareGizmo);
        m.disconnect(circleGizmo, squareGizmo);
        assertEquals(0, circleGizmo.getConnections().size());
    }

}

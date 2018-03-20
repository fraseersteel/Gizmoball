import exceptions.InvalidGizmoException;
import model.*;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class ModelTests {

    private Model model;
    private CircleGizmo c;
    private LeftFlipper lf;
    private RightFlipper rf;
    private Ball ball;
    private SquareGizmo s;

    @Before
    public void setUp() throws Exception {
        model = new Model();
        s = new SquareGizmo("test",15,15);
        c = new CircleGizmo("test",1,1);
        lf = new LeftFlipper("test", 3,3);
        rf = new RightFlipper("test",5,5);
        ball = new Ball("ball",20,20,1,1);
    }


    @Test
    public void testAddGizmo(){
        try {
            model.addGizmo(c);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
        try {
            assertFalse(model.checkLegalPlace(c,c.getxPos(),c.getyPos()));
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLegalPlaceFlipper(){
        try {
            assertTrue(model.checkLegalPlace(lf,lf.getxPos(),lf.getyPos()));
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testMoveBall(){
        model.setBall(ball);
        try {
            model.addGizmo(c);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
        model.moveBall();

        model.moveBallTime(10,ball);
    }


    @Test
    public void removeTest(){
        try {
            model.addGizmo(lf);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
        model.removeItem(lf);
        assertNull(model.findGizmoByCoords(lf.getxPos(),lf.getyPos()));
    }

    @Test
    public void addFlipperOntopTest() {
        try {
            model.addGizmo(lf);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }

        try {
            model.checkLegalPlace(new LeftFlipper("leftFlipper",3,3),3,3);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddAbsorber(){
            model.setAbsorber(new Absorber("abs",1,1,20,20));
        try {
            model.checkLegalPlace(lf,5,5);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getTUC(){
        try {
            model.addGizmo(c);
            model.addGizmo(rf);
            model.addGizmo(lf);
            model.addGizmo(s);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }

        model.setBall(ball);
        ball.start();
        model.moveBall();
    }
}

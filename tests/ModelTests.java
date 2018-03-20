import exceptions.InvalidGizmoException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;


import static org.junit.Assert.*;

public class ModelTests {

    private Model model;
    private CircleGizmo c;
    private TriangleGizmo t;
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
        t = new TriangleGizmo("test",20,15);
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
            rf.flip();
            model.addGizmo(lf);
            lf.flip();
            model.addGizmo(s);
            model.addGizmo(t);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }

        model.setBall(ball);
        ball.start();
        model.moveBall();
    }


    @Test
    public void setGravity(){
        model.setGravity(5);
        assertEquals((int) model.getGravity(),5);
    }

    @Test
    public void setFriction(){
        model.setFriction(2);
        assertEquals((int) model.getFriction(),2);
    }


    @Test
    public void testBallWithAbsorber(){
        //absorber takes up whole map
        model.setBall(ball);
        model.setAbsorber(new Absorber("abs",1,1,20,20));
        model.moveBall();
    }



    @Test
    public void testCollideAndShootAbsorber() {
        Ball ballAbs = new Ball("ball", 1, 1, 0, 1);
        model.setBall(ballAbs);
        model.setAbsorber(new Absorber("test", 1, 18, 20, 20));
        model.moveBallTime(19, ballAbs);
        model.shootAbsorber();
        assertEquals(ballAbs.getVelocity(),new Vect(0, -30));

    }


    @Test
    public void testDoesNotCollideWithAbsorber(){
        Ball ballAbs = new Ball("ball",1,1,0,1);
        model.setBall(ballAbs);
        model.setAbsorber(new Absorber("test",1,18,20,20));
        model.moveBallTime(10,ballAbs);
        // if the ball has not collided with the absorber it should maintain the same velocity
        assertEquals(ballAbs.getVelocity(),new Vect(0,1));
    }

    @Test
    public void flipperMap(){
        LeftFlipper l1 = new LeftFlipper("test",1,1);
        LeftFlipper l2 = new LeftFlipper("test",1,3);
        LeftFlipper l3 = new LeftFlipper("test",1,5);
        LeftFlipper l4 = new LeftFlipper("test",1,7);
        LeftFlipper l5 = new LeftFlipper("test",1,9);
        LeftFlipper l6 = new LeftFlipper("test",1,11);
        LeftFlipper l7 = new LeftFlipper("test",1,13);
        LeftFlipper l8 = new LeftFlipper("test",1,15);
        LeftFlipper l9 = new LeftFlipper("test",1,17);
        LeftFlipper l10 = new LeftFlipper("test",1,19);

        try {
            model.checkLegalPlace(c,1,1);
        } catch (InvalidGizmoException e) {
            e.printStackTrace();
        }
    }
}

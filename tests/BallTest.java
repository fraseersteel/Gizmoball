import model.Ball;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BallTest {

    private Ball ball;

    @Before
    public void setUP(){
        ball = new Ball("B1", 100, 100, 100, 100);
    }

    //Test to check if correct ball ID is displayed initially
    @Test
    public void testID() {
        assertTrue(ball.getId().equals("B1"));
    }

    //Test to check if correct ball ID is displayed after setting new ID
    @Test
    public void testID2() {
        ball.setId("Ball_1");
        assertTrue(ball.getId().equals("Ball_1"));
    }

    //Test to check if correct ball velocity was set initially
    @Test
    public void testVelocity(){
        assertTrue(ball.getXVelo() == 100);
        assertTrue(ball.getYVelo() == 100);
    }

    //Test to check if correct ball velocity is displayed after setting new speed
    @Test
    public void testVelocity2(){
        ball.setVelocity(new Vect(50, 200));
        assertTrue(ball.getXVelo() == 50);
        assertTrue(ball.getYVelo() == 200);
    }

    //Test to check if the correct ball coordinates were set initially
    @Test
    public void testCoord(){
        assertTrue(ball.getX() == 100);
        assertTrue(ball.getY() == 100);
    }

    //Test to check if the correct ball coordinates are displayed after setting new values
    @Test
    public void testCoord2(){
        ball.setXPos(80);
        ball.setYPos(130);
        assertTrue(ball.getX() == 80);
        assertTrue(ball.getY() == 130);
    }

    //Test to check if the ball is currently stopped
    @Test
    public void testStopped(){
        assertTrue(ball.isStopped()); //Tests for initial state
        ball.start();
        assertFalse(ball.isStopped());
        ball.stop();
        assertTrue(ball.isStopped());
    }

    //Test to check if correct circle radius was set initially
    @Test
    public void testRadius(){
        assertTrue(ball.getRadius() == 0.4);
    }

/*
    //Test to check if correct circle radius is displayed after setting a new value
    //Currently we don't have a setter to change rhe initial ball radius (something to think about)
    @Test
    public void testRadius2(){
        ball.setRadius(0.9);
        assertTrue(ball.getRadius() == 0.9);
    }
*/
}

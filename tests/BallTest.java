import model.Ball;
import org.junit.Before;
import org.junit.Test;
import physics.Vect;

import java.awt.*;

import static org.junit.Assert.*;


public class BallTest {

    private Ball ball;

    @Before
    public void setUP(){
        ball = new Ball("B1", 100, 100, 100, 100);
    }

    //Test to check if correct ball ID is displayed initially
    @Test
    public void testID() { assertEquals("B1",ball.getId());}

    //Test to check if correct ball ID is displayed after setting new ID
    @Test
    public void testID2() {
        ball.setId("Ball_1");
        assertEquals("Ball_1", ball.getId());
    }

    //Test to check if correct ball velocity was set initially
    @Test
    public void testVelocity(){
        assertEquals(100,ball.getXVelo(),0);
        assertEquals(100,ball.getYVelo(),0);
    }

    //Test to check if correct ball velocity is displayed after setting new speed
    @Test
    public void testVelocity2(){
        ball.setVelocity(new Vect(50, 200));
        assertEquals(50,ball.getXVelo(),0);
        assertEquals(200,ball.getYVelo(),0);
    }

    //Test to check if the correct ball coordinates were set initially
    @Test
    public void testCoord(){
        assertEquals(100,ball.getXPos());
        assertEquals(100,ball.getYPos());
    }

    //Test to check if the correct ball coordinates are displayed after setting new values
    @Test
    public void testCoord2(){
        ball.setXYPos(80,130);
        assertEquals(80, ball.getXPos());
        assertEquals(130, ball.getYPos());
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
    public void testRadius() { assertEquals(0.4, ball.getRadius(),0);}

    //Test to check if the correct ball radius is displayed after setting new value
    @Test
    public void testRadius2(){
        ball.setRadius(0.9);
        assertEquals(0.9, ball.getRadius(),0);
    }

    //Test to check if the correct Save Signature is displayed
    @Test
    public void testSaveSignature(){
        ball.setId("Ball_2");
        ball.setXYPos(50,60);
        ball.setVelocity(new Vect(15, 18));
        assertEquals("Ball Ball_2 50 60 15 18",ball.saveSignature());
    }

    @Test
    public void testColour(){
        ball.setColour(Color.CYAN);
        assertEquals(Color.CYAN,ball.getColour());
    }

/*
    //Currently can't set the colour of the ball
    //Test to check the initial ball colour
    @Test
    public void testColor(){

    }*/
}

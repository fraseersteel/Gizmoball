import model.LeftFlipper;
import model.Model;
import model.RightFlipper;
import org.junit.Before;
import org.junit.Test;
import physics.LineSegment;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FlipperTest {
    private Model m;
    private LeftFlipper left;
    private RightFlipper right;

    @Before
    public void setUp(){
        m = new Model();
        left = new LeftFlipper("L1",5, 5);
        right = new RightFlipper("R1", 10, 10);
    }

    //Test to check initial IDs
    @Test
    public void testID(){
        assertEquals("L1", left.getId());
        assertEquals("R1", right.getId());
    }

    //Test to check IDs after changing
    @Test
    public void testID2(){
        left.setID("Left1");
        right.setID("Right1");
        assertEquals("Left1", left.getId());
        assertEquals("Right1", right.getId());
    }

    //Test to check the initial x coordinate
    @Test
    public void testXPos(){
        assertEquals(5, left.getxPos());
        assertEquals(10, right.getxPos());
    }

    //Test to check the x coordinate after changing
    @Test
    public void testXPos2(){
        left.setXPos(4);
        right.setXPos(9);
        assertEquals(4, left.getxPos());
        assertEquals(9, right.getxPos());
    }

    //Test to check the initial y coordinate
    @Test
    public void testYPos(){
        assertEquals(5, left.getyPos());
        assertEquals(10, right.getyPos());
    }

    //Test to check the y coordinate after changing
    @Test
    public void testYPos2(){
        left.setYPos(4);
        right.setYPos(9);
        assertEquals(4, left.getyPos());
        assertEquals(9, right.getyPos());
    }

    //Test to check lines that compose left flipper
    @Test
    public void testLeftLines(){
        assertEquals(2, left.getLines().size());
        ArrayList<LineSegment> leftLines = left.getLines();
        assertEquals(5, leftLines.get(0).p1().x(), 0);
        assertEquals(5, leftLines.get(0).p1().y(), 0);
        assertEquals(5+0.5, leftLines.get(0).p2().x(), 0);
        assertEquals(5+2, leftLines.get(0).p2().y(), 0);
        assertEquals(5+0.5, leftLines.get(1).p1().x(), 0);
        assertEquals(5, leftLines.get(1).p1().y(), 0);
        assertEquals(5+0.5, leftLines.get(1).p2().x(), 0);
        assertEquals(5+2, leftLines.get(1).p2().y(), 0);

    }

    //Checking positions of lines for right flipper
    @Test
    public void testRightLines(){
        assertEquals(2, right.getLines().size());
        ArrayList<LineSegment> rightLines = right.getLines();
        assertEquals(10+1.5, rightLines.get(0).p1().x(), 0);
        assertEquals(10, rightLines.get(0).p1().y(), 0);
        assertEquals(10+1.5, rightLines.get(0).p2().x(), 0);
        assertEquals(10+2, rightLines.get(0).p2().y(), 0);
        assertEquals(10+2, rightLines.get(1).p1().x(), 0);
        assertEquals(10, rightLines.get(1).p1().y(), 0);
        assertEquals(10+2, rightLines.get(1).p2().x(), 0);
        assertEquals(10+2, rightLines.get(1).p2().y(), 0);
    }

    //Test changing colour
    @Test
    public void testColor(){
        left.setColour(Color.GREEN);
        right.setColour(Color.BLUE);
        assertEquals(Color.GREEN, left.getColour());
        assertEquals(Color.BLUE, right.getColour());
    }

    //Check coordinates remain the same after rotation
    @Test
    public void testRotationLines(){
        left.rotate();
        right.rotate();
        assertEquals(5, left.getxPos());
        assertEquals(10, right.getyPos());
    }

    //Testing save signature is correct
    @Test
    public void testSaveSignature(){
        assertEquals("LeftFlipper L1 5 5", left.saveSignature().get(0));
        assertEquals("RightFlipper R1 10 10", right.saveSignature().get(0));
    }


}



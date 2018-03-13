import model.CircleGizmo;
import org.junit.Before;
import org.junit.Test;
import physics.Circle;

import java.awt.*;

import static org.junit.Assert.*;

public class CircleGizmoTest {

    private CircleGizmo circleGizmo;

    @Before
    public void setUP(){
        circleGizmo = new CircleGizmo("C1",10,15);
    }

    //Test to check if initial circle ID is displayed correctly
    @Test
    public void testID(){
        assertEquals("C1", circleGizmo.getId());
    }

    //Test to check if circle ID is displayed correctly after setting a new ID
    @Test
    public void testID2(){
        circleGizmo.setId("Circle_1");
        assertEquals("Circle_1", circleGizmo.getId());
    }

    //Test to check the initial circle x position
    @Test
    public void testXPos(){ assertEquals(10,circleGizmo.getxPos()); }

    //Test to check if circles x position is displayed correctly after setting a new value
    @Test
    public void testXPos2(){
        circleGizmo.setXPos(20);
        assertEquals(20,circleGizmo.getxPos());
    }

    //Test to check the initial circle y position
    @Test
    public void testYPos(){
        assertEquals(15,circleGizmo.getyPos());
    }

    //Test to check if circles y position is displayed correctly after setting a new value
    @Test
    public void testYPos2(){
        circleGizmo.setYPos(12);
        assertEquals(12,circleGizmo.getyPos());
    }

    //Test to check if the circles retain same coordinates after rotation
    @Test
    public void testRotationLines(){
        circleGizmo.setXPos(55);
        circleGizmo.setYPos(66);
        circleGizmo.rotate();
        assertEquals(55, circleGizmo.getxPos());
        assertEquals(66,circleGizmo.getyPos());
    }

    //Test to check if the colour is displayed correctly after setting a new value
    @Test
    public void testColor(){
        circleGizmo.setColour(Color.GREEN);
        assertEquals(Color.GREEN,circleGizmo.getColour());
    }

    //Test to check if the rotation angle always stays at 0
    @Test
    public void testRotation(){
        circleGizmo.rotate();
        assertEquals(0, circleGizmo.getRotationAngle());
    }

    //Test to check that the correct number of circles are in the arraylist
    //Test to check x and y coordinates of the position 0 circle
    //Test to check the radius of the position 0 circle
    @Test
    public void testCircles(){
        assertEquals(1,circleGizmo.getCircles().size());
        Circle c = circleGizmo.getCircles().get(0);
        assertEquals(10+0.5, c.getCenter().x(),0);
        assertEquals(15+0.5, c.getCenter().y(),0);
        assertEquals(0.5, c.getRadius(),0);
    }

    //Test to check the initial number of connections
    @Test
    public void testHasConnections(){
        assertEquals(0,circleGizmo.getConnections().size());
    }

    //Test to check if the number of lines for a circle gizmo are 0
    @Test
    public void testLines(){
        assertEquals(null,circleGizmo.getLines());
    }

    //Test to check position 0 string
    @Test
    public void testSaveSignature(){
        circleGizmo.setID("Circle_2");
        circleGizmo.setXYPos(16,20);
        assertEquals("Circle Circle_2 16 20", circleGizmo.saveSignature().get(0));
    }



}

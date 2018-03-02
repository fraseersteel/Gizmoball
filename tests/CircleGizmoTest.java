import model.CircleGizmo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CircleGizmoTest {

    CircleGizmo circleGizmo;

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
        assertEquals(12,circleGizmo.getxPos());
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

}

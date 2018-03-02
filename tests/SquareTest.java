import model.SquareGizmo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareTest {

    SquareGizmo squareGizmo;

    @Before
    public void setUP(){
        squareGizmo = new SquareGizmo("S1",25,20);
    }

    //Test to check if initial square ID is displayed correctly
    @Test
    public void testID(){
        assertEquals("S1", squareGizmo.getId());
    }

    //Test to check if square ID is displayed correctly after setting a new ID
    @Test
    public void testID2(){
        squareGizmo.setId("Square_1");
        assertEquals("Square_1", squareGizmo.getId());
    }

    //Test to check the initial x position of the square
    @Test
    public void testXPos(){ assertEquals(25,squareGizmo.getxPos()); }

    //Test to check if squares x position is displayed correctly after setting a new value
    @Test
    public void testXPos2(){
        squareGizmo.setXPos(15);
        assertEquals(15,squareGizmo.getxPos());
    }

    //Test to check the initial y position of the square
    @Test
    public void testYPos(){
        assertEquals(20,squareGizmo.getyPos());
    }

    //Test to check if squares y position is displayed correctly after setting a new value
    @Test
    public void testYPos2(){
        squareGizmo.setYPos(17);
        assertEquals(17,squareGizmo.getxPos());
    }

    //Test to check if the correct number of lines are used to construct the square gizmo
    @Test
    public void testNumOfLines(){
        assertEquals(4, squareGizmo.getLines().size());
    }

    //Test to check if the correct number of 0 size circles are used for square gizmo
    @Test
    public void testNumOfCircles(){
        assertEquals(4, squareGizmo.getCircles().size());
    }

    //Test to check if the squares retain same number of lines after rotation
    @Test
    public void testRotationLines(){
        squareGizmo.rotate();
        assertEquals(4, squareGizmo.getLines().size());
    }

    //Test to check if the squares retain same number of 0 size circles after rotation
    @Test
    public void testRotationCircles(){
        squareGizmo.rotate();
        assertEquals(4, squareGizmo.getCircles().size());
    }
}

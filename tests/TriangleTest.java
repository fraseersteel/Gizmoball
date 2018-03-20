import model.TriangleGizmo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleTest {

    TriangleGizmo triangleGizmo;

    @Before
    public void setUP(){
        triangleGizmo = new TriangleGizmo("T1",11,19);
    }

    //Test to check if initial triangle ID is displayed correctly
    @Test
    public void testID(){
        assertEquals("T1", triangleGizmo.getId());
    }

    //Test to check if triangle ID is displayed correctly after setting a new ID
    @Test
    public void testID2(){
        triangleGizmo.setId("Triangle_1");
        assertEquals("Triangle_1", triangleGizmo.getId());
    }

    //Test to check the initial x position of the triangle
    @Test
    public void testXPos(){
        assertEquals(11,triangleGizmo.getxPos());
    }

    //Test to check if triangles x position is displayed correctly after setting a new value
    @Test
    public void testXPos2(){
        triangleGizmo.setXPos(15);
        assertEquals(15,triangleGizmo.getxPos());
    }

    //Test to check the initial y position of the triangle
    @Test
    public void testYPos(){
        assertEquals(19,triangleGizmo.getyPos());
    }

    //Test to check if triangles y position is displayed correctly after setting a new value
    @Test
    public void testYPos2(){
        triangleGizmo.setYPos(17);
        assertEquals(17,triangleGizmo.getyPos());
    }

    //Test to check if the correct number of lines are used to construct the triangle gizmo
    @Test
    public void testNumOfLines(){
        assertEquals(3, triangleGizmo.getLines().size());
    }

    //Test to check if the correct number of 0 size circles are used for triangle gizmo
    @Test
    public void testNumOfCircles(){
        assertEquals(3, triangleGizmo.getCircles().size());
    }

    //Test to check if the triangles retain same number of lines after rotation
    @Test
    public void testRotationLines(){
        triangleGizmo.rotate();
        assertEquals(3, triangleGizmo.getLines().size());
    }

    //Test to check if the triangles retain same number of 0 size circles after rotation
    @Test
    public void testRotationCircles(){
        triangleGizmo.rotate();
        assertEquals(3, triangleGizmo.getCircles().size());
    }


}

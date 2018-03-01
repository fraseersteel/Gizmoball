import model.SquareGizmo;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SquareTest {

    SquareGizmo squareGizmo;

    @Before
    public void setUP(){
        squareGizmo = new SquareGizmo("test",25,20);
    }

    @Test
    public void testXPos(){
        assertEquals(25,squareGizmo.getxPos());
    }

    @Test
    public void testYPos(){
        assertEquals(20,squareGizmo.getyPos());
    }


}
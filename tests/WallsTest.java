import model.Wall;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class WallsTest {

    Wall wall;

    @Before
    public void setUP(){
        wall = new Wall(0,0,20,20);
    }

    //Test to check if the correct number of lines was used for constructing walls
    @Test
    public void testNumOfLines(){
        assertEquals(4, wall.getLines().size());
    }

}

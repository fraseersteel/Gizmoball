

package testing;
import org.junit.jupiter.api.Test;
import model.Ball;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ModelTest {

    @Test
    public void testBallRadius(){
    Ball ball = new Ball(10);
    assertEquals(ball.getRadius(),10);
    }

    @Test
    public void testEqualsReflex(){

    }

    @Test
    public void testEqualSymetric(){

    }

    @Test
    public void testEqualsTransitive(){

    }

    @Test
    public void testEqualsConsist(){

    }

    @Test
    public void testEqualsNotSameInstance(){

    }

    @Test
    public void testEqualsNull(){

    }


}

import model.Model;
import model.SquareGizmo;
import model.TriangleGizmo;
import org.junit.Before;
import org.junit.Test;
import physics.LineSegment;
import org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class GizmoTests {

    private Model model;
    private SquareGizmo square1;
    private TriangleGizmo triangle1, triangle2;

    @Before
    public void setUp() throws Exception {
        model = new Model();

    }

    @Test
    public void rotate90Triangle() {
        triangle1 = new TriangleGizmo("triangle1", 2, 2);
        triangle1.rotate();
        assertEquals(90, triangle1.getRotationAngle());
    }

    @Test
    public void rotateAroundTriangle() {
        triangle1 = new TriangleGizmo("triangle1", 2, 2);
        triangle1.rotate();
        triangle1.rotate();
        triangle1.rotate();
        triangle1.rotate();
        assertEquals(0, triangle1.getRotationAngle());
    }

    @Test
    public void physicsLinesRot0Triangle() {
        triangle1 = new TriangleGizmo("triangle1", 2, 2);
        int x = triangle1.getxPos();
        int y = triangle1.getyPos();

        List<LineSegment> expectedLines = new ArrayList<>();
        expectedLines.add(new LineSegment(x, y, x+1, y));
        expectedLines.add(new LineSegment(x+1, y, x, y+1));
        expectedLines.add(new LineSegment(x, y+1, x, y));

        assertEquals(expectedLines, triangle1.getLines());
    }

    @Test
    public void physicsLinesRot90Triangle() {
        triangle1 = new TriangleGizmo("triangle1", 2, 2);
        triangle1.rotate();
        int x = triangle1.getxPos();
        int y = triangle1.getyPos();

        List<LineSegment> expectedLines = new ArrayList<>();
        expectedLines.add(new LineSegment(x, y, x+1, y));
        expectedLines.add(new LineSegment(x+1, y, x+1, y+1));
        expectedLines.add(new LineSegment(x+1, y+1, x, y));

        assertEquals(expectedLines, triangle1.getLines());
    }



    /*
    @Test
    public void checkLines() {
        List<LineSegment> correctLines = new ArrayList<>();
        correctLines.add(new LineSegment())
        square1.getLines();
    }
    */

}

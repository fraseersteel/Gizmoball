package absorberModel;

import absorberPhysics.LineSegment;

import java.util.ArrayList;

public class Wall {

    private int xPos1;
    private int yPos1;
    private int xPos2;
    private int yPos2;

    public Wall(int x1, int y1, int x2,int y2){

        xPos1 = x1;
        yPos1 = y1;
        xPos2 = x2;
        yPos2 = y2;

    }

    public ArrayList<LineSegment> getLines(){
        ArrayList<LineSegment> walls = new ArrayList<LineSegment>();
        LineSegment l1 = new LineSegment(xPos1, yPos1, xPos2, yPos1);
        walls.add(l1);
        LineSegment l2 = new LineSegment(xPos1, yPos1, xPos1, yPos2);
        walls.add(l2);
        LineSegment l3 = new LineSegment(xPos2, yPos1, xPos2, yPos2);
        walls.add(l3);
        LineSegment l4 = new LineSegment(xPos1, yPos2, xPos2, yPos2);
        walls.add(l4);
        return walls;
    }

}

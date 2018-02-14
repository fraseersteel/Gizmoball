package collisionsModel;

import collisionsPhysics.LineSegment;

public class HorizontalLines {

    private int xPos;
    private int yPos;
    private int width;
    private LineSegment line;


    public HorizontalLines(int x, int y, int w){
        this.xPos = x;
        this.yPos = y;
        width = w;
        line = new LineSegment(x,y,x+w,y);
    }

    public LineSegment getLine() {
        return line;
    }

    public int getxPos(){
        return xPos;
    }

    public int getyPos(){
        return yPos;
    }

    public int getWidth(){
        return width;
    }
}

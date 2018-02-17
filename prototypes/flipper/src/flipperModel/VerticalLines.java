package flipperModel;

import flipperPhysics.LineSegment;

public class VerticalLines {


    private int xPos;
    private int yPos;
    private int height;
    private LineSegment line;


    public VerticalLines(int x, int y, int h){
        this.xPos = x;
        this.yPos = y;
        height = h;
        line = new LineSegment(x,y,x,y+h);
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

    public int getHeight(){
        return height;
    }
}

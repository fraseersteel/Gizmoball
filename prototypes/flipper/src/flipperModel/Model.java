package flipperModel;

import flipperPhysics.Circle;
import flipperPhysics.Geometry;
import flipperPhysics.LineSegment;
import flipperPhysics.Vect;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<flipperPhysics.LineSegment> lines;
    private ArrayList<Gizmo> gizmos;
    private ArrayList<Absorber> absorbers;
    private RightFlipper right;
    private ArrayList<RightFlipper> rFlippers;
    private flipperModel.Ball ball;
    private flipperModel.Wall walls;
    private ArrayList<VerticalLines> vertlines;
    private ArrayList<HorizontalLines> horzLines;
    private double accDueToGrav = 30;


    public Model() {

        gizmos = new ArrayList<>();
        absorbers = new ArrayList<>();
        ball = new Ball("Ball", 170, 50, 0, 0);
        walls = new flipperModel.Wall(0, 0, 500, 500);
        rFlippers = new ArrayList<RightFlipper>();
        RightFlipper right = new RightFlipper("right", 200,200);
        rFlippers.add(right);
        vertlines = new ArrayList<VerticalLines>();
        horzLines = new ArrayList<HorizontalLines>();

    }

    public void rotateFlippers(){
        rFlippers.get(0).rotate();
        System.out.print("Executed Model.rotateFlippers");
    }

    public void applyGravity() {

        Vect veloAfterGrav = new Vect(ball.getXVelo(), ball.getYVelo() + accDueToGrav);
        ball.setVelocity(veloAfterGrav);

    }

    public void applyFriction() {

    }

    public void moveBall() {


        double moveTime = 0.05;

        if (!ball.isStopped() && ball != null) {
            CollisionDetails cd = timeUntilCollision();
            if (cd != null) {
                System.out.println("the speed of the ball before gravity is:" + ball.getVelocity());
                //applyGravity();
                System.out.println("the speed of the ball after gravity is:  " + ball.getVelocity());
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    ball = moveBallTime(moveTime, ball);
                } else {
                    ball = moveBallTime(tuc, ball);
                    ball.setVelocity(cd.getVelocity());
                }

                this.setChanged();
                this.notifyObservers();
            }
        }
    }

    public Ball moveBallTime(double time, Ball ball) {

        double newXPos = 0.0;
        double newYPos = 0.0;
        double xVel = ball.getXVelo();
        double yVel = ball.getYVelo();
        newXPos = ball.getXPos() + (xVel * time);
        newYPos = ball.getYPos() + (yVel * time);
        ball.setXPos(newXPos);
        ball.setYPos(newYPos);

        return ball;
    }

    private CollisionDetails timeUntilCollision() {

        double time = 0.0;
        double minTUC = Double.MAX_VALUE;
        System.out.println("velocity = " + ball.getVelocity());
        Vect ballVelocity = ball.getVelocity();
        Circle ballCircle = ball.getCircle();
        Vect newVelocity = new Vect(0, 0);


        //for loop for each gizmo type

        ArrayList<LineSegment> lines = walls.getLines();
        for (LineSegment line : lines) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < minTUC) {
                minTUC = time;
                newVelocity = Geometry.reflectWall(line, ball.getVelocity());
            }
        }


        return new CollisionDetails(minTUC, newVelocity);
    }

    public void setBallVeloctiy(int x, int y) {
        ball.setVelocity(new Vect(x, y));
    }


    public Ball getBall() {
        return ball;
    }

    public ArrayList<Gizmo> getGizmo() {
        return gizmos;
    }
    public ArrayList<RightFlipper> getFlippers() {
        return rFlippers;
    }


    public ArrayList<VerticalLines> getVertlines() {
        return vertlines;
    }

    public void addVerticalLines(VerticalLines vl) {
        vertlines.add(vl);
    }

    public ArrayList<HorizontalLines> getHorzLines() {
        return horzLines;
    }

    public void addHorizontalLines(HorizontalLines hl) {
        horzLines.add(hl);
    }

    public void addAbsorber (Absorber absorber) {
        absorbers.add(absorber);
    }


    public ArrayList<Gizmo> getGizmos() {
        return gizmos;
    }

    public ArrayList<Absorber> getAbsorbers() {
        return absorbers;
    }

}
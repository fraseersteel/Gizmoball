package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.Geometry;
import collisionsPhysics.LineSegment;
import collisionsPhysics.Vect;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<collisionsPhysics.LineSegment> lines;
    private ArrayList<IGizmo> gizmos;
    private Absorber absorber;
    private collisionsModel.Ball ball;
    private collisionsModel.Wall walls;
    private ArrayList<VerticalLines> vertlines;
    private ArrayList<HorizontalLines> horzLines;
    private double accDueToGrav = 30;
    private double deaccDueToFric = 1.05;


    public Model() {

        gizmos = new ArrayList<>();
        ball = new Ball("Ball", 250, 50, 0, 0);
        walls = new collisionsModel.Wall(0, 0, 500, 500);
        vertlines = new ArrayList<VerticalLines>();
        horzLines = new ArrayList<HorizontalLines>();

    }


    public void applyGravity() {

        Vect veloAfterGrav = new Vect(ball.getXVelo(), ball.getYVelo() + accDueToGrav);
        ball.setVelocity(veloAfterGrav);

    }

    public void applyFriction() {

        Vect veloAfterFric = new Vect(ball.getXVelo()/deaccDueToFric, ball.getYVelo()/deaccDueToFric);
        ball.setVelocity(veloAfterFric);

    }

    public void moveBall() {


        double moveTime = 0.05;

        if (!ball.isStopped() && ball != null) {
            CollisionDetails cd = timeUntilCollision();
            if (cd != null) {
                System.out.println("the speed of the ball before gravity is:" + ball.getVelocity());
                applyGravity();
                applyFriction();
                System.out.println("the speed of the ball after gravity is:  " + ball.getVelocity());
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    ball = moveBallTime(moveTime, ball);
                } else {
                    ball = moveBallTime(tuc, ball);
                    ball.setVelocity(cd.getVelocity());
                }

                if (checkAbsorber())
                    ball.stop();

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
        Circle circle = ball.getCircle();
        Vect newVelocity = new Vect(0, 0);


        //for loop for each gizmo type
        ArrayList<LineSegment> lines = walls.getLines();
        for (LineSegment line : lines) {
            time = Geometry.timeUntilWallCollision(line, circle, ballVelocity);
            if (time < minTUC) {
                minTUC = time;
                newVelocity = Geometry.reflectWall(line, ball.getVelocity());
            }
        }

        for (VerticalLines line : vertlines) {
            LineSegment ls = line.getLine();
            time = Geometry.timeUntilWallCollision(ls, circle, ballVelocity);
            if (time < minTUC) {
                minTUC = time;
                newVelocity = Geometry.reflectWall(ls, ball.getVelocity());
            }
        }

        for (HorizontalLines line : horzLines) {
            LineSegment ls = line.getLine();
            time = Geometry.timeUntilWallCollision(ls, circle, ballVelocity);
            if (time < minTUC) {
                minTUC = time;
                newVelocity = Geometry.reflectWall(ls, ball.getVelocity());
            }
        }


        /*
        for (LineSegment line : absorber.getEdges()) {
            time = Geometry.timeUntilWallCollision(line, circle, ballVelocity);
            if (time < minTUC) {

                if (ball.getYPos() > (absorber.getStartY()*25)) {
                    System.out.println("BOOM");

                    //ball.setYPos(absorber.getEndY()*25);
                    ball.stop();
                    // capture absorber
                }
            }
        }
        */


        for (IGizmo gizmo : gizmos) {

            for (LineSegment line : gizmo.getLines()) {
                time = Geometry.timeUntilWallCollision(line, circle, ballVelocity);
                if (time < minTUC) {
                    minTUC = time;
                    newVelocity = Geometry.reflectWall(line, ball.getVelocity());
                }
            }
            //for all gizmo in array check what type of gizmo is contained within the model and get tuc

            /*
            if (gizmo.getClass().getName().contains("Square")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("Cirlce")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("Triangle")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("LeftFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
            }
            */

        }

        return new CollisionDetails(minTUC, newVelocity);
    }

    public boolean checkAbsorber() {
        System.out.println("Y pos " + ball.getYPos());
        if (ball.getYPos() > (absorber.getStartY()*25) && ball.getYPos() < (absorber.getEndY()*25)) {
            System.out.println("ABSORBER!!");
            return true;
        }
        return false;
    }

    public boolean shootAbsorber() {

        if (ball.getYPos() < (absorber.getStartY()*20)) {
            return false;
        }

        ball.start();
        setBallVeloctiy(0, -800);
        //setVelocity(0, -300);
        return true;
    }

    public void setBallVeloctiy(int x, int y) {
        ball.setVelocity(new Vect(x, y));
    }


    public Ball getBall() {
        return ball;
    }

    public ArrayList<IGizmo> getGizmo() {
        return gizmos;
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

    public void addAbsorber (Absorber ab) {
        absorber = ab;
    }

    public void addSquare(Square square) {
        gizmos.add(square);
    }

    public ArrayList<IGizmo> getGizmos() {
        return gizmos;
    }

    public Absorber getAbsorber() {
        return absorber;
    }

}
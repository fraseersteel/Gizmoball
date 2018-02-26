package model;

import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;


import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<IGizmo> gizmos;
    private ArrayList<Circle> circles;

    private Absorber absorber;
    private Ball ball;
    private Wall walls;

    private double accDueToGrav;
    private double deaccDueToFric;


    public Model() {
        accDueToGrav = 1;
        deaccDueToFric = 1.05;

        gizmos = new ArrayList<>();
        circles = new ArrayList<>();

        walls = new model.Wall(0, 0, 20, 20);
        absorber = null;
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
                applyGravity();
                //applyFriction();
                double tuc = cd.getTuc();
                if (tuc > moveTime) {
                    ball = moveBallTime(moveTime, ball);
                } else {
                    ball = moveBallTime(tuc, ball);
                    ball.setVelocity(cd.getVelocity());
                }

                if (checkAbsorber()) {
                    ball.setXPos(7);
                    ball.stop();
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
        Vect ballVelocity = ball.getVelocity();
        Circle ballCircle = ball.getCircle();
        Vect newVelocity = new Vect(0, 0);


        //for loop for each gizmo type
        ArrayList<LineSegment> wallLines = walls.getLines();
        for (LineSegment line : wallLines) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < minTUC) {
                minTUC = time;
                newVelocity = Geometry.reflectWall(line, ball.getVelocity());
            }
        }

        for (IGizmo gizmo : gizmos) {

            //for all gizmo in array check what type of gizmo is contained within the model and get tuc

            if (gizmo instanceof SquareGizmo) {
                //checks for collisions of square's circles with radius 0
                for (Circle circleX : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circleX, ballCircle, ballVelocity);
                    if (circleTime < minTUC) {
                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circleX.getCenter(), ballCircle.getCenter(), ballVelocity);
                    }
                }
                //checks for collisions of square lines
                for (LineSegment lines : gizmo.getLines()) {
                    double lineTime = Geometry.timeUntilWallCollision(lines, ballCircle, ballVelocity);
                    if (lineTime < minTUC) {
                        minTUC = lineTime;
                        newVelocity = Geometry.reflectWall(lines, ball.getVelocity());
                    }
                }
            } else if (gizmo instanceof TriangleGizmo) {

                for (Circle circleX : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circleX, ballCircle, ballVelocity);
                    if (circleTime < minTUC) {
                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circleX.getCenter(), ballCircle.getCenter(), ballVelocity);
                    }
                }
                //collision stuff
                for (LineSegment lines : gizmo.getLines()) {
                    double lineTime = Geometry.timeUntilWallCollision(lines, ballCircle, ballVelocity);
                    if (lineTime < minTUC) {
                        minTUC = lineTime;
                        newVelocity = Geometry.reflectWall(lines, ball.getVelocity());
                    }
                }
            } else if (gizmo instanceof CircleGizmo) {
                for (Circle circle : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                    if (circleTime < minTUC) {
                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    }
                }
            } else if (gizmo instanceof RightFlipper) {

                //collision stuff
            } else if (gizmo instanceof LeftFlipper) {

                //collision stuff
            }
        }

        return new CollisionDetails(minTUC, newVelocity);
    }


    public boolean checkAbsorber() {
        if ((ball.getYPos() > absorber.getStartY()) && (ball.getYPos() < absorber.getEndY())) {
            System.out.println("ABSORBER!!");
            return true;
        }
        return false;
    }

    public boolean shootAbsorber() {

        if (ball.getYPos() < (absorber.getStartY())) {
            return false;
        }

        ball.start();
        setBallVeloctiy(2, -30);
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


    public ArrayList<physics.Circle> getCircles() {
        return circles;
    }


    public void addCircle(CircleGizmo c) {
        gizmos.add(c);
    }

    public void addTriangle(TriangleGizmo t) {
        gizmos.add(t);
    }

    public void addSquare(SquareGizmo s) {
        gizmos.add(s);
    }

    public void addLeftFlipper(LeftFlipper flipper) {
        gizmos.add(flipper);
    }

    public void addRightFlipper(RightFlipper flipper) {
        gizmos.add(flipper);
    }

    public void setAbsorber(Absorber a) {
        absorber = a;
    }

    public void setBall(Ball b) {
        ball = b;
    }

    public ArrayList<IGizmo> getGizmos() {
        return gizmos;
    }

    public Absorber getAbsorber() {
        return absorber;
    }

    public IGizmo findGizmo(String gizmoId) {
        for (IGizmo giz : getGizmos()) {
            if (giz.getId().equals(gizmoId))
                return giz;
        }
        return null;
    }



}

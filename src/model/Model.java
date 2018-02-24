package model;

import model.Square;
import model.IGizmo;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;
import model.Absorber;
import model.Ball;
import model.Triangle;


import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<IGizmo> gizmos;
    private ArrayList<Absorber> absorbers;
    private model.Ball ball;
    private ArrayList<physics.Circle> circles;
    private model.Wall walls;


    public Model() {

        gizmos = new ArrayList<>();
        absorbers = new ArrayList<>();
        circles = new ArrayList<physics.Circle>();
        ball = new Ball("Ball", 8, 10, 14, 12.5);
        walls = new model.Wall(10, 10, 20, 20);

//        gizmos.add(new model.Square("new", 4, 4));
//        gizmos.add(new model.Square("new", 8, 4));
//        gizmos.add(new model.Square("new", 12, 4));
//        gizmos.add(new model.Square("new", 16, 4));
//
//        gizmos.add(new model.Triangle("new", 4, 8));
//        gizmos.add(new model.Triangle("new", 8, 8));
//        gizmos.add(new model.Triangle("new", 12, 8));
//        gizmos.add(new model.Triangle("new", 16, 8));
//
//
//        gizmos.add(new model.CircleGizmo("new", 4, 12));
//        gizmos.add(new model.CircleGizmo("new", 8, 12));
//        gizmos.add(new model.CircleGizmo("new", 12, 12));
//        gizmos.add(new model.CircleGizmo("new", 16, 12));


        //       gizmos.add(new model.LeftFlipper("Left Flipper", 4,4);


        //extreme testing

        gizmos.add(new model.Triangle("new", 6, 8));
        gizmos.add(new model.Triangle("new", 6, 9));
        gizmos.add(new model.Triangle("new", 6, 10));
        gizmos.add(new model.Triangle("new", 6, 11));
        gizmos.add(new model.Triangle("new", 6, 12));

        Triangle t1 = new model.Triangle("new", 6, 12);
        t1.rotate();
        t1.rotate();
        gizmos.add(t1);
        Triangle t2 = new model.Triangle("new", 7, 12);
        t2.rotate(); t2.rotate();
        gizmos.add(t2);
        Triangle t3 = new model.Triangle("new", 8, 12);
        t3.rotate();
        t3.rotate();
        gizmos.add(t3);
        Triangle t4 = new model.Triangle("new", 9, 12);
        t4.rotate();
        t4.rotate();
        gizmos.add(t4);
        Triangle t5 = new model.Triangle("new", 10, 12);
        t5.rotate();
        t5.rotate();
        gizmos.add(t5);


        gizmos.add(new model.Triangle("new", 10, 8));
        gizmos.add(new model.Triangle("new", 10, 9));
        gizmos.add(new model.Triangle("new", 10, 10));
        gizmos.add(new model.Triangle("new", 10, 11));
        gizmos.add(new model.Triangle("new", 10, 12));

        gizmos.add(new model.Triangle("new", 7, 8));
        gizmos.add(new model.Triangle("new", 8, 8));
        gizmos.add(new model.Triangle("new", 9, 8));
        gizmos.add(new model.Triangle("new", 10, 8));


    }

    public void moveBall() {


        double moveTime = 0.05;

        if (!ball.isStopped() && ball != null) {
            CollisionDetails cd = timeUntilCollision();
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

            if (gizmo.getClass().getName().contains("Square")) {
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
            } else if (gizmo.getClass().getName().contains("Triangle")) {

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
            } else if (gizmo.getClass().getName().contains("CircleGizmo")) {
                for (Circle circle : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                    if (circleTime < minTUC) {
                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    }
                }
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("LeftFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
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

    public ArrayList<IGizmo> getGizmo() {
        return gizmos;
    }


    public ArrayList<physics.Circle> getCircles() {
        return circles;
    }



    public void addCircle(CircleGizmo c) {
        gizmos.add(c);
    }

    public void addTriangle(Triangle t) {
        gizmos.add(t);
    }

    public void addSquare(Square s) {
        gizmos.add(s);
    }

    public void addLeftFlipper(LeftFlipper flipper) {
//        gizmos.add(flipper);
    }

    public void addRightFlipper(RightFlipper flipper) {
//        gizmos.add(flipper);
    }

    public void addAbsorber(Absorber a) {
        absorbers.add(a);
    }

    public void setBall(Ball b) {
        ball = b;
    }


    public ArrayList<IGizmo> getGizmos() {
        return gizmos;
    }

    public ArrayList<Absorber> getAbsorbers() {
        return absorbers;
    }

    public IGizmo findGizmo(String gizmoId) {
        for (IGizmo giz : getGizmos()) {
            if (giz.getId().equals(gizmoId))
                return giz;
        }
        return null;
    }



}

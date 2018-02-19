package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.Geometry;
import collisionsPhysics.LineSegment;
import collisionsPhysics.Vect;


import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<IGizmo> gizmos;
    private ArrayList<Absorber> absorbers;
    private collisionsModel.Ball ball;
    private ArrayList<collisionsPhysics.Circle> circles;
    private collisionsModel.Wall walls;


    public Model() {

        gizmos = new ArrayList<>();
        absorbers = new ArrayList<>();
        circles = new ArrayList<collisionsPhysics.Circle>();
        ball = new Ball("Ball", 4, 7, 14, 12.5);
        walls = new collisionsModel.Wall(0, 0, 20, 20);

        gizmos.add(new collisionsModel.Square("new", 4, 4));
        gizmos.add(new collisionsModel.Square("new", 8, 4));
        gizmos.add(new collisionsModel.Square("new", 12, 4));
        gizmos.add(new collisionsModel.Square("new", 16, 4));

        gizmos.add(new collisionsModel.Triangle("new", 4, 8));
        gizmos.add(new collisionsModel.Triangle("new", 8, 8));
        gizmos.add(new collisionsModel.Triangle("new", 12, 8));
        gizmos.add(new collisionsModel.Triangle("new", 16, 8));


        circles.add(new collisionsPhysics.Circle(100, 300, 12.5));
        circles.add(new collisionsPhysics.Circle(200, 300, 12.5));
        circles.add(new collisionsPhysics.Circle(300, 300, 12.5));
        circles.add(new collisionsPhysics.Circle(400, 300, 12.5));


        //extreme testing

//        gizmos.add(new collisionsModel.Triangle("new", 150, 200));
//        gizmos.add(new collisionsModel.Triangle("new", 150, 225));
//        gizmos.add(new collisionsModel.Triangle("new", 150, 250));
//        gizmos.add(new collisionsModel.Triangle("new", 150, 275));
//        gizmos.add(new collisionsModel.Triangle("new", 150, 300));
//
//        Triangle t1 = new collisionsModel.Triangle("new", 150, 300);
//        t1.rotate(); t1.rotate(); gizmos.add(t1);
//        Triangle t2 = new collisionsModel.Triangle("new", 175, 300);
//        t2.rotate(); t2.rotate(); gizmos.add(t2);
//        Triangle t3 = new collisionsModel.Triangle("new", 200, 300);
//        t3.rotate(); t3.rotate(); gizmos.add(t3);
//        Triangle t4 = new collisionsModel.Triangle("new", 225, 300);
//        t4.rotate(); t4.rotate(); gizmos.add(t4);
//        Triangle t5 = new collisionsModel.Triangle("new", 250, 300);
//        t5.rotate(); t5.rotate(); gizmos.add(t5);
//
//
//        gizmos.add(new collisionsModel.Triangle("new", 250, 200));
//        gizmos.add(new collisionsModel.Triangle("new", 250, 225));
//        gizmos.add(new collisionsModel.Triangle("new", 250, 250));
//        gizmos.add(new collisionsModel.Triangle("new", 250, 275));
//        gizmos.add(new collisionsModel.Triangle("new", 250, 300));
//
//        gizmos.add(new collisionsModel.Triangle("new", 175, 200));
//        gizmos.add(new collisionsModel.Triangle("new", 200, 200));
//        gizmos.add(new collisionsModel.Triangle("new", 225, 200));
//        gizmos.add(new collisionsModel.Triangle("new", 250, 200));


    }

    public void moveBall() {


        double moveTime = 0.05;

        if (!ball.isStopped() && ball != null) {
            CollisionDetails cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if (tuc > moveTime) {
                ball = moveBallTime(moveTime, ball);
                System.out.println("x " + ball.getXPos() + " y " + ball.getYPos());
            } else {
                ball = moveBallTime(tuc, ball);
                ball.setVelocity(cd.getVelocity());
                System.out.println("x " + ball.getX() + " y " + ball.getY());
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

                //
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
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("LeftFlipper")) {

                //collision stuff
            } else if (gizmo.getClass().getName().contains("RightFlipper")) {

                //collision stuff
            }

        }

        for (Circle c : circles) {
            double circleTime = Geometry.timeUntilCircleCollision(c, ballCircle, ballVelocity);
            if (circleTime < minTUC) {
                minTUC = circleTime;
                newVelocity = Geometry.reflectCircle(c.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
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


    public ArrayList<collisionsPhysics.Circle> getCircles() {
        return circles;
    }
}
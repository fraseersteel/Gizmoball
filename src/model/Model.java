package model;

import exceptions.InvalidGizmoException;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;


import java.awt.*;
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

    private double moveTime;

    private int lFlipperCount = 0;
    private int rFlipperCount = 0;
    private int squareCount = 0;
    private int circleCount = 0;
    private int triangleCount = 0;


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

    public void setGravity(int gravity){
        accDueToGrav = gravity;
        System.out.println("gravity: " + accDueToGrav);
    }

    public void setFriction(int friciton){
        deaccDueToFric = friciton;
        System.out.println("friction: " + deaccDueToFric);
    }

    public void applyFriction() {
        Vect veloAfterFric = new Vect(ball.getXVelo() / deaccDueToFric, ball.getYVelo() / deaccDueToFric);
        ball.setVelocity(veloAfterFric);
    }

    public void moveBall() {
        moveTime = 0.05;

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
                    ball.setXPos(absorber.getEndX()-0.5);
                    ball.setYPos(absorber.getEndY()-0.5);
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

                        if (circleTime <= moveTime)
                            triggerGizmo(gizmo);

                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circleX.getCenter(), ballCircle.getCenter(), ballVelocity);
                    }
                }
                //checks for collisions of square lines
                for (LineSegment lines : gizmo.getLines()) {
                    double lineTime = Geometry.timeUntilWallCollision(lines, ballCircle, ballVelocity);
                    if (lineTime < minTUC) {

                        if (lineTime <= moveTime)
                            triggerGizmo(gizmo);

                        minTUC = lineTime;
                        newVelocity = Geometry.reflectWall(lines, ball.getVelocity());

                    }
                }
            } else if (gizmo instanceof TriangleGizmo) {

                for (Circle circleX : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circleX, ballCircle, ballVelocity);
                    if (circleTime <= minTUC) {

                        if (circleTime <= moveTime)
                            triggerGizmo(gizmo);

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

                        if (lineTime <= moveTime)
                            triggerGizmo(gizmo);

                    }
                }
            } else if (gizmo instanceof CircleGizmo) {
                for (Circle circle : gizmo.getCircles()) {
                    double circleTime = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                    if (circleTime < minTUC) {

                        if (circleTime <= moveTime)
                            triggerGizmo(gizmo);

                        minTUC = circleTime;
                        newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    }
                }
            } else if (gizmo instanceof RightFlipper) {
                if (!((RightFlipper) gizmo).getIsStopped()) { //if it's moving
                    for (LineSegment lines : ((RightFlipper) gizmo).getLines()) {
                        double lineTime = Geometry.timeUntilRotatingWallCollision(lines, ((RightFlipper) gizmo).getLineCor(),
                                ((RightFlipper) gizmo).getAngle(), ballCircle, ballVelocity);
                        if (lineTime < minTUC) {
                            minTUC = lineTime;
                            newVelocity = Geometry.reflectRotatingWall(lines, ((RightFlipper) gizmo).getLineCor(), ((RightFlipper) gizmo).getAngle(),
                                    ballCircle, ballVelocity, 0.95);
                        }
                    }

                    for (Circle circle : ((RightFlipper) gizmo).getCircles()) {
                        double circleTime = Geometry.timeUntilRotatingCircleCollision(circle, ((RightFlipper) gizmo).getCircleCor(),
                                ((RightFlipper) gizmo).getAngle(), ballCircle, ballVelocity);
                        if (circleTime < minTUC) {
                            minTUC = circleTime;
                            newVelocity = Geometry.reflectRotatingCircle(circle, ((RightFlipper) gizmo).getCircleCor(),
                                    ((RightFlipper) gizmo).getAngle(), ballCircle, ballVelocity, 0.95);
                        }
                    }
                } else { //and if it isn't moving
                    for (LineSegment lines : gizmo.getLines()) {
                        double lineTime = Geometry.timeUntilWallCollision(lines, ballCircle, ballVelocity);
                        if (lineTime < minTUC) {
                            minTUC = lineTime;
                            newVelocity = Geometry.reflectWall(lines, ballVelocity, 0.95);
                        }
                    }

                    for (Circle circle : gizmo.getCircles()) {
                        double circleTime = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                        if (circleTime < minTUC) {
                            minTUC = circleTime;
                            newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                        }
                    }
                }
            } else if (gizmo instanceof LeftFlipper) {
                if (!((LeftFlipper) gizmo).getIsStopped()) { //if it's moving
                    for (LineSegment lines : ((LeftFlipper) gizmo).getLines()) {
                        double lineTime = Geometry.timeUntilRotatingWallCollision(lines, ((LeftFlipper) gizmo).getLineCor(),
                                ((LeftFlipper) gizmo).getAngle(), ballCircle, ballVelocity);
                        if (lineTime < minTUC) {
                            minTUC = lineTime;
                            newVelocity = Geometry.reflectRotatingWall(lines, ((LeftFlipper) gizmo).getLineCor(), ((LeftFlipper) gizmo).getAngle(),
                                    ballCircle, ballVelocity, 0.95);
                        }
                    }

                    for (Circle circle : ((LeftFlipper) gizmo).getCircles()) {
                        double circleTime = Geometry.timeUntilRotatingCircleCollision(circle, ((LeftFlipper) gizmo).getCircleCor(),
                                ((LeftFlipper) gizmo).getAngle(), ballCircle, ballVelocity);
                        if (circleTime < minTUC) {
                            minTUC = circleTime;
                            newVelocity = Geometry.reflectRotatingCircle(circle, ((LeftFlipper) gizmo).getCircleCor(),
                                    ((LeftFlipper) gizmo).getAngle(), ballCircle, ballVelocity, 0.95);
                        }
                    }
                } else { //and if it isn't moving
                    for (LineSegment lines : gizmo.getLines()) {
                        double lineTime = Geometry.timeUntilWallCollision(lines, ballCircle, ballVelocity);
                        if (lineTime < minTUC) {
                            minTUC = lineTime;
                            newVelocity = Geometry.reflectWall(lines, ballVelocity, 0.95);
                        }
                    }

                    for (Circle circle : gizmo.getCircles()) {
                        double circleTime = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                        if (circleTime < minTUC) {
                            minTUC = circleTime;
                            newVelocity = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 0.95);
                        }
                    }
                }
            }
        }


        return new CollisionDetails(minTUC, newVelocity);
    }

    public void connect(IGizmo connector, IGizmo connected) {

        if (!connector.getConnections().contains(connected)){
            connector.addConnection(connected);
        }
    }

    public void disconnect(IGizmo connector, IGizmo connected){
        connector.getConnections().remove(connected);
    }

    private void triggerGizmo(IGizmo gizmo) {
        System.out.println("Trigger");
        gizmo.trigger();

    }


    public boolean checkAbsorber() {
        if (absorber != null) {
            if ((ball.getYPos() > absorber.getStartY()) && (ball.getYPos() < absorber.getEndY()) &&
                    (ball.getXPos() > absorber.getStartX()) && (ball.getXPos() < absorber.getEndX())) {
                return true;
            }
        }
        return false;
    }

    public boolean shootAbsorber() {
        if (absorber != null) {
            if (ball.getYPos() < (absorber.getStartY())) {
                return false;
            }

            ball.start();
            setBallVeloctiy(0, -30);
            //setVelocity(0, -300);
            return true;
        }
        return false;
    }


    public void setBallVeloctiy(int x, int y) {
        ball.setVelocity(new Vect(x, y));
    }

    public Ball getBall() {
        return ball;
    }


    public ArrayList<physics.Circle> getCircles() {
        return circles;
    }


    // TODO: Method is too large/messy right now, possibly split into smaller methods for each gizmo/object type?
    public boolean checkLegalPlace(Object gizmo, int x, int y) throws InvalidGizmoException {
        if (!checkLegalGizmo(gizmo) && !(gizmo instanceof Ball) && !(gizmo instanceof Absorber)) {
            throw new InvalidGizmoException("Attempting to check if an invalid gizmo/object type can be placed on the board");
        }

        if (findGizmoByCoords(x, y) != null)
            return false;

        // Check no flippers in vicinity
        for (LeftFlipper flipper : getLeftFlippers()) {
            if (flipper.getxPos() + 1 == x && flipper.getyPos() == y)
                return false;
            if (flipper.getxPos() + 1 == x && flipper.getyPos() + 1 == y)
                return false;
            if (flipper.getxPos() == x && flipper.getyPos() + 1 == y)
                return false;
        }
        for (RightFlipper flipper : getRightFlippers()) {
            if (flipper.getxPos() + 1 == x && flipper.getyPos() == y)
                return false;
            if (flipper.getxPos() + 1 == x && flipper.getyPos() + 1 == y)
                return false;
            if (flipper.getxPos() == x && flipper.getyPos() + 1 == y)
                return false;
        }

        // Check no ball in vicinity
        if (ball != null) {
            Ball ball = getBall();
            if (ball.getXPos() == x && ball.getYPos() == y)
                return false;
            if (ball.getXPos() - 1 == x && ball.getYPos() == y)
                return false;
            if (ball.getXPos() - 1 == x && ball.getYPos() - 1 == y)
                return false;
            if (ball.getXPos() == x && ball.getYPos() - 1 == y)
                return false;
        }

        // Check no absorber in vicinity
        if (absorber != null) {
            if (x >= absorber.getStartX() && x <= absorber.getEndX()-1 &&
                    y >= absorber.getStartY() && y <= absorber.getEndY()-1)
                return false;
        }


        // ABSORBER
        if (gizmo instanceof Absorber) {
            Absorber abs = (Absorber) gizmo;
            for (int xSer=abs.getStartX(); xSer < abs.getEndX(); xSer++) {
                for (int ySer=abs.getStartY(); ySer < abs.getEndY(); ySer++) {
                    if (findGizmoByCoords(xSer, ySer) != null) {
                        return false;
                    }

                    if (ball != null) {
                        if (ball.getXPos() == xSer && ball.getYPos() == ySer)
                            return false;
                        if (ball.getXPos() - 1 == xSer && ball.getYPos() == ySer)
                            return false;
                        if (ball.getXPos() - 1 == xSer && ball.getYPos() - 1 == ySer)
                            return false;
                        if (ball.getXPos() == xSer && ball.getYPos() - 1 == ySer)
                            return false;
                    }

                    for (LeftFlipper flipper : getLeftFlippers()) {
                        if (flipper.getxPos() + 1 == xSer && flipper.getyPos() == ySer)
                            return false;
                        if (flipper.getxPos() + 1 == xSer && flipper.getyPos() + 1 == ySer)
                            return false;
                        if (flipper.getxPos() == xSer && flipper.getyPos() + 1 == ySer)
                            return false;
                    }
                    for (RightFlipper flipper : getRightFlippers()) {
                        if (flipper.getxPos() + 1 == xSer && flipper.getyPos() == ySer)
                            return false;
                        if (flipper.getxPos() + 1 == xSer && flipper.getyPos() + 1 == ySer)
                            return false;
                        if (flipper.getxPos() == xSer && flipper.getyPos() + 1 == ySer)
                            return false;
                    }
                }
            }
        }

        // BALL
        // Note: Due to way balls are drawn, assume [x,y] location is bottom right of ball coords.
        if (gizmo instanceof Ball) {
            if (findGizmoByCoords(x, y - 1) != null)
                return false;
            if (findGizmoByCoords(x - 1, y - 1) != null)
                return false;
            if (findGizmoByCoords(x - 1, y) != null)
                return false;

            if (absorber != null) {
                if (absorber.occupies(x, y))
                    return false;
                if (absorber.occupies(x, y - 1))
                    return false;
                if (absorber.occupies(x - 1, y - 1))
                    return false;
                if (absorber.occupies(x - 1, y))
                    return false;
            }
        }

        // FLIPPERS
        if (gizmo instanceof LeftFlipper || gizmo instanceof RightFlipper) {
            if (findGizmoByCoords(x + 1, y) != null)
                return false;
            if (findGizmoByCoords(x, y + 1) != null)
                return false;
            if (findGizmoByCoords(x + 1, y + 1) != null)
                return false;

            if (absorber != null) {
                if (absorber.occupies(x, y))
                    return false;
                if (absorber.occupies(x + 1, y))
                    return false;
                if (absorber.occupies(x, y + 1))
                    return false;
                if (absorber.occupies(x + 1, y + 1))
                    return false;
            }
        }

        return true;
    }


    public void addGizmo(Object gizmo) throws InvalidGizmoException {
        if (!checkLegalGizmo(gizmo)) {
            throw new InvalidGizmoException("Attempted to add invalid gizmo type!");
        }
        gizmos.add((IGizmo) gizmo);
    }


    /**
     * Checks to see if a given object is a valid gizmo for this system.
     *
     * @param object the object we want to check for being a valid gizmo.
     * @return true if object is a valid gizmo, false otherwise.
     */
    public boolean checkLegalGizmo(Object object) {
        if (!(object instanceof SquareGizmo) &&
                !(object instanceof CircleGizmo) &&
                !(object instanceof TriangleGizmo) &&
                !(object instanceof LeftFlipper) &&
                !(object instanceof RightFlipper)) {
            return false;
        }
        return true;
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

    public ArrayList<LeftFlipper> getLeftFlippers() {
        ArrayList<LeftFlipper> leftFlippers = new ArrayList<>();
        for (IGizmo gizmo : gizmos) {
            if (gizmo instanceof LeftFlipper) {
                leftFlippers.add((LeftFlipper) gizmo);
            }
        }
        return leftFlippers;
    }

    public ArrayList<RightFlipper> getRightFlippers() {
        ArrayList<RightFlipper> rightFlippers = new ArrayList<>();
        for (IGizmo gizmo : gizmos) {
            if (gizmo instanceof RightFlipper) {
                rightFlippers.add((RightFlipper) gizmo);
            }
        }
        return rightFlippers;
    }


    public Absorber getAbsorber() {
        return absorber;
    }

    public IGizmo findGizmoByID(String gizmoId) {
        for (IGizmo giz : getGizmos()) {
            if (giz.getId().equals(gizmoId))
                return giz;
        }
        return null;
    }




    public IGizmo findGizmoByCoords(int x, int y) {
        for (IGizmo gizmo : gizmos) {
            if (gizmo.getxPos() == x && gizmo.getyPos() == y) {
                return gizmo;
            }
            if (gizmo instanceof LeftFlipper || gizmo instanceof RightFlipper) {
                if (gizmo.getxPos()+1 == x && gizmo.getyPos() == y)
                    return gizmo;
                if (gizmo.getxPos()+1 == x && gizmo.getyPos()+1 == y)
                    return gizmo;
                if (gizmo.getxPos() == x && gizmo.getyPos()+1 == y)
                    return gizmo;
            }
        }
        return null;
    }


    public boolean removeItemByCoords(int x, int y) {
        if (findGizmoByCoords(x, y) != null) {
            gizmos.remove(findGizmoByCoords(x, y));
            return true;
        }

        if (ball != null) {
            if (ball.getXPos() == x && ball.getYPos() == y)
                setBall(null);
            if (ball.getXPos()-1 == x && ball.getYPos() == y)
                setBall(null);
            if (ball.getXPos()-1 == x && ball.getYPos()-1 == y)
                setBall(null);
            if (ball.getXPos() == x && ball.getYPos()-1 == y)
                setBall(null);

            if (ball == null)
                return true;
        }

        if (absorber.occupies(x, y)) {
            absorber = null;
            return true;
        }
        return false;
    }


    public String getGizmoTypeName(int x,int y){
       return findGizmoByCoords(x,y).getClass().getName().replace("model.", "");
    }

    public void removeAllGizmo(){
        gizmos.clear();
        circleCount = squareCount = lFlipperCount = rFlipperCount = triangleCount = 0;
    }


    public void reset() {
        gizmos.clear();
        ball = null;
        absorber = null;
    }

}

package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.Geometry;
import collisionsPhysics.LineSegment;
import collisionsPhysics.Vect;
import model.Absorber;
import model.Wall;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {

    private ArrayList<physics.LineSegment> lines;
    private ArrayList<IGizmo> gizmos;
    private ArrayList<Absorber> absorbers;
    private collisionsModel.Ball ball;
    private collisionsModel.Wall walls;


    public Model() {

        gizmos = new ArrayList<>();
        absorbers = new ArrayList<>();
        ball = new Ball("Ball", 100, 100, 50, 50);
        walls = new collisionsModel.Wall(0, 0, 500, 500);
        gizmos.add(new collisionsModel.Circle("new",100,100));
        gizmos.add(new Square("new",100,100));
        gizmos.add(new Square("new",100,100));
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
        Circle circle = ball.getCircle();
        Vect newVelocity = new Vect(0, 0);


        //for loop for each gizmo type
        ArrayList<LineSegment> lines = walls.getLines();
        for (LineSegment line : lines) {
            time = Geometry.timeUntilWallCollision(line, circle, ballVelocity);
            if (time<minTUC) {
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

    public ArrayList<IGizmo> getGizmo(){
        return gizmos;
    }

}
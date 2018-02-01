package collisionsModel;

import physics.Circle;
import physics.Vect;

import java.awt.*;

public class Ball {

    private double radius;
    private Vect velocity;
    private double xPos;
    private double yPos;
    private boolean stopped;
    private Color colour;

    public Ball(double x,double y, double xVel, double yVel){
        xPos = x;
        yPos = y;
        colour = Color.RED;
        velocity = new Vect(xVel,yVel);
        radius = 5;
        stopped = false;
    }

    public double getRadius(){
        return radius;
    }

    public Circle getCircle(){
        return new Circle(xPos,yPos,radius);
    }

    public double getxPos(){
        return xPos;
    }

    public double getyPos(){
        return yPos;
    }

    public void stopBall(){
        stopped = true;
    }

    public void moveBall(){
        stopped = false;
    }

    public Vect getVelocity(){
        return velocity;
    }

    public void setVelocity(Vect v){
        velocity = v;
    }

}

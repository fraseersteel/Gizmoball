package model;

import physics.Vect;
import physics.Circle;

public class Ball {

    private String id;
    private Vect velocity;
    private double xPosition;
    private double yPosition;
    private double radius;

    public Ball(String id, double radius, double xPosition, double yPosition, double xVelocity, double yVelocity){

        this.id = id;
        this.radius = radius;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        velocity = new Vect(xVelocity, yVelocity);
    }

    // constructor for balls of fixed size? would ideally want a maximum size for user specified balls
    // to avoid, uh, shenanigans
    // would have to test how big they can get before it just sort of breaks

    public String getId(){
        return id;
    }

    public void setId(String i){
        id = i;
    }

    public double getRadius(){
        return radius;
    }

    public void setRadius(double r){
        radius = r;
    }

    public double getXPos(){
        return xPosition;
    }

    public void setXPos(double x){
        xPosition = x;
    }

    public double getYPos(){
        return yPosition;
    }

    public void setYPos(double y){
        yPosition = y;
    }

    public Vect getVelocity(){
        return velocity;

    }
    public double getXVelo(){
        return velocity.x();
    }

    public double getYVelo(){
        return velocity.y();
    }

    public void setVelocity(Vect v){
        velocity = v;
    }
}

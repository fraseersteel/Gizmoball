package collisionsModel;

import collisionsPhysics.Circle;
import collisionsPhysics.Vect;

import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

public class Ball extends JPanel implements Observer {

    private String id;
    private Vect velocity;
    private double xPos;
    private double yPos;
    private double radius;
    private boolean isStopped;

    public Ball(String id, double xPosition, double yPosition, double xVelocity, double yVelocity){

        radius = 15;
        this.id = id;
        this.radius = radius;
        xPos = xPosition;
        yPos = yPosition;
        velocity = new Vect(xVelocity, yVelocity);
        isStopped = false;
    }


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
        return xPos;
    }

    public void setXPos(double x){
        xPos = x;
    }

    public double getYPos(){
        return yPos;
    }

    public void setYPos(double y){
        yPos = y;
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

    public boolean isStopped(){
        return isStopped;
    }

    public Circle getCircle(){
        return new Circle(xPos,yPos,radius);
    }

    @Override
    public void update(Observable o, Object arg) {

    }



}

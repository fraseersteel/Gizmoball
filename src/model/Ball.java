package model;

import physics.Circle;
import physics.Vect;

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


    public Ball(String id, int xPosition, int yPosition, double xVelocity, double yVelocity){
        this.id = id;
        this.velocity = new Vect(xVelocity, yVelocity);

        this.radius = 0.25;
        this.xPos = xPosition;
        this.yPos = yPosition;

        this.isStopped = false;
    }


    public String getId(){
        return id;
    }

    public double getRadius(){
        return radius;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos(){
        return yPos;
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

    public boolean isStopped(){
        return isStopped;
    }

    public Circle getCircle(){
        return new Circle(xPos,yPos,radius);
    }


    public void setId(String i){
        id = i;
    }


    public void setXPos(double x){
        xPos = x;
    }

    public void setYPos(double y){
        yPos = y;
    }

    public void setVelocity(Vect v){
        velocity = v;
    }

    public void start() {
        isStopped = false;
    }

    public void stop() {
        isStopped = true;
    }

    public String saveSignature() {
        return "Ball " + id + " " + xPos + " " + yPos + " " + velocity.x() + " " + velocity.y();
    }

    @Override
    public void update(Observable o, Object arg) {

    }



}

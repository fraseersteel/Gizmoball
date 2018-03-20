package model;

import physics.Circle;
import physics.Vect;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class Ball implements Observer {

    private String id;
    private Vect velocity, defaultVelocity;

    private double xPos, defaultxPos;
    private double yPos, defaultyPos;
    private double radius;
    private Color colour;

    private boolean isStopped;


    public Ball(String id, int xPosition, int yPosition, double xVelocity, double yVelocity){
        this.id = id;
        this.velocity = new Vect(xVelocity, yVelocity);
        this.defaultVelocity = new Vect(xVelocity, yVelocity);

        this.radius = 0.25;

        this.xPos = xPosition;
        this.defaultxPos = xPosition;
        this.yPos = yPosition;
        this.defaultyPos = yPosition;

        this.isStopped = true;

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

    public double getYVelo() { return velocity.y();}

    public Color getColour(){return colour;}

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

    public void setXYPos(double x, double y){
        xPos = x;
        yPos = y;
    }

    public void setVelocity(Vect v){
        velocity = v;
    }

    public void setRadius(double r){radius = r;}

    public void setColour(Color color){this.colour = color;}

    public void start() {
        isStopped = false;
    }

    public void stop() {
        isStopped = true;
    }

    public void reset() {
        xPos = defaultxPos;
        yPos = defaultyPos;
        velocity = defaultVelocity;
    }

    public String saveSignature() {
        return "Ball " + id + " " + xPos + " " + yPos + " " + velocity.x() + " " + velocity.y();
    }

    public boolean occupies(int x, int y) {
        if (xPos == x && yPos == y)
            return true;
        if (xPos-1 == x && yPos == y)
            return true;
        if (xPos-1 == x && yPos-1 == y)
            return true;
        if (xPos == x && yPos-1 == y)
            return true;

        return false;
    }

    @Override
    public void update(Observable o, Object arg) {

    }



}

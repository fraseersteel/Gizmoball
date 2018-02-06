package model;

import physics.Vect;
import physics.Circle;

public class Ball {

    private String id;
    private Vect velocity;
    private double xPosition, yPosition;
    private double radius;

    public Ball(String id, double radius, double xPosition, double yPosition){

        this.id = id;
        this.radius = radius;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public double getRadius(){
        return radius;
    }
}

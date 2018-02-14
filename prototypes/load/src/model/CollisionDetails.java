package model;

import physics.Vect;

public class CollisionDetails {
    //per the moveBall method from the slides
    //precise details - the time until collision obviously and the velocity at which the collision occurs?


    private double tuc;
    private Vect velocity;

    public CollisionDetails(double t,Vect vel){
        tuc = t;
        velocity = vel;
    }

    public double getTuc(){
        return tuc;
    }

    public Vect getVelocity(){
        return velocity;
    }
}

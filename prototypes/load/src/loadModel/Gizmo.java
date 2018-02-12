package loadModel;

public class Gizmo implements IGizmo {

    private String id;
    private double xLocation;
    private double yLocation;


    public Gizmo(String id, double x, double y) {
        this.xLocation = x;
        this.yLocation = y;
        this.id = id;
    }

    public String getId(){
        return id;
    }

    public void setId(String i){
        id = i;
    }

    public double getxLocation() {

        return xLocation;
    }

    public void setXPos(double x){
        xLocation = x;
    }

    public double getyLocation() {
        return yLocation;
    }

    public void setYLocation(double y){
        yLocation = y;
    }
}

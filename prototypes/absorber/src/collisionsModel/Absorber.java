package collisionsModel;

public class Absorber {

    private String id;
    private double startX, startY;
    private double endX, endY;

    public Absorber(String name, double startX, double startY, double endX, double endY) {
        this.id = name;

        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public String getId() {
        return id;
    }

    public double getStartX() {
        return startX;
    }

    public double getStartY() {
        return startY;
    }

    public double getEndX() {
        return endX;
    }

    public double getEndY() {
        return endY;
    }
}

package absorberModel;

import java.util.ArrayList;

public interface IGizmo {

    String getId();

    void setId(String i);

    double getXPos();

    void setXPos(double x);

    double getYPos();

    void setYPos(double y);

    int getRotationAngle();

    void rotate();

    ArrayList<Gizmo> getTriggers();

    void addTrigger(Gizmo gizmo);
}

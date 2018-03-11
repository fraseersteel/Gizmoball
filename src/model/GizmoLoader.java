package model;


import exceptions.InvalidGizmoException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GizmoLoader {

    private Model model;
    private String file;

    public GizmoLoader(Model model, String fileName) {
        this.model = model;
        this.file = fileName;
        //load();
    }

    public boolean loadSave() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);

            model.removeAllGizmo();

            String line;
            while ((line = buffReader.readLine()) != null){
                if (!line.isEmpty()) {
                    String[] element = line.split("\\s+");

                    processLine(element);

                }
            }
            buffReader.close();

        } catch (FileNotFoundException e) { //delete the printing of the actual exceptions later
            System.err.println("Error: file not found. " + e);
            return false;
        } catch (IOException e) {
            System.err.println("Error: problem with input/output. " + e);
            return false;
        }
        return true;
    }


    private boolean processLine(String[] line) {
        if (line.length == 0)
            return false;

        String id = line[1];

        // Right now for testing, we only worry about shapes
        if (line[0].equals("Triangle") || line[0].equals("Circle") || line[0].equals("Square") || line[0].equals("Ball") || line[0].equals("LeftFlipper") || line[0].equals("RightFlipper")) {
            int x = (int) Double.parseDouble(line[2]);
            int y = (int) Double.parseDouble(line[3]);

            if (!processShape(line[0], id, x, y))
                System.out.println("Error found in save file for gizmo.");

            return true;
        }

        if (line[0].equals("Absorber")) {
            int startX = Integer.parseInt(line[2]);
            int startY = Integer.parseInt(line[3]);
            int endX = Integer.parseInt(line[4]);
            int endY = Integer.parseInt(line[5]);

            if (!processAbsorber(id, startX, startY, endX, endY))
                System.out.println("Error found in save file for absorber.");
        }

        if (line[0].equals("Move")) {
            int x = Integer.parseInt(line[2]);
            int y = Integer.parseInt(line[3]);
            moveGizmo(id, x, y);
        }

        if (line[0].equals("Rotate")) {
            rotateGizmo(id);
        }

        if (line[0].equals("Connect")) {
            connectTrigger(id, line[2]);
        }

        if (line[0].equals("KeyConnect")) {
            // still to do.
        }

        return true;
    }


    private boolean processShape(String shape, String id, int x, int y) {

        // TODO - always returns false

        IGizmo gizmo = null;
        switch(shape) {
            case "LeftFlipper":
                gizmo = new LeftFlipper(id, x, y);
                break;
            case "RightFlipper":
                gizmo = new RightFlipper(id, x, y);
                break;
            case "Triangle":
                gizmo = new TriangleGizmo(id, x, y);
                break;
            case "Square":
                gizmo = new SquareGizmo(id, x, y);
                break;
            case "Circle":
                gizmo = new CircleGizmo(id, x, y);
                break;
            case "Ball":
                model.setBall(new Ball(id, x, y, 0, 0));
                return true;
        }

        try {
            model.addGizmo(gizmo);
        } catch (InvalidGizmoException ex) { }
        return false;
    }

    private boolean processAbsorber(String id, int startX, int startY, int endX, int endY) {
        if (endX <= startX || endY <= startY)
            return false;

        model.setAbsorber(new Absorber(id, startX, startY, endX, endY));
        return true;
    }

    private boolean moveGizmo(String id, int x, int y) {
        IGizmo gizmo = model.findGizmoByID(id);

        if (gizmo == null)
            return false;

        gizmo.setXPos(x);
        gizmo.setYPos(y);
        return true;
    }

    private boolean rotateGizmo(String id) {
        IGizmo gizmo = model.findGizmoByID(id);

        if (gizmo == null)
            return false;

        gizmo.rotate();
        return true;
    }

    private boolean connectTrigger(String id, String trigger) {
        IGizmo baseGizmo = model.findGizmoByID(id);
        IGizmo triggerGizmo = model.findGizmoByID(trigger);

        if (triggerGizmo == null)
            return false;


        if (id.equals("OuterWalls")) {
            // Still to connect walls to triggers here.
        }

        if (baseGizmo == null || triggerGizmo == null) {
            return false;
        }

        baseGizmo.addTrigger((IGizmo) triggerGizmo);
        return true;
    }

}

package model;


import exceptions.CorruptSaveException;
import exceptions.InvalidGizmoException;
import exceptions.LoadWarningException;

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

                    if (!processLine(element)) {
                        return false;
                    }

                }
            }
            buffReader.close();

            System.out.println("Successfully loaded.");

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

        if (line[0].equals("Triangle") || line[0].equals("Circle") || line[0].equals("Square") || line[0].equals("Ball") || line[0].equals("LeftFlipper") || line[0].equals("RightFlipper")) {
            int x = (int) Double.parseDouble(line[2]);
            int y = (int) Double.parseDouble(line[3]);

            try {
                if (!processShape(line[0], id, x, y))
                    System.out.println("Error found in save file for gizmo.");
            } catch (CorruptSaveException ex) {
                System.err.println(ex);
                return false;
            }

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

            try {
                moveGizmo(id, x, y);
            } catch (LoadWarningException ex) {
                System.err.println(ex);
                return false;
            }
        }

        if (line[0].equals("Rotate")) {
            try {
                rotateGizmo(id);
            } catch (LoadWarningException ex) {
                System.err.println(ex);
                return false;
            }

        }

        if (line[0].equals("Connect")) {
            try {
                connectTrigger(id, line[2]);
            } catch (LoadWarningException ex) {
                System.err.println(ex);
                return false;
            }
        }

        if (line[0].equals("KeyConnect")) {
            // still to do.
        }

        return true;
    }


    private boolean processShape(String shape, String id, int x, int y) throws CorruptSaveException {


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
            if (!model.checkLegalPlace(gizmo, x, y)) {
                throw new CorruptSaveException("Save file isn't valid (possible that gizmos overlap in save)");
            } else {
                model.addGizmo(gizmo);
                return true;
            }
        } catch (InvalidGizmoException ex) {
            return false;
        }
    }

    private boolean processAbsorber(String id, int startX, int startY, int endX, int endY) {
        if (endX <= startX || endY <= startY)
            return false;

        model.setAbsorber(new Absorber(id, startX, startY, endX, endY));
        return true;
    }

    private boolean moveGizmo(String id, int x, int y) throws LoadWarningException {
        IGizmo gizmo = model.findGizmoByID(id);

        if (gizmo == null)
            throw new LoadWarningException("Attempting to move gizmo that doesn't exist.");

        gizmo.setXPos(x);
        gizmo.setYPos(y);
        return true;
    }

    private boolean rotateGizmo(String id) throws LoadWarningException {
        IGizmo gizmo = model.findGizmoByID(id);

        if (gizmo == null)
            throw new LoadWarningException("Attempting to rotate gizmo that doesn't exist.");

        gizmo.rotate();
        return true;
    }

    private boolean connectTrigger(String id, String trigger) throws LoadWarningException {
        IGizmo baseGizmo = model.findGizmoByID(id);
        IGizmo triggerGizmo = model.findGizmoByID(trigger);

        if (model.getAbsorber() != null) {
            if (model.getAbsorber().getId().equals(id)) {
                model.getAbsorber().setConnectedItself(true);
                return true;
            }
        }

        if (id.equals("OuterWalls")) {
            // TODO: connect walls to triggers
            return true;
        }

        if (baseGizmo != null && triggerGizmo != null) {
            baseGizmo.addTrigger(triggerGizmo);
            return true;
        }

        throw new LoadWarningException("Attempting to connect gizmos that don't exist. ("+id+")");
    }

}

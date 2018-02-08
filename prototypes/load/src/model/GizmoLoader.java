package model; //should really be in controller I imagine


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

            String line;
            while ((line = buffReader.readLine()) != null){
                if (!line.isEmpty()) {
                    String[] element = line.split("\\s+");

                    processLine(element);

                }
            }
            buffReader.close();

        } catch (FileNotFoundException e) { //delete the printing of the actual exception later
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

        // Right now for testing, we only worry about shapes
        if (line[0].equals("Triangle") || line[0].equals("Circle") || line[0].equals("Square") || line[0].equals("Ball")) {
            String id = line[1];
            double x = Double.parseDouble(line[2]);
            double y = Double.parseDouble(line[3]);
            processShape(line[0], id, x, y);

            return true;
        }

        return true;
    }


    private void processShape(String shape, String id, double x, double y) {
        switch(shape) {
            case "Triangle":
                model.addTriangle(new Triangle(id, x, y));
                System.out.println("added triangle");
                break;
            case "Square":
                model.addSquare(new Square(id, x, y));
                System.out.println("added square");
                break;
            case "Circle":
                model.addCircle(new Circle(id, x, y));
                System.out.println("added circle");
                break;
            case "Ball":
                model.setBall(new Ball(id, x, y, 0, 0));
                System.out.println("added ball");
                break;
        }
    }

}

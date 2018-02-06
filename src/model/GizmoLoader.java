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

    private boolean loadSave() {
        try {
            FileReader reader = new FileReader(file);
            BufferedReader buffReader = new BufferedReader(reader);

            String line;
            while ((line = buffReader.readLine()) != null){
                if (!line.isEmpty()) {
                    String[] element = line.split("\\s+");

                    Double x = Double.parseDouble(element[2]);
                    Double y = Double.parseDouble(element[3]);

                    switch (element[0]) {
                        case "Triangle":
                            model.addTriangle(new Triangle(x, y));
                            break;
                        case "Circle":
                            model.addCircle(new Circle(x, y));
                            break;
                        case "Square":
                            model.addSquare(new Square(x, y));
                            break;
                        default:
                            //
                    }
                }
            }
            buffReader.close();

        } catch (FileNotFoundException e) { //delete the printing of the actual exception later
            System.err.println("Error: file not found. " + e);
        } catch (IOException e) {
            System.err.println("Error: problem with input/output. " + e);
        }
        return true;
    }
}

package model;

import java.io.*;
import java.util.ArrayList;

public class SaveFile {

    private Model model;
    private File file;
    private ArrayList<String> saveLine;

    public SaveFile(Model model, File fileName) {
        this.model = model;
        this.file = fileName;
        saveLine = new ArrayList<>();

    }


    public ArrayList<String> save(){

        Absorber absorber = model.getAbsorber();

        for(IGizmo gizmo: model.getGizmos()){
           saveLine.addAll(gizmo.saveSignature());
        }
        if(absorber != null){
            saveLine.addAll(absorber.saveSignature());
            if (absorber.isConnectedItself()) {
                saveLine.add("Connect " + absorber.getId() + " " + absorber.getId());
            }
        }
        if (model.getBall() != null) {
            saveLine.add(model.getBall().saveSignature());
        }

        for (IGizmo gizmo : model.getGizmos()) {
            for (IGizmo connectionGizmo : gizmo.getConnections()) {
                saveLine.add("Connect " + gizmo.getId() + " " + connectionGizmo.getId());
            }
            for (String key : gizmo.getKeyConnections()) {
                saveLine.add("KeyConnect key " + key + " down " + gizmo.getId());
            }
        }



        //for ()

        return saveLine;
    }


    public void saveToFile(){
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            ArrayList<String> lines = save();

            for(int i = 0; i<lines.size(); i++){
                if(lines.get(i) != null){
                    out.println(lines.get(i));
                    System.out.println(lines.get(i));
                }
            }
            out.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

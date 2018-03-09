package model;

import javax.jnlp.FileContents;
import java.io.*;
import java.util.ArrayList;

public class SaveFile {

    private Model model;
    private String file;
    private ArrayList<String> saveLine;

    public SaveFile(Model model, String fileName) {
        this.model = model;
        this.file = fileName;
        saveLine = new ArrayList<>();

    }


    public ArrayList<String> save(){

        for(IGizmo gizmo: model.getGizmos()){
           saveLine.add(gizmo.toString());
        }

        return saveLine;
    }


    public void saveToFile(){
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            ArrayList<String> lines = save();
            for(int i=0;i<lines.size();i++){
                bw.write(lines.get(i) + "\n");
            }
            fw.close();
            bw.close();

        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}

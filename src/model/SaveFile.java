package model;

import javax.jnlp.FileContents;
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

        for(IGizmo gizmo: model.getGizmos()){
           saveLine.addAll(gizmo.saveSignature());
        }
        if(model.getAbsorber() != null){
            saveLine.addAll(model.getAbsorber().saveSignature());
        }
        if (model.getBall() != null) {
            saveLine.add(model.getBall().saveSignature());
        }

        return saveLine;
    }


    public void saveToFile(){
        try{
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            ArrayList<String> lines = save();

            for(int i = 0; i<lines.size(); i++){
                System.out.println(i);
                if(lines.get(i) != null){
                    out.write(lines.get(i));
                    System.out.println(lines.get(i));
                    out.write("\n");
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

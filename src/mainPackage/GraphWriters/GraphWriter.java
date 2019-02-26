package mainPackage.GraphWriters;

import mainPackage.generators.BaseGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class GraphWriter {
    protected static final String BASE_PATH="C:"+File.separator+"Users"+File.separator+
                                            "benam"+File.separator+"Desktop"+File.separator+
                                            "ProjectFiles"+ File.separator;

    protected static final String GRAPHS_PATH=BASE_PATH+"graphs"+File.separator;

    protected BufferedWriter bw=null;

    protected FileWriter fw=null;

    protected BaseGenerator graphGenerator;

    public GraphWriter(BaseGenerator graphGenerator){
        this.graphGenerator=graphGenerator;
    }

    public void openFile() throws IOException {
        fw = new FileWriter(GRAPHS_PATH+"edgesN.graph");
        bw=new BufferedWriter(fw);
    }

    public void close() throws IOException {
        if(bw!=null)
            bw.close();
        if(fw!=null)
            fw.close();

    }

    public abstract void write() throws IOException;
}
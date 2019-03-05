package mainPackage.GraphWriters;

import mainPackage.generators.BaseGenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
 
/**
* Cette classe est la classe mère de tous les classes responsables de l'écriture des graphes dans des fichiers,
* les classes filles doivent implémenter la methode write()
*/
public abstract class GraphWriter {
    /**
    * le chemin de base vers le repertoire des fichers d'ecritures
    */
    protected static final String BASE_PATH=".."+File.separator+
                                            "ProjectFiles"+ File.separator;
    /**
    * le chemin de base vers le ficher representant graphe
    */
    protected static final String GRAPHS_PATH=BASE_PATH+"graphs"+File.separator;

    protected BufferedWriter bw=null;

    protected FileWriter fw=null;
    /**
    * le graphe à écrire dans le fichier
    */
    protected BaseGenerator graphGenerator;
    
    /**
    * le constructeur du GraphWriter
     * @param graphGenerator le generateur qui contient le graph à écrire
    */
    public GraphWriter(BaseGenerator graphGenerator){
        this.graphGenerator=graphGenerator;
    }

    /**
     * la methode chargée de l'ouverture du fichier d'écriture
     * @param name nom di fichier
     * @throws IOException
     */
    public void openFile(String name) throws IOException {
        fw = new FileWriter(GRAPHS_PATH+name);
        bw=new BufferedWriter(fw);
    }

    /**
    * la methode chargée de la fermeture du fichier d'écriture
     * @throws IOException
    */
    public void close() throws IOException {
        if(bw!=null)
            bw.close();
        if(fw!=null)
            fw.close();

    }

    /**
    * la methode chargée de l'ecriture du graphe dans fichier d'écriture
    * @throws IOException
     */
    public abstract void write() throws IOException;
}

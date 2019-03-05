package mainPackage.GraphWriters;

import mainPackage.Node;
import mainPackage.generators.BaseGenerator;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
*<p>
*   Cette classe écrit le graphe sous la forme suivante 
*   noeud[{Nom_idée,Index_idée,Uncertainty,degré,Nombre_followers},{List_followers}]
dans un fichier appelé "edgeN.graph"
*</p>
*/
public class StructeredGraphWriter extends GraphWriter {
    
    /**
    * le chemin vers le fichier d'écriture
    */
    private static final String FILE_PATH="edgesN.graph";
    
    /**
    *   le constructeur
    */
    public StructeredGraphWriter(BaseGenerator generator){
        super(generator);
    }

    /**
    * la methode qui ecrit la premiere partie d'une ligne 
    */
    private String writeNodeCharacteristics(Node nSrc, int nbrFollowers,int nbrFollowing)
    {
        String line="";
        line+="n"+nSrc.getNum()+" ";
        line+="[";
        line+="{";
        line+=nSrc.getIdea().getName();
        line+=",";
        line+=nSrc.getIdea().getIndex();
        line+=",";
        line+=nSrc.getUncertainty();
        line+=",";
        line+=(nbrFollowers+nbrFollowing);
        line+=",";
        line+=nbrFollowers+"}";
        return line;
    }

    /**
    *   la methode qui ecrit la deuxieme partie d'une ligne
    */
    private String writeNodeFollowersList(List<Node> listFollowers)
    {
       String line="";
         line+=",";
        line+="{";
        for(int i=0;i<listFollowers.size()-1;i++)
        {
            line+="n"+listFollowers.get(i).getNum();
            line+=",";
        }
        line+="n"+listFollowers.get(listFollowers.size()-1).getNum();
        line+="}";
        return  line;
    }
    
    private void writeNode(Node nSrc,List<Node> listFollowers,int nbrFollowing){
        String line="";
        int nbrFollowers=listFollowers.size();
        line+=writeNodeCharacteristics(nSrc,nbrFollowers,nbrFollowing);
        if(!listFollowers.isEmpty())
        line+=writeNodeFollowersList(listFollowers);
        line+="]\n";
        try {
            bw.write(line);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
    * la methode qui ecrit toutes les lignes des noeuds
    */
    private void writeNodes(){
        Map<Node, List<Node>> followersLists=graphGenerator.getOrganizedFollowersList();
        Map<Node,List<Node>> followingList=graphGenerator.getOrganizedFollowingList();
        followersLists.forEach((nSrc,listFollowers)-> writeNode(nSrc,listFollowers,followingList.get(nSrc).size()));
    }

    /**
    * la methode qui gere les exceptions de IO
    */
    public void write(){
        try {
            openFile(FILE_PATH);
            writeNodes();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                if (bw != null || fw!=null) {
                    try {
                        close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        }
    }

}

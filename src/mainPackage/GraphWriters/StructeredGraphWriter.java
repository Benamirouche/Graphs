package mainPackage.GraphWriters;

import mainPackage.Node;
import mainPackage.generators.BaseGenerator;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class StructeredGraphWriter extends GraphWriter {
    private static final String FILE_PATH="edgesN.graph";

    public StructeredGraphWriter(BaseGenerator generator){

        super(generator);

    }


    private String writeNodeCharacteristics(Node nSrc, int nbrFollowers,int nbrFollowing)
    {
        String line="";
        line+="n"+nSrc.getNum()+" ";
        line+="[";
        line+="{";
        line+=nSrc.getIdea().getName();
        line+=",";
        System.out.println("hello "+nSrc.getIdea().getIndex());


        line+=nSrc.getIdea().getIndex();
        line+=",";
        line+=nSrc.getUncertainty();
        line+=",";
        line+=(nbrFollowers+nbrFollowing);
        line+=",";
        line+=nbrFollowers+"}";
        return line;
    }

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
        System.out.println(line);


    }

    private void writeNodes(){
        Map<Node, List<Node>> followersLists=graphGenerator.getOrganizedFollowersList();
        Map<Node,List<Node>> followingList=graphGenerator.getOrganizedFollowingList();

        followersLists.forEach((nSrc,listFollowers)-> writeNode(nSrc,listFollowers,followingList.get(nSrc).size()));


    }



    public void write(){

        try {
            openFile(FILE_PATH);

            writeNodes();




            System.out.println("Done");
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
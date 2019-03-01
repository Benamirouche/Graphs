package mainPackage.GraphWriters;

import mainPackage.Edge;
import mainPackage.Node;
import mainPackage.generators.BaseGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StructeredGraphWriter extends GraphWriter {
    public static final String FILE_PATH="edgesN.graph";

    public StructeredGraphWriter(BaseGenerator generator){

        super(generator);

    }

    public Map<Node,List<Node>> getOrganizedFollowingList(){
        return graphGenerator.getEdges().stream()
                .collect(Collectors.groupingBy(Edge::getNodeSrc,
                        Collectors.mapping(Edge::getNodeDest, Collectors.toList()))
                );
    }
    public Set<Node> getNonFollowingNodes(Map<Node,List<Node>> organizedList){
        return graphGenerator.getNodes().values().stream().filter((n)->!organizedList.containsKey(n)).collect(Collectors.toSet());
    }

    public Set<Node> getNonFollowedNodes(Map<Node,List<Node>> organizedList){
        return graphGenerator.getNodes().values()
                .stream()
                .filter((n)->!organizedList.containsKey(n))
                .collect(Collectors.toSet());
    }

    public Map<Node,List<Node>> getOrganizedFollowersList()
    {
        Map<Node, List<Node>> followersList= graphGenerator.getEdges().stream()
                                                                      .collect(Collectors.groupingBy
                                                                              (Edge::getNodeDest,
                                                                                      Collectors.mapping(Edge::getNodeSrc, Collectors.toList()))
                                                                                );

        getNonFollowedNodes(followersList).forEach(node->followersList.put(node,new ArrayList<Node>()));

        return followersList;

    }
    public String writeNodeCharacteristics(Node nSrc, int nbrFollowers)
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
        line+=nbrFollowers+"}";
        return line;
    }

    public String writeNodeFollowersList(List<Node> listFollowers)
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









    public void writeNode(Node nSrc,List<Node> listFollowers){
        String line="";
        int nbrFollowers=listFollowers.size();


        line+=writeNodeCharacteristics(nSrc,nbrFollowers);
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

    public void writeNodes(){
        Map<Node, List<Node>> followersLists=getOrganizedFollowersList();


        followersLists.forEach((nSrc,listFollowers)-> writeNode(nSrc,listFollowers));


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

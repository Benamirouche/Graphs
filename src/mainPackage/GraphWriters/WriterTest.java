package mainPackage.GraphWriters;

import mainPackage.Node;
import mainPackage.generators.BaseGenerator;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WriterTest extends GraphWriter {
    public WriterTest(BaseGenerator graphGenerator) {
        super(graphGenerator);
    }

    @Override
    public void write() throws IOException {
            Map<Node, List<Node>> followersList=graphGenerator.getOrganizedFollowersList();
            Set<Node> nonFollowedNodes=graphGenerator.getNonFollowedNodes(followersList);
            nonFollowedNodes.forEach(node->followersList.put(node,new ArrayList<Node>()));

            Map <Node,List<Node>> followingList=graphGenerator.getOrganizedFollowingList();
            Set<Node> nonFollowingNodes=graphGenerator.getNonFollowingNodes(followersList);
            nonFollowingNodes.forEach(node->followingList.put(node,new ArrayList<Node>()));

            BufferedWriter bw = null ;
            FileWriter fw = null;
                fw = new FileWriter(GRAPHS_PATH+"edgesN.graph");
                bw=new BufferedWriter(fw);
                final BufferedWriter bw2=bw;

                followersList.forEach((nSrc,listNeighbours)->
                        {String line="";
                            line+="n"+nSrc.getNum()+" "+"[";
                            line+="{"+nSrc.getIdea().getName()+","+graphGenerator.getIdeas().indexOf(nSrc.getIdea())*0.1+","+new Random().nextFloat()+","+listNeighbours.size()+"}"+",{";
                            for(int i=0;i<listNeighbours.size()-1;i++)
                            {
                                line+="n"+listNeighbours.get(i).getNum()+",";
                            }
                            if(!listNeighbours.isEmpty())
                                line+="n"+listNeighbours.get(listNeighbours.size()-1).getNum();
                            line+="}]\n";
                            try {
                                bw2.write(line);
                                System.out.println(line);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                );
                nonFollowedNodes.forEach(n->{
                    String  line="n"+n.getNum()+" "+"["+"{"+n.getIdea().getName()+","+graphGenerator.getIdeas().indexOf(n.getIdea())*0.1+","+new Random().nextFloat()+","+0+"}"+"]\n";
                    try {
                        bw2.write(line);
                        System.out.println(line);

                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                });
    }
}

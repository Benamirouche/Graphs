package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.Idea;
import mainPackage.Node;
import java.util.List;
import java.util.Random;

public class RandomGraphGenerator extends BaseGenerator{
    /**
     *
     * @param nbrNodes
     * @param ideas
     */
    public RandomGraphGenerator(int nbrNodes, List<Idea> ideas) {
        this.nbrNodes=nbrNodes;
        this.ideas=ideas;
        setIdeaValues();
        initGraph();
    }

    /**
     *
     */
    public void initGraph() {
        for (int i = 0; i < nbrNodes; i++) {
            nodes.put(i,new Node(getRandomIdea(),i));
        }
    }

    /**
     *
     * @param p
     */
    public boolean generate(float p) {
        for (int i = 0; i < this.nbrNodes; i++) {
            for (int j = 0; j < this.nbrNodes; j++) {
                generateOneEdge(getNode(i),getNode(j),p);
            }
        }
        return connectedGraph();
    }

    /**
     *
     * @param i
     * @param j
     * @param p
     */
    public void generateOneEdge(Node i,Node j,float p){
        Random random = new Random();
        float r;
        Edge edge;
        r = random.nextFloat();
        if(r<=p) {
            edge=new Edge(i,j,generateRandomWeight());
            edges.add(edge);
            this.nbrEdges++;
            saveNodesStatistics(edge);
        }
    }

    /**
     *
     * @return
     */
    public boolean connectedGraph(){
        int i= (int) nodes.values().stream().filter(n->n.getDegree()==0).count();
        if(i==0)return true;
        return false;
    }

    /**
     *
     */
    public void printDegrees(){
        for (Node n : this.nodes.values()) {
            System.out.println(n.getInDegree());
        }
    }
}

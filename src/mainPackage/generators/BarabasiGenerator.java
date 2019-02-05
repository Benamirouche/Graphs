package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.Idea;
import mainPackage.Node;

import java.util.List;
import java.util.Random;

public class BarabasiGenerator extends BaseGenerator {
    /**
     *
     */
    private float randomRatio;

    /**
     *
     * @param randomRatio
     * @param ideas
     */
    public BarabasiGenerator(float randomRatio,List<Idea> ideas){
        this.ideas=ideas;
        this.randomRatio=randomRatio;
        setIdeaValues();
    }

    /**
     *
     * @param n
     * @param k
     */
    public void generateWithSeed(int n,int k,float RandomDensity){
        RandomGraphGenerator ranGen=new RandomGraphGenerator(Math.round(randomRatio*n),this.ideas);
        do {
            ranGen.generate(RandomDensity);
        }while(!ranGen.connectedGraph());
        this.nbrNodes= ranGen.getNbrNodes();
        this.nbrEdges=ranGen.getNbrEdges();
        this.nodes.putAll(ranGen.getNodes());
        this.edges.addAll(ranGen.getEdges());
        Node node;
        int i=this.nbrNodes;
        while (i<n) {
            node=new Node(getRandomIdea(),i);
            if(generateOneNode(i,node,k)){
                i++;//generate one node increments automatically the number of nodes
            }
        }
    }

    /**
     *
     * @param n
     * @param k
     */
    public void generateWithoutSeed(int n, int k){
        Edge edge;
        Node node;
        this.nodes.put(0,new Node(getRandomIdea(), 1));
        this.nodes.put(1,new Node(getRandomIdea(), 2));
        edge=new Edge(getNode(0),getNode(1),generateRandomWeight());
        this.edges.add(edge);
        saveNodesStatistics(edge);
        int i=2;
        while (i<n) {
            node=new Node(getRandomIdea(),i);
            if(generateOneNode(i,node,k)){
                i++;//generate one node increments automatically the number of nodes
            }
        }
    }

    /**
     *
     * @param i
     * @param node
     * @param k
     * @return
     */
    public boolean generateOneNode(int i, Node node, int k){
        Random random = new Random();
        float r,p;
        Edge edge;
        for (Node existentNode :this.nodes.values()) {
            r=random.nextFloat();
            p=calculateProbability(existentNode);
            if(r<k*p){
                /* should be careful about the in or out degree
                        in this case n is always the sender and i is the receiver
                     */
                edge=new Edge(node,existentNode,generateRandomWeight());
                edges.add(edge);
                this.nbrEdges++;
                saveNodesStatistics(edge);
            }
        }
        if(node.getDegree()!=0){
            this.nodes.put(i,node);
            this.nbrNodes++;
            return true;
        }
        return false;
    }

    /**
     *
     * @param existentNode
     * @return
     */
    private float calculateProbability(Node existentNode) {
        return (float)existentNode.getDegree()/getDegreeSum();
    }
}

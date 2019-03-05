package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTBarabasiAlbertGenerator;

import java.util.List;

public class JgraphTBarabasiCustomizedSimulation extends CustomizedSimulation {


    public JgraphTBarabasiCustomizedSimulation(List<Idea> ideas, int initialNumberOfNodes, int numberOfEdgesAddedPerNode, int finalNumberOfNodes,Idea idea){
        super(new JgraphTBarabasiAlbertGenerator(ideas, initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes),idea);

    }
    public JgraphTBarabasiCustomizedSimulation(List<Idea> ideas, int initialNumberOfNodes, int numberOfEdgesAddedPerNode, int finalNumberOfNodes,long seed,Idea idea){
        super(new JgraphTBarabasiAlbertGenerator(ideas, initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes,seed),idea);

    }
}
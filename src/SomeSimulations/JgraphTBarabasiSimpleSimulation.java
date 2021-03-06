package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTBarabasiAlbertGenerator;
import java.util.List;

/**
* Cette classe prepare une simulation simple d'un generateur Barabasi de JgraphT  
*/
public class JgraphTBarabasiSimpleSimulation extends  SimpleSimulation{

    public JgraphTBarabasiSimpleSimulation(List<Idea> ideas, int initialNumberOfNodes, int numberOfEdgesAddedPerNode, int finalNumberOfNodes){
        super(new JgraphTBarabasiAlbertGenerator(ideas, initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes));

    }
    public JgraphTBarabasiSimpleSimulation(List<Idea> ideas, int initialNumberOfNodes, int numberOfEdgesAddedPerNode, int finalNumberOfNodes,long seed){
        super(new JgraphTBarabasiAlbertGenerator(ideas, initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes,seed));

    }
}

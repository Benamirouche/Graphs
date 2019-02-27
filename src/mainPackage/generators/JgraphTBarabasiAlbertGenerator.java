package mainPackage.generators;

import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class JgraphTBarabasiAlbertGenerator extends JgraphTGenerators {


    public JgraphTBarabasiAlbertGenerator(List<Idea> ideas,int initialNumberOfNodes,int numberOfEdgesAddedPerNode,int finalNumberOfNodes){
        super(ideas,new BarabasiAlbertGraphGenerator<Node, DefaultEdge>(initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes));




    }
}

package mainPackage.generators;

import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.generate.BarabasiAlbertGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import java.util.List;

/**
* <p>
*   Cette Class adapt le generateur Barabasi de JgraphT a nos classes de Node et Edge
*</p>
*/
public class JgraphTBarabasiAlbertGenerator extends JgraphTGenerators {
    public JgraphTBarabasiAlbertGenerator(List<Idea> ideas,int initialNumberOfNodes,int numberOfEdgesAddedPerNode,int finalNumberOfNodes){
        super(ideas,new BarabasiAlbertGraphGenerator<Node, DefaultEdge>(initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes),finalNumberOfNodes);
    }
    public JgraphTBarabasiAlbertGenerator(List<Idea> ideas,int initialNumberOfNodes,int numberOfEdgesAddedPerNode,int finalNumberOfNodes,long seed){
        super(ideas,new BarabasiAlbertGraphGenerator<Node, DefaultEdge>(initialNumberOfNodes,numberOfEdgesAddedPerNode,finalNumberOfNodes,seed),finalNumberOfNodes);
    }
}

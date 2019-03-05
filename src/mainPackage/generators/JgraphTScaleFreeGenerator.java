package mainPackage.generators;

import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import java.util.List;

/**
* <p>
*   Cette Classe adapt le generateur ScaleFree de JgraphT a nos classes de Node et Edge
*</p>
*/
public class JgraphTScaleFreeGenerator extends JgraphTGenerators{
    public JgraphTScaleFreeGenerator(List<Idea> ideas, int numberOfNodes) {
        super(ideas,new ScaleFreeGraphGenerator<Node, DefaultEdge>(numberOfNodes),numberOfNodes);
    }
}

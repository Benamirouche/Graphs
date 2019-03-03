package mainPackage.generators;

import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.generate.ScaleFreeGraphGenerator;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class JgraphTScaleFreeGenerator extends JgraphTGenerators{
    public JgraphTScaleFreeGenerator(List<Idea> ideas, int numberOfNodes) {
        super(ideas,new ScaleFreeGraphGenerator<Node, DefaultEdge>(numberOfNodes),numberOfNodes);
    }
}

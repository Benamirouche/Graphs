package mainPackage.generators;

import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.generate.KleinbergSmallWorldGraphGenerator;
import org.jgrapht.graph.DefaultEdge;

import java.util.List;

public class JgraphTKleinbergSmallWorldGenerator extends  JgraphTGenerators{





    public JgraphTKleinbergSmallWorldGenerator(List<Idea> ideas,int n,int p,int q,int r) {
        super(ideas,new KleinbergSmallWorldGraphGenerator<Node, DefaultEdge>(n,p,q,r));
    }



}

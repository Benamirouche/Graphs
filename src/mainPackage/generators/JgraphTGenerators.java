package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.Idea;
import mainPackage.Node;
import org.jgrapht.Graph;
import org.jgrapht.generate.GraphGenerator;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleDirectedGraph;
import org.jgrapht.util.SupplierUtil;
import java.util.List;
import java.util.function.Supplier;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
* <p>
*   Cette Class est la classe mere pour tous les generateur de JgraphT,
*   les autres generateurs de JgraphT herite de cette classe et redifinicent les methodes necessaires.
*   Les methode de cette classe cherche a adapter les generateurs de JgraphT a nos generateurs.
* </p>
*/
public  class JgraphTGenerators extends BaseGenerator {

    protected Graph<Node, DefaultEdge> graphGenerated;
    protected GraphGenerator<Node,DefaultEdge,Node> graphGenerator;

    protected  JgraphTGenerators(List<Idea> ideas,GraphGenerator<Node,DefaultEdge,Node> graphGenerator ){
       super(ideas);
        this.graphGenerator=graphGenerator;
        Supplier<Node> nodeSupplier = new Supplier<Node>()
        {
            private int id = 0;

            @Override
            public Node get()
            {
                return new Node(getRandomIdea(),id++ );
            }
        };
        graphGenerated =  new SimpleDirectedGraph<Node,DefaultEdge>(nodeSupplier, SupplierUtil.createDefaultEdgeSupplier(),false);
    }
    
    protected  JgraphTGenerators(List<Idea> ideas,GraphGenerator<Node,DefaultEdge,Node> graphGenerator,int numberOfNodes ){
        super(ideas,numberOfNodes);
        this.graphGenerator=graphGenerator;
        Supplier<Node> nodeSupplier = new Supplier<Node>()
        {
            private int id = 0;

            @Override
            public Node get()
            {
                return new Node(getRandomIdea(),id++ );
            }
        };
        graphGenerated =  new SimpleDirectedGraph<Node,DefaultEdge>(nodeSupplier, SupplierUtil.createDefaultEdgeSupplier(),false);
    }

    public  void generate()
    {
        graphGenerator.generateGraph(graphGenerated);
        graphGenerated.edgeSet().forEach(System.out::println);
        graphGenerated.vertexSet().forEach(System.out::println);
        transferNodes();
        transferEdges();
    }
    
    public void transferNodes() {
        graphGenerated.vertexSet().forEach(node->nodes.put(node.getNum(), node));
    }

    public void transferEdges(){
        graphGenerated.edgeSet().forEach(dEdge->{
            Edge edge=getEdge(dEdge);
            saveNodesStatistics(edge);
            edges.add(edge);
        });
    }
    
    public Edge getEdge(DefaultEdge dEdge) {
        Pattern p = Pattern.compile("[(]node num: (\\d+) : node num: (\\d+)[)]");
        Matcher m =p.matcher(dEdge.toString());
        return new Edge(nodes.get(Integer.parseInt(m.group(1)))
                ,nodes.get(Integer.parseInt(m.group(2))),generateRandomWeight());
    }
}

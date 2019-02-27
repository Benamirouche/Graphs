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


public class JgraphTGenerators extends BaseGenerator {

    protected Graph<Node, DefaultEdge> graphGenerated;
    protected GraphGenerator<Node,DefaultEdge,Node> graphGenerator;
    protected List<Idea>  ideas;

    protected  JgraphTGenerators(List<Idea> ideas,GraphGenerator<Node,DefaultEdge,Node> graphGenerator ){
        this.ideas=ideas;
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
        this.ideas=ideas;


        graphGenerated =  new SimpleDirectedGraph<Node,DefaultEdge>(nodeSupplier, SupplierUtil.createDefaultEdgeSupplier(),false);



    }

    public  void generate()
    {

        graphGenerator.generateGraph(graphGenerated);
        graphGenerated.edgeSet().forEach(System.out::println);

    }
    public void transferNodes() {


        graphGenerated.vertexSet().forEach(node->nodes.put(node.getNum(), node));



    }

    public void transferEdges(){



        graphGenerated.edgeSet().forEach(dEdge->{

            edges.add(getEdge(dEdge));

        });





    }
    public Edge getEdge(DefaultEdge dEdge) {

        //Pattern p = Pattern.compile("(node [0-9]+) :  (node [0-9]+)");
        Pattern p = Pattern.compile("[(] node (\\d+) :  node (\\d+)[)]");
        Matcher m =p.matcher(dEdge.toString());

        System.out.println(m.matches());
        System.out.println(m.group(2));
        return new Edge(nodes.get(Long.parseLong(m.group(1))),nodes.get(Long.parseLong(m.group(2))),generateRandomWeight());

    }




}

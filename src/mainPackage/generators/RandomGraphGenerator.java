package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.Idea;
import mainPackage.Node;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
*<p>
*   Cette Classe est un générateur de graphes complètement aléatoires
*</p>
*/
public class RandomGraphGenerator extends BaseGenerator{
    
    /**
    * Cette variable represente la densité du graphe aléatoire généré
    */
    private float p;
    /**
     * <p>
     *     le constructeur du graphe aléatoire qui prends en paramètres le nombre de noeuds et
     *     la liste des idées possibles
     * </p>
     * @param nbrNodes le nombre de noeuds
     * @param ideas la liste des idées possible dans le graphe
     */
    public RandomGraphGenerator(int nbrNodes, List<Idea> ideas,float p) {
        super(ideas,nbrNodes);
        this.p=p;
        setIdeaValues();
        initGraph();
    }

    /**
     * <p>
     *     Cette methode initialise le graphe en instanciant tous les noeuds avec des idées aléatoires
     * </p>
     */
    public void initGraph() {
        for (int i = 1; i <= nbrNodes; i++) {
            nodes.put(i,new Node(getRandomIdea(),i));
        }
    }

    /**
     * <p>
     *     Cette fonction est responsable de la generation du graphe
     * </p>
     */
    public void generate() {
        for (int i = 1; i <= this.nbrNodes; i++) {
            for (int j = 1; j < this.nbrNodes; j++) {
                generateOneEdge(getNode(i),getNode(j),p);
            }
        }
        edges=edges.stream().distinct().collect(Collectors.toList());
    }

    /**
     * <p>
     *     Cette fonction crée un seul lien entre deux noeuds précis avec un poids aleatoire
     *     celon une probabilité
     * </p>
     * @param i le noeud source
     * @param j le noeud destinataire
     * @param p la probabilité de créer un lien entre les deux noeuds
     */
    public void generateOneEdge(Node i,Node j,float p){
        Random random = new Random();
        float r;
        Edge edge;
        r = random.nextFloat();
        if(r<p) {
            edge=new Edge(i,j,generateRandomWeight());
            edges.add(edge);
            this.nbrEdges++;
            saveNodesStatistics(edge);
        }
    }

    /**
     * <p>
     *     verifie si le graphe est connexe
     * </p>
     * @return vrai si le graphe et connexe et faux sinon
     */
    public boolean connectedGraph(){
        int i= (int) nodes.values().stream().filter(n->n.getDegree()==0).count();
        if(i==0)return true;
        return false;
    }
}

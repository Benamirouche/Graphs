package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.GraphDrawer;
import mainPackage.GraphWriters.GexfGraphWriter;
import mainPackage.GraphWriters.IdeasGraphWriter;
import mainPackage.GraphWriters.StructeredGraphWriter;
import mainPackage.Idea;
import mainPackage.Node;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 *     La classe BaseGeneratoe est la classe mère de tous les générateur,
 *     elle contient tous les attrbiuts et methodes communes entre les générateur.
 *     Cette classe n'est pas instanciable, et les classes qui héritent doivent implémenter
 *     la methode generate() qui est responsable de la génération du graphe.
 * </p>
 * @see BaseGenerator#generate()
 */
public abstract class BaseGenerator {

	/**
    * le chemin (relatif) base du repertoire qui contiendra les fichiers de sorties
    */
	public static final String BASE_PATH="ProjectFiles"+ File.separator;
	
	/**
     * le chemin (relatif) vers le fichier des logs
     */
	public static final String LOGS_PATH=BASE_PATH+"logs"+File.separator;
	/**
	 * le chemin (relatif) vers le fichier du graphe
     */
	public static final String GRAPHS_PATH=BASE_PATH+"graphs"+File.separator;
    /**
     * le seuil à ne pas dépasser pour les poids des liens
     */
    protected static final int WEIGHT_MAX = 100;
    /**
     * le nombre de noeuds
     */
    protected int nbrNodes=0;
    /**
     * le nombre de liens
     */
    protected int nbrEdges=0;
    /**
     * la liste des idées possibles dans le graphe
     */
    protected List<Idea> ideas;
    /**
     * la liste de tout les liens du graphe
     */
    protected List<Edge> edges;
    /**
     * map qui attribut à chaque identificateur l'objet Node correspondant
     */
    protected Map<Integer, Node> nodes;

    /**
     * <p>
     *     La map prefAttachSorted représente les degrés de chaque noeud, elle est utilisé pour verifier
     *     la loi de disrtibution des degrés et confirmer si le graphe resultant représente bel et bien
     *     un réseau unvariant d'échelle (scale free network).
     *</p>
     */
    protected Map<Integer, List<Integer>> prefAttachSorted = new TreeMap<>();

    /**
     * <p>
     *     un constructeur de classe BaseGenerator
     * </p>
     * @param ideas la lste des idées possible dans le graphe
     * @param nbrNodes le nombre de noeuds dans le graphe
     */
    protected BaseGenerator(List<Idea>ideas,int nbrNodes){
        this.nodes = new TreeMap<>();
        this.edges=new ArrayList<>();
        this.ideas=ideas;
        this.nbrNodes=nbrNodes;
    }

    /**
     * <p>
     *     un constructeur de classe BaseGenerator
     * </p>
     * @param ideas la lste des idées possible dans le graphe
     * @param nbrNodes le nombre de noeuds dans le graphe
     * @param nbrEdges le nombre de liens dans le graphe
     */
    protected BaseGenerator(List<Idea> ideas,int nbrNodes,int nbrEdges){
        this(ideas,nbrNodes);
        this.nbrEdges=nbrEdges;

    }

    /**
     * <p>
     *     un constructeur de classe BaseGenerator
     * </p>
     * @param ideas la lste des idées possible dans le graphe
     */
    public BaseGenerator(List<Idea> ideas) {
        this.nodes = new TreeMap<>();
        this.edges=new ArrayList<>();
        this.ideas=ideas;
    }

    /**
     * Cette methods est responsable de la génération du graphe,
     * toute les classe qui herite de BaseGanerator doivent l'implémenter
     */
    abstract public void generate();

    /**
     * initialise les noeuds
     * cette methode instancie tout les noeuds et attribut a chaque noeud un idée aléatoire
     *
     */
    public void initNodes() {
        for (int i = 0; i < nbrNodes; i++) {
            nodes.put(i, new Node(getRandomIdea(), i));
        }
    }

    /**
     * cette methode génère une poids aléatoire
     * @return un poids aléatoire.
     */
    public int generateRandomWeight() {
        Random random = new Random();
        return 1 + random.nextInt(WEIGHT_MAX - 1);
    }

    /**
     * cette methode nous permet de trier les
     * noeuds selon leurs degrés de centralité
     * et les afficher.
     */
    public void powerLawVerification() {

        nodes.forEach((key, value) -> {
            if (prefAttachSorted.containsKey(value.getDegree())) {
                prefAttachSorted.get(value.getDegree()).add(Math.toIntExact(key));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(Math.toIntExact(key));
                prefAttachSorted.put(value.getDegree(), list);
            }
        });
        prefAttachSorted.forEach((key, value) ->
            System.out.println(" Degré: " + key + " nombre de noeuds: " + value.size())
        );
    }

    /**
     * dessine le graphe de distribution des degrés des noeuds
     */
    public void drawPowerLaw() {
        new GraphDrawer(prefAttachSorted).setVisible(true);
    }

    /**
     * cette methode permet d'identifier les neouds d'un lien pour calculer leurs degrés
     * @param edge le lien qui contient les informations sur les noeuds source et destination
     */
    public void saveNodesStatistics(Edge edge) {
        edge.getNodeSrc().incOutDegree();
        edge.getNodeDest().incInDegree();
    }

    /**
     * le getter de la liste des liens
     * @return
     */
    public List<Edge> getEdges()
    {
        return edges;
    }

    /**
     * genere une idée aléatoire de la liste des idees et la retourne
     * @return un idée aléatoire
     *
     * @see BaseGenerator#ideas
     */
    public Idea getRandomIdea() {
        Random random = new Random();
        return ideas.get(random.nextInt(ideas.size() ));
    }

	/**
	 * retourne la liste des idées possibles dans le graphe
	 * @return la liste des idées possibles dans le graphe
	 */
	public List<Idea> getIdeas() {
		return ideas;
	}

	/**
     *retourne le noeud correspondant au numéro indiqué en paramètre
     * @param node le numéro du noeud
     * @return le noeud correspondant au numéro indiqué
     */
    public Node getNode(int node) {
        return this.nodes.get(node);
    }

    /**
     * retourne une map qui contient tous les noeuds avec leurs numéros comme clés
     * @return une map qui contient tous les noeuds avec leurs numéros comme clés
     */
    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    /**
     * retourne le nombre de neouds dans le graphe
     * @return le nombre de neouds dans le graphe
     */
    public int getNbrNodes() {
        return nbrNodes;
    }

    /**
     * retourne le nombre de liens dans le graphe
     * @return le nombre de liens dans le graphe
     */
    public int getNbrEdges() {
        return nbrEdges;
    }

    /**
     * calcule la somme des degrés de tous les noeuds du graphe
     * @return la somme des degrés de tous les noeuds du graphe
	 * //todo streams instead of for loop
     */
    public int getDegreeSum() {
        int s=0;
        for (Node n :nodes.values()) {
            s+=n.getInDegree();
        }
        return s;
    }

    //todo review this with anis
    /**
    anis changes**/
		
    public void writeGexfGraph() {
		new GexfGraphWriter(this).write();
	}




   	public void writeStructuredGraph() {
		new StructeredGraphWriter(this).write();

	}



	public void writeIdeasApparition() {
	  new IdeasGraphWriter(this).write();
	}
//TODO
    public Map<Node,List<Node>> getOrganizedFollowingList(){
        Map<Node, List<Node>> followingList= edges.stream()
                .collect(Collectors.groupingBy(Edge::getNodeSrc,
                        Collectors.mapping(Edge::getNodeDest, Collectors.toList()))
                );
        getNonFollowingNodes(followingList).forEach(node->followingList.put(node,new ArrayList<Node>()));

        return followingList;

    }

//TODO
    public Set<Node> getNonFollowingNodes(Map<Node,List<Node>> organizedList){
        return nodes.values().stream().filter((n)->!organizedList.containsKey(n)).collect(Collectors.toSet());
    }





//TODO
    public Set<Node> getNonFollowedNodes(Map<Node,List<Node>> organizedList){
        return nodes.values()
                .stream()
                .filter((n)->!organizedList.containsKey(n))
                .collect(Collectors.toSet());
    }

    //TODO
    public Map<Node,List<Node>> getOrganizedFollowersList() {
        Map<Node, List<Node>> followersList = edges.stream()
                .collect(Collectors.groupingBy
                        (Edge::getNodeDest,
                                Collectors.mapping(Edge::getNodeSrc, Collectors.toList()))
                );

        getNonFollowedNodes(followersList).forEach(node -> followersList.put(node, new ArrayList<Node>()));

        return followersList;


    }

//TODO

    public Optional<Map.Entry<Node, List<Node>>> getMostFollowedNode(){

          Map<Node,List<Node>>followersList= getOrganizedFollowersList();

          return followersList.entrySet().stream().max(Comparator.comparing(entry->entry.getValue().size()));
    }

    public void customizeGraph(Node mostFollowedNode, Idea idea) {
        ideas.add(idea);
        nodes.remove(mostFollowedNode.getNum());
        mostFollowedNode.setIdea(idea);
        nodes.put(mostFollowedNode.getNum(),mostFollowedNode);
    }
}

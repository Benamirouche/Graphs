package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.GraphDrawer;
import mainPackage.GraphWriters.GexfGraphWriter;
import mainPackage.GraphWriters.IdeasGraphWriter;
import mainPackage.GraphWriters.StructeredGraphWriter;
import mainPackage.Idea;
import mainPackage.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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
    protected List<Idea> ideas = new ArrayList<>();
    /**
     * la liste de tout les liens du graphe
     */
    protected List<Edge> edges = new ArrayList<>();
    /**
     * cet map prefAttachSorted est utilisé pour verifier la loi de disrtibution présenté dans le rapport
     * map qui attribut à chaque noeud une liste de tout les autres noeuds
     * qui sont lies à ce premier
     * on identifie un noeud par un numéro
     *
     * @see Node
     */
    protected Map<Integer, List<Integer>> prefAttachSorted = new TreeMap<>();
    /**
     * map qui attribut à chaque identificateur l'objet Node correspondant
     */
    protected Map<Integer, Node> nodes = new TreeMap<>();


    //TODO: comment this

    abstract public void generate();









    /**
     * initialise les noeuds
     * cette methode instancie tout les noeuds et attribut a chaque noeud un idée aléatoire
     *
     * @see BaseGenerator#getRandomIdea()
     */
    public void initNodes() {
        for (int i = 0; i < nbrNodes; i++) {
            nodes.put(i, new Node(getRandomIdea(), i));
        }
    }

    /**
     * cette methode génère une poids aléatoire
     * @return un poids aléatoire
     */
    public int generateRandomWeight() {
        Random random = new Random();
        return 1 + random.nextInt(WEIGHT_MAX - 1);
    }

    /**
     * cette methode nous permet de trier les
     * noeuds selon leurs degrés de centralité
     * et les afficher
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

    //TODO comment after updating the code
    public void saveIdeasStatisticsInFile(){
        try{
            FileWriter fw=new FileWriter("IdeaStatistics.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            Map<Idea,Long> sum=nodes.values().stream().collect(
                    Collectors.groupingBy(Node::getIdea,Collectors.counting())
            );
            sum.forEach((k,v)-> {
                try {
                    bw.write(k.getName()+":"+v+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * cette methode permet de générer un fichier
     * contenant tout les liens du graphe
     * sous la forme
     * arcs[(0,1), (0,3), (1,3), ...]
     *
     * @see RmatGenerator#edges
     */
    public void saveEdgesInFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("edges.txt");
            bw = new BufferedWriter(fw);
            bw.write("arcs=[");
            Iterator<Edge> it = edges.iterator();
            Edge d = it.next();
            bw.write("(" + d.getNodeSrc().getNum() +"," + d.getNodeSrc().getIdea().getValue()
                    + "," + d.getNodeDest().getNum() + ")");
            for (; it.hasNext();) {
                d = it.next();
                bw.write(", (" + d.getNodeSrc().getNum() + "," + d.getNodeDest().getNum() + ")");
            }
            bw.write("]");
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * dessine le graphe de distribution des centralités des noeuds
     */
    public void drawPowerLaw() {
        new GraphDrawer(prefAttachSorted).setVisible(true);
    }

    /**
     * cette methode permet d'identifier les neouds d'un lien pour calculer leurs centralités
     * @param edge le lien qui contient les informations sur les noeuds source et destination
     */
    public void saveNodesStatistics(Edge edge) {
        edge.getNodeSrc().incOutDegree();
        edge.getNodeDest().incInDegree();
    }

    /**
     *
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
        return ideas.get(random.nextInt(ideas.size() - 1));
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
        for (Node n :
                nodes.values()) {
            s+=n.getInDegree();
        }
        return s;
    }

    /**
     *^just trying to help anass with the values of h=the ideas
	 * // todo remove this in the end of the project
     */
    public void setIdeaValues(){
        float v=-1;
        for (Idea i :
                this.ideas) {
            i.setValue(v);
            v+=2.f/(this.ideas.size()-1);
        }
    }

    /**
	 *^just trying to help anass with the values of h=the ideas
     * // todo also remove this in the end
     */
    public void printIdeaValues(){
        this.ideas.stream().forEach(System.out::println);
    }

    //todo review this with anis
    /**
    anis changes**/
		
    public void saveEdgesGEXF() {
		new GexfGraphWriter(this).write();
	}




   	public void getNodesNeighbours() {
		new StructeredGraphWriter(this).write();

	}



	public void saveIdeasApparition() {
	  new IdeasGraphWriter(this).write();
	}
	  
	   
	   
		   
		   
		   
	   
}	

	
	
	
  	
	
	
	
	
    
    
    
    
    
    
    
    


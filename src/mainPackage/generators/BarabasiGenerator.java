package mainPackage.generators;

import Exceptions.WrongParametersException;
import mainPackage.Edge;
import mainPackage.Idea;
import mainPackage.Node;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


/**
 *
 *     Cette classe représente un generateur de graphe par l'approche de barabasi-albert,
 *     la génération du graphe peut être effectuer à partir d'un graphe aléatoire déja existant et
 *     pour cela on aura besoin des parametres suivants:
 *     <ul>
 *         <li>randomRation: qui représente le pourcentage de la partie aléatoire de notre graphe.</li>
 *         <li>finalNbrNodes: le nombre de noeuds total du graphe qu'on veut générer.</li>
 *         <li>k: 2*k represente (avec une petite marge d'erreur) le degrés moyen de chaque noeud du graphe.</li>
 *     </ul>
 *
 */
public class BarabasiGenerator extends BaseGenerator {

    /**
     * L'attribut randomRation est le pourcentage de notre graphe qui va être un graphe ER (aléatoire)
     * et qui est utilisé comme graphe initial pour la génération.
     */
    private float randomRatio=0;

    /**
     * 2*k represente (avec une petite marge d'erreur) le degrés moyen de chaque noeud du graphe.
     */
    private int k;

    /**
     * la densité du graphe qui servira comme graphe initial (seed)
     */
    private float randomDensity=-1f;

    /**
     * <p>
     *     Un constructeur de la classe qui prend en paramètres:
     * </p>
     * @param randomRatio Le pourcentage du graphe utilisé comme graphe initial.
     * @param ideas La liste des idées possible dans le graphe.
     * @param finalNbrNodes Le nombre total de noeuds du graphe resultant.
     * @param k Un paramètre pour controller la densité du graphe.
     */
    public BarabasiGenerator(float randomRatio, List<Idea> ideas, int finalNbrNodes , int k){
        super(ideas,finalNbrNodes);
        this.ideas=ideas;
        this.randomRatio=randomRatio;
        this.k=k;
    }

    /**
     * <p>
     *     Un constructeur de la classe qui prend en parametre:
     * </p>
     * @param randomRatio Le pourcentage du graphe utilisé comme graphe initial.
     * @param ideas La liste des idées possible dans le graphe.
     * @param finalNbrNodes Le nombre total de noeuds du graphe resultant.
     * @param k Un paramètre pour controller la densité du graphe.
     * @param randomDensity la densité du graphe initial
     */
    public BarabasiGenerator(float randomRatio,List<Idea> ideas,int finalNbrNodes ,int k,float randomDensity){
        super(ideas,finalNbrNodes);
        this.ideas=ideas;
        this.randomRatio=randomRatio;
        this.k=k;
        this.randomDensity=randomDensity;
    }

    /**
     * <p>
     *     Cette methode génère un graphe par l'approche Barabasi-Albert à partir d'un graphe aléatoire,
     *     le graphe aléatoire initial représente randomRation% du graphe final et qui a comme densité le parametre
     *     randomDensity,
     *     il faut noté que le graphe aléatoire resultant doit être connexe, sinon on réessaye de générer un autre graphe,
     *     il faut donc choisir soigneusement les paramètres randomRatio et randomDensity
     * </p>
     * @param randomDensity Ce paramètre représente ldensité du graphe aléatoire initial
     * @throws WrongParametersException
     */
    public void generateWithSeed(float randomDensity) throws WrongParametersException{
        RandomGraphGenerator ranGen=new RandomGraphGenerator(Math.round(this.nbrNodes*this.randomRatio),this.ideas,randomDensity);
        if(Math.round(this.nbrNodes*this.randomRatio)<3)
            throw new WrongParametersException("Random ratio too small to be used as seed, please try to increase it.");//exception :the seed is too small, can't be used to
        do {
            ranGen.generate();
        }while(!ranGen.connectedGraph());
        this.nodes.putAll(ranGen.getNodes());
        this.edges.addAll(ranGen.getEdges());
        Node node;
        int i=this.nbrNodes+1;
        while (i<=this.nbrNodes) {
            node=new Node(getRandomIdea(),i);
            if(generateOneNode(i,node)){
                i++;//generate one node increments automatically the number of nodes
            }
        }
        edges=edges.stream().distinct().collect(Collectors.toList());// this line prevents a node from having a recirsive edge
    }

    /**
     *<p>
     *     Cette methode génère un graphe par l'approche Barabasi-Albert sans avoir besoin d'un graphe aléatoire initial
     *</p>
     */
    public void generateWithoutSeed(){
        Edge edge;
        Node node;
        this.nodes.put(1,new Node(getRandomIdea(), 1));
        this.nodes.put(2,new Node(getRandomIdea(), 2));
        edge=new Edge(getNode(1),getNode(2),generateRandomWeight());
        this.edges.add(edge);
        saveNodesStatistics(edge);
        //this.nbrNodes=2;
        int i=3;
        while (i<=this.nbrNodes) {
            node=new Node(getRandomIdea(),i);
            if(generateOneNode(i,node)){
                System.out.println(" nbr nodes:"+nbrNodes+" comteur: "+i);
                i++;//generate one node increments automatically the number of nodes
            }
        }
    }

    /**
     * <p>
     *     Cette methode ajoute un nouveau noeud au graphe par l'approche de Barabasi-Albert.
     * </p>
     * @param i Le numéro du noeud
     * @param node Le nouveau noeud
     * @return Si le noeud est isolé du graphe, il ne sera pas créé (il ne sera pas ajouté à la liste des noeuds du graphe)
     * et on retourne False
     * sinon l est ajouter à la liste des noeuds du graphe et on retourne True
     */
    public boolean generateOneNode(int i, Node node){
        Random random = new Random();
        this.nodes.values().stream().forEach(n->
        {
            Edge edge;
            float r=random.nextFloat();
            if(r<this.k*calculateProbability(n))
            {
                /* should be careful about the in or out degree
                        in this case n is always the sender and i is the receiver
                     */
                edge=new Edge(node,n,generateRandomWeight());
                edges.add(edge);

                saveNodesStatistics(edge);
            }
        });
        if(node.getDegree()!=0){
            this.nodes.put(i,node);

            return true;
        }
        return false;
    }

    /**
     * <p>
     *     Cette fonction calcule la probabilité qu'un nouveau neoud crée un lien avec noeud qui existe déja
     *     dans le graphe, cette probabilité est infulencé par le degré du noeud existant et la somme des degrés
     *     de tous les noeud dans le graphe.
     * </p>
     * @param existentNode est le existant
     * @return la probabilité qui sera entre 0 et 1
     */
    private float calculateProbability(Node existentNode) {
        return (float)existentNode.getDegree()/getDegreeSum();
    }

    /**
     * Cette methode servira comme indicateur de choix entre un generateur avec ou sans graphe initial
     * @return retourne true si RandomDensity est differente de -1
      et retourne faux sinon
     */
    public boolean isSeeded(){ return randomDensity!=-1f;}

    /**
     * <p>
     *     Cette fonction est la fonction responsable de la génération final du graphe, elle fait appel à la fonction
     *     correspondante selon le constructeur choisi par l'utilisateur.
     * </p>
     */
    @Override
    public void generate() {
        try {
            if(isSeeded()) {
                generateWithSeed(randomDensity);
            }
            else{
                generateWithoutSeed();
            }
        } catch (WrongParametersException e) {
            e.printStackTrace();
        }
    }

}

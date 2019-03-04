package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.Idea;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * <h1>RmatGenerator est la classe qui contient le generateur du graphe suivant la methode R-MAT</h1>
 * pour generer un graphe le generateur aura besoin des parametres suivants:
 * <ul>
 *     <li>le nombre de noeuds</li>
 *     <li>le nombre de liens</li>
 *     <li>la matrice R-MAT: les probabilité α,β,γ et δ</li>
 * </ul>
 *
 */
public class RmatGenerator extends BaseGenerator{

	/**
	 * la probabilité α (alpha de la matrice R-MAT)
	 */
	private float a;
	/**
	 * la probabilité β (beta de la matrice R-MAT)
	 */
	private float b;
	/**
	 * la probabilité γ (gamma de la matrice R-MAT)
	 */
	private float c;
	/**
	 * la probabilité δ (delta de la matrice R-MAT)
	 */
	private float d;
	/**
	 * k est le logarithme du nombre de noeuds
	 */
	private int k;

	/**
	 * le constructeur du générateur
	 * <p>notons que la probabilité δ est calculé en fonction
	 * des autres valeur α,β et γ
	 * le nombre de noeud est arrondi au plus proche 2^k
	 * </p>
	 * @param nbrNodes le nombre de noeuds
	 * @param nbrEdges le nombre de liens
	 * @param a la probabilité α
	 * @param b la probabilité β
	 * @param c la probabilité γ
	 * @param ideas la liste des idées possible dans le graphe
	 *
	 * @see RmatGenerator#nbrNodes
	 * @see RmatGenerator#nbrEdges
	 * @see RmatGenerator#a
	 * @see RmatGenerator#b
	 * @see RmatGenerator#c
	 * @see RmatGenerator#d
	 * @see RmatGenerator#ideas
	 * @see RmatGenerator#initNodes()
	 *
	 */
	public RmatGenerator(int nbrNodes, int nbrEdges, float a, float b, float c, List<Idea> ideas) {
		super(ideas,nbrNodes,nbrEdges);

		this.k = Math.toIntExact(Math.round(Math.log(nbrNodes) / Math.log(2)));

		super.nbrNodes=(int) Math.pow(2, k);

		this.a = a;
		this.b = b;
		this.c = c;
		this.d = 1 - (a + b + c);

		setIdeaValues();
		initNodes();
	}

	/**
	 * Cette methode crée un lien entre deux noeuds selon l'approche rmat
	 * @return le lien créée du type Edge
	 */
	private Edge generateRandomEdge() {

		int nodeSrcNum = 0;
		int nodeDestNum = 0;
		int j = 1;
		while (j <= k) {
			int quadrant = generateQuadrant();
			switch (quadrant) {
				case 2:
					nodeDestNum += Math.pow(2, k - j);
					break;
				case 3:
					nodeSrcNum += Math.pow(2, k - j);
					break;
				case 4:
					nodeSrcNum += Math.pow(2, k - j);
					nodeDestNum += Math.pow(2, k - j);
					break;
			}
			j++;
		}
		return new Edge(nodes.get(nodeSrcNum), nodes.get(nodeDestNum), generateRandomWeight());
	}

	/**
	 * cette methode choisi un quadrant en se basant sur les probabilitées a,b,c et d
	 * en tirant un nombre aléatoire et voir l'interval contenant cette valeur aléatoire
	 * @return le quadrant choisi
	 */
	private int generateQuadrant() {
		Random random = new Random();
		float i = random.nextFloat();

		return i < a ? 1 : i < a + b ? 2 : i < a + b + c ? 3 : 4;
	}

	/**
	 * generate est la mathode chargée de la génération du graphe
	 * <p>
	 *     a chaque itération on génère un lien aléatoire
	 *     si le lien existe deja on ignore cette itération pour chercher
	 *     d'autres lien possible
	 *     sinon on ajoute le lien a edges qui est la lieste des liens
	 *     et on garde des informations pour verifier après la loi de disribution
	 *     ce traitement est répéter autant de fois que le nombre de liens (nbrEdges)
	 * </p>
	 *
	 * @see RmatGenerator#edges
	 * @see RmatGenerator#generateRandomEdge()
	 * @see Edge#getNodeDest()
	 * @see Edge#getNodeSrc()
	 */
	public void generate() {
		int i = 1;
		while (i <= nbrEdges) {
			Edge edge = generateRandomEdge();
			if ( edge.getNodeSrc().getNum() != edge.getNodeDest().getNum()) {
				edges.add(edge);
				saveNodesStatistics(edge);
				i++;
			}
		}
		edges=edges.stream().distinct().collect(Collectors.toList());
	}
}
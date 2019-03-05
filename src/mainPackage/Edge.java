package mainPackage;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * <h1>Edge est la classe qui représente un lien entre deux noeuds</h1>
 * <p>Cette classe représente un lien entre deux noeuds,
 * un noeud source et un noeud destination
 * avec un poids
 * et un compteur dans le cas où le nombre de liens
 * est dupliqué
 * le distinction entre les deux noeuds source est destination n'est pas
 * importante dans le cas où le graphe généré n'est pas orienté
 * </p>
 */
public class Edge {
	/**
	 * le noeud source, le premier bout du lien
	 *
	 * @see Node
	 *
	 * @see Edge#getNodeSrc()
	 * @see Edge#setNodeSrc(Node)
	 */
	private Node nodeSrc;
	/**
	 * le noeud destination, le deuxieme bout du lien
	 *
	 * @see Node
	 *
	 * @see Edge#getNodeDest()
	 * @see Edge#setNodeDest(Node)
	 */
	private Node nodeDest;
	/**
	 *le poids du lien
	 *
	 * @see Edge#getWeight()
	 * @see Edge#setWeight(int)
	 */
	private int weight;
	/**
	 * le nombre de liens dupliqués
	 *
	 * @see Edge#incrementCompteur()
	 */
	int compteur=0;

	/**
	 *
	 * constructeur Edge qui represente un lien
	 *
	 * @param nodeSrc noeud source
	 * @param nodeDest noeud destination
	 * @param weight poids du lien
	 *
	 * @see Edge#nodeSrc
	 * @see Edge#nodeDest
	 * @see Edge#weight
	 */
	public Edge(Node nodeSrc,Node nodeDest,int weight)
	{
		this.nodeSrc=nodeSrc;
    	this.nodeDest=nodeDest;
    	this.weight=weight;
	}

	/**
	 * Retourne le noeud source
	 * @return le neoud source
	 */
	public Node getNodeSrc() {
	return nodeSrc;
}

	/**
	 * mettre à jour le noeud source
	 * @param nodeSrc le nouveau noeud source
	 */
	public void setNodeSrc(Node nodeSrc) {
	this.nodeSrc = nodeSrc;
}

	/**
	 * Retourne le noeud destination
	 * @return le noeud destination
	 */
	public Node getNodeDest() {
	return nodeDest;
}

	/**
	 * mettre à jour le noeud destination
	 * @param nodeDest le nouveau noeud destination
	 */
	public void setNodeDest(Node nodeDest) {
	this.nodeDest = nodeDest;
}

	/**
	 * Retourne le poids du lien
	 * @return le poids du lien
	 */
	public int getWeight() {
	return weight;
}

	/**
	 * metrre à jour le poids du lien
	 * @param weight le nouveau poids du lien
	 */
	public void setWeight(int weight) {
	this.weight = weight;
}

	/**
	 * incrémente la valeur du compteur
	 */
	public void incrementCompteur() { compteur++;}

	/**
	 * convertie les infromations du lien en texte
	 * @return le texte contenant les numéros des noeuds et le poids du lien
	 */
	@Override
	public String toString() {

		return " node source: "+nodeSrc.getNum() +"  node destination :"+nodeDest.getNum()+"  wieght: "+weight;
	}

	/**
	 * redéfinition de la methode equals
	 * @param other l'element à verifié
	 * @return true si les liens sont les memes
	 */
	public boolean equals(Object other) {
		if(!(other instanceof Edge)) return false;
		Edge eother =(Edge)other;
		return eother.nodeDest.getNum()==this.nodeDest.getNum() && eother.nodeSrc.getNum()==this.nodeSrc.getNum();
	}

	public void writeGexf(BufferedWriter bw,int index) throws IOException {

		bw.write("<edge id=\""+index+"\" source=\""+nodeSrc.getNum()+"\" target=\""+nodeDest.getNum()+"\" weight=\""+weight+"\"/>\n");
	}

}

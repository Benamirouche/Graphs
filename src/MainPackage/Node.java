package MainPackage;

/**
 * <h1>Node est la classe qui représente un noeud</h1>
 * <p>
 * Node est la classe qui represente un noeud du graphe
 * un noeud est représenté par un nom, une idée,
 * un num qui est l'identificateur du noeud
 * et le nombre de voisins
 * </p>
 */
public class Node  {
	/**
	 * l'idée adopté
	 */
 	private Idea idea;
	/**
	 * l'identificateur du noeud
	 */
	private int num;
	/**
	 * le nombre de voisins qui est équivalent au nombre d'appartion
	 * dans la liste des liens
	 */
 	private int inDegree =0;

 	private int outDegree=0;

 	private float uncertenty;

 	/* degre d'insertitude 0~1
 	   getRandomUncertenty
 	 */

	/**
	 * constructeur d'un noeud
	 * notons que le nobre de voisins sera calculé à la generation
	 * @param idea l'idée adopté par le noeud
	 * @param num l'identificateur du noeud
	 */
 	public Node(Idea idea,int num){
	 this.idea=idea;
	 this.num=num;
 	}

	/**
	 * incrémente le nombre de voisins
	 */
	public void incInDegree() {
		inDegree++;
	}

	public void incOutDegree(){
		outDegree++;
	}

	public int getDegree(){
		return inDegree+outDegree;
	}



	/**
	 * retourne le nombre de voisins
	 * @return le nombre de voisins
	 */
	public int getInDegree() {return inDegree;}

	/**
	 *
	 * @return
	 */
	public int getOutDegree() {
		return outDegree;
	}

	/**
	 *
	 * @return
	 */
	public float getUncertenty() {
		return uncertenty;
	}


	/**
	 * retourne l'idée adopté par le noeud
	 * @return l'idée adopté par le noeud
	 */
	public Idea getIdea() {
	return idea;
}

	/**
	 * mettre a jour l'idée adopté
	 * @param idea la nouvele idée adopté
	 */
	public void setIdea(Idea idea) {
	this.idea = idea;
}

	/**
	 * retourne l'identificateur du noeud
	 * @return l'identificateur du noeud
	 */
	public int getNum() {
	return num;
}

	/**
	 * mettre à jour l'identificateur du noeud
	 * @param num le nouveau identificateur
	 */
	public void setNum(int num) {
		this.num = num;
	}

	public String toString()
	{
		return "node num: "+this.getNum();
	}

}
package mainPackage;

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
	 * le nombre de liens entrants dans ce noeud
	 */
 	private int inDegree =0;

	/**
	 * le nombre de liens sortants de ce noeud
	 */
	private int outDegree=0;

	/**
	 * le degree d'incertitude
	 */
 	private float uncertenty;
	// TODO : degre d'insertitude 0~1 getRandomUncertenty


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
	 * incrémente le degré entrant
	 */
	public void incInDegree() {
		inDegree++;
	}

	/**
	 * incrémente le degré sortant
	 */
	public void incOutDegree(){
		outDegree++;
	}

	/**
	 * getter de degrés total du noeud
	 * @return le degré de centralité du noeud, qui est égale à la somme des degrés sortant et entrant
	 */
	public int getDegree(){
		return inDegree+outDegree;
	}

	/**
	 * retourne le degré entrant du noeud
	 * @return le degré entrant du noeud
	 */
	public int getInDegree() {return inDegree;}

	/**
	 * retourne le degré sortant du noeud
	 * @return le degré sortant du noeud
	 */
	public int getOutDegree() {
		return outDegree;
	}

	/**
	 * retourne le degré d'insertitude de ce noeud
	 * @return le degré d'insertitude de ce noeud
	 */
	public float getUncertenty() {
		return uncertenty;
	}


	/**
	 * retourne l'idée adopté par le noeud
	 * @return l'idée adopté par le noeud
	 */
	public Idea getIdea() {	return idea;}

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

	/**
	 * represente le neoud sous forme d'une chaine de caractères
	 * @return la chaine de caractère qui représente le neoud
	 */
	public String toString()
	{
		return "node num: "+this.getNum();
	}

}
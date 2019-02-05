package Comparator;
import mainPackage.Node;

import java.util.Comparator;

/**
 * la classe NodeComparator est la classe qui compare entre deux noeuds
 */
public class NodeComparator implements Comparator<Node> {

	@Override
	public int compare(Node node1, Node node2) {
	
		return node1.getNum()<node2.getNum()?-1:
            node1.getNum()>node2.getNum()?1:0; 
	
	}

}

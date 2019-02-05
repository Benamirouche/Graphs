package Comparator;
import mainPackage.Edge;

import java.util.Comparator;

/**
 * le classe EdgeComparator qui compare entre deux liens
 */
public class EdgeComparator implements Comparator<Edge>{

	@Override
	public int compare(Edge edge1, Edge edge2) {
		if(edge1.getNodeSrc().getNum()==edge2.getNodeSrc().getNum()
				&& edge1.getNodeDest().getNum()==edge2.getNodeDest().getNum())
			return 0;
		//if(edge1.getNodeSrc().getNum()==edge2.getNodeDest().getNum() && edge1.getNodeDest().getNum()==edge2.getNodeSrc().getNum()) return 0;
		
		if(edge1.getNodeSrc().getNum()==edge2.getNodeSrc().getNum())
			return new NodeComparator().compare(edge1.getNodeDest(), edge2.getNodeDest());
		
		return new NodeComparator().compare(edge1.getNodeSrc(), edge2.getNodeSrc());
		
		
	}

	
}

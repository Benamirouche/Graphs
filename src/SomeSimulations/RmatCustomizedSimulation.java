package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.RmatGenerator;
import java.util.List;

/**
* Cette classe prepare une simulation personalisé d'un generateur Rmat 
*/
public class RmatCustomizedSimulation extends CustomizedSimulation {

    public RmatCustomizedSimulation(List<Idea> ideas, int nbrNodes, int nbrEdges, float a, float b, float c,Idea idea){
        super(new RmatGenerator(nbrNodes,nbrEdges,a,b,c,ideas),idea);
    }

}

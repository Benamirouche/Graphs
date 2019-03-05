package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.RmatGenerator;
import java.util.List;

/**
* Cette classe prepare une simulation simple d'un generateur Rmat
*/
public class RmatSimpleSimulation extends Simulation{

    public RmatSimpleSimulation(List<Idea> ideas,int nbrNodes, int nbrEdges, float a, float b, float c){
        super(new RmatGenerator(nbrNodes,nbrEdges,a,b,c,ideas));
    }

    @Override
    public void customizingGeneration() {
        return;
    }
}

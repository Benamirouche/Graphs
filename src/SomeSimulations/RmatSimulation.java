package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.RmatGenerator;

import java.util.List;

public class RmatSimulation extends Simulation{


    public RmatSimulation(List<Idea> ideas,int nbrNodes, int nbrEdges, float a, float b, float c){
        super(new RmatGenerator(nbrNodes,nbrEdges,a,b,c,ideas));

    }



    @Override
    public void customizingGeneration() {
        return;
    }
}

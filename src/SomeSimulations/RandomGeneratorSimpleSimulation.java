package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.RandomGraphGenerator;

import java.util.List;

public class RandomGeneratorSimpleSimulation extends SimpleSimulation {

    public RandomGeneratorSimpleSimulation(int nbrNodes, List<Idea> ideas, float p){
        super(new RandomGraphGenerator(nbrNodes,ideas,p));
    }

}

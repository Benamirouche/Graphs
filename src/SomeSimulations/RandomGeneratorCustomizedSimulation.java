package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.RandomGraphGenerator;

import java.util.List;

public class RandomGeneratorCustomizedSimulation extends CustomizedSimulation{
    public RandomGeneratorCustomizedSimulation(int nbrNodes, List<Idea> ideas, float p,Idea idea)
    {
        super(new RandomGraphGenerator(nbrNodes,ideas,p),idea);


    }



}

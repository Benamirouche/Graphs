package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.BarabasiGenerator;

import java.util.List;

public class BarabasiSimpleSimulation extends SimpleSimulation {


    public BarabasiSimpleSimulation(float randomRatio, List<Idea> ideas, int finalNbrNodes , int k){
        super(new BarabasiGenerator(randomRatio,ideas,finalNbrNodes,k));

    }

    public BarabasiSimpleSimulation(float randomRatio, List<Idea> ideas, int finalNbrNodes , int k,float randomDensity){
        super(new BarabasiGenerator(randomRatio,ideas,finalNbrNodes,k,randomDensity));

    }
}

package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.BarabasiGenerator;

import java.util.List;

public class BarabasiCustomizedSimulation extends CustomizedSimulation {





    public BarabasiCustomizedSimulation(float randomRatio, List<Idea> ideas, int finalNbrNodes , int k,Idea idea){
        super(new BarabasiGenerator(randomRatio,ideas,finalNbrNodes,k),idea);

    }

    public BarabasiCustomizedSimulation(float randomRatio, List<Idea> ideas, int finalNbrNodes , int k,float randomDensity,Idea idea){
        super(new BarabasiGenerator(randomRatio,ideas,finalNbrNodes,k,randomDensity),idea);

    }

}

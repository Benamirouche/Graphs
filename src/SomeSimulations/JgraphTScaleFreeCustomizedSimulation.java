package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTScaleFreeGenerator;

import java.util.List;

public class JgraphTScaleFreeCustomizedSimulation extends CustomizedSimulation{


    public JgraphTScaleFreeCustomizedSimulation(List<Idea> ideas, int nbrOfNodes,Idea idea){
        super(new JgraphTScaleFreeGenerator(ideas,nbrOfNodes),idea);
    }


}

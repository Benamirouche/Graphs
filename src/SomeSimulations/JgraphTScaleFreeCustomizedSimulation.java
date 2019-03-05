package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTScaleFreeGenerator;
import java.util.List;

/**
* Cette classe prepare une simulation personalisé d'un generateur ScalFree de JgraphT
*/
public class JgraphTScaleFreeCustomizedSimulation extends CustomizedSimulation{

    public JgraphTScaleFreeCustomizedSimulation(List<Idea> ideas, int nbrOfNodes,Idea idea){
        super(new JgraphTScaleFreeGenerator(ideas,nbrOfNodes),idea);
    }

}

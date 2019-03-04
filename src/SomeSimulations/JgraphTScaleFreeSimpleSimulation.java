package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTScaleFreeGenerator;

import java.util.List;

public class JgraphTScaleFreeSimpleSimulation extends SimpleSimulation{

    public JgraphTScaleFreeSimpleSimulation(List<Idea> ideas,int nbrOfNodes){
        super(new JgraphTScaleFreeGenerator(ideas,nbrOfNodes));
    }

}

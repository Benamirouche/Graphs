package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTKleinbergSmallWorldGenerator;

import java.util.List;

public class JgraphTKleinbergSmallWorldSimpleSimulation extends SimpleSimulation {


    public JgraphTKleinbergSmallWorldSimpleSimulation(List<Idea> ideas, int n, int p, int q, int r)
    {
        super(new JgraphTKleinbergSmallWorldGenerator(ideas,n,p,q,r));
    }
}

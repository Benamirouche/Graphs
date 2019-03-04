package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTKleinbergSmallWorldGenerator;

import java.util.List;

public class JgraphTKleinbergSmallWorldCustomizedSimulation extends CustomizedSimulation {

    public JgraphTKleinbergSmallWorldCustomizedSimulation(List<Idea> ideas, int n, int p, int q, int r,Idea idea){
        super(new JgraphTKleinbergSmallWorldGenerator(ideas,n,p,q,r),idea);

    }
}

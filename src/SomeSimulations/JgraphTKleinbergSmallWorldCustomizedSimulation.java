package SomeSimulations;

import mainPackage.Idea;
import mainPackage.generators.JgraphTKleinbergSmallWorldGenerator;
import java.util.List;

/**
* Cette classe prepare une simulation personalisé d'un generateur TKleinbergSmallWorld de JgraphT  
*/
public class JgraphTKleinbergSmallWorldCustomizedSimulation extends CustomizedSimulation {

    public JgraphTKleinbergSmallWorldCustomizedSimulation(List<Idea> ideas, int n, int p, int q, int r,Idea idea){
        super(new JgraphTKleinbergSmallWorldGenerator(ideas,n,p,q,r),idea);

    }
}

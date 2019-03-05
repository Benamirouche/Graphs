package SomeSimulations;

import mainPackage.Idea;
import mainPackage.Node;
import mainPackage.generators.BaseGenerator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
* Cette classe represente une simulation personalisé, tel qu'on affecte à un noeud infuent une idée differente des autres pour
* observer la propagation de cette idée dans le réseau.
*/
public class CustomizedSimulation extends Simulation {
protected Idea idea;
protected CustomizedSimulation(BaseGenerator generator, Idea idea){
    super(generator);
    this.idea=idea;
}

    @Override
    public void customizingGeneration() {
        Optional<Map.Entry<Node, List<Node>>> mostFollowedNodeEntry=generator.getMostFollowedNode();
        mostFollowedNodeEntry.ifPresent(entry->{
            Node mostFollowedNode=entry.getKey();
            generator.customizeGraph(mostFollowedNode,idea);
        });
    }
}

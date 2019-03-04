package SomeSimulations;

import mainPackage.Idea;
import mainPackage.Node;
import mainPackage.generators.BaseGenerator;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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


            System.out.println("***************"+entry.getKey()+" avec "+entry.getValue().size()+" followers *****");
            System.out.println("************");

            Node mostFollowedNode=entry.getKey();
            generator.customizeGraph(mostFollowedNode,idea);



        });

    }



}

package SomeSimulations;

import mainPackage.generators.BaseGenerator;
/**
* Cette classe represente une simulation simple, ça veut dire qu'on affecte aléatoirement des idée aux noeuds du graphe
*/
public class SimpleSimulation extends  Simulation {

public SimpleSimulation(BaseGenerator generator){
    super(generator);
}

    @Override
    public void customizingGeneration() {
     return;
    }
}

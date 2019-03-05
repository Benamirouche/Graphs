package mainPackage.GraphWriters;

import mainPackage.Idea;
import mainPackage.generators.BaseGenerator;
import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

/**
* Cette classe écrit le nombre d'occurences de tous les idées dans le graphe (utile pour la visualisation des données)
*/
public class IdeasGraphWriter extends GraphWriter{
  private static final String FILE_PATH="ideas.txt";

    public IdeasGraphWriter(BaseGenerator generator){
        super(generator);
    }
    private Map<String,Long> getOrganizedIdeas(){
        return graphGenerator.getNodes().entrySet().stream().map(v->v.getValue().getIdea()).collect(Collectors.groupingBy(Idea::getName,Collectors.counting()));
    }

    @Override
    public void write() {
        try {
            openFile(FILE_PATH);
            Map<String,Long> organizedIdeas=getOrganizedIdeas();
            organizedIdeas.forEach((idea,nombreApparition)-> {
                try {
                    bw.write(idea+":"+nombreApparition+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
                      if (bw != null || fw!=null) {
                            try {
                                close();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                    }
        }
    }
}

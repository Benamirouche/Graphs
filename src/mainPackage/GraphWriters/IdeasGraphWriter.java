package mainPackage.GraphWriters;

import mainPackage.Idea;
import mainPackage.generators.BaseGenerator;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Collectors;

public class IdeasGraphWriter extends GraphWriter{
  public static final String FILE_PATH="ideas.txt";

    public IdeasGraphWriter(BaseGenerator generator){

        super(generator);

    }
    public Map<String,Long> getOrganizedIdeas(){
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




            System.out.println("Done");
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

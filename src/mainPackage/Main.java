package mainPackage;

import SomeSimulations.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe qui appel le generateur
 */
public class Main {
    public static final int TYPE_GENERATOR_INDEX=0;
    public static final int NUMBER_IDEAS_INDEX=1;
    public static final int NUMBER_NODES_INDEX=2;
    public static final int NUMBER_EDGE_INDEX=3;
    public static final int SEED_PARAMS_INDEX=4;
    //***** RMAT*****//
    public static final int RMAT_SIMPLE_GENERATOR_INDEX=11;
    public static final int RMAT_CUSTOMIZED_GENERATOR_INDEX=21;
    // SCALE FREE GENERATORS
    public static final int SCALE_FREE_SIMPLE_GENERATOR_INDEX=12;
    public static final int SCALE_FREE_CUSTOMIZED_GENERATOR_INDEX=22;
    // ******BARABASI******//
    public static final int BARABASI_SIMPLE_GENERATOR_INDEX=13;
    public static final int BARABASI_CUSTOMIZED_GENERATOR_INDEX=23;
    public static final int BARABASI_SIMPLE_SEEDED_GENERATOR_INDEX=14;
    public static final int BARABASI_CUSTOMIZED_SEEDED_GENERATOR_INDEX=24;
    //***JGRAPHT BARABASI*****
    public static final int JGRAPHT_BARABASI_SIMPLE_GENERATOR_INDEX=15;
    public static final int JGRAPHT_BARABASI_CUSTOMIZED_GENERATOR_INDEX=25;
    public static final int JGRAPHT_BARABASI_SIMPLE_SEEDED_GENERATOR_INDEX=16;
    public static final int JGRAPHT_BARABASI_CUSTOMIZED_SEEDED_GENERATOR_INDEX=26;
    //*** RANDOM GENERATORS****
    public static final int RANDOM_SIMPLE_GENERATOR_INDEX=17;
    public static final int RANDOM_CUSTOMIZED_GENERATOR_INDEX=27;
    // ****SMALL WORLD GRAPH GENERATORS
    public static final int JGRAPHT_SMALL_WORLD_SIMPLE_GENERATOR_INDEX=18;
    public static final int JGRAPHT_SMALL_WORLD_CUSTOMIZED_GENERATOR_INDEX=28;
   static List<Idea> ideasDictionary=new ArrayList<>();
    static int typeOfGenerator;
    static  int numberOfIdea;
    static int numberOfNodes;
    static int numberOfEdge;
    static float seed;


    public static void main(String[] args) {


        loadParamsFromArgs(args);
        initIdeasDictionary();

//        List<Idea> ideas=getIdeasRequested(4);
//        List<Idea> customizedIdeas=new ArrayList<>(ideas);
//        customizedIdeas.remove(3);

//        BarabasiGenerator gen=new BarabasiGenerator(0.3f,ideasDictionary,100,5);
//        gen.generate();
        //new JgraphTBarabasiAlbertGenerator(ideasDictionary, 100,20,400).generate();


        // new BarabasiSimpleSimulation(0.3f,ideasDictionary,1000,100,0.3f).simulate();
       // new JgraphTBarabasiCustomizedSimulation(customizedIdeas,100,20,1000,ideasDictionary.get(3)).simulate();
        //





       SwingUtilities.invokeLater(() -> {

                generate();

//           BarabasiGenerator g=new BarabasiGenerator(0.5f,ideas,1000,3,0.01f);
//
//
//               g.generate();
//
//           //RmatGenerator g=new RmatGenerator(100000,2000000,0.4f,0.2f,0.2f,ideas);
//           //g.generate();
//           //RandomGraphGenerator g=new RandomGraphGenerator(1000,ideas);
//           //System.out.println(g.generate(0.1f));
//           System.out.println(g.getNbrNodes()+",,,,,"+g.getNbrNodes());
//           System.out.println(g.getNbrEdges());
//           System.out.println(((float)g.getDegreeSum()/(float)1000));
//           System.out.println(g.getNodes().size());
//           g.powerLawVerification();
//           g.drawPowerLaw();
//           new GexfGraphWriter(g).write();
//           new IdeasGraphWriter(g).write();
//           new StructeredGraphWriter(g).write();
//
//           //g.saveEdgesGEXF();
//            //bg.saveEdgesInFile();
       });
	}


public static void initIdeasDictionary(){


	    float i=0.0f;
	    ideasDictionary.add(new Idea("A",i));
	    i+=0.1f;
	    ideasDictionary.add(new Idea("B",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("C",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("D",i));
         i+=0.1f;
        ideasDictionary.add(new Idea("E",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("F",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("G",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("H",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("I",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("J",i));
        i+=0.1f;
        ideasDictionary.add(new Idea("K",i));


}

public static void  loadParamsFromArgs(String[] args){
    typeOfGenerator=Integer.parseInt(args[TYPE_GENERATOR_INDEX]);
    numberOfIdea=Integer.parseInt(args[NUMBER_IDEAS_INDEX]);
    numberOfNodes=Integer.parseInt(args[NUMBER_NODES_INDEX]);
    numberOfEdge=Integer.parseInt(args[NUMBER_EDGE_INDEX]);
    seed=Float.parseFloat(args[SEED_PARAMS_INDEX]);
}


public static List<Idea> getIdeasRequested(int numberOfIdea){


	    List<Idea> ideas=new ArrayList<Idea>();
	    for(int j=0;j<numberOfIdea;j++) {

	        ideas.add(ideasDictionary.get(j));

        }
	    return ideas;

}

public static void generate(){
    List<Idea> ideas=getIdeasRequested(numberOfIdea);
    List<Idea> customizedIdeas=new ArrayList<>(ideas);
    customizedIdeas.remove(numberOfIdea-1);
        switch (typeOfGenerator){

            case RMAT_SIMPLE_GENERATOR_INDEX:
                new RmatSimpleSimulation(ideas,numberOfNodes,numberOfEdge,0.4f,0.2f,0.2f).simulate(); break;

            case RMAT_CUSTOMIZED_GENERATOR_INDEX:
                new RmatCustomizedSimulation(customizedIdeas,numberOfNodes,numberOfEdge,0.4f,0.2f,0.2f,getNewIdea()).simulate(); break;

            case SCALE_FREE_SIMPLE_GENERATOR_INDEX: new JgraphTScaleFreeSimpleSimulation(ideas,numberOfNodes).simulate(); break;

            case SCALE_FREE_CUSTOMIZED_GENERATOR_INDEX: new JgraphTScaleFreeCustomizedSimulation(customizedIdeas,numberOfNodes,getNewIdea()).simulate(); break;

            case BARABASI_SIMPLE_GENERATOR_INDEX:
                new BarabasiSimpleSimulation(0.3f,ideas,numberOfNodes,20).simulate();break;
            case BARABASI_CUSTOMIZED_GENERATOR_INDEX:
                new BarabasiCustomizedSimulation(0.3f,ideas,numberOfNodes,20,getNewIdea()).simulate();break;
            case BARABASI_SIMPLE_SEEDED_GENERATOR_INDEX:
                new BarabasiSimpleSimulation(0.3f,ideas,numberOfNodes,20,seed).simulate();   break;
            case BARABASI_CUSTOMIZED_SEEDED_GENERATOR_INDEX: new BarabasiCustomizedSimulation(0.3f,ideas,numberOfNodes,20,0.5f,getNewIdea()).simulate(); break;

            case JGRAPHT_BARABASI_SIMPLE_GENERATOR_INDEX :
                new JgraphTBarabasiSimpleSimulation(ideas,(int)numberOfNodes/3,20,numberOfNodes).simulate(); break;
            case JGRAPHT_BARABASI_CUSTOMIZED_GENERATOR_INDEX:
                new JgraphTBarabasiSimpleSimulation(customizedIdeas,(int)numberOfNodes/3,20,numberOfNodes,0).simulate();
                break;
            case JGRAPHT_BARABASI_SIMPLE_SEEDED_GENERATOR_INDEX:  new JgraphTBarabasiSimpleSimulation(ideas,(int)numberOfNodes/3,20,numberOfNodes,(long)seed).simulate(); break;
            case JGRAPHT_BARABASI_CUSTOMIZED_SEEDED_GENERATOR_INDEX:new JgraphTBarabasiCustomizedSimulation(customizedIdeas,(int)numberOfNodes/3,20,numberOfNodes,(long)seed,getNewIdea()).simulate(); break;

            case RANDOM_SIMPLE_GENERATOR_INDEX:  new RandomGeneratorSimpleSimulation(numberOfNodes,ideas,seed).simulate();break;
            case RANDOM_CUSTOMIZED_GENERATOR_INDEX: new RandomGeneratorCustomizedSimulation(numberOfNodes,customizedIdeas,seed,getNewIdea()).simulate(); break;


            case JGRAPHT_SMALL_WORLD_SIMPLE_GENERATOR_INDEX: new JgraphTKleinbergSmallWorldSimpleSimulation(ideas,100,3,2,2).simulate(); break;

            case JGRAPHT_SMALL_WORLD_CUSTOMIZED_GENERATOR_INDEX: new JgraphTKleinbergSmallWorldCustomizedSimulation(customizedIdeas,(int)Math.sqrt(numberOfNodes),3,2,2,getNewIdea()).simulate(); break;









        }





}

    private static Idea getNewIdea() {

        return ideasDictionary.get(numberOfIdea-1);
    }


}

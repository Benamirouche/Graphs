package MainPackage;

import Exceptions.WrongParametersException;
import MainPackage.generators.BarabasiGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe qui appel le generateur
 */
public class Main {

	public static void main(String[] args) {
		List<Idea> ideas=new ArrayList<>();
		
		
       SwingUtilities.invokeLater(() -> {
            ideas.add(new Idea("A"));
            ideas.add(new Idea("B"));
            ideas.add(new Idea("C"));
           /* ideas.add(new Idea("D"));
            ideas.add(new Idea("E"));
            ideas.add(new Idea("F"));
            ideas.add(new Idea("G"));
            ideas.add(new Idea("H"));
            ideas.add(new Idea("I"));
            ideas.add(new Idea("J"));*/
           BarabasiGenerator g=new BarabasiGenerator(0.5f,ideas,1000,3);
           //g.generateWithoutSeed();
           try {
               g.generateWithSeed(0.01f);
           } catch (WrongParametersException e) {
               e.printStackTrace();
               System.exit(-1);
           }
           //RmatGenerator g=new RmatGenerator(100000,2000000,0.4f,0.2f,0.2f,ideas);
           //g.generate();
           //RandomGraphGenerator g=new RandomGraphGenerator(1000,ideas);
           //System.out.println(g.generate(0.1f));
           System.out.println(g.getNbrNodes()+",,,,,"+g.getFinalNbrNodes());
           System.out.println(g.getNbrEdges());
           System.out.println(((float)g.getDegreeSum()/(float)1000));
           System.out.println(g.getNodes().size());
           g.powerLawVerification();
           g.drawPowerLaw();
           g.saveEdgesGEXF();
            //bg.saveEdgesInFile();
       });
	}

}

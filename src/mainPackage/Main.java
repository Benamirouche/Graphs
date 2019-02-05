package mainPackage;

import mainPackage.generators.RandomGraphGenerator;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe qui appel le generateur
 */
public class Main {

	public static void main(String[] args) {
		List<Idea> ideas=new ArrayList<Idea>();
		
		
       SwingUtilities.invokeLater(new Runnable() {
           @Override
           public void run() {
				ideas.add(new Idea("A"));
				ideas.add(new Idea("B"));
				ideas.add(new Idea("C"));
				ideas.add(new Idea("D"));
				ideas.add(new Idea("E"));
				ideas.add(new Idea("F"));
				ideas.add(new Idea("G"));
				ideas.add(new Idea("H"));
				ideas.add(new Idea("I"));
				ideas.add(new Idea("J"));
				//BarabasiGenerator g=new BarabasiGenerator(0.5f,ideas);
				//g.generateWithoutSeed(100,3);
				//g.generateWithSeed(100,3,0.01f);
			   //RmatGenerator g=new RmatGenerator(100,600,0.45f,0.22f,0.22f,ideas);
			   //g.generate();
			   RandomGraphGenerator g=new RandomGraphGenerator(100,ideas);
			   System.out.println(g.generate(0.1f));
			   System.out.println(g.getNbrEdges());
				System.out.println(g.getDegreeSum()/100);
			   	System.out.println(g.getNodes().size());
				g.powerLawVerification();
				g.drawPowerLaw();
				//bg.saveEdgesInFile();
		   }
       });
	}

}

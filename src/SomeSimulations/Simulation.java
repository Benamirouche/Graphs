package SomeSimulations;

import mainPackage.GraphWriters.GexfGraphWriter;
import mainPackage.GraphWriters.IdeasGraphWriter;
import mainPackage.GraphWriters.StructeredGraphWriter;
import mainPackage.generators.BaseGenerator;

public abstract class Simulation {

    protected BaseGenerator generator;

    protected Simulation(BaseGenerator generator){

        this.generator=generator;



    }

    public void  generate(){

        generator.generate();
    }

    public void powerLawVerification(){

        generator.powerLawVerification();
    }
    public void drawPowerLaw(){


        generator.drawPowerLaw();
    }

    public void writeGexf(){

        new GexfGraphWriter(generator).write();

    }
    public  void writeStructuredGraph(){

        new StructeredGraphWriter(generator).write();
    }

    public void writeIdeas(){

        new IdeasGraphWriter(generator).write();
    }
    abstract public void customizingGeneration();

 public void simulate(){

        generate();
        customizingGeneration();
        powerLawVerification();
        drawPowerLaw();
        writeGexf();
        writeStructuredGraph();
        writeIdeas();


 }


}

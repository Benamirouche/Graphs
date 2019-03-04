package SomeSimulations;

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
        generator.writeGexfGraph();

    }
    public  void writeStructuredGraph(){

        generator.writeStructuredGraph();
    }

    public void writeIdeas(){

       generator.writeIdeasApparition();
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

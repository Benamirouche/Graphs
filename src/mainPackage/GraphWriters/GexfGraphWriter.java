package mainPackage.GraphWriters;

import mainPackage.Edge;
import mainPackage.Node;
import mainPackage.generators.BaseGenerator;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class GexfGraphWriter extends GraphWriter {
    private static final String FILE_PATH="graph.gexf";

    public GexfGraphWriter(BaseGenerator generator){
        super(generator);
    }

    public void write(){

        try {
           openFile(FILE_PATH);

            writeHead();

            writeNodes();

            writeEdges();

            writeCloseTags();




            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null || fw!=null)
                   close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }



    }

    private void writeHead() throws IOException {
        bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        bw.write("<gexf xmlns=\"http://www.gexf.net/1.2draft\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"http://www.gexf.net/1.2draft http://www.gexf.net/1.2draft/gexf.xsd\" version=\"1.2\">\n");
        bw.write("\n \n");
        bw.write("\t<meta lastmodifieddate=\""+new Date().toString()+"\">\n");
        bw.write("\t <creator>Anis</creator>\n");
        bw.write("\t <description>Social Network Graph</description>\n");
        bw.write("\t</meta>/n\n");
        bw.write("\t<graph defaultedgetype=\"directed\">\n");
        bw.write("\t\t<attributes class=\"node\">\n");
        //bw.write("\t\t\t<attribute id=\"0\" title=\"name\" type=\"string\"/>");

        bw.write("\t\t\t<attribute id=\"0\" title=\"idea\" type=\"string\"/>\n");
        bw.write("\t\t</attributes>\n\n");

    }

   private void writeNodes() throws IOException {
       bw.write("\t\t<nodes>\n");

       for(Node node:graphGenerator.getNodes().values())
       {


          node.writeGexf(bw);
       }

       bw.write("\t\t</nodes>\n\n\n");


   }
    private void writeEdges() throws IOException {
        List<Edge> edges=graphGenerator.getEdges();
        bw.write("\t\t<edges>\n");


        for(int i=0;i<edges.size();i++)
            edges.get(i).writeGexf(bw,i);

        bw.write("\t\t</edges>\n\n\n");


    }

    private  void writeCloseTags() throws IOException {

        bw.write("\t </graph>\n");
        bw.write("</gexf>\n");
    }




}

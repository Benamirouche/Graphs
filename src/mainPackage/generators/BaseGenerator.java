package mainPackage.generators;

import mainPackage.Edge;
import mainPackage.GraphDrawer;
import mainPackage.Idea;
import mainPackage.Node;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public abstract class BaseGenerator {
	
     /**
     * 
     */
	public static final String BASE_PATH="ProjectFiles"+ File.separator;
	
     /**
     *
     */
	public static final String LOGS_PATH=BASE_PATH+"logs"+File.separator;
	/**
     *
     */
	public static final String GRAPHS_PATH=BASE_PATH+"graphs"+File.separator;
    /**
     * le seuil à ne pas dépasser pour les poids des liens
     */
    protected static final int WEIGHT_MAX = 100;
    /**
     * le nombre de noeuds
     */
    protected int nbrNodes=0;
    /**
     * le nombre de liens
     */
    protected int nbrEdges=0;
    /**
     * la liste des idées possibles dans le graphe (travail à faire)
     */
    protected List<Idea> ideas = new ArrayList<>();
    /**
     * la liste de tout les liens du graphe
     */
    protected List<Edge> edges = new ArrayList<>();
    /**
     * cet map prefAttachSorted est utilisé pour verifier la loi de disrtibution présenté dans le rapport
     * map qui attribut à chaque noeud une liste de tout les autres noeuds
     * qui sont lies à ce premier
     * on identifie un noeud par un numéro
     *
     * @see Node
     */
    protected Map<Integer, List<Integer>> prefAttachSorted = new TreeMap<>();
    /**
     * map qui attribut à chaque identificateur l'objet Node correspondant
     */
    protected Map<Integer, Node> nodes = new TreeMap<>();

    /**
     * initialise les noeuds
     * cette methode instancie tout les noeuds et attribut a chaque noeud un idée aléatoire
     *
     * @see BaseGenerator#getRandomIdea()
     */
    public void initNodes() {
        for (int i = 0; i < nbrNodes; i++) {
            nodes.put(i, new Node(getRandomIdea(), i));
        }
    }

    /**
     * cette methode génère une poids aléatoire
     * @return un poids aléatoire
     */
    public int generateRandomWeight() {
        Random random = new Random();
        return 1 + random.nextInt(WEIGHT_MAX - 1);
    }

    /**
     * cette methode nous permet de trier les
     * noeuds selon leurs degrés de centralité
     * et les afficher
     */
    public void powerLawVerification() {

        nodes.forEach((key, value) -> {
            if (prefAttachSorted.containsKey(value.getDegree())) {
                prefAttachSorted.get(value.getDegree()).add(Math.toIntExact(key));
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(Math.toIntExact(key));
                prefAttachSorted.put(value.getDegree(), list);
            }
        });
        prefAttachSorted.forEach((key, value) ->
            System.out.println(" Degré: " + key + " nombre de noeuds: " + value.size())
        );
    }

    public void saveIdeasStatisticsInFile(){
        try{
            FileWriter fw=new FileWriter("IdeaStatistics.txt");
            BufferedWriter bw=new BufferedWriter(fw);
            Map<Idea,Long> sum=nodes.values().stream().collect(
                    Collectors.groupingBy(Node::getIdea,Collectors.counting())
            );
            sum.forEach((k,v)-> {
                try {
                    bw.write(k.getName()+":"+v+"\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            bw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * cette methode permet de générer un fichier
     * contenant tout les liens du graphe
     * sous la forme
     * arcs[(0,1), (0,3), (1,3), ...]
     *
     * @see RmatGenerator#edges
     */
    public void saveEdgesInFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter("edges.txt");
            bw = new BufferedWriter(fw);
            bw.write("arcs=[");
            Iterator<Edge> it = edges.iterator();
            Edge d = it.next();
            bw.write("(" + d.getNodeSrc().getNum() +"," + d.getNodeSrc().getIdea().getValue()
                    + "," + d.getNodeDest().getNum() + ")");
            for (; it.hasNext();) {
                d = it.next();
                bw.write(", (" + d.getNodeSrc().getNum() + "," + d.getNodeDest().getNum() + ")");
            }
            bw.write("]");
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * dessine le graphe de distribution des centralités des noeuds
     */
    public void drawPowerLaw() {
        new GraphDrawer(prefAttachSorted).setVisible(true);
    }

    /**
     * cette methode permet d'identifier les neouds d'un lien pour calculer leurs centralités
     * @param edge le lien qui contient les informations sur les noeuds source et destination
     */
    public void saveNodesStatistics(Edge edge) {
        edge.getNodeSrc().incOutDegree();
        edge.getNodeDest().incInDegree();
    }

    /**
     *
     * @return
     */
    public List<Edge> getEdges()
    {
        return edges;
    }

    /**
     * genere une idée aléatoire de la liste des idees et la retourne
     * @return un idée aléatoire
     *
     * @see BaseGenerator#ideas
     */
    public Idea getRandomIdea() {
        Random random = new Random();
        return ideas.get(random.nextInt(ideas.size() - 1));
    }

	public List<Idea> getIdeas() {
		return ideas;
	}

	/**
     *
     * @param node
     * @return
     */
    public Node getNode(int node) {
        return this.nodes.get(node);
    }

    /**
     *
     * @return
     */
    public Map<Integer, Node> getNodes() {
        return nodes;
    }

    /**
     *
     * @return
     */
    public int getNbrNodes() {
        return nbrNodes;
    }

    /**
     *
     * @return
     */
    public int getNbrEdges() {
        return nbrEdges;
    }

    /**
     *
     * @return
     */
    public int getDegreeSum() {
        int s=0;
        for (Node n :
                nodes.values()) {
            s+=n.getInDegree();
        }
        return s;
    }

    /**
     *
     */
    public void setIdeaValues(){
        float v=-1;
        for (Idea i :
                this.ideas) {
            i.setValue(v);
            v+=2.f/(this.ideas.size()-1);
        }
    }

    /**
     *
     */
    public void printIdeaValues(){
        this.ideas.stream().forEach(System.out::println);
    }
    
    /**
    anis changes**/
		
		public void saveEdgesGEXF() {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(GRAPHS_PATH+"graph.gexf");
			bw = new BufferedWriter(fw);
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
			bw.write("\t\t<nodes>\n");
			
			for(Node node:nodes.values())
			{
				
				
				bw.write("\t\t\t<node id=\""+node.getNum()+"\" label=\""+"node "+node.getNum()+"\">\n");
				bw.write("\t\t\t\t<attvalues>\n");
//<<<<<<< HEAD:src/mainPackage/generators/BaseGenerator.java
     			bw.write("\t\t\t\t\t<attvalue for=\"0\" value=\""+node.getIdea().getName()+"\"/>\n");
//=======
     			bw.write("\t\t\t\t\t<attvalue for=\"0\" value=\""+node.getIdea().getName()+"\"/>\n");	
//>>>>>>> origin/master:src/mainPackage/generators/BaseGenerator.java
     			bw.write("\t\t\t\t</attvalues>\n");
     		
     			bw.write("\t\t\t</node>\n\n");
			}
			
			bw.write("\t\t</nodes>\n\n\n");
			
			
			
			
			bw.write("\t\t<edges>\n");
			
			
			for(int i=0;i<edges.size();i++)
			bw.write("<edge id=\""+i+"\" source=\""+edges.get(i).getNodeSrc().getNum()+"\" target=\""+edges.get(i).getNodeDest().getNum()+"\" weight=\""+edges.get(i).getWeight()+"\"/>\n");
			
			bw.write("\t\t</edges>\n\n\n");
			
			
			
			bw.write("\t </graph>\n");
			bw.write("</gexf>\n");
						
			
			
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	public Map<Node,List<Node>> getOrganizedFollowersList() {
		return edges.stream()
				.collect(Collectors.groupingBy
							(Edge::getNodeDest,
							 Collectors.mapping(Edge::getNodeSrc, Collectors.toList()))
						);
	}
	public Set<Node> getNonFollowedNodes(Map<Node,List<Node>> organizedList){
		return nodes.values()
				.stream()
				.filter((n)->!organizedList.containsKey(n))
				.collect(Collectors.toSet());
	}

	public Map<Node,List<Node>> getOrganizedFollowingList(){
		return edges.stream()
				.collect(Collectors.groupingBy(Edge::getNodeSrc,
												Collectors.mapping(Edge::getNodeDest, Collectors.toList()))
						);
	}
	public Set<Node> getNonFollowingNodes(Map<Node,List<Node>> organizedList){
			return nodes.values().stream().filter((n)->!organizedList.containsKey(n)).collect(Collectors.toSet());
	}

   	public void getNodesNeighbours() {
		Map<Node,List<Node>> followersList=getOrganizedFollowersList();
		Set<Node> nonFollowedNodes=getNonFollowedNodes(followersList);
		nonFollowedNodes.forEach(node->followersList.put(node,new ArrayList<Node>()));

		Map <Node,List<Node>> followingList=getOrganizedFollowingList();
		Set<Node> nonFollowingNodes=getNonFollowingNodes(followersList);
		nonFollowingNodes.forEach(node->followingList.put(node,new ArrayList<Node>()));

	    BufferedWriter bw = null ;
		FileWriter fw = null;
		try {
			fw = new FileWriter(GRAPHS_PATH+"edgesN.graph");
			bw=new BufferedWriter(fw);
			final BufferedWriter bw2=bw;
			
		   followersList.forEach((nSrc,listNeighbours)->
		  
		   {String line="";
		     line+="n"+nSrc.getNum()+" "+"[";
		     line+="{"+nSrc.getIdea().getName()+","+ideas.indexOf(nSrc.getIdea())*0.1+","+new Random().nextFloat()+","+listNeighbours.size()+"}"+",{";
		     for(int i=0;i<listNeighbours.size()-1;i++)
		     {
		    	 line+="n"+listNeighbours.get(i).getNum()+",";
		     }
			   line+="n"+listNeighbours.get(listNeighbours.size()-1).getNum()+"}]\n";
			   try {
					bw2.write(line);
					System.out.println(line);
				    } catch (IOException e) {
					e.printStackTrace();
				   }
			 
		   }
			
				    );
		   nonFollowedNodes.forEach(n->{
			   

			 
			    String  line="n"+n.getNum()+" "+"["+"{"+n.getIdea().getName()+","+ideas.indexOf(n.getIdea())*0.1+","+new Random().nextFloat()+","+0+"}"+"]\n";
			     try {
					bw2.write(line);
					System.out.println(line);
					
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
			   
		   });
		   
		   
			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	public void saveFollowingNodes() {
		Map <Node,List<Node>> organizedList=edges.stream()
		
				.collect(Collectors.groupingBy(Edge::getNodeSrc,
						                       Collectors.mapping(Edge::getNodeDest, Collectors.toList()))
						);
				
		Set<Node> nonFollowingNodes=nodes.values()
									.stream()
									.filter((n)->!organizedList.containsKey(n))
									.collect(Collectors.toSet());
				
	    BufferedWriter bw = null ;
		FileWriter fw = null;
		try {
			fw = new FileWriter("followingLists.txt");
			bw=new BufferedWriter(fw);
			final BufferedWriter bw2=bw;
			
		   organizedList.forEach((nSrc,listNeighbours)->
		  
		   {String line="";
			   
		     line+="n"+nSrc.getNum()+" "+"[";
			
		     
		     line+="{"+nSrc.getIdea().getName()+","+ideas.indexOf(nSrc.getIdea())*0.1+","+new Random().nextFloat()+","+listNeighbours.size()+"}"+",{";
		     
		     for(int i=0;i<listNeighbours.size()-1;i++)
		     {
		    	 line+="n"+listNeighbours.get(i).getNum()+",";
		    	 
		    	 
		     }
		     
			   line+="n"+listNeighbours.get(listNeighbours.size()-1).getNum()+"}]\n";
			   
			   try {
				   
					bw2.write(line);
					System.out.println(line);
					
				    } catch (IOException e) {
					e.printStackTrace();
				   }
			 
		   }
			
				    );
		   nonFollowingNodes.forEach(n->{
			   

			 
			    String  line="n"+n.getNum()+" "+"["+"{"+n.getIdea().getName()+","+ideas.indexOf(n.getIdea())*0.1+","+new Random().nextFloat()+","+0+"}"+"]\n";
			     try {
					bw2.write(line);
					System.out.println(line);
					
				    } catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				   }
			   
		   });
		   
		   
		   
		   
		
			
			

			System.out.println("Done");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	public void saveIdeasApparition() {
	Map<String,Long> flatedNodes=nodes.entrySet().stream().map(v->v.getValue().getIdea()).collect(Collectors.groupingBy(Idea::getName,Collectors.counting()));
	
	
	   BufferedWriter bw = null  ;
	   FileWriter fw = null;
	
	   	try {
			fw=new FileWriter(GRAPHS_PATH+"ideas.txt");
			 bw=new BufferedWriter(fw);
			 
			 BufferedWriter bw2=bw;
			 flatedNodes.forEach((idea,nombreApparition)->{
					try {
						bw2.write(idea+":"+nombreApparition+"\n");
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				   
			 });
			 
		} catch (IOException e1) {
		
			e1.printStackTrace();
		}
	   	finally {
	   		try {
	   			if (bw != null)
	   				bw.close();
	   			if (fw != null)
	   				fw.close();
	   			} catch (IOException ex) {
	   					ex.printStackTrace();
	   			}
}
	  
	   
	   
		   
		   
		   
	   
}	

	
	
	
  	
	
	
	
	
    
    
    
    
    
    
    
    
}

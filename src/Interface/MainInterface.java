package Interface;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import MainPackage.generators.RmatGenerator;
import MainPackage.Idea;

import java.util.ArrayList;
import java.util.List;

/**
 * la classe de l'interface graphique pour générer un graphe suivant la methode R-MAT
 * et génère un fichier qui contient tout les liens
 */
public class MainInterface extends Application{

    public static void main(String[] args) throws InterruptedException {

        Application.launch(MainInterface.class, args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Social Network Generators");


        final Group root = new Group();
        Scene scene = new Scene(root, 1100, 600, Color.LIGHTGREEN);


        HBox rmatHbox=new HBox();
        rmatHbox.setLayoutX(20);
        rmatHbox.setLayoutY(100);

        Text text=new Text("R-MAT GRaph");
        text.setFont(new Font(25));
        text.setX(450);
        text.setY(50);

//    	   	 ProgressBar pb = new ProgressBar(0);
//             pb.setLayoutX(450);
//             pb.setLayoutY(400);
//             pb.setPrefSize(300,30);
//             root.getChildren().add(pb);
//


        Label nodesLabel=new Label(" Number Of Nodes");
        nodesLabel.setFont(new Font("Arial",22));
        nodesLabel.setPrefSize(200, 30);
        nodesLabel.setStyle("-fx-font-weight: bold");


        rmatHbox.getChildren().add(nodesLabel);


        TextField nodesField=new TextField();
        nodesField.setPrefSize(200, 30);

        rmatHbox.getChildren().add(nodesField);



        Label edgesLabel=new Label(" Number Of Edges");
        edgesLabel.setFont(new Font("Arial",22));
        edgesLabel.setPrefSize(300, 30);
        edgesLabel.setStyle("-fx-font-weight: bold");

        edgesLabel.setPadding(new Insets(0, 0, 0, 100));
        rmatHbox.getChildren().add(edgesLabel);

        TextField edgesField=new TextField();
        edgesField.setPrefSize(200, 30);

        rmatHbox.getChildren().add(edgesField);

        Button btn = new Button();
        btn.setLayoutX(450);
        btn.setLayoutY(170);
        btn.setPrefSize(200,30);
        btn.setText("Generate");
        btn.setFont(new Font("Arial",25));

        btn.addEventFilter(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Platform.setImplicitExit(false);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        boolean b=false;
                        int edgesNbr = 0;
                        int nodesNbr = 0;
                        try {
                            nodesNbr=Integer.parseInt(nodesField.getText());}
                        catch(NumberFormatException e) {
                            nodesField.clear();
                            nodesField.setPromptText("this field in not a long number");
                            nodesField.setFocusTraversable(true);
                            b=true;
                        }
                        try {  edgesNbr=Integer.parseInt(edgesField.getText()); }
                        catch(NumberFormatException e) {
                            edgesField.clear();
                            edgesField.setPromptText("this field in not a long number");
                            edgesField.setFocusTraversable(true);
                            b=true;
                        }
                        if(!b)
                        {
                            final int edgesNbr2=edgesNbr;
                            final int nodesNbr2=nodesNbr;
                            Thread th = new Thread(new Task<Object>(){

                                @Override
                                protected Object call() throws Exception {
                                    List<Idea> ideas=new ArrayList<Idea>();
                                    ideas.add(new Idea("A"));
                                    ideas.add(new Idea("B"));
                                    ideas.add(new Idea("C"));
                                    ideas.add(new Idea("D"));
                                    ideas.add(new Idea("E"));
                                    ideas.add(new Idea("F"));
                                    ideas.add(new Idea("G"));
                                    ideas.add(new Idea("H"));
                                    RmatGenerator generator=new RmatGenerator(nodesNbr2,edgesNbr2, 0.40f, 0.20f, 0.20f, ideas);
                                   /* generator.generate();
                                    generator.saveEdgesInFile();
                                    generator.powerLawVerification();
                                    generator.drawPowerLaw();
*/
                                    return null;
                                }
                            });
                            th.setDaemon(true);
                            th.start();
                        }
                    }
                });
            }
        });
        root.getChildren().add(btn);
        root.getChildren().add(rmatHbox);
        root.getChildren().add(text);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

}

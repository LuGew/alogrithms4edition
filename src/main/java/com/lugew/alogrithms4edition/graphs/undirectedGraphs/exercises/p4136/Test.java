package com.lugew.alogrithms4edition.graphs.undirectedGraphs.exercises.p4136;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * @author LuGew
 * @since 2018/4/23
 */
public class Test extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Group group = new Group();
        Scene scene = new Scene(group, 1920, 1080, Color.WHITE);
        EulideanGraph eulideanGraph = new EulideanGraph("D:\\LuGew\\Documents\\Algorithms/map.txt");
        int x, y;
        String location[] = new String[2];
        for (int i = 0; i < eulideanGraph.getVertex(); i++) {
            location = eulideanGraph.name(i).split(",");
            group.getChildren().add(new Circle(Integer.parseInt(location[0]), Integer.parseInt(location[1]), 20));
        }
        primaryStage.setScene(scene);
        primaryStage.setTitle("EulideanGraph");
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}

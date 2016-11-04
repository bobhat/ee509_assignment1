package ee509_assign1;

import javafx.application.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;


import javafx.stage.Stage;

public class MyJavaFx extends Application {
	@Override
	public void start(Stage primaryStage){
		
		Pane pane = new Pane();
		
		Circle circle = new Circle();
		circle.centerXProperty().bind(pane.widthProperty().divide(2));
		circle.centerYProperty().bind(pane.heightProperty().divide(2));
		circle.setRadius(50);
		circle.setStroke(Color.BLACK);
		circle.setFill(Color.RED);
		
		
		pane.getChildren().add(circle);
		pane.setStyle("-fx-background-color: lightgrey;");
		pane.setRotate(45);
		
		Scene scene = new Scene(pane, 200, 250);
		
		primaryStage.setTitle("MYfirsttest");
		primaryStage.setScene(scene);
		primaryStage.show();
		
				
	}

	
	public static void main(String[] args) {
		launch(args);

	}
}

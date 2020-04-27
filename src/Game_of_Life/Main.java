package Game_of_Life;

import java.io.*;

import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Main class
 * @author Anton Pridybailo
 */
 
public class Main extends Application {
	private static final int sceneX = 1250;                   
	private static final int sceneY = 640;                    
	private static final int canvasX = 1142;
	private static final int canvasY = 562;
	private static final int cellSize = 12;
	private static final int gridX = canvasY/cellSize;
	private static final int gridY = canvasX/cellSize;
	
	FieldManager fm = new FieldManager(canvasX,canvasY,cellSize,gridX,gridY);
	boolean autorun = false;
	int autorunSpeed = 1000;
	Slider slider = new Slider(1,25,12);
	public static void main(String[] args) {
		launch(args);
    }
	

	public void start (Stage primaryStage) throws Exception{
		
		FileManager fl = new FileManager();
		
		fm.setCellGrid(fl.loadSave(fm.getCellGrid()));
		fm.stagnateField();
		Auto auto = new Auto(); 
		Stage window;
		Scene scene;
		window = primaryStage;
		window.setMaximized(true);			
		slider.setShowTickMarks(true);
		
		ObservableList<String> modes = FXCollections.observableArrayList("Dot", "Glyder", 
				"Switch","SpaceShip","Pulsar");
		ComboBox<String> cursorMode = new ComboBox<String>(modes);
		cursorMode.setValue("Dot");
		
		Button button_run = new Button("Run"); 
		Button button_stop = new Button("Stop");
		Button button_stepf = new Button("Next\nGen");
		Button button_clear = new Button("Clear\nfield");
		Button button_randomize = new Button("Rand");
		Button button_savefile = new Button("Save file");
		Button button_exit = new Button("Exit");
		button_run.setStyle("-fx-background-color: #98FB98;");
		button_stop.setStyle("-fx-background-color: #FF6347;");
		button_stepf.setStyle("-fx-background-color: #F0FFF0;");
		button_randomize.setStyle("-fx-background-color: #F0FFF0;");
		button_clear.setStyle("-fx-background-color: #F0FFF0;");
		button_savefile.setStyle("-fx-background-color: #98FB98;");
		button_exit.setStyle("-fx-background-color: #FF6347;");
		VBox leftMenu = new VBox(2);
		VBox rightMenu = new VBox();
		HBox topMenu = new HBox(3);
		HBox bottomMenu = new HBox(3);
		topMenu.setStyle("-fx-background-color: #303030;"); 
		topMenu.setPadding(new Insets(10, 0, 10, 130));
		leftMenu.setStyle("-fx-background-color: #C0C0C0;");
		leftMenu.setPadding(new Insets(10, 5, 10, 5));
		rightMenu.setStyle("-fx-background-color: #C0C0C0;");
		rightMenu.setPadding(new Insets(10, 45, 10, 5));
		bottomMenu.setStyle("-fx-background-color: #303030;");
		bottomMenu.setPadding(new Insets(13, 0, 10, 160)); 
		leftMenu.getChildren().addAll(button_run,button_stop,button_stepf,button_clear,button_randomize);
		topMenu.getChildren().addAll(cursorMode,button_savefile,button_exit);
		bottomMenu.getChildren().addAll(slider);
		
		button_run.setOnAction(e -> {autorun = true; auto.start();}); 
		button_stop.setOnAction(e -> {autorun = false;});
		button_stepf.setOnAction(e -> fm.updateField());
		button_savefile.setOnAction(e -> {try {
			fl.refreshSave(fm.getCellGrid());
		} catch (Exception e1) {
			e1.printStackTrace();
		}});
		button_exit.setOnAction(e ->{ try {fl.closeAll();} catch (Exception e1) {e1.printStackTrace();} System.exit(0);});
		button_clear.setOnAction(e -> {fm.clearField();});
		button_randomize.setOnAction(e -> fm.randomizeField());		
		
		BorderPane borderpane = new BorderPane(); 
		borderpane.setTop(topMenu);
		borderpane.setLeft(leftMenu);
		borderpane.setBottom(bottomMenu);
		borderpane.setRight(rightMenu);
		borderpane.setCenter(fm.getField());		
		
		scene = new Scene(borderpane,sceneX,sceneY);
		
		scene.setOnMouseClicked(e -> fm.clickMouse(e.getSceneX(), e.getSceneY(),cursorMode.getValue()) );
	
		window.setScene(scene);
		window.setTitle("Game of Life");
		window.show();
		
		
		
	}
	
	class Auto extends Thread {  
		@Override
		public void run() {
		while(true) {
			if(autorun) fm.updateField();	
				try {
					Thread.sleep(1000/(long)slider.getValue());
				}
				catch(InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}		
		}
		
	}
	
}
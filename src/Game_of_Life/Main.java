package Game_of_Life;

import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
//Создал классы CellGrid и Cell, реализующую логику игры
//Реализовал игровое поле, его пошаговое изменение
//Создал специальный класс FieldManager,который редактирует игровое поле
//Рабочие кнопки на данный момент - Step Forward, Clear, Randomize, можно рисовать на поле с помощью мыши
//Кнопки RUN(авторежим) и Stop работают, но только один раз, второе нажатие крашит программу,
//в дальнейшем разберусь с этой проблемой(дело в потоке Auto)

public class Main extends Application {
	private static final int sceneX = 1250;                   //Define'ы для параметров поля, в дальнейшем
	private static final int sceneY = 640;                    //сделаю изменение вручную
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
		Auto auto = new Auto(); 
		Stage window;
		Scene scene;
		window = primaryStage;
		
		slider.setShowTickMarks(true);
		Button button_run = new Button("Run"); // создаю кнопки
		Button button_stop = new Button("Stop");
		Button button_stepf = new Button("Next\nGen");
		Button button_stepb = new Button("Prev\nGen");
		Button button_clear = new Button("Clear\nfield");
		Button button_randomize = new Button("Rand");
		Button button_openfile = new Button("Open file");
		Button button_importfile = new Button("Import file");
		Button button_savefile = new Button("Save file");
		VBox leftMenu = new VBox(2);
		VBox rightMenu = new VBox();
		HBox topMenu = new HBox(3);
		HBox bottomMenu = new HBox(3);
		topMenu.setStyle("-fx-background-color: #6495ED;"); //раскрашиваю панели
		topMenu.setPadding(new Insets(10, 0, 10, 130));
		leftMenu.setStyle("-fx-background-color: #ADD8E6;");
		leftMenu.setPadding(new Insets(10, 5, 10, 5));
		rightMenu.setStyle("-fx-background-color: #ADD8E6;");
		rightMenu.setPadding(new Insets(10, 45, 10, 5));
		bottomMenu.setStyle("-fx-background-color: #87CEFA;");
		bottomMenu.setPadding(new Insets(13, 0, 10, 160)); //6495ED 336699
		leftMenu.getChildren().addAll(button_run,button_stop,button_stepf,
				button_stepb,button_clear,button_randomize);
		topMenu.getChildren().addAll(button_openfile,button_importfile,button_savefile);
		bottomMenu.getChildren().addAll(slider);
		
		button_run.setOnAction(e -> {autorun = true; auto.start();}); //привязываю действия к кнопкам
		button_stop.setOnAction(e -> {autorun = false;});
		button_stepf.setOnAction(e -> fm.updateField());
		button_stepb.setOnAction(e -> {});
		button_clear.setOnAction(e -> fm.clearField());
		button_randomize.setOnAction(e -> fm.randomizeField());		
		
		BorderPane borderpane = new BorderPane(); // Собираю borderpane
		borderpane.setTop(topMenu);
		borderpane.setLeft(leftMenu);
		borderpane.setBottom(bottomMenu);
		borderpane.setRight(rightMenu);
		borderpane.setCenter(fm.getField());		
		
		scene = new Scene(borderpane,sceneX,sceneY);
		
		scene.setOnMouseClicked(e -> fm.clickMouse(e.getSceneX(), e.getSceneY()) );
	
		window.setScene(scene);
		window.setTitle("Game of Life");
		window.show();
		
	}
	
	class Auto extends Thread {  // Поток авторежима
		@Override
		public void run() {
		while(true) {
			System.out.println("sss");
			if(!autorun) break;
			fm.updateField();
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
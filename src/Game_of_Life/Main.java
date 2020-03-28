package Game_of_Life;

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
//Рабочие кнопки на данный момент - Step Forward, Clear, Randomize, можно рисовать на поле с помощью мыши
//Прямо сейчас пытаюсь создать класс FieldManager, который будет работать с полем
//(вынести туда то, чего в мейне быть не должно)
public class Main extends Application {
	private static final int sceneX = 1250;                   //Define'ы для параметров поля, в дальнейшем
	private static final int sceneY = 640;                    //сделаю изменение вручную
	private static final int canvasX = 1142;
	private static final int canvasY = 562;
	private static final int cellSize = 17;
	private static final int gridX = canvasY/cellSize;
	private static final int gridY = canvasX/cellSize;
	
	public static void main(String[] args) {
		launch(args);
    }
	
	public void start (Stage primaryStage) throws Exception{
		Stage window;
		Scene scene;
		window = primaryStage;
		
		CellGrid MyGrid = new CellGrid(gridX,gridY);
		MyGrid.setGlyder(10,10);
		MyGrid.setGlyder(15,8);
		MyGrid.setGlyder(7,25);
		MyGrid.setGlyder(13,20);
		MyGrid.setGlyder(16,32);
		MyGrid.setGlyder(14,40);
		
		MyGrid.setSwitch(27,10);
		MyGrid.setSwitch(27,15);
		MyGrid.setSwitch(27,20);
		MyGrid.setSwitch(27,25);
		MyGrid.setSwitch(27,30);
		MyGrid.setSwitch(27,35);
		MyGrid.setSwitch(27,40);
		MyGrid.setSwitch(27,45);
		MyGrid.setSwitch(27,50);
		MyGrid.setSwitch(27,55);
		
		GraphicsContext gc;                         
		Canvas canvas = new Canvas(canvasX,canvasY); //устанавливаю поле в начальное положение
		gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
	    gc.setFill(Color.WHITE);
		gc.setLineWidth(0);
		gc.fillRect(1,1,canvasX,canvasY);
		 gc.setFill(Color.BLACK);
		  
		for(int i = 0; i < gridX; i++)
		for(int j = 0; j < gridY; j++)
		{
		if(MyGrid.getCell(i, j).getState())
		gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
		else
		gc.strokeRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
		
		}
		
		Slider slider = new Slider();
		Button button_run = new Button("Run"); // создаю кнопки
		Button button_stop = new Button("Stop");
		Button button_stepf= new Button("Next\nGen");
		Button button_stepb= new Button("Prev\nGen");
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
		
		button_run.setOnAction(e ->{}); //привязываю действия к кнопкам
		button_stop.setOnAction(e -> {});
		button_stepf.setOnAction(e -> {
		MyGrid.stepForward();
		 gc.setFill(Color.WHITE);
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++)
			{
			gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
			}
		 gc.setFill(Color.BLACK);
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++)
			{
			if(MyGrid.getCell(i, j).getState())
			gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
			else
			gc.strokeRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
			}
		}
		);
		button_stepb.setOnAction(e -> {});
		button_clear.setOnAction(e -> {
			MyGrid.clearGrid();
			gc.setFill(Color.WHITE);
			gc.fillRect(1,1,canvasX,canvasY);
			for(int i = 0; i < gridX; i++)
				for(int j = 0; j < gridY; j++)
				{
				gc.strokeRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
				}
		}
		);
		button_randomize.setOnAction(e -> {
		MyGrid.randomizeGrid();
		 gc.setFill(Color.WHITE);
			for(int i = 0; i < gridX; i++)
				for(int j = 0; j < gridY; j++)
				{
				gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
				}
			 gc.setFill(Color.BLACK);
			for(int i = 0; i < gridX; i++)
				for(int j = 0; j < gridY; j++)
				{
				if(MyGrid.getCell(i, j).getState())
				gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
				else
				gc.strokeRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
				}
			}
	);		
		
		BorderPane borderpane = new BorderPane(); // Собираю borderpane
		borderpane.setTop(topMenu);
		borderpane.setLeft(leftMenu);
		borderpane.setBottom(bottomMenu);
		borderpane.setRight(rightMenu);
		borderpane.setCenter(canvas);		
		
		scene = new Scene(borderpane,sceneX,sceneY);
		
		scene.setOnMouseClicked(e -> {  //Реакция поля на нажатие мыши
			System.out.println(e.getSceneX() + " " + e.getSceneY() );
			if(!MyGrid.getCell((int)((e.getSceneY() - 45)/cellSize ),(int)((e.getSceneX() - 56)/cellSize)).getState() )
			MyGrid.setCell((int)((e.getSceneY() - 45)/cellSize ),(int) ((e.getSceneX() - 56)/cellSize), true);
			else MyGrid.setCell((int)((e.getSceneY() - 45)/cellSize ),(int) ((e.getSceneX() - 56)/cellSize), false);
			gc.setFill(Color.WHITE);
			for(int i = 0; i < gridX; i++)
				for(int j = 0; j < gridY; j++)
				gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);	
			 gc.setFill(Color.BLACK);
			for(int i = 0; i < gridX; i++)
				for(int j = 0; j < gridY; j++)
				{
					if(MyGrid.getCell(i, j).getState())
						gc.fillRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
					else
						gc.strokeRect(j*cellSize + 1, i*cellSize + 1, cellSize, cellSize);
				}
			
		}
		);
	

		window.setScene(scene);
		window.setTitle("Game of Life");
		window.show();

	}
	
}
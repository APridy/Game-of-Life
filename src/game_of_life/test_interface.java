package game_of_life;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;

public class test_interface extends Application {
	
	Stage window;
	Scene scene;
	
	public static void main(String[] args) {
		launch(args);
    }
	
	@Override
	public void start (Stage primaryStage) throws Exception{
		window = primaryStage;
		
		Slider slider = new Slider();
		Label label = new Label("						ЛР 1\n	   Создание графического интерфейса(Игра 'Жизнь')\n"
				+ "\nЗдесь будет раcполагаться само игровое поле\nКнопки слева отвечают за"
				+ " взаимодействие с полем\nПолзунок снизу регулирует скорость симуляции"
				+ "\nКнопки сверху будут отвечать за загрузку сохранений,\nвставку сохраненных фигурок и тд"
				+ "\n\nP.S. Со временем сделаю все красивее, сейчас показываю \nчто я разобрался с JavaFx");
		
		Button button_run = new Button("Run"); // создаю кнопки
		Button button_stop = new Button("Stop");
		Button button_stepf= new Button("Next\nGen");
		Button button_stepb= new Button("Prev\nGen");
		Button button_clear= new Button("Clear\nfield");
		Button button_undo = new Button("Undo");
		Button button_openfile = new Button("Open file");
		Button button_importfile = new Button("Import file");
		Button button_savefile = new Button("Save file");


		VBox leftMenu = new VBox(2);
		VBox rightMenu = new VBox();
		HBox topMenu = new HBox(3);
		HBox bottomMenu = new HBox(3);
		VBox centerMenu = new VBox();
		topMenu.setStyle("-fx-background-color: #6495ED;"); //раскрашиваю панели
		topMenu.setPadding(new Insets(10, 0, 10, 130));
		leftMenu.setStyle("-fx-background-color: #ADD8E6;");
		leftMenu.setPadding(new Insets(10, 5, 10, 5));
		rightMenu.setStyle("-fx-background-color: #ADD8E6;");
		rightMenu.setPadding(new Insets(10, 45, 10, 5));
		bottomMenu.setStyle("-fx-background-color: #87CEFA;");
		bottomMenu.setPadding(new Insets(13, 0, 10, 160)); //6495ED 336699
		
		leftMenu.getChildren().addAll(button_run,button_stop,button_stepf,button_stepb,button_clear,button_undo);
		topMenu.getChildren().addAll(button_openfile,button_importfile,button_savefile);
		bottomMenu.getChildren().addAll(slider);
		centerMenu.getChildren().addAll(label);
		
		button_run.setOnAction(e -> System.out.println("Run")); //пробовал привязывать действия к кнопкам
		button_stop.setOnAction(e -> System.out.println("Stop"));
		button_stepf.setOnAction(e -> System.out.println("Step forward"));
		button_stepb.setOnAction(e -> System.out.println("Step backward"));
		button_clear.setOnAction(e -> System.out.println("Clear"));
		
		BorderPane borderpane = new BorderPane();
		borderpane.setTop(topMenu);
		borderpane.setLeft(leftMenu);
		borderpane.setBottom(bottomMenu);
		borderpane.setRight(rightMenu);
		borderpane.setCenter(centerMenu);
		
		scene = new Scene(borderpane,456,380);
		
		window.setScene(scene);
		window.setTitle("Game of Life");
		window.show();
	}
			
}
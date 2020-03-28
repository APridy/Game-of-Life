package Game_of_Life;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class FieldManager {  //тестовый класс, пока не используется
	
	Canvas field = new Canvas();
	GraphicsContext gc = field.getGraphicsContext2D();
	int fieldX = 0;
	int fieldY = 0;
	int fieldCellSize = 0;
	
	FieldManager(int x,int y,int c,int cs) {
		fieldX = x;
		fieldY = y;
		fieldCellSize = cs;
	}
}

package Game_of_Life;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FieldManager {  //тестовый класс, пока не используется
	
	int fieldX;
	int fieldY;
	int fieldCellSize;
	int gridX;
	int gridY;
	Canvas field = new Canvas();
	GraphicsContext gc = field.getGraphicsContext2D();
	CellGrid MyGrid = new CellGrid(0,0);
	
	FieldManager(int x,int y,int cs, int X, int Y) {
		fieldX = x;
		fieldY = y;
		fieldCellSize = cs;
		gridX = X;
		gridY = Y;
		MyGrid = new CellGrid(gridX,gridY);
		//
		MyGrid.setGlyder(10,10);  // здесь поставил на поле пару фигур
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
		//
		field = new Canvas(fieldX,fieldY); 
		gc = field.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
	    gc.setFill(Color.WHITE);
		gc.setLineWidth(0);
		gc.fillRect(1,1,fieldX,fieldY);
		gc.setFill(Color.BLACK);
		System.out.println("dd");
		
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++) {
			if(MyGrid.getCell(i, j).getState()) 
			gc.fillRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize);
			else
			gc.strokeRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize); 
		}
			
	}
	void prepareField() { // очистить поле
		gc.setFill(Color.WHITE);
		gc.setLineWidth(0);
		gc.fillRect(1,1,fieldX,fieldY);
		gc.setStroke(Color.BLACK);
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++)
			{
			gc.strokeRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize);
			}
	}
	void clearField() { // очистить сетку и поле
		MyGrid.clearGrid();
		prepareField();
	}
	
	void stagnateField() { //считать значение сетки и нарисовать соотв.поле
		prepareField();
		gc.setFill(Color.BLACK);
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++) {
			if(MyGrid.getCell(i, j).getState()) 
			gc.fillRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize);
			else
			gc.strokeRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize); 
		}
	}
	
	void updateField() {// шаг вперед
		MyGrid.stepForward();
		prepareField();
		gc.setFill(Color.BLACK);
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++) {
			if(MyGrid.getCell(i, j).getState()) 
			gc.fillRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize);
			else
			gc.strokeRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize); 
		}				
	}
	
	void clickMouse(double SceneX,double SceneY) { //реакция на клик
		if(!MyGrid.getCell((int)((SceneY - 45)/fieldCellSize ),(int)((SceneX - 56)/fieldCellSize)).getState() )
			MyGrid.setCell((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 56)/fieldCellSize), true);
		else MyGrid.setCell((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 56)/fieldCellSize), false);	
		stagnateField();
	}
	
	void randomizeField() { //рандомизировать сетку и поле
		MyGrid.randomizeGrid();
		updateField();
	}
	
	Canvas getField() {	//возвратить поле
		System.out.println("ss");
		return field;
	}
}

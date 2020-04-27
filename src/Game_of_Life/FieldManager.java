package Game_of_Life;


import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Class FieldManager
 * Used to draw cell grid on the game field
 * @author Anton Pridybailo
 */
public class FieldManager { 
	/** Data member field width in pixels*/
	int fieldX;
	/** Data member field height in pixels*/
	int fieldY;
	/** Data member size of cell in pixels*/
	int fieldCellSize;
	/** Data member height of cell grid*/
	int gridX;
	/** Data member width of cell grid*/
	int gridY;
	/** Data member game field*/
	Canvas field = new Canvas();
	/** Data member graphics context*/
	GraphicsContext gc = field.getGraphicsContext2D();
	/** Data member cell grid*/
	CellGrid MyGrid = new CellGrid(0,0);
	
	/**
	 * Constructor function with parameters
	 * @param x field width in pixels
	 * @param y field height in pixels
	 * @param cs size of cell in pixels
	 * @param X height of cell grid
	 * @param Y width of cell grid
	 */
	public FieldManager(int x,int y,int cs, int X, int Y) {
		fieldX = x;
		fieldY = y;
		fieldCellSize = cs;
		gridX = X;
		gridY = Y;
		MyGrid = new CellGrid(gridX,gridY);
		field = new Canvas(fieldX,fieldY);
		
		gc = field.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
	    gc.setFill(Color.WHITE);
		gc.setLineWidth(0);
		gc.fillRect(1,1,fieldX,fieldY);
		gc.setFill(Color.BLACK);
		
		for(int i = 0; i < gridX; i++)
			for(int j = 0; j < gridY; j++) {
			if(MyGrid.getCell(i, j).getState()) 
			gc.fillRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize);
			else
			gc.strokeRect(j*fieldCellSize + 1, i*fieldCellSize + 1, fieldCellSize, fieldCellSize); 
		}
			
	}
	
	/**
	 * Covers field with empty squares
	 */
	public void prepareField() { 
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
	
	/**
	 * Clears cell grid and calls prepareField method
	 * @see #prepareField()
	 */
	public void clearField() { 
		MyGrid.clearGrid();
		prepareField();
	}
	
	/**
	 * Reads the state of cell grid and draws field according to it
	 */
	public void stagnateField() { 
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
	
	/**
	 * Makes a step forward for cell grid and updates field afterwards
	 * @see #stagnateField()
	 */
	public void updateField() {
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
	
	/**
	 * Draws a figure on the field according to chosen cursor mode
	 * @param SceneX cursor x position
	 * @param SceneY cursor y position
	 * @param mode chosen cursor mode
	 */
	public void clickMouse(double SceneX,double SceneY,String mode) { //реакция на клик
		switch(mode) {
		case "Dot": {
			if(!MyGrid.getCell((int)((SceneY - 45)/fieldCellSize ),(int)((SceneX - 72)/fieldCellSize)).getState() )
				MyGrid.setCell((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize), true);
			else MyGrid.setCell((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize), false);	
			break;
		}
		case "Glyder": {		
				MyGrid.setGlyder((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize));
			break;
		}
		case "Switch": {		
			MyGrid.setSwitch((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize));
		break;
		}
		case "SpaceShip": {		
			MyGrid.setSpaceShip((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize));
		break;
		}
		case "Pulsar": {		
			MyGrid.setPulsar((int)((SceneY - 45)/fieldCellSize ),(int) ((SceneX - 72)/fieldCellSize));
		break;
		}
		}
		stagnateField();
	}
	
	/**
	 * Randomizes cell grid and updates field afterwards
	 * @see #stagnateField()
	 */
	public void randomizeField() { 
		MyGrid.randomizeGrid();
		stagnateField();
	}
	
	/**
	 * Returns cell grid
	 * @return returns cell grid
	 */
	public CellGrid getCellGrid() {
		return MyGrid;
	}
	
	/**
	 * Sets cell grid state
	 * @param Grid cell grid
	 */
	public void setCellGrid(CellGrid Grid) {
		MyGrid = Grid;
	}
	
	/**
	 * Returns canvas value of the game field
	 * @return field
	 */
	public Canvas getField() {	
		return field;
	}
}

package Game_of_Life;

/**
 * Class CellGrid
 * Used to redact a matrix of Cell class objects
 * @author Anton Pridybailo
 */
public class CellGrid {

/** Data member matrix of cells*/
Cell[][] Grid;
/** Data member second matrix of cells*/
Cell[][] GridNext;
/** Data members second height and length of cell grid*/
int hei,len;

/**
 * returns height of cell grid
 * @return height of cell grid
 */
public int getHeight() {
	return hei;
}

/**
* returns length of cell grid
* @return length of cell grid
*/
public int getLength() {
	return len;
}

/**
 * Constructor function
 */
public CellGrid() { 
	hei = 0;
	len = 0;
	Grid = new Cell[hei][len];
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
		  Grid[i][j] = new Cell(); 
	}
	GridNext = new Cell[hei][len];
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
		  GridNext[i][j] = new Cell(); 
	}
}

/**
 * Constructor with parameters
 * @param x height of cell grid
 * @param y length of cell grid
 */
public CellGrid(int x,int y) {
resizeGrid(x,y);
}

/**
 * Returns cell grid
 * @return cell grid
 */
public Cell[][] getGrid(){
return Grid;	
}

/**
 * Returns specific cell
 * @param x x cell coordinate
 * @param y y cell coordinate
 * @return cell
 */
public Cell getCell(int x,int y){
return Grid[x][y];
}

/**
 * Sets specific cell state
 * @param x x cell coordinate
 * @param y y cell coordinate
 * @param b cell state
 */
public void setCell(int x,int y,boolean b){
	Grid[x][y].setState(b);
}

/**
 * Resizes cell grid
 * @param x new cell grid height
 * @param y new cell grid length
 */
public void resizeGrid(int x,int y) { 
	hei = x;
	len = y;
	Grid = new Cell[hei][len];
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
		  Grid[i][j] = new Cell(); 
	}
	GridNext = new Cell[hei][len];
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
		  GridNext[i][j] = new Cell(); 
	}
}

/**
 * Randomize cell grid
 */
public void randomizeGrid(){ 
	double r;
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
			r = Math.random();
			if (r > 0.5)
			Grid[i][j].setState(true);
			else
			Grid[i][j].setState(false);
		}
	
}

/**
 * Sets all cell states to "false"
 */
public void clearGrid() { 
	for(int i = 0;i < hei;i ++) 
		for(int j = 0;j < len; j++) 
			Grid[i][j].setState(false);
}

/**
 * Prints grid in console
 */
public void printGrid() { 
	for(int i = 0;i < hei;i ++) {
		for(int j = 0;j < len; j++) {
			if(Grid[i][j].getState() == true) System.out.print("■");
			else 
			System.out.print("□");
		}
		System.out.print('\n');
	}	
}

/**
 * Draws glyder on chosen coordinate
 * @param x x coordinate
 * @param y y coordinate
 */
public void setGlyder(int x,int y) { 
	setCell(x - 1,y,true);
	setCell(x,y + 1,true);
	setCell(x + 1,y + 1,true);
	setCell(x + 1,y,true);
	setCell(x + 1,y - 1,true);
}

/**
 * Draws switch on chosen coordinate
 * @param x x coordinate
 * @param y y coordinate
 */
public void setSwitch(int x,int y) {
	setCell(x - 1,y,true);
	setCell(x,y,true);
	setCell(x + 1,y,true);
}

/**
 * Draws lightweight spaceship on chosen coordinate
 * @param x x coordinate
 * @param y y coordinate
 */
public void setSpaceShip(int x,int y) {
	setCell(x - 2,y + 2,true);
	setCell(x - 2,y - 1,true);
	setCell(x - 1,y - 2,true);
	setCell(x,y - 2,true);
	setCell(x,y + 2,true);
	setCell(x + 1,y - 2,true);
	setCell(x + 1,y - 1,true);
	setCell(x + 1,y,true);
	setCell(x + 1,y + 1,true);
}

/**
 * Draws pulsar on chosen coordinate
 * @param x x coordinate
 * @param y y coordinate
 */
public void setPulsar(int x,int y) {
	setCell(x - 6,y - 4,true);
	setCell(x - 6,y - 3,true);
	setCell(x - 6,y - 2,true);
	setCell(x - 6,y + 4,true);
	setCell(x - 6,y + 3,true);
	setCell(x - 6,y + 2,true);
	
	setCell(x - 1,y - 4,true);
	setCell(x - 1,y - 3,true);
	setCell(x - 1,y - 2,true);
	setCell(x - 1,y + 4,true);
	setCell(x - 1,y + 3,true);
	setCell(x - 1,y + 2,true);
	
	setCell(x + 1,y - 4,true);
	setCell(x + 1,y - 3,true);
	setCell(x + 1,y - 2,true);
	setCell(x + 1,y + 4,true);
	setCell(x + 1,y + 3,true);
	setCell(x + 1,y + 2,true);
	
	setCell(x + 6,y - 4,true);
	setCell(x + 6,y - 3,true);
	setCell(x + 6,y - 2,true);
	setCell(x + 6,y + 4,true);
	setCell(x + 6,y + 3,true);
	setCell(x + 6,y + 2,true);
	
	setCell(x - 4,y - 6,true);
	setCell(x - 3,y - 6,true);
	setCell(x - 2,y - 6,true);
	setCell(x + 4,y - 6,true);
	setCell(x + 3,y - 6,true);
	setCell(x + 2,y - 6,true);
	
	setCell(x - 4,y - 1,true);
	setCell(x - 3,y - 1,true);
	setCell(x - 2,y - 1,true);
	setCell(x + 4,y - 1,true);
	setCell(x + 3,y - 1,true);
	setCell(x + 2,y - 1,true);
	
	setCell(x - 4,y + 1,true);
	setCell(x - 3,y + 1,true);
	setCell(x - 2,y + 1,true);
	setCell(x + 4,y + 1,true);
	setCell(x + 3,y + 1,true);
	setCell(x + 2,y + 1,true);
	
	setCell(x - 4,y + 6,true);
	setCell(x - 3,y + 6,true);
	setCell(x - 2,y + 6,true);
	setCell(x + 4,y + 6,true);
	setCell(x + 3,y + 6,true);
	setCell(x + 2,y + 6,true);
	
	
	
}

/**
 * Returns number of alive cells around chosen cell
 * @param x x cell coordinate
 * @param y y cell coordinate
 * @return number of alive cells around chosen cell
 */
public int getCellCrowdness (int x,int y) {                               
	boolean[] neighbours = {true,true,true,true,true,true,true,true}; 
	int crowdness = 0,neighbour_x = 0,neighbour_y = 0;                 

	if(x == 0) {
		neighbours[0] = false;
		neighbours[1] = false;
		neighbours[2] = false;
	}	
	if(y == len - 1){
		neighbours[2] = false;
		neighbours[3] = false;
		neighbours[4] = false;
	}
	if(x == hei - 1) {
		neighbours[4] = false;
		neighbours[5] = false;
		neighbours[6] = false;
	}
	if(y == 0) {
		neighbours[6] = false;
		neighbours[7] = false;
		neighbours[0] = false;
	}
	
	for (int i = 0; i < 8; i++) {
		if (neighbours[i]){
		switch(i) {
			case 0: neighbour_x = x - 1; neighbour_y = y - 1; break;
			case 1: neighbour_x = x - 1; neighbour_y = y; break;
			case 2: neighbour_x = x - 1; neighbour_y = y + 1; break;
			case 3: neighbour_x = x; neighbour_y = y + 1; break;
			case 4: neighbour_x = x + 1; neighbour_y = y + 1; break;
			case 5: neighbour_x = x + 1; neighbour_y = y; break;
			case 6: neighbour_x = x + 1; neighbour_y = y - 1; break;
			case 7: neighbour_x = x; neighbour_y = y - 1; break;
			}
		if(Grid[neighbour_x][neighbour_y].getState()) 
			crowdness++;
		}
	}
	return crowdness;
}

/**
 * Updates cell grid according to mechanics of Game of Life
 */
public void stepForward(){ 
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
			if(!Grid[i][j].getState()) {
				if(getCellCrowdness(i,j) == 3) 
					GridNext[i][j].setState(true);	
				else GridNext[i][j].setState(false);
			}
			else {
				if((getCellCrowdness(i,j) > 3) | (getCellCrowdness(i,j) < 2))
				GridNext[i][j].setState(false);
				else GridNext[i][j].setState(true);
			}
		}

	
	for(int i = 0;i < hei;i ++) 
		for(int j = 0;j < len; j++) 
			Grid[i][j].setState(GridNext[i][j].getState());
}
}

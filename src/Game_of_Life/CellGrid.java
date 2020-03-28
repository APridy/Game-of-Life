package Game_of_Life;

public class CellGrid {
	
Cell[][] Grid;
Cell[][] GridNext;
int hei,len;

CellGrid() { //конструктор
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

CellGrid(int x,int y) {
resizeGrid(x,y);
}

Cell[][] getGrid(){
return Grid;	
}

Cell getCell(int x,int y){
return Grid[x][y];
}

void setCell(int x,int y,boolean b){
	Grid[x][y].setState(b);
}

void resizeGrid(int x,int y) { //изменить размер сетки
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

void randomizeGrid(){ //присвоить клеткам случайные значения
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
void clearGrid() { //"убить" все клетки
	for(int i = 0;i < hei;i ++) 
		for(int j = 0;j < len; j++) 
			Grid[i][j].setState(false);
}
void printGrid() { //вывести сетку в консоль
	for(int i = 0;i < hei;i ++) {
		for(int j = 0;j < len; j++) {
			if(Grid[i][j].getState() == true) System.out.print("■");
			else 
			System.out.print("□");
		}
		System.out.print('\n');
	}	
}

void setGlyder(int x,int y) { // создает фигуру "Глайдер" на переданных координатах
	setCell(x - 1,y,true);
	setCell(x,y + 1,true);
	setCell(x + 1,y + 1,true);
	setCell(x + 1,y,true);
	setCell(x + 1,y - 1,true);
}

void setSwitch(int x,int y) {// создает фигуру "Свитч" на переданных координатах
	setCell(x - 1,y,true);
	setCell(x,y,true);
	setCell(x + 1,y,true);
}

int getCellCrowdness (int x,int y) {                               //012 этот метод возвращает int,
boolean[] neighbours = {true,true,true,true,true,true,true,true};  //7 3 в котором содержится количество
int crowdness = 0,neighbour_x = 0,neighbour_y = 0;                 //654 "живых" соседей клетки 

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

void stepForward(){ //Изменить состояние сетки на шаг вперед
	for(int i = 0;i < hei;i ++)
		for(int j = 0;j < len; j++) {
			if(!Grid[i][j].getState()) {
				if(getCellCrowdness(i,j) == 3) 
					GridNext[i][j].setState(true);	
				else GridNext[i][j].setState(false);
			}
			else {
				System.out.println(i + " " + j + " " + getCellCrowdness(i,j));
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

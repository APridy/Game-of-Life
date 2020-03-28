package Game_of_Life;

public class Cell {
	
boolean state;

Cell(){
state = false;
}
Cell(boolean b){
state = b;
}

boolean getState()	{
return state;
}

void setState(boolean b)	{
this.state = b;
}

}

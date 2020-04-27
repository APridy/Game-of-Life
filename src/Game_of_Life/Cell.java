package Game_of_Life;
/**
 * Class Cell
 * Represents one cell with two states - alive or dead(true or false)
 * @author Anton Pridybailo
 */

public class Cell {

/** Data member current cell state*/
private boolean state;

/** 
 * Constructor function
 */
public Cell(){
state = false;
}

/** 
 * Constructor function with parameters 
 * @param b - cell state
 */
public Cell(boolean b){
state = b;
}

/**
 * Member function that returns cell state
 * @return returns cell state
 */
public boolean getState()	{
return state;
}

/**
 * Member function that sets cell state
 * @param b - cell state
 */
public void setState(boolean b)	{
this.state = b;
}

}

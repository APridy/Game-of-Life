package Game_of_Life;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class FileManager
 * Manages save files
 * @author Anton Pridybailo
 */
public class FileManager {

	/** Data member save reader*/
	private FileReader saveReader;
	/** Data member save writer*/
	private FileWriter saveWriter;
	/** Data member scanner*/
	private Scanner saveScan;
	
	/**
	 * Constructor function
	 * @throws Exception
	 */
	FileManager() throws Exception{
		saveReader = new FileReader("save.txt");
		saveWriter = new FileWriter("save.txt",true);
		saveScan = new Scanner(saveReader);
	}
	
	/**
	 * Loads cell grid into file
	 * @param MyGrid cell grid
	 * @throws Exception
	 */
	public void refreshSave(CellGrid MyGrid) throws Exception{
		PrintWriter writer = new PrintWriter("save.txt");
		writer.print("");
		writer.close();
		for(int i = 0; i < MyGrid.getHeight(); i++) {
			for(int j = 0; j < MyGrid.getLength(); j++)
			{
				if(MyGrid.getCell(i,j).getState() == true) {
				saveWriter.write("1");
				} else saveWriter.write("0");
			}
			saveWriter.write("\n");
		}
		
		
	}
	
	/**
	 * Loads cell grid from file
	 * @param MyGrid cell grid
	 * @return
	 * @throws Exception
	 */
	public CellGrid loadSave(CellGrid MyGrid) throws Exception{
		String str;
		for(int i = 0; i < MyGrid.getHeight(); i++) {
			str = saveScan.nextLine();
			for(int j = 0; j < MyGrid.getLength(); j++)
			{
				if (str.charAt(j) == '1') MyGrid.setCell(i, j, true);
				else MyGrid.setCell(i, j, false);
			}
			
		}
	return MyGrid;
	}
	
	/**
	 * Closes FileReader and FileWriter threads
	 * @throws Exception
	 */
	public void closeAll() throws Exception{
		saveWriter.close();
		saveReader.close();
	}
	
	
	
}

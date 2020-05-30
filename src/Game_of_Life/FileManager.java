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
	
	/** Data member stat reader*/
	private FileReader statReader;
	/** Data member stat writer*/
	private FileWriter statWriter;
	/** Data member scanner*/
	private Scanner statScan;
	
	/**
	 * Constructor function
	 * @throws Exception
	 */
	FileManager() throws Exception{
		saveReader = new FileReader("save.txt");
		saveWriter = new FileWriter("save.txt",true);
		saveScan = new Scanner(saveReader);
		statReader = new FileReader("stat.txt");
		statWriter = new FileWriter("stat.txt",true);
		statScan = new Scanner(statReader);
	}
	
	/**
	 * Loads cell grid into file
	 * @param MyGrid cell grid
	 * @throws Exception
	 */
	public int[] loadStat(int which) throws Exception{
		int[] arr = {0,0,0,0,0,0,0,0};
		String str;
		switch (which) {
			case 1: {
				str = statScan.nextLine();
				int j = 0;
				for(int i = 0; i < 8; i++) {
					while(str.charAt(j) != '|') {
						arr[i] *= 10;
						arr[i] += str.charAt(j) - 48;
						j++;
					}
					j++;
				}
			}
			break;
			case 2:
			{
				str = statScan.nextLine();
				int j = 0;
				for(int i = 0; i < 8; i++) {
					while(str.charAt(j) != '|') {
						arr[i] *= 10;
						arr[i] += str.charAt(j) - 48;
						j++;
					}
					j++;
				}
			}
			break;
		}	
		return arr;
	}
	public void saveStat(Stat st) throws Exception{
		PrintWriter writer = new PrintWriter("stat.txt");
		writer.print("");
		writer.close();
		for(int i = 0; i < 8; i++) { 
			int num = st.get_statistics_array()[i];
		    int div = 1;
		    while(num/div > 9) div *= 10;
		    while(div != 0) {
		    	statWriter.write(num/div + 48);
		        num %= div;
		        div /= 10;
		    }
			statWriter.write("|");
		}
		statWriter.write("\n");
		for(int i = 0; i < 8; i++) { 
			int num = st.get_best_statistics_array()[i];
			if (st.get_statistics_array()[i] > num) num = st.get_statistics_array()[i];
		    int div = 1;
		    while(num/div > 9) div *= 10;
		    while(div != 0) {
		    	statWriter.write(num/div + 48);
		        num %= div;
		        div /= 10;
		    }
			statWriter.write("|");
		}
		statWriter.write("\n");	
	}
	
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
		statWriter.close();
		statReader.close();
	}
	
	
	
}

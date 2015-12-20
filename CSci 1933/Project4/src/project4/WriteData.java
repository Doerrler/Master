package project4;

import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
	
	public static void main(String[] args){
		createCsvFile("c:\\P4GroceryData.csv"); 
	}

	
	public static void createCsvFile(String sFileName){  //writes the stats into a CSV file, see Stat.java for more clarity on statistics
		try{
			FileWriter writer = new FileWriter(sFileName, true);
			
			writer.append(String.valueOf(Stat.totalTime));
			writer.append(',');
			writer.append(String.valueOf(Math.round(((Stat.idleTime/Stat.totalTime)*100)*100)/100D));
			writer.append(',');
			writer.append(String.valueOf(Math.round(((Stat.busyTime/Stat.totalTime)*100)*100)/100D));
			writer.append(',');
			writer.append(String.valueOf(ShopperMaker.longest));
			writer.append(',');
			writer.append(String.valueOf(Stat.averageQLength/Stat.totalTime));
			writer.append(',');
			writer.append(String.valueOf(Math.round(((Stat.averageServiceTime/Stat.count)*100)/100D)*ShopperMaker.longest));
			writer.append(',');
			writer.append(String.valueOf(Math.round(((Stat.averageServiceTime/Stat.count)*100)/100D)*Stat.averageQLength/Stat.totalTime));
			writer.append(',');
			writer.append(String.valueOf(Math.round(((Stat.averageServiceTime/Stat.count)*100)/100D)));
			writer.append(',');
			writer.append(String.valueOf(Stat.count));
			writer.append(',');
			writer.append('\n');

			writer.flush();
			writer.close();
		}
		catch(IOException e){
		} 
	}
}

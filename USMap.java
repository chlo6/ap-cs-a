/**
 *	USMap.java
 *	Display points representing points for some of the most populated cities
 *	Big cities colored blue, top 10 largest cities colored red
 * 
 *	@author	Chloe He
 *	@since	9.4.24
 */

import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;


public class USMap 
{
	/**
	 * Draws the US Map
	 */
	 
	 double [][] cities;
	 String [] names;
	 String [] bigcityNames;
	 int [] bigcitySizes;
	 
	 public static void main (String [] args) {
		/*
		 *  Runs the runner method and draws the canvas / map
		 */
		 
		USMap run = new USMap();
		run.runner();
		run.setupCanvas();
	 }
	
	 public void runner()
	 {
		 /*
		  * Gets information (e.g. city names, populations, etc)
		  * Stores information in array
		  */
		  
		 // file utils
		 FileUtils openFile = new FileUtils();
	
		 // get all cities	 
		 java.util.Scanner citiesNE = openFile.openToRead("cities.txt");
		 cities = new double [1112][2];
		 names = new String [1112];
		 for (int i = 0; i < 1112; i++) {
			 cities[i][0] = citiesNE.nextDouble();
			 cities[i][1] = citiesNE.nextDouble();
			 names[i] = citiesNE.nextLine().trim();
		 } 

		 // gets big cities
		 java.util.Scanner bigcitiesNE = openFile.openToRead("bigCities.txt");
		 bigcityNames = new String[276];
		 bigcitySizes = new int[276];
		 for (int i = 0; i < 276; i++) {
			 String str = bigcitiesNE.nextLine().substring(2).trim();
			 String szsubstr = str.substring(str.length() - 7).trim();
			 bigcityNames[i] = str.substring(0, str.length() - 7).trim();
			 bigcitySizes[i] = Integer.parseInt(szsubstr);
		 }
	 }
	 
	 
	public void setupCanvas() 
	{
		/* 
		 * Sets up canvas
		 * Draws points, different for bigcities and top10 size cities
		 */
		
		// set up canvas
		StdDraw.setTitle("USMap");
		StdDraw.setCanvasSize(900, 512);
		StdDraw.setXscale(128.0, 65.0);
		StdDraw.setYscale(22.0, 52.0);
		
		// gray points
        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(StdDraw.GRAY);
		
		for (int i = 0; i < 1112; i++) {
			for (int j = 0; j < 276; j++) {
				// if we found a bigcity...
				if (names[i].equals(bigcityNames[j])) {
					if (j < 10) StdDraw.setPenColor(StdDraw.RED); // top 10 -> red
					else StdDraw.setPenColor(StdDraw.BLUE);	// other -> blue
					
					// draw cities	
					StdDraw.setPenRadius(0.6 * (Math.sqrt(bigcitySizes[j])/18500));
					StdDraw.point(cities[i][1], cities[i][0]);
					
					// reset
					StdDraw.setPenRadius(0.006);
					StdDraw.setPenColor(StdDraw.GRAY);
					j = 277; // break
				}
			}
			StdDraw.point(cities[i][1], cities[i][0]); // draw gray
		}
	} 
}

// file utils to read in information
class FileUtils {
	
	/** 
	 * Opens a file to read using the Scanner class.
	 * @param fileName	name of the file to open
	 * @return 			the Scanner object to the file
	 */
	 
	 public static java.util.Scanner openToRead(String fileName) {
		 java.util.Scanner input = null;
		 
		 try { 
			input = new java.util.Scanner(new java.io.File(fileName));
		 }
		 catch (java.io.FileNotFoundException e) {
			System.err.println("ERROR: Cannot open " + fileName +
							" for reading.");
			System.exit(4); 
		}									
		 
		 return input;
	 }
	 
	 /**
	  * Opens a file to write using the PrintWriter class.
	  * @param fileName		name fo the file to open
	  * @return 			the PrintWriter object to the file
	  */
	  
	  public static PrintWriter openToWrite(String fileName) {
		  PrintWriter output = null;
		  try {
			  output = new PrintWriter(new File(fileName));
		  }
		  catch (FileNotFoundException e) {
			  System.err.println("ERROR: Cannot open " + fileName + 
								"for writing.");
			  System.exit(5);
		  }
		  return output;
	  }
}

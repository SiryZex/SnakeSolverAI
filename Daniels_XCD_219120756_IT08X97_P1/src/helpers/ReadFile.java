package helpers;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class ReadFile {
	
	public static int[][] ReadFileData() {

		int[][] grid = new int[18][18];
		
		try{
			File file = new File("data","GridData.txt");
			Scanner scan = new Scanner(file);

			while(scan.hasNextLine()) {
		         for (int r = 0; r < 18; r++) {
		            String[] line = scan.nextLine().trim().split(" ");
		            for (int c = 0; c < 18; c++) {
		               grid[r][c] = Integer.parseInt(line[c]);
		            }
		         }
			}
			scan.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return grid;
	}
}

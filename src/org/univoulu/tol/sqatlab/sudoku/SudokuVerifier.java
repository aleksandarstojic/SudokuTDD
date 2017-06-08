package org.univoulu.tol.sqatlab.sudoku;

public class SudokuVerifier {

	public int verify(String candidateSolution) {
		// Return 0: means it is a valid Sudoku solution
		// Return -1: means it is violating Rule #1
		// Return -2: means it is violating Rule #2
		// Return -3: means it is violating Rule #3
		// Return -4: means it is violating Rule #4
		
		
		// Splitting the forwarded string into rows of 9 elements
		String[] rows = SplitRows(candidateSolution);
		
		// Splitting the forwarded string into columns of 9 elements
		String[] columns = SplitColumns(candidateSolution);
	
	
		// Iterating through each string to see if there is a digit which is not 1-9
		// If there is a digit which is not 1-9, the verify function will return -1 as per instructions
		for (int i = 0; i < rows.length; i++)
		{
			char[] row = rows[i].toCharArray();
			
			for(int j = 0; j < row.length; j++)
			{
				if(Character.getNumericValue(row[j]) < 1 || Character.getNumericValue(row[j]) > 9)
					return -1;
			}
		}
		
		
		// Iterating through each sub-grid to see if there are two same digits
		// If there are, the verify function will return -2 as per instructions
		
		
		// Initializing the mainGrid (9x9)
		int[][] mainGrid = new int[9][9];
		
		for (int i = 0; i < 9; i++)
		{
			char[] rowsElements = rows[i].toCharArray();
			
			for (int j = 0; j < 9; j++)
			{	
				mainGrid[i][j] = Character.getNumericValue(rowsElements[j]);
			}
		}
		
		///////////PRINTING//////////
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{	
				System.out.print(mainGrid[i][j] + "  ");
			}
			System.out.println();
		}
		System.out.println();
		///////////PRINTING//////////
		
		
		// Looping through the mainGrid and creating the sub-grids (3x3)
		
		int[][] subGrid = new int[3][3];		
		
		for(int i = 0; i < 9; i+=3)
		{
			for(int j = 0; j < 9; j+=3)
			{
				for (int k = 0; k < 3; k++)
				{	
					for (int l = 0; l < 3; l++)
					{	
						subGrid[k][l] = mainGrid[i+k][j+l];					
					}
				}
				
				///////////PRINTING//////////
				for (int m = 0; m < 3; m++)
				{
					for (int n = 0; n < 3; n++)
					{	
						System.out.print(subGrid[m][n] + "  ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
				///////////PRINTING//////////
				
				
				if (CheckTheGrid(subGrid) != true)
				{
					return -2;
				}
				else
				{
					subGrid = new int[3][3];
				}
			}
		}
		
		
		// Iterating through each row to see if some digit appears two times
		// If it does, the verify function will return -3 as per instructions
		
		for (int i = 0; i < rows.length; i++) {
			char[] charArray = rows[i].toCharArray();
			int[] integerArray = new int[9];

			for (int j = 0; j < integerArray.length; j++) {
				integerArray[j] = Character.getNumericValue(charArray[j]);
			}

			for (int k = 0; k < integerArray.length - 1; k++) {
				for (int l = k + 1; l < integerArray.length; l++) {
					if (integerArray[k] == integerArray[l])
						return -3;
				}
			}
		}
		
		// Iterating through each column to see if some digit appears two times
		// If it does, the verify function will return -4 as per instructions
		
		for (int i = 0; i < columns.length; i++) {
			char[] charArray = columns[i].toCharArray();
			int[] integerArray = new int[9];

			for (int j = 0; j < integerArray.length; j++) {
				integerArray[j] = Character.getNumericValue(charArray[j]);
			}

			for (int k = 0; k < integerArray.length - 1; k++) {
				for (int l = k + 1; l < integerArray.length; l++) {
					if (integerArray[k] == integerArray[l])
						return -4;
				}
			}
		}
		
		// The candidate's solution was correct
		return 0;
	}
	
	
	
	
	
	///// HELPER METHODS ///////
	
	public boolean CheckSolutionLength(String solution)
	{
		if(solution.length() == 81)
			return true;
		else
			return false;
	}
	
	public String[] SplitRows(String string)
	{
		int startIndex = 0;
		int endIndex = 9;
		String[] strings = new String[9];
		
		for (int i = 0; i < 9; i++, startIndex += 9, endIndex += 9)
		{
			String row = string.substring(startIndex, endIndex);
			strings[i] = row;
		}
		
		return strings;
	}
	
	
	
	public String[] SplitColumns(String string)
	{
		char[] charArray = string.toCharArray();
		String[] columns = new String[9];
		int columnNumber = 0;
		boolean first = true;
		
		for(int i = 0; i < string.length(); i++, columnNumber++)
		{
			if(columnNumber == 9)
			{
				columnNumber = 0;
				first = false;
			}
			
			int integer = Character.getNumericValue(charArray[i]);
			
			if(first)
				columns[columnNumber] = "" + String.valueOf(integer);
			else
				columns[columnNumber] = columns[columnNumber] + String.valueOf(integer);
		}
		
		return columns;
	}
	
	
	
	public boolean CheckTheGrid(int[][] grid)
	{
		
		int[] array = new int[9];
		int counter = 0;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{	
				array[counter] = grid[i][j];
				counter++;
			}
		}
		
		for (int i = 0; i < array.length - 1; i++) {
			for (int j = i + 1; j < array.length; j++) {
				if (array[i] == array[j])
					return false;
			}
		}
		
		return true;
	}
}

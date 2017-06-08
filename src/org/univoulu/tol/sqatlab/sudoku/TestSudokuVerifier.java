package org.univoulu.tol.sqatlab.sudoku;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSudokuVerifier {

	@Test
	public void TestIfTheCandidatesSolutionIs81() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		boolean check = sudokuVerifier.CheckSolutionLength("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		assertTrue(check);
	}
	
	@Test
	public void TestIfTheRowsAreSplittedCorrectly() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		String[] actual = sudokuVerifier.SplitRows("417369825632158947958724316825437169791586432346912758289643571573291684164875293");
		String expected = "417369825";
		assertEquals(expected, actual[0]);
	}
	
	@Test
	public void TestIfTheColumnsAreSplittedCorrectly()	{
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		String[] actual = sudokuVerifier.SplitColumns("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		String expected = "198765432";
		assertEquals(expected, actual[0]);
	}
	
	@Test
	public void TestIfTheDigitsAreNot1_9()	{
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		int actual = sudokuVerifier.verify("103456789912345678891234567789123456678912345567891234456789123345678912234567891");
		int expected = -1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestIfTheGridIsCheckedCorrectly()
	{
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		int[][] grid = initializeTheGrid();
		boolean check = sudokuVerifier.CheckTheGrid(grid);		
		assertTrue(!check);
	}
	
	@Test
	public void TestIfDigitsAppearOnlyOnceInASubGrid()	{
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		int actual = sudokuVerifier.verify("123456789912345678891234567789123456678912345567891234456789123345678912234567891");
		int expected = -2;
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestIfDigitsAppearOnlyOnceInEveryRow() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		int actual = sudokuVerifier.verify("417369825632158947958724316825437169791586432346912758289643571573295684164871293");
		int expected = -3;
		assertEquals(expected, actual);
	}
	
	@Test
	public void TestIfDigitsAppearOnlyOnceInEveryColumn() {
		SudokuVerifier sudokuVerifier = new SudokuVerifier();
		int actual = sudokuVerifier.verify("147369825632158947958724316825437169791586432346912758289643571573291684164875293");
		int expected = -4;
		assertEquals(expected, actual);
	}
	
	
	/////////// HELPER METHODS ////////////////
	
	public int[][] initializeTheGrid()
	{
		int[][] grid = new int[3][3];
		int counter = 0;
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{	
				grid[i][j] = counter;
				counter++;
			}
		}
		grid[0][1] = 0;
		return grid;
	}
}

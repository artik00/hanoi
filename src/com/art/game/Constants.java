package com.art.game;

public final class Constants {
	//I assume all the disks are on the first rod 
	public static final int INITIAL_ROD_NUMBER = 1;
	
	//This one states the target rod to move all  the disks to
	// can be changed to any other number , but remember to change the 
	public static final int TARGET_ROD_NUMBER = 3;
	
	// I assume there are 3 rods only
	public static final int DEFAULT_NUMBER_OF_RODS = 3;
	//This one will change if we need more than 3 rods
	public static final int SPECIAL_NUMBER_FOR_3_RODS = 6;
	
	public static final String POSITIVE_TESTS_DIR_NAME = "Positive";
	public static final String POSITIVE_TEST_FILENAME_TEMPLATE = "posSolution";
	public static final String NEGATIVE_TESTS_DIR_NAME = "Negative";
	public static final String NEGATIVE_TEST_FILENAME_TEMPLATE = "negSolution";
	public static final int MAX_NUMBER_OF_DISKS = 15;
	public static final int VERBOSE = 0;
}

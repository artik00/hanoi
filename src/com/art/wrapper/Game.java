package com.art.wrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.art.game.HanoiBoard;
import com.art.game.HanoiSolution;

public class Game {
	// arguments passed from the bash scipt 
	// generate will generate test files
	private static final String GENERATE_ARGUMENT = "generate";
	//file FILE_FULL_PATH will run only one test
	private static final String FILE_ARGUMENT = "file";
	//dir will run tests on all files is the directory
	private static final String DIR_NAME_ARGUMENT = "dir";

	public static void main(String[] args) {
		String path = "" ;
		List<File> files = new ArrayList<File>() ;
		if(args != null && args[0]!=null){
			if(DIR_NAME_ARGUMENT.equals(args[0])){
				path=args[1];
				File location = new File(path);
				if(location != null && location.listFiles() != null){
					files.addAll(Arrays.asList(new File(path).listFiles()));
				}
				else{
					System.out.println("The directory is empty or not exists");
				}
			}
			else if(FILE_ARGUMENT.equals(args[0])){
				path = args[1];
				files.add(new File(path));
			}
			else if(GENERATE_ARGUMENT.equals(args[0])){
				HanoiSolutionFilesCreator tests = new HanoiSolutionFilesCreator();
				tests.generateTests();
			}
		}
		if(files != null && files.size()>0){
			iterateOnFiles(files);
		}
	}
	/** Iterate on files and validate the solution specified in file	
	 * 
	 * @param files
	 * Prints FILENAME : "Yes" if test passed
	 * Prints FILENAME : "No" if test failed
	 * Prints FILENAME : "No" if got some exception for illegal argument for example
	 */ 
	private static void iterateOnFiles(List<File> files) {
		for(File file: files){
			try(HanoiFileReader hfr = new HanoiFileReader(file.getAbsolutePath())){
				HanoiSolution possibleOne = hfr.getPossibleSolution();
				HanoiBoard hb = new HanoiBoard(possibleOne.getNumberOfDisks());
				if(hb.applySolution(possibleOne)){
					System.out.println(file.getName()+ " : " + "Yes");
				}
				else{
					System.out.println(file.getName()+ " : " + "No");
				}
			}
			catch(Exception e){
				System.out.println(file.getName()+ " : " + "No");
			}
		}
	}

}

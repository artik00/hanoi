package com.art.wrapper;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.art.game.HanoiBoard;
import com.art.game.Solution;

public class wrapper {

	private static final String GENERATE_ARGUMENT = "generate";
	private static final String FILE_ARGUMENT = "file";
	private static final String DIR_NAME_ARGUMENT = "dir";

	public static void main(String[] args) {
		String path = "" ;
		List<File> files = new ArrayList<File>() ;
		if(args[0]!=null){
			if(DIR_NAME_ARGUMENT.equals(args[0])){
				path=args[1];
				files.addAll(Arrays.asList(new File(path).listFiles()));
			}
			else if(FILE_ARGUMENT.equals(args[0])){
				path=args[1];
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

	private static void iterateOnFiles(List<File> files) {
		for(File file: files){
			try(HanoiFileReader hfr = new HanoiFileReader(file.getAbsolutePath())){
				Solution possibleOne = hfr.getPossibleSolution();
				HanoiBoard hb = new HanoiBoard(possibleOne.getNumberOfDisks());
				if(hb.applySolution(possibleOne)){
					System.out.println(file.getName()+ " : " + "Yes");
				}
			}
			catch(Exception e){
				System.out.println(file.getName()+ " : " + "No");
				//e.printStackTrace(System.out);
			}
		}
	}

}

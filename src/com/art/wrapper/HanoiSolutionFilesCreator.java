package com.art.wrapper;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.art.game.Constants;
import com.art.game.HanoiSolver;
import com.art.game.Move;
import com.art.game.Solution;

public class HanoiSolutionFilesCreator {
	

	Path absolutePathForPositives;
	Path absoulePathForNegatives;
	
	public HanoiSolutionFilesCreator() {
		Path currentRelativePath = Paths.get("");
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		absolutePathForPositives = Paths.get(absolutePath + "/" + Constants.POSITIVE_TESTS_DIR_NAME);
		absoulePathForNegatives = Paths.get(absolutePath +"/"+ Constants.NEGATIVE_TESTS_DIR_NAME);
		createDirIfNotExists(absolutePathForPositives);
		createDirIfNotExists(absoulePathForNegatives);
	}

	private void createDirIfNotExists(Path absolutePathForNewDirectory) {
		if (!Files.exists(absolutePathForNewDirectory)) {
	            try {
	                Files.createDirectories(absolutePathForNewDirectory);
	            } catch (IOException e) {
	                //fail to create directory
	                e.printStackTrace();
	            }
	     }
	}
	
	public void generateTests(){
		generatePositiveTestsFiles();
		generateNegativeTestsFiles();
	}
	
	private void generatePositiveTestsFiles(){
		int targetRod = 3;
		for(int i=1; i<=Constants.MAX_NUMBER_OF_DISKS;i++){
			String newFilename = Constants.POSITIVE_TEST_FILENAME_TEMPLATE+i;
			Path file = Paths.get(absolutePathForPositives + "/" + newFilename);
			HanoiSolver hs = new HanoiSolver(i, Constants.INITIAL_ROD_NUMBER, targetRod);
			List<Move> moves = new ArrayList<Move>(hs.getMovesForSolution());
			try {
				Files.write(file,turnMovesListToStringList(moves,i), Charset.forName("UTF-8"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private List<String> turnMovesListToStringList(List<Move> moves,int numberOfDisks){
		LinkedList<String> strings = new LinkedList<String>();
		strings.add(String.valueOf(numberOfDisks));
		for(Move temp: moves){
			strings.add(temp.toString());
		}
		return strings;
	}
	
	private void generateNegativeTestsFiles(){
		return;
	}
	
}

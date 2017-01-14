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

public class HanoiSolutionFilesCreator {
	

	private Path absolutePathForPositives;
	private Path aboslutePathForNegatives;
	private int targetRod = 3;
	
	
	
	public HanoiSolutionFilesCreator() {
		Path currentRelativePath = Paths.get("");
		String absolutePath = currentRelativePath.toAbsolutePath().toString();
		absolutePathForPositives = Paths.get(absolutePath + "/" + Constants.POSITIVE_TESTS_DIR_NAME);
		aboslutePathForNegatives = Paths.get(absolutePath +"/"+ Constants.NEGATIVE_TESTS_DIR_NAME);
		createDirIfNotExists(absolutePathForPositives);
		createDirIfNotExists(aboslutePathForNegatives);
	}

	/** This one gets a Path and creates if it doesn't exist.	
	 * 
	 * @param absolutePathForNewDirectory
	 * Prints stack if failed to create a directory
	 */
	private void createDirIfNotExists(Path absolutePathForNewDirectory) {
		if (!Files.exists(absolutePathForNewDirectory)) {
	            try {
	                Files.createDirectories(absolutePathForNewDirectory);
	            } catch (IOException e) {
	                //failed to create directory
	                e.printStackTrace();
	            }
	     }
	}
	
	public void generateTests(){
		//This one will generate positive ones
		generateTestsFiles(false);
		//this one will generate negative ones
		generateTestsFiles(true);
	}
	
	
	/** Generate test files , up to MAX_NUMBER_OF_DISKS	
	 *  defined in constants class
	 */
	private void generateTestsFiles(boolean addErrors){
		StringBuffer newFilename = new StringBuffer();
		Path file;

		for(int i=2; i<=Constants.MAX_NUMBER_OF_DISKS;i++){
			file = createFileName(addErrors, newFilename, i);
			HanoiSolver hs = new HanoiSolver(i, Constants.INITIAL_ROD_NUMBER, targetRod,addErrors);
			List<Move> moves = new ArrayList<Move>(hs.getMovesForSolution());
			try {
				//Will present some errors 
				if(addErrors && i%2==0){
					Files.write(file,turnMovesListToStringList(moves,i+1), Charset.forName("UTF-8"));
				}else{
					Files.write(file,turnMovesListToStringList(moves,i), Charset.forName("UTF-8"));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
/** This one creates a right filename	
 * 
 * @param addErrors - to know what dir to use
 * @param newFilename - filename to use
 * @param i - index
 * @return Path of the newly created file
 */
	private Path createFileName(boolean addErrors, StringBuffer newFilename, int i) {
		Path file;
		newFilename.setLength(0);
		if(addErrors){
			newFilename.append(Constants.NEGATIVE_TEST_FILENAME_TEMPLATE);
			file = Paths.get(aboslutePathForNegatives + "/" + newFilename+i);
		}
		else{
			newFilename.append(Constants.POSITIVE_TEST_FILENAME_TEMPLATE);
			file= Paths.get(absolutePathForPositives + "/" + newFilename+i);
		}
		return file;
	}
	
	/** This one generates a list of strings that will be later
	 *  written to the file , first line is the number of disks
	 * @param moves - The moves array to create
	 * @param numberOfDisks - first line of file
	 * @return
	 */
	private List<String> turnMovesListToStringList(List<Move> moves,int numberOfDisks){
		LinkedList<String> strings = new LinkedList<String>();
		strings.add(String.valueOf(numberOfDisks));
		for(Move temp: moves){
			strings.add(temp.toString());
		}
		return strings;
	}
	
}

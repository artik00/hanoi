package com.art.wrapper;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import java.util.List;

import com.art.game.Constants;
import com.art.game.Move;
import com.art.game.Solution;

public class HanoiFileReader implements AutoCloseable{

	private FileReader reader;
	private BufferedReader bfReader;
	private Solution possibleSolution ;
	
	public Solution getPossibleSolution() {
		return possibleSolution;
	}

	public HanoiFileReader(String filePath){
		if(filePath!=null){
			try {
				bfReader = new BufferedReader(new FileReader(filePath));
				List<Move> moves = new ArrayList<Move>();
				String line;
				line=bfReader.readLine();
				validateNumberOfDisks(line);
				int numberOfDisks=Integer.parseInt(line);
				while((line =bfReader.readLine() )!= null){
					Move temp = checkIsLineValid(line,numberOfDisks);
					moves.add(temp);
				}
				possibleSolution = new Solution(numberOfDisks, moves);
			} catch (FileNotFoundException e) {
				if(Constants.VERBOSE==1)
					System.out.println("The specified file was not found : "+filePath);
			} catch (IOException e) {
				if(Constants.VERBOSE==1)
					System.out.print("Error reading the file");
			} catch(IllegalArgumentException e){
				if(Constants.VERBOSE==1)
					System.out.println("The specified file consists of bad arguments");
			}
		}
	}
	private void validateNumberOfDisks(String line){
		validateAllCharsAreNumbers(line);
	}
	
	
	/** This will check whether the given line is a possible move	
	 *  will check that the number is small than the number of disks
	 *  if it 
	 * @param line
	 * @return
	 */
	private Move checkIsLineValid(String line,int maxNum) throws IllegalArgumentException{
		Move move = new Move();
		validateLengthOfTheLine(line,2);
		validateAllCharsAreNumbers(line);
		validateNumbersInGivenRange(line, maxNum, move);
		return move;
	}
	
	private void validateLengthOfTheLine(String line,int rightLength) {
		if(line.length()==rightLength){
			return;
		}
		else {
			throw new IllegalArgumentException("Invalid argument in a line");
		}
	}
	
	private void validateNumbersInGivenRange(String line, int maxNum, Move move) {
		int source = Integer.parseInt(line.substring(0, 1));
		int destination = Integer.parseInt(line.substring(1, 2));
		if(source>Constants.DEFAULT_NUMBER_OF_RODS || destination> Constants.DEFAULT_NUMBER_OF_RODS|| source < 1 || destination < 1){
			throw new IllegalArgumentException("Invalid argument in a line");
		}
		else{
			move.setSource(source);
			move.setDestination(destination);
		}
	}
	
	private void validateAllCharsAreNumbers(String line) {
		for (char c : line.toCharArray())
		{
		    if (!Character.isDigit(c)){
		    	throw new IllegalArgumentException("Invalid argument in a line");
		    }
		}
	}
	
	@Override
	public void close() throws Exception {
		if(reader!=null){
			reader.close();
		}
		if(bfReader!=null){
			bfReader.close();
		}
	}

}

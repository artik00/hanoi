package com.art.game;

import java.util.LinkedList;
import java.util.List;

public class HanoiBoard {
	
	private List<HanoiRod> rods = new LinkedList<HanoiRod>();
	
	public HanoiBoard(int numberOfRods,int numOfDisk){
		if(numberOfRods > 1 && numOfDisk > 0){
			//this is the first rod , will contain all the disk
			HanoiRod firstRod = new HanoiRod(numOfDisk);
			addRod(firstRod);
			//these rods will be empty
			for(int i=1;i<=numberOfRods-1;i++){
				HanoiRod tempRod = new HanoiRod();
				addRod(tempRod);
			}
		}
	}
	
	/** Initiates a board of hanoi, with given number of disks, 
	 *  the default number of rods is defined in constants class
	 * @param numOfDisks
	 */
	public HanoiBoard(int numOfDisks){
		this(Constants.DEFAULT_NUMBER_OF_RODS,numOfDisks);
	}
	
	public List<HanoiRod> getRods() {
		return rods;
	}
	
	/**Return a rod from given index	
	 * 
	 * @param i 0 index of rod , starts from 0!!!
	 * @return rod or null
	 */
	public HanoiRod getRodAtIndex(int i){
		if(i>=0 && i< rods.size()){
			return rods.get(i);
		}
		else{
			return null;
		}
	}

	/** Try to apply given moves on a board	
	 *  will throw an exception if the move is illegal
	 * @param possibleSolution - list of moves
	 * @return true if the solution was applied successfully , false otherwise
	 * 
	 * throws UnsupportedOperationException if we tried to execute unsuported operation
	 * meaning we tried to take a disc from empty rod or we tried to add a bigger
	 * disk on top a smaller one
	 */
	public boolean applySolution(HanoiSolution possibleSolution){
		for(HanoiMove move: possibleSolution){
			HanoiDisk sourceDisk = rods.get(move.getSource()).getLast();
			if(!rods.get(move.getSource()).removeLastDisk()){
				throw new UnsupportedOperationException();
			}
			if(!(rods.get(move.getDestination()).addDisk(sourceDisk))){
				throw new UnsupportedOperationException();
			}
		}
		return checkValidityOfSolution(Constants.INITIAL_ROD_NUMBER);
	}
	
	/** Add an empty rod to the board 	
	 * 
	 * @param rod
	 *  throws NullPointException if the rod is null
	 */
	private void addRod(HanoiRod rod) throws NullPointerException{
		if (rod!=null){
			rods.add(rod);
		}
		else{
			throw new NullPointerException();
		}
	}
	
	
	/** This one will check if the disk moved from the initial rod
	 *  to some other rod and validate that all the other rods are empty
	 * @return
	 */
	private boolean checkValidityOfSolution(int initialRod){
		//since we count rods from 1 but the data sctructure from 0 , we need to substract 1
		if(!rods.get(initialRod - 1).isRodEmpty()){
			return false;
		}
		int counterOfNotEmptyRods=0;
		for(HanoiRod tempRod: rods){
			if(!tempRod.isRodEmpty()){
				counterOfNotEmptyRods++;
			}
		}
		return counterOfNotEmptyRods == 1;
	}
}

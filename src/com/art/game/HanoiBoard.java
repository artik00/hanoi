package com.art.game;

import java.util.LinkedList;
import java.util.List;

public class HanoiBoard {
	
	//I assume all the disks are on the first rod 
	private final static int INITIAL_ROD_NUMBER = 1;
	// I assume there are 3 rods only
	private final static int DEFAULT_NUMBER_OF_RODS = 3;
	
	List<HanoiRod> rods = new LinkedList<HanoiRod>();
	

	
	public HanoiBoard(int numberOfRods,int numOfDisk){
		if(numberOfRods>1 && numOfDisk > 0){
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
	
	public HanoiBoard(int numOfDisks){
		this(DEFAULT_NUMBER_OF_RODS,numOfDisks);
	}
	
	
	private void addRod(HanoiRod rod){
		if (rod!=null){
			rods.add(rod);
		}
		else{
			throw new NullPointerException();
		}
	}
	
	public boolean applySolution(Solution possibleSolution){
		for(Move move: possibleSolution){
			HanoiDisk sourceDisk = rods.get(move.getSource()).getLast();
			if(!rods.get(move.getSource()).removeLastDisk()){
				throw new UnsupportedOperationException();
			}
			if(!rods.get(move.getDestination()).addDisk(sourceDisk)){
				throw new UnsupportedOperationException();
			}
		}
		return checkValidityOfSolution(INITIAL_ROD_NUMBER);
	}
	/** This one will check if the disk moved from the initial rod
	 *  to some other rod and validate that all the other rods are empty
	 * @return
	 */
	private boolean checkValidityOfSolution(int initialRod){
		if(!rods.get(initialRod).isRodEmpty()){
			return false;
		}
		int counterOfNotEmptyRods=0;
		for(HanoiRod tempRod: rods){
			if(!tempRod.isRodEmpty()){
				counterOfNotEmptyRods++;
			}
		}
		return counterOfNotEmptyRods==1;
	}
}

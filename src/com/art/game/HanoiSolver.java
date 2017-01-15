package com.art.game;

import java.util.LinkedList;
import java.util.Random;

public class HanoiSolver {

	
	private LinkedList<HanoiMove> movesForSolution = new LinkedList<HanoiMove>();
	
	public LinkedList<HanoiMove> getMovesForSolution() {
		return movesForSolution;
	}

	public HanoiSolver(int numberOfDisksForSolution,int initialRod,int targetRod,boolean addErrors){
		solve(numberOfDisksForSolution,initialRod,targetRod,addErrors);
	}
	
	/**	Solve the given situation of hanoi tower , add move to list
	 * 
	 * @param diskToMove - what disk to move
	 * @param fromRod - from which rod
	 * @param toRod - dest rod
	 * @param addSaltandPepper - should add errors to move's list
	 */
	private void solve(int diskToMove,int fromRod ,int toRod,boolean addSaltandPepper) {
		if (diskToMove == 0) {
			return;
		}
		else{
		      int whatRodToUse = Constants.SPECIAL_NUMBER_FOR_3_RODS - (fromRod) - (toRod);
		      solve(diskToMove - 1, fromRod, whatRodToUse, addSaltandPepper);
		      addMoveToList(fromRod, toRod, addSaltandPepper);
		      solve(diskToMove - 1,whatRodToUse, toRod,addSaltandPepper);
		}
	}

	private void addMoveToList(int fromRod, int toRod, boolean addSaltandPepper) {
		if(addSaltandPepper){
			  addSomeErrors(fromRod, toRod);
		}
		else{
			 movesForSolution.add(new HanoiMove(fromRod,toRod));
		}
	}
/** Present some random errors	
 * 
 * @param fromRod
 * @param toRod
 */
	private void addSomeErrors(int fromRod, int toRod) {
		Random rdm = new Random();
		int random = rdm.nextInt(100);
		//I tried to present some randomness here , nothing special about 3
		// same goes to 5
		if(random % 3 == 0){
			movesForSolution.add(new HanoiMove(fromRod + 1, toRod));
		}
		else if(random % 5 == 0){
			movesForSolution.add(new HanoiMove(fromRod, toRod + 1));
		}
		else{
			movesForSolution.add(new HanoiMove(fromRod, toRod));
		 }
	}
}

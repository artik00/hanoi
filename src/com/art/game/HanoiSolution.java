package com.art.game;


import java.util.Iterator;
import java.util.LinkedList;

public class HanoiSolution implements Iterable<HanoiMove>{
	private LinkedList<HanoiMove> listOfMoves ;
	private int numberOfDisks = 0;
	
	public int getNumberOfDisks() {
		return numberOfDisks;
	}

	public HanoiSolution(int numOfDisks, LinkedList<HanoiMove> listOfPossibleMoves){
		this.listOfMoves = listOfPossibleMoves;
		this.numberOfDisks = numOfDisks;
	}
/**	
 * This one will help us iterate on the moves for future use
 * throws UsupportedOperationException if we try to remove some element with it
 */
	@Override
    public Iterator<HanoiMove> iterator() {
        return new Iterator<HanoiMove>() {
            private int count = 0;
 
            public boolean hasNext(){
                return count < listOfMoves.size();
            }
            public HanoiMove next(){
                return listOfMoves.get(count++);
            }
 
            public void remove() throws UnsupportedOperationException{
                throw new UnsupportedOperationException();
            }
        };
    }
	
	
}

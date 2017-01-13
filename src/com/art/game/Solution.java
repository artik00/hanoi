package com.art.game;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Solution implements Iterable<Move>{
	private List<Move> listOfMoves = new LinkedList<Move>();
	private int numberOfDisks = 0;
	
	public int getNumberOfDisks() {
		return numberOfDisks;
	}

	public Solution(int numOfDisks, List<Move> listOfPossibleMoves){
		this.listOfMoves=listOfPossibleMoves;
		this.numberOfDisks=numOfDisks;
	}

	@Override
    public Iterator<Move> iterator() {
        return new Iterator<Move>() {
            private int count=0;
 
            public boolean hasNext(){
                return count < listOfMoves.size();
            }
            public Move next(){
                return listOfMoves.get(count++);
            }
 
            public void remove(){
                throw new UnsupportedOperationException();
            }
        };
    }
	
	
	
}

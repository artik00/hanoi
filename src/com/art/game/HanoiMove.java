package com.art.game;

public class HanoiMove {
	private int source;
	private int destination;
	
	public int getSource() {
		return source;
	}
	
	//The test files start counting from 1
	// my rods start from 0,hence need to adapt
	public void setSource(int source) {
		this.source = source - 1;
	}
	public int getDestination() {
		return destination;
	}
	
	//The test files start counting from 1
	// my rods start from 0,hence need to adapt
	public void setDestination(int destination) {
		this.destination = destination - 1;
	}
	
	public HanoiMove(int src, int dest){
		this.source = src;
		this.destination = dest;
	}
	public HanoiMove(){
		this.source = 0;
		this.destination = 0;
	}
	
	@Override
	public String toString(){
		return String.valueOf(source) + String.valueOf(destination);
	}
	
}

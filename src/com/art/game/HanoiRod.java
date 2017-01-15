package com.art.game;

import java.util.LinkedList;

public class HanoiRod {
	LinkedList<HanoiDisk> diskOrder = new LinkedList<HanoiDisk>();
	
	public HanoiRod(int numberOfDiskToInit){
		for(int i=numberOfDiskToInit; i > 0 ; i--){
			HanoiDisk tempDisk = new HanoiDisk(i);
			diskOrder.add(tempDisk);
		}
	}
	
	//init empty Rod
	public HanoiRod(){
		
	}
	
	public boolean isRodEmpty(){
		return diskOrder.isEmpty();
	}
	
	/** Will try to add a given	disk to rod
	 *  
	 * @param size
	 * @return true if added false otherwise
	 */
	public boolean addDisk(HanoiDisk disk){
		if(disk!=null && disk.getSize() > 0){
				if(isRodEmpty() || disk.getSize() < getLast().getSize()){
					diskOrder.addLast(disk);
					return true;
				}
		}
		return false;

	}
	/**Try to remove the upper disk , 	
	 * 
	 * @return true if the operation was successful, false otherwise
	 */
	public boolean removeLastDisk(){
		if(!isRodEmpty()){
			diskOrder.removeLast();
			return true;
		}
		else{
			return false;
		}
	}
	/** Return the upper disk from rod, with-out removing it.	
	 * 
	 * @return true is operation was successful, false otherwise
	 */
	public HanoiDisk getLast(){
		if(!isRodEmpty()){
			return diskOrder.getLast();
		}
		else{
			return null;
		}
	}
	
}

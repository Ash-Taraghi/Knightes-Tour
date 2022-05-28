package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

	private final int width;
	private final int length;
	private String type;
	private List<Location> unexamined;
	
	/**
	 * Generates the locations specific shape and size of chess board
	 * to store in a list
	 * @param width width of board
	 * @param length height of the board
	 * @param type type of board to be generated
	 */
	public Board(int width, int length, String type) {
		unexamined = new ArrayList<>();
		this.width = width;
		this.length = length; 
		this.type = type;
		// Assign locations to unvisited field based on type of board
		if (type == "regular") {
			for (int i = 1; i < width + 1; i++) {
				for (int j = 1; j < length + 1; j++) {
					Location place = new Location(i,j);
					 this.unexamined.add(place);
				}
			}
		}
		if (type == "triangle") {
			int startPoint = 1;
			int endPoint = width +1;
			for (int i = 1; i < length + 1; i++) {
				for (int j = startPoint; j < endPoint; j++) {
					Location place = new Location(i,j);
					 this.unexamined.add(place);
				}
				startPoint++;
				endPoint--;
			}
		}
		if (type == "stairs") {
			int startPoint = 1;
			for (int i = 1; i < length + 1; i++) {
				for (int j = startPoint; j < width+1; j++) {
					Location place = new Location(i,j);
					 this.unexamined.add(place);
				}
				startPoint++;
			}
		}
	}
	/**
	 * Gets the current unvisited locations
	 * @return unexamined locations
	 */
	public List<Location> acess(){
		return this.unexamined;
	}
	
	/**
	 * Gets the current length of the board
	 * @return length of board
	 */
	public int length() {
		return this.length;
	}
	
	/**
	 * Modifies and sets the unexamined locations
	 * @param unexamined The current unexamined location
	 */
	public void change(List<Location> unexamined) {
		this.unexamined = unexamined;
	}
	
	/**
	 * checks the validity of a given location
	 * @param loc location to check for validity
	 * @return {@code true} if location is valid and {@code false} otherwise
	 */
	public boolean isValid(Location loc) {
		if (loc.x() < 1 | loc.x() > this.width | loc.y() < 1 | loc.y() > this.length) {
			return false;
			
		}
		for (int i =0; i< unexamined.size(); i++) {
			if (this.unexamined.get(i).equals(loc)) {
				return true;
			}
		}
		return false; 
	}
	
	//testing class
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//testing board class
		Board b = new Board(8,8,"stairs");
		System.out.println(b.unexamined);
	}

}

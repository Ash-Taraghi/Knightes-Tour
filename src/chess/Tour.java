package chess;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.HashMap;

public class Tour {
	/**
	 * Knight's current Location
	 */
	private Piece piece;
	private Board board; 
	
	/**
	 * Initializes the piece and board in the tour
	 * @param piece the current piece in the tour
	 * @param board the board to be used during the tour
	 */
	public Tour(Piece piece, Board board) {
		this.piece = piece;
		this.board = board; 
	}
	
	/**
	 * Starts the tour of the piece at a given location
	 * @param loc location that tour starts at
	 */
	public void startTour(Location loc) {
		// start the piece off at this location
		this.piece.setLocation(loc);
		//remove the starting location from unexamined locations in Board class
		List<Location> list = new ArrayList<>();
		//get list, remove starting location, return modified list to board class
		list = board.acess();
		list.remove(loc);
		// return modified list to board field.
		board.change(list);
	}
		
	

	/**
	 * Checks to see if any valid moves can be made from the current location
	 * @return {@code true} if there are any legal moves to be made
	 * from current position of knight, {@code false} otherwise
	 */
	public boolean hasNext() {
		//return false if all spaces have been visited
		List<Location> list = new ArrayList<>();
		list = board.acess();
		if (list.isEmpty()) {
			return false;
		}
		//add location of possible moves to new list 
		List <Location> options = new ArrayList<>();
		options = this.piece.moves(this.piece.location(), board);
		//check if options has valid moves
		if (options.isEmpty()) {
			return false;
		}
		return true; 
	}
	
	/**
	 * Applies Waldrof's rule to all possible moves that can be made
	 * @return A map of all possible moves that can be made
	 * with Waldrof's rule applied to each element 
	 */
	public Map<Integer, Location> waldrof(){
		Map<Integer, Location> spaces = new HashMap<Integer, Location>();;
		List <Location> options = new ArrayList<>();
		options = this.piece.moves(this.piece.location(), board);
		// see how many options each single option Location object has
		for (int i =0; i < options.size(); i++) {
			List <Location> options2 = new ArrayList<>();
			options2 = this.piece.moves(options.get(i), board);
			spaces.put(options2.size(), options.get(i));	
		}
		return spaces;
	}
	
	/**
	 * Computes best possible next move and moves location of piece to said location
	 * @return {@code null} if no legal moves can be made from current location.
	 * Otherwise, return location of next valid move
	 */
	public Location next() {
		
		if (hasNext() == false) {
			return null;
		}
		
		 Map<Integer, Location> moveOptions = new HashMap<>();
		 moveOptions = waldrof();
		 // go to smallest value key
		 int smallest = 8;
		 for (Integer num: moveOptions.keySet()) {
			 if (num <= smallest) {
				 smallest = num; 
				 
			 }
		 }
		  
		 Location current = new Location(moveOptions.get(smallest));
		 // store the unexamined field from board class in unexamined
		 List<Location> unexamined = new ArrayList<>();
		 unexamined = this.board.acess();
		 for (int i =0; i < unexamined.size(); i++) {
			 if (unexamined.get(i).equals(current)) {
				 // remove next location you move to from unexamined field.
				 unexamined.remove(i);
			 }
		 }
		 // move location of piece to next location
		 this.piece.setLocation(current);
		 return current;
	}
	// testing the class here
	public static void main(String[] args) {
		Tour t = new Tour (new Piece("knight", new Location(1,1)), new Board(4,4,"regular"));
		t.startTour(new Location(2,2));
		System.out.println(t.waldrof());
		System.out.println(t.next());
	}
}


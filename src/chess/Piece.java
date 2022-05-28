package chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 * 
 * @author Ash Taraghi
 *The {@code Piece} class represents a piece object in chess which supports 
 *kings, pawns, and the knight. The pawn transforms into a knight when it reaches
 *the end of a board by rules of chess.
 */
public class Piece {

	// Field for keeping track of location
	private Location place;
	// Field for the type of chess piece
	private String type;
	
	
	/**
	 * Initializes the type and location fields of object 
	 * @param type is type of chess piece: ("knight", "pawn", etc)
	 * @param loc starting location of chess piece
	 */
	public Piece(String type, Location loc) {
		//initialize the location and type of chess piece.
		this.place = loc;
		this.type = type;
	}
	
	/**
	 * Sets the location of a piece to a certain location
	 * @param loc Location that piece is set to.
	 */
	public void setLocation(Location loc) {
		this.place = loc;
	}
	
	/**
	 * Assessor method to get location of piece
	 * @return current location of piece
	 */
	public Location location() {
		return this.place;
	}
	
	/**
	 *  Calculates a list of all possible legal moves that 
	 *  can be made from a given location
	 * @param from the location from which to calculate possible moves
	 * @param board, the board of locations to which moves are calculated
	 * @return A list of location of all possible moves that can be made
	 */
	public List<Location> moves(Location from, Board board){
		List <Location> result = new ArrayList<>();
		//varying offset depending on type of piece
		// below is for knight
		if (this.type == "knight".toLowerCase()) {
			int[][] offset = {{-2,1}, {-2,-1}, {-1,2}, {-1,-2}, {1,2}, {1,-2}, {2,1}, {2, -1}};
			for (int[] delta: offset) {
				Location newLoc = new Location(from.x() + delta[0], from.y() + delta[1]);
				if (board.isValid(newLoc)) {
					result.add(newLoc);
				}
					
			}
		}
		// Below is for pawn
		if (this.type == "pawn".toLowerCase()) {
			int [] offset = {0,1};
			Location newLoc = new Location(from.x() + offset[0], from.y() + offset[1]);
			if (board.isValid(newLoc)) {
				result.add(newLoc);
			}
			if (from.y() == board.length()) {
				this.type = "knight";
			}
		}
		// Below is for king
		if (this.type == "king".toLowerCase()) {
			int[][] offset = {{1,1}, {-1,-1}, {0,1}, {-1,0}, {1,0}, {-1,-1}, {0,-1}, {1, -1}};
			for (int[] delta: offset) {
				Location newLoc = new Location(from.x() + delta[0], from.y() + delta[1]);
				if (board.isValid(newLoc)) {
					result.add(newLoc);
				}
					
			}
		}
		return result;
		
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// testing piece class
		Piece a1 = new Piece("knight", new Location(4,4));
		Board b1 = new Board(3,2,"regular");
		System.out.println(a1.moves(a1.location(), b1));
	}

}

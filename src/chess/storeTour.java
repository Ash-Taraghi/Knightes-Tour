package chess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class storeTour {
	/**
	 * Knight's current Location
	 */
	private Location Knight;
	private int width; 
	private int length; 
	private List<Location> unexamined;
	
	
	public void Tour (int a, int b) {
		this.width = a;
		this.length = b;
		
	}
	
	
	public void startTour(Location loc) {
		unexamined = new ArrayList<>();
		// set knight location to loc.
		this.Knight = new Location(loc);
		//add all spots on board to unvisited
		for (int i = 1; i < width + 1; i++) {
			for (int j = 1; j < length + 1; j++) {
				Location piece = new Location(i,j);
				 //System.out.println(j);
				 this.unexamined.add(piece);
			}
		}
		// remove current location.
		this.unexamined.remove(loc);
		
	}
	
	
	public List<Location> moves(Location from){
		List <Location> result = new ArrayList<>();
		int [][] offset = {{-2,1}, {-2,-1}, {-1,2}, {-1,-2}, {1,2}, {1,-2}, {2,1}, {2, -1}};
		for (int[] delta: offset) {
			Location newLoc = new Location(from.x() + delta[0], from.y() + delta[1]);
			if (isValid(newLoc)) {
				result.add(newLoc);
			}
		}
		return result;
		
	}
	
	
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
	
	
	
	
	public boolean hasNext() {
		//return false if all spaces have been visited
		if (unexamined.isEmpty()) {
			return false;
		}
		//add location of possible moves to new list
		List <Location> options = new ArrayList<>();
		options = moves(this.Knight);
		//check if options has valid moves
		if (options.isEmpty()) {
			return false;
		}
		return true; 
	}
	
	
	public Map<Integer, Location> waldrof(){
		Map<Integer, Location> spaces = new HashMap<Integer, Location>();;
		List <Location> options = new ArrayList<>();
		options = moves(this.Knight);
		System.out.println(options.toString());
		// see how many options each single option Location object has
		for (int i =0; i < options.size(); i++) {
			List <Location> options2 = new ArrayList<>();
			options2 = moves(options.get(i));
			spaces.put(options2.size(), options.get(i));	
		}
		return spaces;
	}

	public Location next() {
		
		if (hasNext() == false) {
			return null;
		}
		//
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
		 System.out.println(moveOptions.toString());
		 for (int i =0; i < unexamined.size(); i++) {
			 if (this.unexamined.get(i).equals(current)) {
				 this.unexamined.remove(i);
				 System.out.println(this.unexamined.toString());
			 }
		 }
		 this.Knight = current;
		 return current;
	}
	
	public static void main(String[] args) {
		
	}
}

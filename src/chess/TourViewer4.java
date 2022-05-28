package chess;

import princeton.introcs.StdDraw;

public class TourViewer4 {

	/**
	 * Draws a regular rectangular chess board of the specified size.
	 * 
	 * <p>
	 * Students will need to modify this method to draw irregular boards
	 * if their solutions allows for irregular boards. 
	 * 
	 * @param width the number of squares in the width of the board
	 * @param height the number of squares in the height of the board
	 */
	
	/*NOTE TO TA: My tour viewers can be changed just by changing the attributes of object,
	 But I have already created several versions of Tour Viewers to demonstrate
	 Different tours my code can do
	 1.) TourViewer demonstrates a regular knights tour on an 8x8 board.
	 2.) Tourviewer2 demonstrates the king on a pyramid shaped board
	 3.) TourViewer3 demonstrates A king piece on a staircased shaped baord
	 4.) TourViewer4 demonstrates a pawn on a rectangular board that gets promoted
	 to a knight once it reaches the other side of the baord as it could in a standard 
	 game of chess.
	*/
	private static void drawBoard(int width, int height) {
		if (width < 1 || height < 1) {
			throw new IllegalArgumentException();
		}
		int max = Math.max(width, height);
		StdDraw.setScale(0.5, max + 0.5);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if ((i + j) % 2 == 0) {
					StdDraw.setPenColor(StdDraw.BLUE);
				} else {
					StdDraw.setPenColor(StdDraw.RED);
				}
				StdDraw.filledSquare(i + 1, j + 1, 0.5);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		// edit the next line to draw a board of the size that you are testing
		int width =  8; 
		int length = 5;
		drawBoard(width, length);
		
		
		StdDraw.setPenColor(StdDraw.BLACK);
		
		// create a regular shaped board
		Board b = new Board(width, length,"regular");
		// Create location object at Location start
		Location start = new Location(1, 1);
		Piece p = new Piece("pawn", start);
		// start the tour at the given piece and board
		Tour t = new Tour(p,b);

		t.startTour(start);
		Location curr = start;
		int i = 0;
		while (t.hasNext()) {
			Location next = t.next();
			System.out.println(i + " : moving from " + curr + " to " + next);
			StdDraw.line(curr.x(), curr.y(), next.x(), next.y());
			StdDraw.filledCircle(next.x(), next.y(), 0.1);
			curr = new Location(next);
			// uncomment the next line to slow down the viewer; 500 is the pause time in milliseconds
			Thread.sleep(500);
			i++;
		}
		
	}
}

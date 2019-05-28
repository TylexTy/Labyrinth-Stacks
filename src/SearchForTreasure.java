/**
 * SearchForTreasure has just a main method with a purpose of finding the treasure tiles
 * in a labyrinth file. It is very similar to the SearchForExit.java file except it uses 
 * a linkedStack instead of an arrayStack.
 * @author Tyler Bulley
 */

import java.io.FileNotFoundException;
import java.io.IOException;

public class SearchForTreasure {
	
	public static void main (String[] args) {
		
		String labyrinthFileName = null;
		Labyrinth maze = null;
		Hexagon startHex;
		
		try {
			labyrinthFileName = args[0];
		}
		catch (Exception e) {
			System.out.println("Please enter a file name in the command prompt");
			System.exit(1);
		}
		
		try {
			maze = new Labyrinth(labyrinthFileName);
			
		}
		catch (FileNotFoundException e) {
			System.out.println("Could not find file " + labyrinthFileName);
			System.exit(1);
		}
		catch (IOException e) {
			System.out.println("Could not open file " + labyrinthFileName);
			System.exit(1);
		}
		
			
		startHex = maze.getStart();
		
		
		LinkedStack<Hexagon> linkedStack = new LinkedStack<>();
		
		linkedStack.push(startHex);
		
		Hexagon curHex;
		
		int numTiles = 0;
		int numTreasure = 0;
		
		// the while loop is mostly the same as the while loop in SearchForExit except that we
		// take out the condition of the while loop that looks for end of loop, and instead go
		// through the entire labyrinth searching for the treasures.
		
		while (!linkedStack.isEmpty()) {
			curHex = (Hexagon) linkedStack.pop();
			numTiles++;
			if (curHex.hasTreasure()) {
				numTreasure += curHex.getTreasure();
			}
			for (int i = 0; i < 6; i++) {   //figure out way to get # of neighbors instead of 6
				if (curHex.getNeighbour(i) != null && curHex.getNeighbour(i).isUnvisited()) {
					linkedStack.push(curHex.getNeighbour(i));
					curHex.getNeighbour(i).setPushed();
				}
			}
			curHex.setProcessed();
			maze.repaint();
		}
		
		System.out.println("Number of tiles in the labyrinth: " + numTiles);
		System.out.println("Amount of treasure found: " + numTreasure);
	}

}

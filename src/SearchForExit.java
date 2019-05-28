/**
 * SearchForExit employs only a main method which opens a textfile which represents a labyrinth
 * and searches that labyrinth for the exit, prints whether the exit was found, how many tiles it 
 * took to solve and the amount of tiles still on the stack. It then saves the processed labyrinth to 
 * a text file.
 * 
 * @author Tyler Bulley
 * 
 */

import java.io.FileNotFoundException;
import java.io.IOException;


public class SearchForExit {
	
	public static void main (String[] args) throws UnknownLabyrinthCharacterException, FileNotFoundException, IOException {
		
		String labyrinthFileName = null;
		Labyrinth maze = null;
		Hexagon startHex;
		
		
		// the try-catch looks for a textfile string argument on the command line which represents the labyrinth.
		try {
			labyrinthFileName = args[0];
		}
		catch (Exception e) {
			System.out.println("Please enter a file name in the command prompt"); // catches no argument given
			System.exit(1);
		}
		
		// this try-catch method finds out whether the file a) exists, and b) is able to be opened.
		
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
		
		// gets the start tile
		
		startHex = maze.getStart();
		
		
		ArrayStack<Hexagon> arrayStack = new ArrayStack<>();
		
		arrayStack.push(startHex);
		
		// boolean that we will use to find out if we are at the end tile.
		
		boolean atEnd = false;
		
		Hexagon curHex;
		
		int stepsToSolve = 0;
		
		// this while loop which keeps going until the stack is empty and the end is found, 
		// pops the top hexagon from the stack, which was initialized as the start hexagon, asks
		// whether we are at the end, if so we set atEnd to true, if not, we search the neighbors of the 
		// hexagon that are unvisited and push them on the stack.
		
		while (!arrayStack.isEmpty() && !atEnd) {
			curHex = (Hexagon) arrayStack.pop();
			stepsToSolve++;
			if (curHex.isEnd()) {
				atEnd = true;
			} else {
				for (int i = 0; i < 6; i++) { 
					if (curHex.getNeighbour(i) != null && curHex.getNeighbour(i).isUnvisited()) {
						arrayStack.push(curHex.getNeighbour(i));
						curHex.getNeighbour(i).setPushed();
					}
				}
			}
			curHex.setProcessed();
			maze.repaint(); // needed to repaint the labyrinth
		}
		
		System.out.println("Was the end found: " + atEnd);
		System.out.println("Number of steps to solve: " + stepsToSolve);
		System.out.println("Tiles still on the stack: " + arrayStack.size());
		
		maze.saveLabyrith("processed_" + labyrinthFileName); // saves the textfile. 
		
	}
	
}

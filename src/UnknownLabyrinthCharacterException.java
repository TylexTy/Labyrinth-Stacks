
public class UnknownLabyrinthCharacterException extends RuntimeException{
	
	public UnknownLabyrinthCharacterException (char character) {
		
		super ("The character " + character + " is an unknown labyrinth character");
	}

}

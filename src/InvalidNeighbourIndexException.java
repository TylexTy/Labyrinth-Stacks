
public class InvalidNeighbourIndexException extends EmptyCollectionException {
	
	public InvalidNeighbourIndexException(int index) {
		
		super (index + " is an invalid neighbour index (0-5)");
	}

}

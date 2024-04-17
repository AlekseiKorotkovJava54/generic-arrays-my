package telran.shapes.exceptions;

@SuppressWarnings("serial")
public class ShapeAlreadyExistsException extends IllegalStateException {
	public ShapeAlreadyExistsException(long id) {
		super(String.format("Shape with id %d already exists", id));
	}
}

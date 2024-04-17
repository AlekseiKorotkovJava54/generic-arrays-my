package telran.shapes.exceptions;

@SuppressWarnings("serial")
public class NoCanvasException extends RuntimeException {
public NoCanvasException(long id) {
	super(String.format("Shape with id %d is not a Canvas", id));
}
}

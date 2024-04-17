package telran.shapes.exceptions;

import java.util.NoSuchElementException;

@SuppressWarnings("serial")
public class ShapeNotFoundException extends NoSuchElementException {
	public ShapeNotFoundException(long id) {
		super(String.format("Shape with id %d not found", id));
	}
}

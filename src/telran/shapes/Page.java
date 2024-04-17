package telran.shapes;
import java.util.Iterator;

import telran.shapes.exceptions.NoCanvasException;
import telran.shapes.exceptions.ShapeAlreadyExistsException;
import telran.shapes.exceptions.ShapeNotFoundException;
import telran.util.*;
public class Page implements Iterable<Shape>{
	private Shape[] shapes = new Shape[0];
	public void addShape(Shape shape) {
		if (Arrays.indexOf(shapes, shape) > -1) {
			throw new ShapeAlreadyExistsException(shape.getId());
		}
		shapes = Arrays.add(shapes, shape);
	}
	public void addShape(Long[] canvasIds, Shape shape) {
		Canvas canvas = getCanvas(canvasIds);
		canvas.addShape(shape);
	}
	private Canvas getCanvas(Long[] canvasIds) {
		Canvas canvas = getCanvasById(shapes, canvasIds[0]);
		for(int i = 1; i < canvasIds.length; i++) {
			canvas = getCanvasById(canvas.shapes, canvasIds[i]);
		}
		return canvas;
	}
	private Canvas getCanvasById(Shape[] shapes, Long id) {
		int index = Arrays.indexOf(shapes, new Canvas(id));
		if(index < 0) {
			throw new ShapeNotFoundException(id);
		}
		Shape shape = shapes[index];
		Canvas result = null;
		if (shape instanceof Canvas) {
			result = (Canvas)shape;
		} else {
			throw new NoCanvasException(id);
		}
		return result;
	}
	public Shape removeShape(long id) {
		//TODO
		return null;
	}
	public Shape removeShape(Long[] canvasIds, long id) {
		//TODO
		return null;
	}
	@Override
	public Iterator<Shape> iterator() {
		
		return new PageIterator();
	}
	private class PageIterator implements Iterator<Shape> {

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Shape next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
}

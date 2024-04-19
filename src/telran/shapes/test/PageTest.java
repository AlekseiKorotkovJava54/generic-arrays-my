package telran.shapes.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.shapes.*;
import telran.shapes.exceptions.*;

class PageTest {
	Rectangle rectangle1 = new Rectangle(1,10,20);
	Rectangle rectangle2 = new Rectangle(2,10,20);
	Rectangle rectangle3 = new Rectangle(3,10,20);
	Rectangle rectangle4 = new Rectangle(4,10,20);
	Rectangle rectangle5 = new Rectangle(5,10,20);
	Rectangle rectangle6 = new Rectangle(6,10,20);
	Canvas canvas1;
	Canvas canvas2;
	Canvas canvas3;
	Canvas canvas4;
	Long [] arrayCanvasIds1 = {11l,22l,33l,44l};
	Long [] arrayCanvasIds2 = {11l,22l,33l,55l};
	Long [] arrayCanvasIds3 = {11l,22l,33l, 4l};
	Page page;
	@BeforeEach
	void setUp() {
		canvas1 = new Canvas(11);
		canvas1.addShape(rectangle2);
		canvas2 = new Canvas(22);
		canvas2.addShape(rectangle3);
		canvas3 = new Canvas(33);
		canvas3.addShape(rectangle4);
		canvas4 = new Canvas(44);
		canvas4.addShape(rectangle5);
		canvas3.addShape(canvas4);
		canvas2.addShape(canvas3);
		canvas1.addShape(canvas2);
		page = new Page();
		page.addShape(rectangle1);
		page.addShape(canvas1);
	}

	@Test
	void testAddShapeShape() {
		page.addShape(rectangle6);
		assertThrowsExactly(ShapeAlreadyExistsException.class,() -> page.addShape(rectangle6));
		Shape expected = page.removeShape(6);
		assertEquals(expected,rectangle6);
	}

	@Test
	void testAddShapeLongArrayShape() {
		assertThrowsExactly(ShapeNotFoundException.class,() -> page.addShape(arrayCanvasIds2,rectangle6));
		assertThrowsExactly(NoCanvasException.class,() -> page.addShape(arrayCanvasIds3,rectangle6));
		assertEquals(60, canvas4.perimeter());
		page.addShape(arrayCanvasIds1,rectangle6);
		assertEquals(120, canvas4.perimeter());
		assertThrowsExactly(ShapeAlreadyExistsException.class,() -> page.addShape(arrayCanvasIds1,rectangle6));
		
	}

	@Test
	void testRemoveShapeLong() {
		Shape expected = page.removeShape(1);
		assertThrowsExactly(ShapeNotFoundException.class,() -> page.removeShape(1));
		assertEquals(expected, rectangle1);
	}

	@Test
	void testRemoveShapeLongArrayLong() {
		assertEquals(60, canvas4.perimeter());
		page.removeShape(arrayCanvasIds1, 5);
		assertEquals(0, canvas4.perimeter());
		assertThrowsExactly(ShapeNotFoundException.class,() -> page.removeShape(arrayCanvasIds1, 5));
		assertThrowsExactly(NoCanvasException.class,() -> page.removeShape(arrayCanvasIds3,5));
	}

	@Test
	void testIterator() {
		int index = 0;
		Shape [] expected = {rectangle1,canvas1};
		Iterator<Shape> it = page.iterator();
		
		while(it.hasNext()) assertEquals(expected[index++], it.next());
		
		assertEquals(expected.length, index);
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}

}

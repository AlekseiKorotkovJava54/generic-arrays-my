package telran.shapes;

import java.util.Objects;

public abstract class  Shape {
	protected long id;
	public abstract int square();
	public abstract int perimeter();
	public Shape(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		boolean res = false;
		if (obj instanceof Shape) res = id == ((Shape)obj).id;
		return res;
	}
	
	
}

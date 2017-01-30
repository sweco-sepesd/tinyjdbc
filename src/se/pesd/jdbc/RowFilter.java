package se.pesd.jdbc;

public interface RowFilter<T> {

	public boolean accepts(T instance);
}

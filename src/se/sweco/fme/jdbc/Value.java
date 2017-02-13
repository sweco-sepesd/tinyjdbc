package se.sweco.fme.jdbc;

public interface Value {
	public <T> T value(Class<T> type);
	public String name();
	public Class<?> type();
	public int precision();
	public int scale();
	public boolean signed();
}

package se.sweco.fme.jdbc;

public class StringValue implements Value {

	final String name;
	final String value;
	
	public StringValue(String name, String value) {
		this.name = name;
		this.value = value;
	}
	
	@Override
	public <T> T value(Class<T> type) {
		return type.cast(value);
	}

	@Override
	public String name() {
		return name;
	}

	@Override
	public Class<?> type() {
		return String.class;
	}

	@Override
	public int precision() {
		return 0;
	}

	@Override
	public int scale() {
		return 0;
	}

	@Override
	public boolean signed() {
		return false;
	}

}

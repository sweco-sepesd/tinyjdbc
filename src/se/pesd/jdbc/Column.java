package se.pesd.jdbc;

import java.sql.JDBCType;

public interface Column {
	public String name();
	public int ordinalPosition();
	public JDBCType jdbcType();// default JDBCType.OTHER;
	public int columnSize();// default 0;
	public int decimalDigits();// default 0;
	public boolean nullable();// default true;
	public String remarks();// default "";
	public boolean autoIncrement();// default false;
}

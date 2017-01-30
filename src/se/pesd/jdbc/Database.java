package se.pesd.jdbc;


public interface Database {
	public AnnotatedClassResultSet<?> getRows(Table table, RowFilter<?> filter);
}

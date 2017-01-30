package se.pesd.jdbc;

import java.util.Iterator;

public interface Table {
	public String catalog();
	public String schema();
	public String tableName();
	public String tableType();
	public String remarks();
	public String typeCatalog();
	public String typeSchema();
	public String typeName();
	public String selfReferencingColName();
	public String refGeneration();
	public Iterator<Column> columns();
}

package se.pesd.jdbc;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

// TODO make this class keeper of all registered objects
@Table(catalog = "system", schema = "information_schema", tableName = "catalogs")
public class Catalog{

	final SortedMap<SchemaMetadata,SortedMap<Table,SortedSet<Column>>> registry;
	@Column(name = "TABLE_CAT", ordinalPosition = 1)
	public final String TABLE_CAT;

	public Catalog(String catalogName) {
		TABLE_CAT = catalogName;
		registry = new TreeMap<SchemaMetadata,SortedMap<Table,SortedSet<Column>>>();
	}
	public Catalog() {
		this("");
	}
}

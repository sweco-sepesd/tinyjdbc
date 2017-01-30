package se.pesd.jdbc;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

/*
 * TABLE_SCHEM String => schema name
TABLE_CATALOG String => catalog name (may be null)
 */

@Table(catalog = "system", schema = "information_schema", tableName = "schemas")
public class SchemaMetadata {
	@Column(name = "TABLE_SCHEM", ordinalPosition = 1)
	public final String TABLE_SCHEM;

	@Column(name = "TABLE_CATALOG", ordinalPosition = 2)
	public final String TABLE_CATALOG;
	public SchemaMetadata(String schemaName, String catalogName) {
		TABLE_SCHEM = schemaName;
		TABLE_CATALOG = catalogName;
	}	
}

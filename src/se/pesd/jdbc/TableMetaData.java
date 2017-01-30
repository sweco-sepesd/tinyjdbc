package se.pesd.jdbc;

import se.pesd.jdbc.Table;
import se.pesd.jdbc.annotations.Column;

@se.pesd.jdbc.annotations.Table(catalog = "system", schema = "information_schema", tableName = "tables")
public class TableMetaData {

	/**
	 * 
	 */
	@Column(name = "TABLE_CAT", ordinalPosition = 1)
	public String tableCatalog(){return table.catalog();}

	/**
	 * table schema (may be null)
	 */
	@Column(name = "TABLE_SCHEM", ordinalPosition = 2)
	public String tableSchema(){return table.schema();}

	/**
	 * table name
	 */
	@Column(name = "TABLE_NAME", ordinalPosition = 3)
	public String tableName(){return table.tableName();}

	/**
	 * table type. Typical types are "TABLE", "VIEW", "SYSTEM TABLE",
	 * "GLOBAL TEMPORARY", "LOCAL TEMPORARY", "ALIAS", "SYNONYM".
	 */
	@Column(name = "TABLE_TYPE", ordinalPosition = 4)
	public String tableType() {return table.tableType();}

	/**
	 * explanatory comment on the table
	 */
	@Column(name = "REMARKS", ordinalPosition = 5)
	public String remarks(){return table.remarks();}

	/**
	 * the types catalog (may be null)
	 */
	@Column(name = "TYPE_CAT", ordinalPosition = 6)
	public String typeCatalog(){return table.typeCatalog();}

	/**
	 * the types schema (may be null)
	 */
	@Column(name = "TYPE_SCHEM", ordinalPosition = 7)
	public String typeSchema() {return table.typeSchema();}

	/**
	 * type name (may be null)
	 */
	@Column(name = "TYPE_NAME", ordinalPosition = 8)
	public String typeName(){return table.typeName();}

	/**
	 * name of the designated "identifier" column of a typed table (may be null)
	 */
	@Column(name = "SELF_REFERENCING_COL_NAME", ordinalPosition = 9)
	public String selfReferencingColName(){return table.selfReferencingColName();}

	/**
	 * specifies how values in SELF_REFERENCING_COL_NAME are created. Values are
	 * "SYSTEM", "USER", "DERIVED". (may be null)
	 */
	@Column(name = "REF_GENERATION", ordinalPosition = 10)
	public String getRefGeneration(){
		return table.refGeneration();
	}

	public final Table table;
	public TableMetaData(Class<?> annotatedClass) throws NullPointerException {
		this.table = new TableBase(annotatedClass.getAnnotation(se.pesd.jdbc.annotations.Table.class));
	}
	public TableMetaData(Table table) {
		this.table = table;
	}
}

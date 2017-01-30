package se.pesd.jdbc;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.DatabaseMetaData;
import java.sql.JDBCType;
import java.sql.Types;
import java.util.Date;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "system", schema = "information_schema", tableName = "columns")
public class ColumnMetadata implements Comparable<ColumnMetadata> {

	/**
	 * table catalog (may be null)
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
	 * column name
	 */
	@Column(name = "COLUMN_NAME", ordinalPosition = 4)
	public String columnName(){return column.name();}
	
	/**
	 * SQL type from java.sql.Types
	 */
	@Column(name = "DATA_TYPE", ordinalPosition = 5)
	public final int sqlType;
	
	/**
	 * Data source dependent type name, for a UDT the type name is fully
	 * qualified
	 */
	@Column(name = "TYPE_NAME", ordinalPosition = 6)
	public String typeName(){return this.jdbcType.getName();}
	
	/**
	 * column size.
	 */
	@Column(name = "COLUMN_SIZE", ordinalPosition = 7)
	public int columnSize() {return column.columnSize();}
	
	/**
	 * is not used.
	 */
	@Column(name = "BUFFER_LENGTH", ordinalPosition = 8)
	public final static String BUFFER_LENGTH = null;
	
	/**
	 * the number of fractional digits. Null is returned for data types where
	 * DECIMAL_DIGITS is not applicable.
	 */
	@Column(name = "DECIMAL_DIGITS", ordinalPosition = 9)
	public int decimalDigits() {
		return column.decimalDigits();
	}
	
	/**
	 * Radix (typically either 10 or 2)
	 */
	@Column(name = "NUM_PREC_RADIX", ordinalPosition = 10)
	public static final int NUM_PREC_RADIX = 10;
	
	/**
	 * is NULL allowed. columnNoNulls - might not allow NULL values
	 * columnNullable - definitely allows NULL values columnNullableUnknown -
	 * nullability unknown
	 */
	@Column(name = "NULLABLE", ordinalPosition = 11)
	public int nullable(){
		return column.nullable() ? DatabaseMetaData.columnNullable:DatabaseMetaData.columnNoNulls;
	}
	
	/**
	 * comment describing column (may be null)
	 */
	@Column(name = "REMARKS", ordinalPosition = 12)
	public String remarks() {return column.remarks();}
	
	/**
	 * default value for the column, which should be interpreted as a string
	 * when the value is enclosed in single quotes (may be null)
	 */
	@Column(name = "COLUMN_DEF", ordinalPosition = 13)
	public static final String COLUMN_DEF = null;
	
	/**
	 * unused
	 */
	@Column(name = "SQL_DATA_TYPE", ordinalPosition = 14)
	public static final int SQL_DATA_TYPE=0;
	
	/**
	 * unused
	 */
	@Column(name = "SQL_DATETIME_SUB", ordinalPosition = 15)
	public static final int SQL_DATETIME_SUB=0;
	
	/**
	 * for char types the maximum number of bytes in the column
	 */
	@Column(name = "CHAR_OCTET_LENGTH", ordinalPosition = 16)
	public int charLength(){return column.columnSize();}
	
	/**
	 * index of column in table (starting at 1)
	 */
	@Column(name = "ORDINAL_POSITION", ordinalPosition = 17)
	public int ordinalPosition(){return column.ordinalPosition();}
	
	/**
	 * ISO rules are used to determine the nullability for a column. YES --- if
	 * the column can include NULLs NO --- if the column cannot include NULLs
	 * empty string --- if the nullability for the column is unknown
	 */
	@Column(name = "IS_NULLABLE", ordinalPosition = 18)
	public String isNullable(){
		return column.nullable() ? "YES": "NO";
	}
	
	/**
	 * catalog of table that is the scope of a reference attribute (null if
	 * DATA_TYPE isn't REF)
	 */
	@Column(name = "SCOPE_CATALOG", ordinalPosition = 19)
	public static final String SCOPE_CATALOG = null;
	
	/**
	 * schema of table that is the scope of a reference attribute (null if the
	 * DATA_TYPE isn't REF)
	 */
	@Column(name = "SCOPE_SCHEMA", ordinalPosition = 20)
	public static final String SCOPE_SCHEMA = null;
	
	/**
	 * table name that this the scope of a reference attribute (null if the
	 * DATA_TYPE isn't REF)
	 */
	@Column(name = "SCOPE_TABLE", ordinalPosition = 21)
	public static final String SCOPE_TABLE = null;
	
	/**
	 * source type of a distinct type or user-generated Ref type, SQL type from
	 * java.sql.Types (null if DATA_TYPE isn't DISTINCT or user-generated REF)
	 */
	@Column(name = "SOURCE_DATA_TYPE", ordinalPosition = 22)
	public static final short SOURCE_DATA_TYPE = 0;
	
	/**
	 * Indicates whether this column is auto incremented YES --- if the column
	 * is auto incremented NO --- if the column is not auto incremented, empty
	 * string --- if it cannot be determined whether the column is auto
	 * incremented
	 */
	@Column(name = "IS_AUTOINCREMENT", ordinalPosition = 23)
	public String autoIncrement(){
		return column.autoIncrement() ? "YES": "NO";
	}
	
	/**
	 * Indicates whether this is a generated column, YES --- if this a generated
	 * column, NO --- if this not a generated column empty string --- if it
	 * cannot be determined whether this is a generated column
	 */
	@Column(name = "IS_GENERATEDCOLUMN", ordinalPosition = 24)
	public static final String IS_GENERATEDCOLUMN = "NO";
	
	public final Class<?> clz;
	public final Table table;
	public final Column column;
	public final AccessibleObject accessible;
	public final Class<?> javaType;
	public final JDBCType jdbcType;
	
	public ColumnMetadata(Class<?> clz, AccessibleObject accessible) throws NullPointerException {
		if(accessible instanceof Method){
			this.javaType = ((Method) accessible).getReturnType();
		} else if(accessible instanceof Field)
		{
			this.javaType = ((Field) accessible).getType();
		} else
			throw new ClassCastException(String.format("The AccessibleObject (%s) could not be cast to a Field or Method", accessible.getClass().getName()));
		
		this.clz = clz;
		this.column = accessible.getAnnotation(Column.class);
		this.table = clz.getAnnotation(Table.class);
		this.accessible = accessible;
		this.jdbcType = column.jdbcType().equals(JDBCType.OTHER) ? toJDBCType(javaType):column.jdbcType() ;
		this.sqlType = toSqlType(jdbcType);
	}

	@Override
	public int compareTo(ColumnMetadata other) {
		if(this.ordinalPosition() == other.ordinalPosition() && this.accessible.equals(other.accessible) && this.columnName().equals(other.columnName()))
			return 0;
		return this.ordinalPosition() - other.ordinalPosition() ;
	}

	public <V> V cast(Object value, Class<V> returnType) throws ClassCastException{
		// TODO Add more intelligent cast behavior?
		if(!javaType.equals(returnType)){
			if(returnType.equals(BigDecimal.class)){
				if(javaType.equals(Double.class) || javaType.equals(Double.TYPE))
					return returnType.cast(BigDecimal.valueOf(Double.class.cast(value)));
				if (javaType.equals(Long.class) || javaType.equals(Long.TYPE))
					return returnType.cast(BigDecimal.valueOf(Long.class.cast(value), column.decimalDigits()));
				return null;
			}
			if(returnType.equals(Long.class)){
				if(javaType.equals(Integer.class) || javaType.equals(Integer.TYPE)){
					return returnType.cast(Integer.class.cast(value).longValue());
				}
			}
			if(returnType.equals(Integer.class)){
				if(javaType.equals(Long.class) || javaType.equals(Long.TYPE))
					return returnType.cast(Long.class.cast(value).intValue());
				if(javaType.equals(Short.class) || javaType.equals(Short.TYPE))
					return returnType.cast(Short.class.cast(value).intValue());
			}
			if(returnType.equals(String.class)){
				return returnType.cast(String.valueOf(value));
			}
			
		}
			return returnType.cast(value);
	}
	
	
	public static JDBCType toJDBCType(Class<?> javaType){
		if(javaType.equals(String.class))
			return JDBCType.VARCHAR;
		if(javaType.equals(Integer.class) || javaType.equals(Integer.TYPE))
			return JDBCType.INTEGER;
		if(javaType.equals(Long.class) || javaType.equals(Long.TYPE))
			return JDBCType.BIGINT;
		if(javaType.equals(Boolean.class) || javaType.equals(Boolean.TYPE))
			return JDBCType.BIT;
		if(javaType.equals(Double.class) || javaType.equals(Double.TYPE))
			return JDBCType.DOUBLE;
		if(javaType.equals(Float.class) || javaType.equals(Float.TYPE))
			return JDBCType.FLOAT;
		if(javaType.equals(Short.class) || javaType.equals(Short.TYPE))
			return JDBCType.SMALLINT;
		if(javaType.equals(byte[].class))
			return JDBCType.BINARY;
		if(javaType.equals(Date.class))
			return JDBCType.DATE;
		// TODO Add more type lookups
		System.out.printf("Type not handled (%s)\n", javaType.getName());
		return JDBCType.OTHER;
	}
	public static int toSqlType(JDBCType jdbcType){
		switch(jdbcType) {
		case BIT: return Types.BIT;
		case TINYINT: return Types.TINYINT;
		case SMALLINT: return Types.SMALLINT;
		case INTEGER: return Types.INTEGER;
		case BIGINT: return Types.BIGINT;
		case FLOAT: return Types.FLOAT;
		case REAL: return Types.REAL;
		case DOUBLE: return Types.DOUBLE;
		case NUMERIC: return Types.NUMERIC;
		case DECIMAL: return Types.DECIMAL;
		case CHAR: return Types.CHAR;
		case VARCHAR: return Types.VARCHAR;
		case LONGVARCHAR: return Types.LONGVARCHAR;
		case DATE: return Types.DATE;
		case TIME: return Types.TIME;
		case TIMESTAMP: return Types.TIMESTAMP;
		case BINARY: return Types.BINARY;
		case VARBINARY: return Types.VARBINARY;
		case LONGVARBINARY: return Types.LONGVARBINARY;
		case NULL: return Types.NULL;
		case OTHER: return Types.OTHER;
		case JAVA_OBJECT: return Types.JAVA_OBJECT;
		case DISTINCT: return Types.DISTINCT;
		case STRUCT: return Types.STRUCT;
		case ARRAY: return Types.ARRAY;
		case BLOB: return Types.BLOB;
		case CLOB: return Types.CLOB;
		case REF: return Types.REF;
		case DATALINK: return Types.DATALINK;
		case BOOLEAN: return Types.BOOLEAN;
		case ROWID: return Types.ROWID;
		case NCHAR: return Types.NCHAR;
		case NVARCHAR: return Types.NVARCHAR;
		case LONGNVARCHAR: return Types.LONGNVARCHAR;
		case NCLOB: return Types.NCLOB;
		case SQLXML: return Types.SQLXML;
		case REF_CURSOR: return Types.REF_CURSOR;
		case TIME_WITH_TIMEZONE: return Types.TIME_WITH_TIMEZONE;
		case TIMESTAMP_WITH_TIMEZONE: return Types.TIMESTAMP_WITH_TIMEZONE;
		}
		return Types.OTHER;
	}
	

	public static JDBCType toJDBCType(int sqlType) {
		switch(sqlType){
		case java.sql.Types.BIT: return java.sql.JDBCType.BIT;
		case java.sql.Types.TINYINT: return java.sql.JDBCType.TINYINT;
		case java.sql.Types.SMALLINT: return java.sql.JDBCType.SMALLINT;
		case java.sql.Types.INTEGER: return java.sql.JDBCType.INTEGER;
		case java.sql.Types.BIGINT: return java.sql.JDBCType.BIGINT;
		case java.sql.Types.FLOAT: return java.sql.JDBCType.FLOAT;
		case java.sql.Types.REAL: return java.sql.JDBCType.REAL;
		case java.sql.Types.DOUBLE: return java.sql.JDBCType.DOUBLE;
		case java.sql.Types.NUMERIC: return java.sql.JDBCType.NUMERIC;
		case java.sql.Types.DECIMAL: return java.sql.JDBCType.DECIMAL;
		case java.sql.Types.CHAR: return java.sql.JDBCType.CHAR;
		case java.sql.Types.VARCHAR: return java.sql.JDBCType.VARCHAR;
		case java.sql.Types.LONGVARCHAR: return java.sql.JDBCType.LONGVARCHAR;
		case java.sql.Types.DATE: return java.sql.JDBCType.DATE;
		case java.sql.Types.TIME: return java.sql.JDBCType.TIME;
		case java.sql.Types.TIMESTAMP: return java.sql.JDBCType.TIMESTAMP;
		case java.sql.Types.BINARY: return java.sql.JDBCType.BINARY;
		case java.sql.Types.VARBINARY: return java.sql.JDBCType.VARBINARY;
		case java.sql.Types.LONGVARBINARY: return java.sql.JDBCType.LONGVARBINARY;
		case java.sql.Types.NULL: return java.sql.JDBCType.NULL;
		case java.sql.Types.OTHER: return java.sql.JDBCType.OTHER;
		case java.sql.Types.JAVA_OBJECT: return java.sql.JDBCType.JAVA_OBJECT;
		case java.sql.Types.DISTINCT: return java.sql.JDBCType.DISTINCT;
		case java.sql.Types.STRUCT: return java.sql.JDBCType.STRUCT;
		case java.sql.Types.ARRAY: return java.sql.JDBCType.ARRAY;
		case java.sql.Types.BLOB: return java.sql.JDBCType.BLOB;
		case java.sql.Types.CLOB: return java.sql.JDBCType.CLOB;
		case java.sql.Types.REF: return java.sql.JDBCType.REF;
		case java.sql.Types.DATALINK: return java.sql.JDBCType.DATALINK;
		case java.sql.Types.BOOLEAN: return java.sql.JDBCType.BOOLEAN;
		case java.sql.Types.ROWID: return java.sql.JDBCType.ROWID;
		case java.sql.Types.NCHAR: return java.sql.JDBCType.NCHAR;
		case java.sql.Types.NVARCHAR: return java.sql.JDBCType.NVARCHAR;
		case java.sql.Types.LONGNVARCHAR: return java.sql.JDBCType.LONGNVARCHAR;
		case java.sql.Types.NCLOB: return java.sql.JDBCType.NCLOB;
		case java.sql.Types.SQLXML: return java.sql.JDBCType.SQLXML;
		case java.sql.Types.REF_CURSOR: return java.sql.JDBCType.REF_CURSOR;
		case java.sql.Types.TIME_WITH_TIMEZONE: return java.sql.JDBCType.TIME_WITH_TIMEZONE;
		case java.sql.Types.TIMESTAMP_WITH_TIMEZONE: return java.sql.JDBCType.TIMESTAMP_WITH_TIMEZONE;
		}
		return java.sql.JDBCType.OTHER;
	}

}

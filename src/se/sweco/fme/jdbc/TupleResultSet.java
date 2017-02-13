package se.sweco.fme.jdbc;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Statement;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;

public class TupleResultSet implements ResultSet {

	final TupleProvider provider;
	Tuple currentRow;
	Iterator<Value> fieldValues; 
	int currentField = 0;
	Iterator<Tuple> rows;
	boolean closed = false;
	boolean wasNull;
	final Map<Integer, Value> columnsByPosition;
	final Map<String, Integer> columnPosition; 
	private SQLWarning currentWarning;
	private String cursorName;
	private ResultSetMetaData metadata;
	private int currentRowNumber;
	private Statement statement;

	public TupleResultSet(TupleProvider provider) {
		this.provider = provider;
		columnsByPosition = new TreeMap<Integer, Value>();
		columnPosition = new TreeMap<String, Integer>();
		provider.describe().forEach(new Consumer<Value>(){
			int pos = 0;
			@Override
			public void accept(Value t) {
				columnsByPosition.put(++pos, t);
				columnPosition.put(t.name(), pos);
			}
		});
	}

	public TupleResultSet(TupleProvider provider, String name) {
		this(provider);
		this.cursorName = name;
	}

	private void populateMetadata() {
		this.metadata = new ResultSetMetaData() {

			@Override
			public <T> T unwrap(Class<T> iface) throws SQLException {
				if(isWrapperFor(iface))
					return iface.cast(this);
				throw new SQLException();
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				return iface == ResultSetMetaData.class;
			}

			@Override
			public int getColumnCount() throws SQLException {
				return columnPosition.size();
			}

			@Override
			public boolean isAutoIncrement(int column) throws SQLException {
				return false;
			}

			@Override
			public boolean isCaseSensitive(int column) throws SQLException {
				return true;
			}

			@Override
			public boolean isSearchable(int column) throws SQLException {
				return false;
			}

			@Override
			public boolean isCurrency(int column) throws SQLException {
				return false;
			}

			@Override
			public int isNullable(int column) throws SQLException {
				return columnNullable;
			}

			@Override
			public boolean isSigned(int column) throws SQLException {
				return columnsByPosition.get(column).signed();
			}

			@Override
			public int getColumnDisplaySize(int column) throws SQLException {
				return getPrecision(column);
			}

			@Override
			public String getColumnLabel(int column) throws SQLException {
				return columnsByPosition.get(column).name();
			}

			@Override
			public String getColumnName(int column) throws SQLException {
				return columnsByPosition.get(column).name();
			}

			@Override
			public String getSchemaName(int column) throws SQLException {
				return "";
			}

			@Override
			public int getPrecision(int column) throws SQLException {
				return columnsByPosition.get(column).precision();
			}

			@Override
			public int getScale(int column) throws SQLException {
				return columnsByPosition.get(column).scale();
			}

			@Override
			public String getTableName(int column) throws SQLException {
				return "";
			}

			@Override
			public String getCatalogName(int column) throws SQLException {
				return "";
			}

			@Override
			public int getColumnType(int column) throws SQLException {
				return toSqlType(columnsByPosition.get(column));
			}

			@Override
			public String getColumnTypeName(int column) throws SQLException {
				return toJDBCType(columnsByPosition.get(column).type()).getName();
			}

			@Override
			public boolean isReadOnly(int column) throws SQLException {
				return true;
			}

			@Override
			public boolean isWritable(int column) throws SQLException {
				return false;
			}

			@Override
			public boolean isDefinitelyWritable(int column) throws SQLException {
				return false;
			}

			@Override
			public String getColumnClassName(int column) throws SQLException {
				return columnsByPosition.get(column).type().getName();
			}

		};

	}

	static int toSqlType(Value value) {
		JDBCType jdbcType = toJDBCType(value.type());
		return toSqlType(jdbcType);
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
	@SuppressWarnings("unchecked")
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (!this.isWrapperFor(iface))
			throw new SQLException(
					String.format("Can not unwrap class %s as a TupleResultSet", iface.getClass().getName()));
		return (T) iface;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return iface.getClass().equals(TupleResultSet.class);
	}


	@Override
	public <T> T getObject(int columnIndex, Class<T> type) throws SQLException {
		if(columnIndex < currentField)
			throw new SQLException(String.format("Can only access each field once and in order %s %s", columnIndex, currentField));
		if(currentRow == null)
			throw new SQLException("No current row");
		if(fieldValues == null) {
			throw new SQLException("No field values");
		}
		while(currentField < columnIndex) {
			Value v = fieldValues.next();
			System.out.printf("Skipping %s %s", currentField, v.value(v.type()));
			currentField++;
		}
		T retval = fieldValues.next().value(type);
		currentField++;
		wasNull = retval == null;
		return retval;
	}

	@Override
	public <T> T getObject(String columnLabel, Class<T> type) throws SQLException {
		return getObject(columnPosition.get(columnLabel), type);
	}
	
	@Override
	public boolean next() throws SQLException {
		if (rows == null)
			rows = this.provider.tuples().iterator();
		if (!rows.hasNext())
			return false;
		currentRow = rows.next();
		fieldValues = currentRow.values().iterator();
		currentField = 1;
		return true;
	}

	@Override
	public void close() throws SQLException {
		closed = true;
	}

	@Override
	public boolean wasNull() throws SQLException {
		return wasNull;
	}

	@Override
	public String getString(int columnIndex) throws SQLException {
		return getObject(columnIndex, String.class);
	}

	@Override
	public boolean getBoolean(int columnIndex) throws SQLException {
		return getObject(columnIndex, Boolean.class);
	}

	@Override
	public byte getByte(int columnIndex) throws SQLException {
		return getObject(columnIndex, Byte.TYPE);
	}

	@Override
	public short getShort(int columnIndex) throws SQLException {
		return getObject(columnIndex, Short.TYPE);
	}

	@Override
	public int getInt(int columnIndex) throws SQLException {
		return getObject(columnIndex, Integer.TYPE);
	}

	@Override
	public long getLong(int columnIndex) throws SQLException {
		return getObject(columnIndex, Long.TYPE);
	}

	@Override
	public float getFloat(int columnIndex) throws SQLException {
		return getObject(columnIndex, Float.TYPE);
	}

	@Override
	public double getDouble(int columnIndex) throws SQLException {
		return getObject(columnIndex, Double.TYPE);
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex, int scale) throws SQLException {
		return getObject(columnIndex, BigDecimal.class);
	}

	@Override
	public byte[] getBytes(int columnIndex) throws SQLException {
		return getObject(columnIndex, byte[].class);
	}

	@Override
	public Date getDate(int columnIndex) throws SQLException {
		return getObject(columnIndex, Date.class);
	}

	@Override
	public Time getTime(int columnIndex) throws SQLException {
		return getObject(columnIndex, Time.class);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex) throws SQLException {
		return getObject(columnIndex, Timestamp.class);
	}

	@Override
	public InputStream getAsciiStream(int columnIndex) throws SQLException {
		return getBinaryStream(columnIndex);
	}

	@Override
	public InputStream getUnicodeStream(int columnIndex) throws SQLException {
		return new ByteArrayInputStream(getString(columnIndex).getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public InputStream getBinaryStream(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return getObject(columnIndex, InputStream.class);
	}

	@Override
	public String getString(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return getObject(columnLabel, String.class);
	}

	@Override
	public boolean getBoolean(String columnLabel) throws SQLException {
		return getObject(columnLabel, Boolean.TYPE);
	}

	@Override
	public byte getByte(String columnLabel) throws SQLException {
		return getObject(columnLabel, Byte.TYPE);
	}

	@Override
	public short getShort(String columnLabel) throws SQLException {
		return getObject(columnLabel, Short.TYPE);
	}

	@Override
	public int getInt(String columnLabel) throws SQLException {
		return getObject(columnLabel, Integer.TYPE);
	}

	@Override
	public long getLong(String columnLabel) throws SQLException {
		return getObject(columnLabel, Long.TYPE);
	}

	@Override
	public float getFloat(String columnLabel) throws SQLException {
		return getObject(columnLabel, Float.TYPE);
	}

	@Override
	public double getDouble(String columnLabel) throws SQLException {
		return getObject(columnLabel, Double.TYPE);
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel, int scale) throws SQLException {
		return getObject(columnLabel, BigDecimal.class);
	}

	@Override
	public byte[] getBytes(String columnLabel) throws SQLException {
		return getObject(columnLabel, byte[].class);
	}

	@Override
	public Date getDate(String columnLabel) throws SQLException {
		return getObject(columnLabel, Date.class);
	}

	@Override
	public Time getTime(String columnLabel) throws SQLException {
		return getObject(columnLabel, Time.class);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel) throws SQLException {
		return getObject(columnLabel, Timestamp.class);
	}

	@Override
	public InputStream getAsciiStream(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getUnicodeStream(String columnLabel) throws SQLException {
		return new ByteArrayInputStream(getString(columnLabel).getBytes(StandardCharsets.UTF_8));
	}

	@Override
	public InputStream getBinaryStream(String columnLabel) throws SQLException {
		return getObject(columnLabel, InputStream.class);
	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		return currentWarning;
	}

	@Override
	public void clearWarnings() throws SQLException {
		currentWarning = null;
	}

	@Override
	public String getCursorName() throws SQLException {
		return cursorName;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		if (metadata == null)
			populateMetadata();
		return metadata;
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		return getObject(columnIndex, Object.class);
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		return getObject(columnLabel, Object.class);
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		if (columnPosition.containsKey(columnLabel))
			return columnPosition.get(columnLabel);
		throw new SQLException(String.format("Could not find column %s", columnLabel));
	}

	@Override
	public Reader getCharacterStream(int columnIndex) throws SQLException {
		return getObject(columnIndex, Reader.class);
	}

	@Override
	public Reader getCharacterStream(String columnLabel) throws SQLException {
		return getObject(columnLabel, Reader.class);
	}

	@Override
	public BigDecimal getBigDecimal(int columnIndex) throws SQLException {
		return getObject(columnIndex, BigDecimal.class);
	}

	@Override
	public BigDecimal getBigDecimal(String columnLabel) throws SQLException {
		return getObject(columnLabel, BigDecimal.class);
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFirst() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isLast() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void beforeFirst() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterLast() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean first() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean last() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getRow() throws SQLException {
		return currentRowNumber;
	}

	@Override
	public boolean absolute(int row) throws SQLException {
		throw new SQLException("Not allowed for this ResultSet (TYPE_FORWARD_ONLY)");
	}

	@Override
	public boolean relative(int rows) throws SQLException {
		if (rows == 1)
			return next();
		throw new SQLException("Not allowed for this ResultSet (TYPE_FORWARD_ONLY)");
	}

	@Override
	public boolean previous() throws SQLException {
		throw new SQLException("Not allowed for this ResultSet (TYPE_FORWARD_ONLY)");
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		if (direction != ResultSet.FETCH_FORWARD)
			throw new SQLException("Not allowed for this ResultSet (TYPE_FORWARD_ONLY)");
	}

	@Override
	public int getFetchDirection() throws SQLException {
		return ResultSet.FETCH_FORWARD;
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		// Ignored
	}

	@Override
	public int getFetchSize() throws SQLException {
		return 1;
	}

	@Override
	public int getType() throws SQLException {
		return ResultSet.TYPE_FORWARD_ONLY;
	}

	@Override
	public int getConcurrency() throws SQLException {
		return ResultSet.CONCUR_READ_ONLY;
	}

	@Override
	public boolean rowUpdated() throws SQLException {
		return false;
	}

	@Override
	public boolean rowInserted() throws SQLException {
		return false;
	}

	@Override
	public boolean rowDeleted() throws SQLException {
		return false;
	}

	@Override
	public void updateNull(int columnIndex) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBoolean(int columnIndex, boolean x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateByte(int columnIndex, byte x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateShort(int columnIndex, short x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateInt(int columnIndex, int x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateLong(int columnIndex, long x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateFloat(int columnIndex, float x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateDouble(int columnIndex, double x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBigDecimal(int columnIndex, BigDecimal x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateString(int columnIndex, String x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBytes(int columnIndex, byte[] x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateDate(int columnIndex, Date x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateTime(int columnIndex, Time x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateTimestamp(int columnIndex, Timestamp x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateObject(int columnIndex, Object x, int scaleOrLength) throws SQLException {
		// Ignored
	}

	@Override
	public void updateObject(int columnIndex, Object x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateNull(String columnLabel) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBoolean(String columnLabel, boolean x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateByte(String columnLabel, byte x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateShort(String columnLabel, short x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateInt(String columnLabel, int x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateLong(String columnLabel, long x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateFloat(String columnLabel, float x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateDouble(String columnLabel, double x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBigDecimal(String columnLabel, BigDecimal x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateString(String columnLabel, String x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBytes(String columnLabel, byte[] x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateDate(String columnLabel, Date x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateTime(String columnLabel, Time x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateTimestamp(String columnLabel, Timestamp x) throws SQLException {
		// Ignored
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, int length) throws SQLException {
		// Ignored
	}

	@Override
	public void updateObject(String columnLabel, Object x, int scaleOrLength) throws SQLException {
		// Ignored
	}

	@Override
	public void updateObject(String columnLabel, Object x) throws SQLException {
		// Ignored
	}

	@Override
	public void insertRow() throws SQLException {
		// Ignored
	}

	@Override
	public void updateRow() throws SQLException {
		// Ignored
	}

	@Override
	public void deleteRow() throws SQLException {
		// Ignored
	}

	@Override
	public void refreshRow() throws SQLException {
		// Ignored
	}

	@Override
	public void cancelRowUpdates() throws SQLException {
		// Ignored
	}

	@Override
	public void moveToInsertRow() throws SQLException {
		// Ignored
	}

	@Override
	public void moveToCurrentRow() throws SQLException {
		// Ignored
	}

	@Override
	public Statement getStatement() throws SQLException {
		return statement;
	}

	@Override
	public Object getObject(int columnIndex, Map<String, Class<?>> map) throws SQLException {
		return getObject(columnIndex, map.getOrDefault(getMetaData().getColumnTypeName(columnIndex), Object.class));
	}

	@Override
	public Ref getRef(int columnIndex) throws SQLException {
		return getObject(columnIndex, Ref.class);
	}

	@Override
	public Blob getBlob(int columnIndex) throws SQLException {
		return getObject(columnIndex, Blob.class);
	}

	@Override
	public Clob getClob(int columnIndex) throws SQLException {
		return getObject(columnIndex, Clob.class);
	}

	@Override
	public Array getArray(int columnIndex) throws SQLException {
		return getObject(columnIndex, Array.class);
	}

	@Override
	public Object getObject(String columnLabel, Map<String, Class<?>> map) throws SQLException {
		return getObject(findColumn(columnLabel), map);
	}

	@Override
	public Ref getRef(String columnLabel) throws SQLException {
		return getObject(columnLabel, Ref.class);
	}

	@Override
	public Blob getBlob(String columnLabel) throws SQLException {
		return getObject(columnLabel, Blob.class);
	}

	@Override
	public Clob getClob(String columnLabel) throws SQLException {
		return getObject(columnLabel, Clob.class);
	}

	@Override
	public Array getArray(String columnLabel) throws SQLException {
		return getObject(columnLabel, Array.class);
	}

	@Override
	public Date getDate(int columnIndex, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnIndex, Date.class);
	}

	@Override
	public Date getDate(String columnLabel, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnLabel, Date.class);
	}

	@Override
	public Time getTime(int columnIndex, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnIndex, Time.class);
	}

	@Override
	public Time getTime(String columnLabel, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnLabel, Time.class);
	}

	@Override
	public Timestamp getTimestamp(int columnIndex, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnIndex, Timestamp.class);
	}

	@Override
	public Timestamp getTimestamp(String columnLabel, Calendar cal) throws SQLException {
		// TODO use calender
		return getObject(columnLabel, Timestamp.class);
	}

	@Override
	public URL getURL(int columnIndex) throws SQLException {
		return getObject(columnIndex, URL.class);
	}

	@Override
	public URL getURL(String columnLabel) throws SQLException {
		return getObject(columnLabel, URL.class);
	}

	@Override
	public void updateRef(int columnIndex, Ref x) throws SQLException {
		// ignored
	}

	@Override
	public void updateRef(String columnLabel, Ref x) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(int columnIndex, Blob x) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(String columnLabel, Blob x) throws SQLException {
		// ignored
	}

	@Override
	public void updateClob(int columnIndex, Clob x) throws SQLException {
		// ignored
	}

	@Override
	public void updateClob(String columnLabel, Clob x) throws SQLException {
		// ignored
	}

	@Override
	public void updateArray(int columnIndex, Array x) throws SQLException {
		// ignored
	}

	@Override
	public void updateArray(String columnLabel, Array x) throws SQLException {
		// ignored
	}

	@Override
	public RowId getRowId(int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RowId getRowId(String columnLabel) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRowId(int columnIndex, RowId x) throws SQLException {
		// ignored
	}

	@Override
	public void updateRowId(String columnLabel, RowId x) throws SQLException {
		// ignored
	}

	@Override
	public int getHoldability() throws SQLException {
		return ResultSet.CLOSE_CURSORS_AT_COMMIT;
	}

	@Override
	public boolean isClosed() throws SQLException {
		return closed;
	}

	@Override
	public void updateNString(int columnIndex, String nString) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateNString(String columnLabel, String nString) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(int columnIndex, NClob nClob) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(String columnLabel, NClob nClob) throws SQLException {
		// ignored
	}

	@Override
	public NClob getNClob(int columnIndex) throws SQLException {
		return getObject(columnIndex, NClob.class);
	}

	@Override
	public NClob getNClob(String columnLabel) throws SQLException {
		return getObject(columnLabel, NClob.class);
	}

	@Override
	public SQLXML getSQLXML(int columnIndex) throws SQLException {
		return getObject(columnIndex, SQLXML.class);
	}

	@Override
	public SQLXML getSQLXML(String columnLabel) throws SQLException {
		return getObject(columnLabel, SQLXML.class);
	}

	@Override
	public void updateSQLXML(int columnIndex, SQLXML xmlObject) throws SQLException {
		// ignored
	}

	@Override
	public void updateSQLXML(String columnLabel, SQLXML xmlObject) throws SQLException {
		// ignored
	}

	@Override
	public String getNString(int columnIndex) throws SQLException {
		return getString(columnIndex);
	}

	@Override
	public String getNString(String columnLabel) throws SQLException {
		return getString(columnLabel);
	}

	@Override
	public Reader getNCharacterStream(int columnIndex) throws SQLException {
		return getObject(columnIndex, Reader.class);
	}

	@Override
	public Reader getNCharacterStream(String columnLabel) throws SQLException {
		return getObject(columnLabel, Reader.class);
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateClob(int columnIndex, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateClob(String columnLabel, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader, long length) throws SQLException {
		// ignored
	}

	@Override
	public void updateNCharacterStream(int columnIndex, Reader x) throws SQLException {
		// ignored
	}

	@Override
	public void updateNCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// ignored
	}

	@Override
	public void updateAsciiStream(int columnIndex, InputStream x) throws SQLException {
		// ignored
	}

	@Override
	public void updateBinaryStream(int columnIndex, InputStream x) throws SQLException {
		// ignored
	}

	@Override
	public void updateCharacterStream(int columnIndex, Reader x) throws SQLException {
		// ignored
	}

	@Override
	public void updateAsciiStream(String columnLabel, InputStream x) throws SQLException {
		// ignored
	}

	@Override
	public void updateBinaryStream(String columnLabel, InputStream x) throws SQLException {
		// ignored
	}

	@Override
	public void updateCharacterStream(String columnLabel, Reader reader) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(int columnIndex, InputStream inputStream) throws SQLException {
		// ignored
	}

	@Override
	public void updateBlob(String columnLabel, InputStream inputStream) throws SQLException {
		// ignored
	}

	@Override
	public void updateClob(int columnIndex, Reader reader) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateClob(String columnLabel, Reader reader) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(int columnIndex, Reader reader) throws SQLException {
		// ignored
	}

	@Override
	public void updateNClob(String columnLabel, Reader reader) throws SQLException {
		// ignored
	}


}

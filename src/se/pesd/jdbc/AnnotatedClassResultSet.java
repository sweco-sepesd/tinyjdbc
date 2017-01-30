package se.pesd.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import se.pesd.jdbc.annotations.Column;

/**
 * This class can be used to provide a java.sql.ResultSet from an iterable of T's.
 * The T's are supposed to be annotated using se.pesd.jdbc.annotations.Table and se.pesd.jdbc.annotations.Column.
 * @author Peter Segerstedt
 *
 * @param <T>
 */
public class AnnotatedClassResultSet<T> extends AbstractResultSet {

	//static Logger logger = Logger.getLogger(AbstractDriver.class.getName());

	final Iterator<T> rows;
	final Class<T> clazz;
	final Map<String, ColumnMetadata> columnsByName = new HashMap<String, ColumnMetadata>();
	final Map<Integer, ColumnMetadata> columnsByPosition = new HashMap<Integer, ColumnMetadata>();

	T currentRow = null;
	int rownum = 0;
	boolean wasNull = false;

	public AnnotatedClassResultSet(final Iterable<T> rows, final Class<T> clazz) {
		logger.fine(String.format("Creating resultset for class %s", clazz.getName()));
		this.rows = rows.iterator();
		this.clazz = clazz;
		for (Method m : clazz.getMethods()) {
			// logger.fine(String.format("class %s has method %s",
			// clazz.getName(), m.getName()));
			Column col = m.getAnnotation(Column.class);
			if (col == null)
				continue;
			System.out.println(m.getName());
			ColumnMetadata metadata = new ColumnMetadata(clazz, m);
			columnsByName.put(metadata.columnName(), metadata);
			columnsByPosition.put(metadata.ordinalPosition(), metadata);
		}
		for (Field f : clazz.getFields()) {
			// logger.fine(String.format("class %s has field %s",
			// clazz.getName(), f.getName()));
			Column col = f.getAnnotation(Column.class);
			if (col == null)
				continue;
			ColumnMetadata metadata = new ColumnMetadata(clazz, f);
			columnsByName.put(metadata.columnName(), metadata);
			columnsByPosition.put(metadata.ordinalPosition(), metadata);
		}
	}

	<V> V getObject(ColumnMetadata metadata, Class<V> returnType) throws SQLException {
		// TODO Add more intelligent cast behavior?
		if(!metadata.javaType.equals(returnType)){
			logger.warning(String.format("Danger zone - trying to cast from %s to %s", metadata.javaType.getName(), returnType.getName()));
			return metadata.cast(getObject(metadata), returnType);
		}
		try {
			return returnType.cast(getObject(metadata));

		} catch (ClassCastException| IllegalArgumentException e) {
			return null;
		}
	}
	Object getObject(ColumnMetadata metadata) throws SQLException {
		try {
			Object retval = null;
			if (metadata.accessible instanceof Method)
				retval = ((Method) metadata.accessible).invoke(currentRow);
			else if (metadata.accessible instanceof Field)
				retval = ((Field) metadata.accessible).get(currentRow);
			wasNull = retval == null;
			return retval;

		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new SQLException(e);
		}
	}


	@Override
	public <V> V getObject(int columnIndex, Class<V> returnType) throws SQLException {
		ColumnMetadata metadata = columnsByPosition.get(new Integer(columnIndex));
		if (metadata == null)
			throw new SQLException("No such column");
		return getObject(metadata, returnType);
	}

	@Override
	public <V> V getObject(String columnName, Class<V> returnType) throws SQLException {
		ColumnMetadata metadata = columnsByName.get(columnName);
		if (metadata == null)
			throw new SQLException("No such column");
		return getObject(metadata, returnType);
	}

	@Override
	public int findColumn(String columnLabel) throws SQLException {
		return columnsByName.get(columnLabel).ordinalPosition();
	}

	@Override
	public boolean isBeforeFirst() throws SQLException {
		return currentRow == null;
	}

	@Override
	public boolean isAfterLast() throws SQLException {
		return !rows.hasNext();
	}

	@Override
	public boolean next() throws SQLException {
		if (!rows.hasNext())
			return false;
		currentRow = rows.next();
		rownum++;
		return true;
	}

	@Override
	public ResultSetMetaData getMetaData() throws SQLException {
		// TODO Auto-generated method stub

		return new ResultSetMetaData() {

			@Override
			public <I> I unwrap(Class<I> iface) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public boolean isWrapperFor(Class<?> iface) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public int getColumnCount() throws SQLException {
				// TODO Auto-generated method stub
				return columnsByName.size();
			}

			@Override
			public boolean isAutoIncrement(int column) throws SQLException {
				return columnsByPosition.get(column).column.autoIncrement();
			}

			@Override
			public boolean isCaseSensitive(int column) throws SQLException {
				return true;
			}

			@Override
			public boolean isSearchable(int column) throws SQLException {
				return true;
			}

			@Override
			public boolean isCurrency(int column) throws SQLException {
				return false;
			}

			@Override
			public int isNullable(int column) throws SQLException {
				return columnsByPosition.get(column).nullable();
			}

			@Override
			public boolean isSigned(int column) throws SQLException {
				return Number.class.isInstance(columnsByPosition.get(column).javaType);
			}

			@Override
			public int getColumnDisplaySize(int column) throws SQLException {
				return columnsByPosition.get(column).columnSize();
			}

			@Override
			public String getColumnLabel(int column) throws SQLException {
				return columnsByPosition.get(column).columnName();
			}

			@Override
			public String getColumnName(int column) throws SQLException {
				return getColumnLabel(column);
			}

			@Override
			public String getSchemaName(int column) throws SQLException {
				return columnsByPosition.get(column).tableSchema();
			}

			@Override
			public int getPrecision(int column) throws SQLException {
				return columnsByPosition.get(column).columnSize();
			}

			@Override
			public int getScale(int column) throws SQLException {
				return columnsByPosition.get(column).decimalDigits();
			}

			@Override
			public String getTableName(int column) throws SQLException {
				return columnsByPosition.get(column).tableName();
			}

			@Override
			public String getCatalogName(int column) throws SQLException {
				return columnsByPosition.get(column).tableCatalog();
			}

			@Override
			public int getColumnType(int column) throws SQLException {
				return columnsByPosition.get(column).sqlType;
			}

			@Override
			public String getColumnTypeName(int column) throws SQLException {
				return columnsByPosition.get(column).typeName();
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
				return columnsByPosition.get(column).javaType.getName();
			}
		};
	}

	@Override
	public boolean isFirst() throws SQLException {
		// TODO Auto-generated method stub
		return rownum == 1;
	}

	@Override
	public boolean isLast() throws SQLException {
		return !rows.hasNext();
	}

	@Override
	public int getRow() throws SQLException {
		return rownum;
	}

	@Override
	public boolean wasNull() throws SQLException {
		return wasNull;
	}

	@Override
	public Object getObject(int columnIndex) throws SQLException {
		return getObject(columnsByPosition.get(columnIndex));
	}

	@Override
	public Object getObject(String columnLabel) throws SQLException {
		return getObject(columnsByName.get(columnLabel));
	}

}

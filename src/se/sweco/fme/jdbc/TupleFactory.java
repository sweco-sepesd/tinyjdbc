package se.sweco.fme.jdbc;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.JDBCType;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import se.pesd.jdbc.annotations.Column;

public class TupleFactory {

	static Value toDescription(Map.Entry<Column, AccessibleObject> entry) {
		return new Value() {

			@Override
			public <T> T value(Class<T> type) {
				// Not implemented
				return null;
			}

			@Override
			public String name() {
				return entry.getKey().name();
			}

			@Override
			public Class<?> type() {
				if (entry.getValue() instanceof Field)
					return Field.class.cast(entry.getValue()).getType();
				else if (entry.getValue() instanceof Method)
					return Method.class.cast(entry.getValue()).getReturnType();
				return null;
			}

			@Override
			public int precision() {
				return entry.getKey().columnSize();
			}

			@Override
			public int scale() {
				return entry.getKey().decimalDigits();
			}

			@Override
			public boolean signed() {
				return isSigned(entry.getKey().jdbcType());
			}
		};
	}

	static <T> Tuple toTuple(final Class<T> iface, T item) {
		return new Tuple() {

			@Override
			public Stream<Value> values() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	static boolean isSigned(JDBCType jdbcType) {
		switch (jdbcType) {
		case BIGINT:
		case DECIMAL:
		case DOUBLE:
		case FLOAT:
		case INTEGER:
		case NUMERIC:
		case REAL:
		case SMALLINT:
		case TINYINT:
			return true;
		default:
			break;

		}
		return false;
	}

	static <T> TupleResultSet forAnnotatedClass(final Class<T> iface, final Stream<T> rows) {

		// final Table table = iface.getAnnotation(Table.class);

		final Map<Integer, Map.Entry<Column, AccessibleObject>> columns = new TreeMap<Integer, Map.Entry<Column, AccessibleObject>>();

		for (Method method : iface.getMethods()) {
			final Column column = method.getAnnotation(Column.class);
			if (column == null)
				continue;
			Map.Entry<Column, AccessibleObject> entry = new Map.Entry<Column, AccessibleObject>() {

				@Override
				public Column getKey() {
					return column;
				}

				@Override
				public AccessibleObject getValue() {
					return method;
				}

				@Override
				public AccessibleObject setValue(AccessibleObject value) {
					return null;
				}
			};
			columns.put(column.ordinalPosition(), entry);
		}

		for (Field field : iface.getFields()) {
			final Column column = field.getAnnotation(Column.class);
			if (column == null)
				continue;
			Map.Entry<Column, AccessibleObject> entry = new Map.Entry<Column, AccessibleObject>() {

				@Override
				public Column getKey() {
					return column;
				}

				@Override
				public AccessibleObject getValue() {
					return field;
				}

				@Override
				public AccessibleObject setValue(AccessibleObject value) {
					return null;
				}
			};
			columns.put(column.ordinalPosition(), entry);
		}

		return new TupleResultSet(new TupleProvider() {

			@Override
			public Stream<Value> describe() {
				return columns.values().stream().map(TupleFactory::toDescription);
			}

			@Override
			public Stream<Tuple> tuples() {
				return rows.map(row -> {
					return new Tuple() {

						@Override
						public Stream<Value> values() {
							return columns.values().stream().map(columnDesc -> {
								return new Value() {

									AccessibleObject accessible = columnDesc.getValue();

									@Override
									public <V> V value(Class<V> type) {
										try {
											Object retval = null;
											if (accessible instanceof Method) {
												retval = Method.class.cast(accessible).invoke(row);
											} else if (accessible instanceof Field) {
												retval = Field.class.cast(accessible).get(row);
											}
											return type.cast(retval);
										} catch (IllegalAccessException | IllegalArgumentException
												| InvocationTargetException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										return null;
									}

									@Override
									public String name() {
										return columnDesc.getKey().name();
									}

									@Override
									public Class<?> type() {
										return (accessible instanceof Method)
												? Method.class.cast(accessible).getReturnType()
												: (accessible instanceof Field) ? Field.class.cast(accessible).getType()
														: null;
									}

									@Override
									public int precision() {
										return columnDesc.getKey().columnSize();
									}

									@Override
									public int scale() {
										return columnDesc.getKey().decimalDigits();
									}

									@Override
									public boolean signed() {
										return isSigned(columnDesc.getKey().jdbcType());
									}
								};
							});
						}
					};
				});
			}
		});

	}
}

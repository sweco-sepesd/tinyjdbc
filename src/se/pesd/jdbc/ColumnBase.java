package se.pesd.jdbc;

import java.lang.annotation.Annotation;
import java.sql.JDBCType;

public class ColumnBase implements Column {

	final se.pesd.jdbc.annotations.Column columnAnnotation;

	public ColumnBase(se.pesd.jdbc.annotations.Column columnAnnotation) {
		this.columnAnnotation = columnAnnotation;
	}
	public ColumnBase(final String name, final int ordinalPosition, final JDBCType jdbcType) {
		this(new se.pesd.jdbc.annotations.Column() {

			@Override
			public Class<? extends Annotation> annotationType() {
				return se.pesd.jdbc.annotations.Column.class;
			}

			@Override
			public String name() {
				return name;
			}

			@Override
			public int ordinalPosition() {
				return ordinalPosition;
			}

			@Override
			public JDBCType jdbcType() {
				return jdbcType;
			}

			@Override
			public int columnSize() {
				return 0;
			}

			@Override
			public int decimalDigits() {
				return 0;
			}

			@Override
			public boolean nullable() {
				return true;
			}

			@Override
			public String remarks() {
				return "";
			}

			@Override
			public boolean autoIncrement() {
				return false;
			}});
	}

	@Override
	public String name() {
		return columnAnnotation.name();
	}

	@Override
	public int ordinalPosition() {
		return columnAnnotation.ordinalPosition();
	}

	@Override
	public JDBCType jdbcType() {
		return columnAnnotation.jdbcType();
	}

	@Override
	public int columnSize() {
		return columnAnnotation.columnSize();
	}

	@Override
	public int decimalDigits() {
		return 0;
	}

	@Override
	public boolean nullable() {
		return columnAnnotation.nullable();
	}

	@Override
	public String remarks() {
		return columnAnnotation.remarks();
	}

	@Override
	public boolean autoIncrement() {
		return columnAnnotation.autoIncrement();
	}

}

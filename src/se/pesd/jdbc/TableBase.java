package se.pesd.jdbc;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Iterator;

public class TableBase implements Table{

	final se.pesd.jdbc.annotations.Table tableAnnotation;
	protected final ArrayList<Column> columns = new ArrayList<Column>();

	public TableBase(final String catalog, final String schema, final String tableName) {
		this(new se.pesd.jdbc.annotations.Table(){

			@Override
			public Class<? extends Annotation> annotationType() {
				return se.pesd.jdbc.annotations.Table.class;
			}

			@Override
			public String catalog() {
				return catalog;
			}

			@Override
			public String schema() {
				return schema;
			}

			@Override
			public String tableName() {
				return tableName;
			}

			@Override
			public String tableType() {
				return "TABLE";
			}

			@Override
			public String remarks() {
				return "";
			}

			@Override
			public String typeCatalog() {
				return "";
			}

			@Override
			public String typeSchema() {
				return "";
			}

			@Override
			public String typeName() {
				return "";
			}

			@Override
			public String selfReferencingColName() {
				return "";
			}

			@Override
			public String refGeneration() {
				return "";
			}});
	}
	public TableBase(se.pesd.jdbc.annotations.Table tableAnnotation) {
		this.tableAnnotation = tableAnnotation;
	}

	@Override
	public String catalog() {
		return tableAnnotation.catalog();
	}

	@Override
	public String schema() {
		return tableAnnotation.schema();
	}

	@Override
	public String tableName() {
		return tableAnnotation.tableName();
	}

	@Override
	public String tableType() {
		return tableAnnotation.tableType();
	}

	@Override
	public String remarks() {
		return tableAnnotation.remarks();
	}

	@Override
	public String typeCatalog() {
		return tableAnnotation.typeCatalog();
	}

	@Override
	public String typeSchema() {
		return tableAnnotation.typeSchema();
	}

	@Override
	public String typeName() {
		return "";
	}

	@Override
	public String selfReferencingColName() {
		return tableAnnotation.selfReferencingColName();
	}

	@Override
	public String refGeneration() {
		return tableAnnotation.refGeneration();
	}
	@Override
	public Iterator<Column> columns() {
		return columns.iterator();
	}

}

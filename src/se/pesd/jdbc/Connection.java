package se.pesd.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Procedure;

/**
 * This should be the work horse that does all the heavy lifting
 * @author Admin
 *
 */

public abstract class Connection extends AbstractConnection {
	
	final protected String url;
	final protected Properties info;
	boolean connectionClosed = false;

	Map <String, Catalog> catalogs = new HashMap<String, Catalog>();
	Map <String, SchemaMetadata> schemas = new HashMap<String, SchemaMetadata>();
	Map <String, TableMetaData> tables = new HashMap<String, TableMetaData>();
	Map <String, ColumnMetadata> columns = new HashMap<String, ColumnMetadata>();

	static Logger logger = Logger.getLogger(AbstractDriver.class.getName());
	
	public Connection(String url, Properties info) {
		this.url = url;
		this.info = info;
		registerTable(Catalog.class);
		registerTable(SchemaMetadata.class);
		registerTable(TableMetaData.class);
		registerTable(ColumnMetadata.class);
	}

	@Override
	public Statement createStatement() throws SQLException {
		// TODO Auto-generated method stub
		return new se.pesd.jdbc.Statement(this);
	}

	@Override
	public String nativeSQL(String sql) throws SQLException {
		return sql;
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub
		connectionClosed = true;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return connectionClosed;
	}

	@Override
	public boolean isReadOnly() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isValid(int timeout) throws SQLException {
		return !isClosed();
	}

	@Override
	public void abort(Executor executor) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public DatabaseMetaData getMetaData() throws SQLException {
		return new AbstractDatabaseMetaData()  {

			@Override
			public ResultSet getTables(String catalog, String schemaPattern, String tableNamePattern, String[] types)
					throws SQLException {
				return new AnnotatedClassResultSet<TableMetaData>(tables.values(), TableMetaData.class);
			}

			@Override
			public ResultSet getColumns(String catalog, String schemaPattern, String tableNamePattern,
					String columnNamePattern) throws SQLException {
				// TODO Auto-generated method stub
				return new AnnotatedClassResultSet<ColumnMetadata>(columns.values(), ColumnMetadata.class);
			}

			@Override
			protected void debugMsg(String msg) {
				logger.info(msg);
				
			}

			@Override
			public ResultSet getSchemas() throws SQLException {
				return new AnnotatedClassResultSet<SchemaMetadata>(schemas.values(), SchemaMetadata.class);
			}

			@Override
			public ResultSet getCatalogs() throws SQLException {
				return new AnnotatedClassResultSet<Catalog>(catalogs.values(), Catalog.class);
			}
			
		};
	}
	
	public void registerProcedures(Class<?> implementingClass) {
		for (Method m : implementingClass.getMethods()) {
			Procedure procedure = m.getAnnotation(Procedure.class);
		}
	}
	
	public void registerTable(Class<?> tableClass){
		// TODO Duplicated code in TableResultSet, figure out how to do this more elegantly
		for (Method m : tableClass.getMethods()) {
			Column col = m.getAnnotation(Column.class);
			if (col == null)
				continue;
			ColumnMetadata metadata = new ColumnMetadata(tableClass, m);
			String qName = String.format("%s.%s.%s.%s", metadata.tableCatalog(), metadata.tableSchema(), metadata.tableName(),metadata.columnName());
			columns.put(qName, metadata);
		}
		for (Field f : tableClass.getFields()) {
			Column col = f.getAnnotation(Column.class);
			if (col == null)
				continue;
			ColumnMetadata metadata = new ColumnMetadata(tableClass, f);
			String qName = String.format("%s.%s.%s.%s", metadata.tableCatalog(), metadata.tableSchema(), metadata.tableName(),metadata.columnName());
			columns.put(qName, metadata);
		}
		TableMetaData tblMetadata = new TableMetaData(tableClass);
		String qName = String.format("%s.%s.%s", tblMetadata.tableCatalog(), tblMetadata.tableSchema(), tblMetadata.tableName());
		tables.put(qName, tblMetadata);

		SchemaMetadata schemaMetadata = new SchemaMetadata(tblMetadata.tableSchema(),tblMetadata.tableCatalog());
		String schemaQName = String.format("%s.%s", tblMetadata.tableCatalog(), tblMetadata.tableSchema());
		schemas.put(schemaQName, schemaMetadata);
		
		catalogs.put(tblMetadata.tableCatalog(), new Catalog(tblMetadata.tableCatalog()));
	}


}

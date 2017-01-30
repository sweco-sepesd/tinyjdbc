package se.pesd.jdbc.sysinfo;

import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;

import se.pesd.jdbc.RowFilter;
import se.pesd.jdbc.AnnotatedClassResultSet;
import se.pesd.jdbc.Table;

public class Driver extends se.pesd.jdbc.AbstractDriver {

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith("jdbc:sepesd:sysinfo");
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		if (!acceptsURL(url))
			throw new SQLException(
					String.format("The url `%s` is not valid for this driver (%s)", url, this.getClass().getName()));
		logger.fine(String.format("Creating connection using url %s", url));
		final String user = info.getProperty("user");
		se.pesd.jdbc.Connection conn = new se.pesd.jdbc.Connection(url, info) {

			@Override
			public AnnotatedClassResultSet<?> getRows(Table table, RowFilter<?> filter) {
				String qname = String.format("%s.%s", table.schema(), table.tableName());
				if (qname.equals("sysinfo.runtime")) {

					logger.info("Returning result from class RunTimeTable");
					final List<RunTimeTable> rows = new ArrayList<RunTimeTable>();
					rows.add(new RunTimeTable());
					return new AnnotatedClassResultSet<RunTimeTable>(rows, RunTimeTable.class);
				}
				if(qname.equals("sysinfo.charset")){
					final List<CharSetTable> rows = new ArrayList<CharSetTable>();
					for(Entry<String,Charset> e : Charset.availableCharsets().entrySet())
						rows.add(new CharSetTable(e.getKey(), e.getValue(), user));
					return new AnnotatedClassResultSet<CharSetTable>(rows, CharSetTable.class);
				}
				if(qname.equals("sysinfo.environment")){
					final List<EnvironmentTable> rows = new ArrayList<EnvironmentTable>();
					for(Entry<String,String> e : System.getenv().entrySet())
						rows.add(new EnvironmentTable(e.getKey(), e.getValue()));
					return new AnnotatedClassResultSet<EnvironmentTable>(rows, EnvironmentTable.class);
				}
				if(qname.equals("sysinfo.properties")){
					final List<PropertiesTable> rows = new ArrayList<PropertiesTable>();
					for(Entry<Object,Object> e : System.getProperties().entrySet())
						rows.add(new PropertiesTable(e.getKey().toString(), e.getValue().toString()));
					return new AnnotatedClassResultSet<PropertiesTable>(rows, PropertiesTable.class);
				}
				return null;
			}
		};
		conn.registerTable(RunTimeTable.class);
		conn.registerTable(EnvironmentTable.class);
		conn.registerTable(PropertiesTable.class);
		conn.registerTable(CharSetTable.class);
		return conn;
	}

}

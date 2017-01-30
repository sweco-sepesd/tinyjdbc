package se.pesd.jdbc.fmeinfo;

import java.sql.Connection;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import COM.safe.fmeobjects.FMEException;
import COM.safe.fmeobjects.FMEObjects;
import COM.safe.fmeobjects.IFMESession;
import COM.safe.fmeobjects.IFMEStringArray;
import se.pesd.jdbc.AbstractDriver;
import se.pesd.jdbc.RowFilter;
import se.pesd.jdbc.AnnotatedClassResultSet;
import se.pesd.jdbc.Table;

public class Driver extends AbstractDriver {

	@Override
	public Connection connect(String url, Properties info) throws SQLException {

		if (!acceptsURL(url))
			throw new SQLException(
					String.format("The url `%s` is not valid for this driver (%s)", url, this.getClass().getName()));
		logger.fine(String.format("Creating connection using url %s", url));

		try {
			final IFMESession session = FMEObjects.createSession();
			
			se.pesd.jdbc.Connection conn = new se.pesd.jdbc.Connection(url, info) {

				@Override
				public AnnotatedClassResultSet<?> getRows(Table table, RowFilter<?> filter) {
					String qname = String.format("%s.%s", table.schema(), table.tableName());
					if(qname.equals("fme.session")){

						logger.info("Returning result from class SessionInfo");
						final List<SessionInfo> rows = new ArrayList<SessionInfo>();
						rows.add(new SessionInfo(session));
						return new AnnotatedClassResultSet<SessionInfo>(rows, SessionInfo.class);
					}
					if (qname.equals("fme.session_properties")) {
						IFMEStringArray properties = session.createStringArray();
						final List<SessionProperties> rows = new ArrayList<SessionProperties>();
						try {
							session.getAllProperties(properties);
							for (int i = 0; i < properties.entries(); i+=2) {
									rows.add(new SessionProperties(properties.getElement(i), properties.getElement(i+1)));
							}
						} catch (FMEException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						return new AnnotatedClassResultSet<SessionProperties>(rows, SessionProperties.class);
					}
						
					return null;
				}

				@Override
				public void close() throws SQLException {
					// TODO Auto-generated method stub
					super.close();
					// Is this how we cleanup??
					session.dispose();
				}
				
			
			};
			conn.registerTable(SessionInfo.class);
			conn.registerTable(SessionProperties.class);
			return conn;
		} catch (FMEException e) {
			throw new SQLException("Could not create FMEObjects Session");
		}
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith("jdbc:sepesd:fmeinfo");
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}

package se.pesd.jdbc.test;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import COM.safe.fmeobjects.FMEException;
import COM.safe.fmeobjects.FMEGenerateInfo;
import COM.safe.fmeobjects.FMEObjects;
import COM.safe.fmeobjects.IFMESession;
import COM.safe.fmeobjects.IFMEStringArray;

public class TestDriver {

	public static void main(String[] args) throws SQLException, FMEException {
		Properties prop = new Properties();
		prop.setProperty("user", "åäö");
		Driver d = new se.pesd.jdbc.sysinfo.Driver();
		Connection conn = d.connect("jdbc:sepesd:sysinfo", prop);
		
		//ResultSet result = conn.getMetaData().getTables("", "", "", null);
		
		Statement stmnt = conn.createStatement();
		stmnt.execute("select * from \"sysinfo\".\"charset\"");
		ResultSet result = stmnt.getResultSet();
		ResultSetMetaData metadata = result.getMetaData();
		for (int i = 0; i < metadata.getColumnCount(); i++) {
			System.out.printf("%s ", metadata.getColumnName(i+1));
		}
		System.out.println();
		while(result.next()) {
			System.out.printf("%-4d ", result.getRow());
			for (int i = 1; i <= metadata.getColumnCount(); i++) {
				Object o = result.getString(i);
				if(result.wasNull())
					System.out.printf("null ");
				else
					System.out.printf("%s (%s) ",  o, o.getClass().getName());
			}
			System.out.println(); 
		}
		
		FMEGenerateInfo info = new FMEGenerateInfo();
		System.out.println();
		System.out.printf("%s\n%s\n", info.parameterFileName, info.workspaceFileName);
		
		IFMESession session = FMEObjects.createSession();
		IFMEStringArray properties = session.createStringArray();
		session.getAllProperties(properties);
		for (int i = 0; i < properties.entries(); i++)
			System.out.println(properties.getElement(i));

	}

}

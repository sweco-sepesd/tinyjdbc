package se.sweco.fme.jdbc;

import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;

import se.pesd.jdbc.annotations.Column;

public class Testo {

	@Column(name = "csdf", ordinalPosition = 1, jdbcType = JDBCType.VARCHAR)
	public final String name; 

	@Column(name = "bsdf", ordinalPosition = 2, jdbcType = JDBCType.INTEGER)
	public int nameLength() {
		return name.length();
	}
	@Column(name = "asdf", ordinalPosition = 3, jdbcType = JDBCType.INTEGER)
	public int nameByteLength() {
		return name.getBytes().length;
	}
	
	public Testo(String name) {
		this.name = name;
	}
	
	public static void main(String[] args) throws SQLException {
		Testo[] testos = {new Testo("hello"), new Testo("world"), new Testo("åäö")};
		
		ResultSet res = TupleFactory.forAnnotatedClass(Testo.class, Arrays.stream(testos));
		ResultSetMetaData metadata = res.getMetaData();
		for(int i = 1; i <= metadata.getColumnCount(); i++) {
			System.out.printf("%-30s", String.format("%s (%s)", metadata.getColumnName(i), metadata.getColumnTypeName(i)));
		}
		System.out.println();
		while (res.next()) {
			for(int i = 1; i <= metadata.getColumnCount(); i++) {
				System.out.printf("%-30s", res.getObject(i));
			}
			System.out.println();
		}
		res.close();
		
		res = new TupleResultSet(new SystemInfoProvider());
		metadata = res.getMetaData();
		for(int i = 1; i <= metadata.getColumnCount(); i++) {
			System.out.printf("%-30s", String.format("%s (%s)", metadata.getColumnName(i), metadata.getColumnTypeName(i)));
		}
		System.out.println();
		while (res.next()) {
			for(int i = 1; i <= metadata.getColumnCount(); i++) {
				System.out.printf("%-30s", res.getObject(i));
			}
			System.out.println();
		}
		res.close();
	}
	
	

}

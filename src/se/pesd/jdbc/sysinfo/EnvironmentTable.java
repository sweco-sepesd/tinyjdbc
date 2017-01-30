package se.pesd.jdbc.sysinfo;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "sysinfo", tableName = "environment")
public class EnvironmentTable {

	@Column(name = "name", ordinalPosition = 1)
	public final String name;
	@Column(name = "value", ordinalPosition = 2)
	public final String value;
	public EnvironmentTable(String name, String value) {
		this.name = name;
		this.value = value;
	}
	

}

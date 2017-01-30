package se.pesd.jdbc.sysinfo;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "sysinfo", tableName = "properties")
public class PropertiesTable {

	@Column(name = "name", ordinalPosition = 1)
	public final String name;
	@Column(name = "value", ordinalPosition = 2)
	public final String value;
	public PropertiesTable(String name, String value) {
		this.name = name;
		this.value = value;
	}
	

}

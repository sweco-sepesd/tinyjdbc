package se.pesd.jdbc.fmeinfo;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "fme", tableName = "session_properties")
public class SessionProperties {

	@Column(name = "name", ordinalPosition = 1)
	public final String name;
	@Column(name = "value", ordinalPosition = 2)
	public final String value;
	public SessionProperties(String name, String value) {
		this.name = name;
		this.value = value;
	}
	

}

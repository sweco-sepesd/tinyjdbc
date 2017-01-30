package se.pesd.jdbc.sysinfo;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "sysinfo", tableName = "runtime")
public class RunTimeTable {

	@Column(name = "memory_free", ordinalPosition = 1)
	public long freeMemory() {
		return Runtime.getRuntime().freeMemory();
	}

	@Column(name = "memory_total", ordinalPosition = 2)
	public long totalMemory() {
		return Runtime.getRuntime().totalMemory();
	}
	@Column(name = "memory_max", ordinalPosition = 3)
	public long maxMemory() {
		return Runtime.getRuntime().maxMemory();
	}
	@Column(name = "n_processors", ordinalPosition = 4)
	public int availableProcessors() {
		return Runtime.getRuntime().availableProcessors();
	}
}

package se.pesd.jdbc.sysinfo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

import se.pesd.jdbc.annotations.Column;
import se.pesd.jdbc.annotations.Table;

@Table(catalog = "", schema = "sysinfo", tableName = "charset")
public class CharSetTable {

	@Column(name = "name", ordinalPosition = 1)
	public final String name;
	@Column(name = "sample", ordinalPosition = 2)
	public final String sample;
	@Column(name = "bytes", ordinalPosition = 3)
	public final byte[] bytes;
	public CharSetTable(String name, Charset charset, String sample) {
		this.name = name;
		this.sample = sample;
		byte[] data;
		try {
		ByteBuffer buf = charset.encode(sample);
		
		data = new byte[buf.limit()];
		buf.get(data);
		} catch (UnsupportedOperationException e) {
			 data = new byte[0];
		}
		this.bytes = data;
	}
}

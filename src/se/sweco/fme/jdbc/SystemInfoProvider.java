package se.sweco.fme.jdbc;

import java.util.Arrays;
import java.util.stream.Stream;

public class SystemInfoProvider implements TupleProvider {

	@Override
	public Stream<Value> describe() {
		Value [] row = {new StringValue("key", null), new StringValue("value", null)};
		return Arrays.stream(row);
	}

	@Override
	public Stream<Tuple> tuples() {
		return System.getProperties().entrySet().stream().map(e -> new Tuple(){
			@Override
			public Stream<Value> values() {
				Value [] row = {new StringValue("key", e.getKey().toString()), new StringValue("value", e.getValue().toString())};
				return Arrays.stream(row);
			}});
	}

}

package se.sweco.fme.jdbc;

import java.util.stream.Stream;

public interface TupleProvider {
	public Stream<Value> describe();
	public Stream<Tuple> tuples();
}

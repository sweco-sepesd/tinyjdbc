package se.sweco.fme.jdbc;

import java.util.stream.Stream;

public interface Tuple {

		public Stream<Value> values();
}

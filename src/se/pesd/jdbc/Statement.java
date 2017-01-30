package se.pesd.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import se.pesd.jdbc.PSQLParser.ExecuteContext;
import se.pesd.jdbc.PSQLParser.SelectContext;

public class Statement extends AbstractStatement {
	
	final se.pesd.jdbc.Connection connection;
	Logger logger = Logger.getLogger(AbstractDriver.class.getName());
	private ArrayList<String> batch = new ArrayList<String>();
	private int updateCount = 0;
	
	private ResultSet currentResult = null;

	static final Pattern selectFromWhere = Pattern.compile("SELECT +(.*) +FROM +(.*) +WHERE +(.*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	static final Pattern selectFrom = Pattern.compile("SELECT +(.*) +FROM +(.*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
	static final Pattern tableIdentifier = Pattern.compile("\"([a-z|_]+)\"\\.\"([a-z|_]+)\"", Pattern.CASE_INSENSITIVE);
	public Statement(se.pesd.jdbc.Connection connection) {
		this.connection = connection;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {
		logger.fine(String.format("Executing sql `%s`", sql));
		CharStream input = new ANTLRInputStream(sql);

		PSQLLexer lexer = new PSQLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		PSQLParser parser = new PSQLParser(tokens);

		PSQLParser.ParseContext tree = parser.parse();

		switch (tokens.get(0).getType()) {
		case PSQLLexer.K_SELECT:
			return select(tree.select());
		case PSQLLexer.K_EXECUTE:
			return execute(tree.execute());
		default:
			throw new SQLException("Only select and execute implemented");
		}
	}

	private ResultSet execute(ExecuteContext execute) {
		// TODO Auto-generated method stub
		return null;
	}
	public static String unquote(String s, String q) {
		String doubleq = q + q;
		return (s != null && s.startsWith(q) && s.endsWith(q))
				? s = (s.substring(1, s.length() - 1)).replaceAll(doubleq, q) : s.replaceAll(doubleq, q);
	}
	private ResultSet select(SelectContext select) throws SQLException {

		String schemaName = unquote(select.qtable().schema().ID().getText(), "\"");
		String tableName = unquote(select.qtable().table().ID().getText(), "\"");
		String qName = String.format("%s.%s",schemaName, tableName);
		logger.fine(String.format("qname is %s", qName));
		for(Entry<String,TableMetaData> e : connection.tables.entrySet()){
			if(e.getKey().endsWith(qName)) {
				if(e.getValue().tableCatalog().equals("system")) {
					DatabaseMetaData dbMetadata = connection.getMetaData();
					if(e.getKey().equals("system.information_schema.catalogs"))
						return dbMetadata.getCatalogs();
					if(e.getKey().equals("system.information_schema.schemas"))
						return dbMetadata.getSchemas();
					if(e.getKey().equals("system.information_schema.tables"))
						return dbMetadata.getTables(null, null, null, null);
					if(e.getKey().equals("system.information_schema.columns"))
						return dbMetadata.getColumns(null, null, null, null);
						
				}
				return connection.getRows(e.getValue().table, null);
			}
		}
		logger.fine("Giving up...");
		throw new SQLException("Giving up...");
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return updateCount;
	}

	@Override
	public void close() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean execute(String sql) throws SQLException {
		// TODO Auto-generated method stub
		//logger.fine(String.format("Executing sql `%s`", sql));
		this.currentResult = executeQuery(sql);
		return true;
	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		return currentResult;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultSetType() throws SQLException {
		return ResultSet.TYPE_FORWARD_ONLY;
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		batch.add(sql);
	}

	@Override
	public void clearBatch() throws SQLException {
		batch = new ArrayList<String>();
	}

	@Override
	public int[] executeBatch() throws SQLException {
		int[] updateCounts = new int[batch.size()];
		for(int i = 0; i < batch.size(); i++){
			execute( batch.get(i));
			updateCounts[i] = getUpdateCount();
		}
		return updateCounts;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return connection;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		return updateCount;
	}

}

package se.pesd.jdbc.test;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.bind.DatatypeConverter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.TerminalNode;

import se.pesd.jdbc.PSQLBaseListener;
import se.pesd.jdbc.PSQLLexer;
import se.pesd.jdbc.PSQLParser;
import se.pesd.jdbc.PSQLParser.ArgsContext;
import se.pesd.jdbc.PSQLParser.ExecuteContext;
import se.pesd.jdbc.PSQLParser.FieldsContext;
import se.pesd.jdbc.PSQLParser.LiteralContext;
import se.pesd.jdbc.PSQLParser.ProcedureContext;
import se.pesd.jdbc.PSQLParser.QtableContext;
import se.pesd.jdbc.PSQLParser.SchemaContext;
import se.pesd.jdbc.PSQLParser.SelectContext;
import se.pesd.jdbc.PSQLParser.TableContext;

public class TestPSQL extends PSQLBaseListener {

	public static String unquote(String s, String q) {
		String doubleq = q + q;
		return (s != null && s.startsWith(q) && s.endsWith(q))
				? s = (s.substring(1, s.length() - 1)).replaceAll(doubleq, q) : s.replaceAll(doubleq, q);
	}

	StringBuilder qTableBuilder = null;

	@Override
	public void enterSelect(SelectContext ctx) {
		// TODO Auto-generated method stub
		super.enterSelect(ctx);
	}

	@Override
	public void enterExecute(ExecuteContext ctx) {
		// TODO Auto-generated method stub
		super.enterExecute(ctx);
	}

	@Override
	public void enterProcedure(ProcedureContext ctx) {
		// TODO Auto-generated method stub
		super.enterProcedure(ctx);
	}

	@Override
	public void enterArgs(ArgsContext ctx) {

	}

	@Override
	public void enterFields(FieldsContext ctx) {
		if (ctx.STAR() != null)
			System.out.println("<all fields>");
		else {
			for (TerminalNode node : ctx.ID())
				System.out.println(unquote(node.getText(), "\""));
		}
	}

	@Override
	public void enterQtable(QtableContext ctx) {
		qTableBuilder = new StringBuilder();
	}

	@Override
	public void exitQtable(QtableContext ctx) {
		System.out.println(qTableBuilder.toString());
	}

	@Override
	public void enterSchema(SchemaContext ctx) {
		qTableBuilder.append(unquote(ctx.ID().getText(), "\""));
		qTableBuilder.append(".");
	}

	@Override
	public void enterTable(TableContext ctx) {
		qTableBuilder.append(unquote(ctx.ID().getText(), "\""));
	}

	static void execute(ExecuteContext ctx) {
		System.out.println(ctx.procedure().ID());
		// TODO: Lookup procedure and break/return/throw if not found

		ArrayList<Object> args = new ArrayList<Object>();
		if (ctx.args() != null) {
			for (LiteralContext literal : ctx.args().literal()) {
				if (literal.signed_number() != null) {
					int sign = literal.signed_number().getText().charAt(0) == '-' ? -1 : 1;
					if (literal.signed_number().NUMERIC_LITERAL().getText().contains("."))
						args.add(Double.parseDouble(literal.signed_number().NUMERIC_LITERAL().getText()) * sign);
					else
						args.add(Integer.parseInt(literal.signed_number().NUMERIC_LITERAL().getText()) * sign);
					continue; // TODO
				}
				TerminalNode node = literal.STRING_LITERAL();
				if (node != null) {
					args.add(unquote(node.getText(), "'"));
					continue;
				}
				node = literal.NUMERIC_LITERAL();
				if (node != null) {
					if (literal.signed_number().NUMERIC_LITERAL().getText().contains("."))
						args.add(Double.parseDouble(node.getText()));
					else
						args.add(Integer.parseInt(node.getText()));
					continue;
				}
				node = literal.BLOB_LITERAL();
				if (node != null) {
					args.add(DatatypeConverter.parseHexBinary(node.getText().substring(2)));
					continue;
				}
			}
		}
		int i = 0;
		for (Object o : args) {
			System.out.printf("%s %s (%s)\n", i++, o.toString(), o.getClass().getSimpleName());
		}
	}

	static void select(SelectContext ctx) {
		System.out.println(ctx.qtable().getText());
	}

	static void parse(String sql) throws IOException {

		// StringReader reader = new StringReader(sql);
		CharStream input = new ANTLRInputStream(sql);

		PSQLLexer lexer = new PSQLLexer(input);

		CommonTokenStream tokens = new CommonTokenStream(lexer);

		PSQLParser parser = new PSQLParser(tokens);

		PSQLParser.ParseContext tree = parser.parse();

		switch (tokens.get(0).getType()) {
		case PSQLLexer.K_SELECT:
			select(tree.select());
			break;
		case PSQLLexer.K_EXECUTE:
			execute(tree.execute());
			break;
		default:
			break;
		}

		// System.out.printf("%s\n", tree.execute() != null ? "EXECUTE" :
		// tree.select() != null ? "SELECT" : "UNKNOWN");
		// TestPSQL extractor = new TestPSQL();
		// ParseTreeWalker.DEFAULT.walk(extractor, tree);

	}

	public static void main(String[] args) throws IOException {
		String[] sqls = { "execute myproc 'asdf', 'bsdf', 123, 2.54, -25, 0x12345e", "select * from mytab where 1=0",
				"seleCt \"å ä ö\" FROM public.\"the table\"" };
		for (String sql : sqls) {
			System.out.println("# " + sql);
			parse(sql);
			System.out.println();
		}

	}

}

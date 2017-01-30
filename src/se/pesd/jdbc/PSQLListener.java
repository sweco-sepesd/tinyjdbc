// Generated from PSQL.g4 by ANTLR 4.5.3
package se.pesd.jdbc;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link PSQLParser}.
 */
public interface PSQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link PSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void enterParse(PSQLParser.ParseContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#parse}.
	 * @param ctx the parse tree
	 */
	void exitParse(PSQLParser.ParseContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#error}.
	 * @param ctx the parse tree
	 */
	void enterError(PSQLParser.ErrorContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#error}.
	 * @param ctx the parse tree
	 */
	void exitError(PSQLParser.ErrorContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void enterSelect(PSQLParser.SelectContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#select}.
	 * @param ctx the parse tree
	 */
	void exitSelect(PSQLParser.SelectContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#execute}.
	 * @param ctx the parse tree
	 */
	void enterExecute(PSQLParser.ExecuteContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#execute}.
	 * @param ctx the parse tree
	 */
	void exitExecute(PSQLParser.ExecuteContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#procedure}.
	 * @param ctx the parse tree
	 */
	void enterProcedure(PSQLParser.ProcedureContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#procedure}.
	 * @param ctx the parse tree
	 */
	void exitProcedure(PSQLParser.ProcedureContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(PSQLParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(PSQLParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(PSQLParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(PSQLParser.FieldsContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#qtable}.
	 * @param ctx the parse tree
	 */
	void enterQtable(PSQLParser.QtableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#qtable}.
	 * @param ctx the parse tree
	 */
	void exitQtable(PSQLParser.QtableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#schema}.
	 * @param ctx the parse tree
	 */
	void enterSchema(PSQLParser.SchemaContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#schema}.
	 * @param ctx the parse tree
	 */
	void exitSchema(PSQLParser.SchemaContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void enterTable(PSQLParser.TableContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#table}.
	 * @param ctx the parse tree
	 */
	void exitTable(PSQLParser.TableContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(PSQLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(PSQLParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link PSQLParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void enterSigned_number(PSQLParser.Signed_numberContext ctx);
	/**
	 * Exit a parse tree produced by {@link PSQLParser#signed_number}.
	 * @param ctx the parse tree
	 */
	void exitSigned_number(PSQLParser.Signed_numberContext ctx);
}
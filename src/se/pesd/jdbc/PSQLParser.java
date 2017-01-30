// Generated from PSQL.g4 by ANTLR 4.5.3
package se.pesd.jdbc;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, K_SELECT=5, K_EXECUTE=6, K_FROM=7, K_WHERE=8, 
		K_NULL=9, WHERE=10, STAR=11, NUMERIC_LITERAL=12, STRING_LITERAL=13, BLOB_LITERAL=14, 
		ID=15, WS=16, UNEXPECTED_CHAR=17;
	public static final int
		RULE_parse = 0, RULE_error = 1, RULE_select = 2, RULE_execute = 3, RULE_procedure = 4, 
		RULE_args = 5, RULE_fields = 6, RULE_qtable = 7, RULE_schema = 8, RULE_table = 9, 
		RULE_literal = 10, RULE_signed_number = 11;
	public static final String[] ruleNames = {
		"parse", "error", "select", "execute", "procedure", "args", "fields", 
		"qtable", "schema", "table", "literal", "signed_number"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "','", "'.'", "'+'", "'-'", null, null, null, null, null, "'1=0'", 
		"'*'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, "K_SELECT", "K_EXECUTE", "K_FROM", "K_WHERE", 
		"K_NULL", "WHERE", "STAR", "NUMERIC_LITERAL", "STRING_LITERAL", "BLOB_LITERAL", 
		"ID", "WS", "UNEXPECTED_CHAR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "PSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ParseContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(PSQLParser.EOF, 0); }
		public SelectContext select() {
			return getRuleContext(SelectContext.class,0);
		}
		public ExecuteContext execute() {
			return getRuleContext(ExecuteContext.class,0);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(26);
			switch (_input.LA(1)) {
			case K_SELECT:
				{
				setState(24);
				select();
				}
				break;
			case K_EXECUTE:
				{
				setState(25);
				execute();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(28);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ErrorContext extends ParserRuleContext {
		public Token UNEXPECTED_CHAR;
		public TerminalNode UNEXPECTED_CHAR() { return getToken(PSQLParser.UNEXPECTED_CHAR, 0); }
		public ErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_error; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitError(this);
		}
	}

	public final ErrorContext error() throws RecognitionException {
		ErrorContext _localctx = new ErrorContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_error);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			((ErrorContext)_localctx).UNEXPECTED_CHAR = match(UNEXPECTED_CHAR);
			throw new RuntimeException("UNEXPECTED_CHAR=" + (((ErrorContext)_localctx).UNEXPECTED_CHAR!=null?((ErrorContext)_localctx).UNEXPECTED_CHAR.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SelectContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(PSQLParser.K_SELECT, 0); }
		public TerminalNode K_FROM() { return getToken(PSQLParser.K_FROM, 0); }
		public FieldsContext fields() {
			return getRuleContext(FieldsContext.class,0);
		}
		public QtableContext qtable() {
			return getRuleContext(QtableContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(PSQLParser.K_WHERE, 0); }
		public TerminalNode WHERE() { return getToken(PSQLParser.WHERE, 0); }
		public SelectContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterSelect(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitSelect(this);
		}
	}

	public final SelectContext select() throws RecognitionException {
		SelectContext _localctx = new SelectContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_select);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33);
			match(K_SELECT);
			{
			setState(34);
			fields();
			}
			setState(35);
			match(K_FROM);
			{
			setState(36);
			qtable();
			}
			setState(39);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(37);
				match(K_WHERE);
				setState(38);
				match(WHERE);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExecuteContext extends ParserRuleContext {
		public TerminalNode K_EXECUTE() { return getToken(PSQLParser.K_EXECUTE, 0); }
		public ProcedureContext procedure() {
			return getRuleContext(ProcedureContext.class,0);
		}
		public ArgsContext args() {
			return getRuleContext(ArgsContext.class,0);
		}
		public ExecuteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_execute; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterExecute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitExecute(this);
		}
	}

	public final ExecuteContext execute() throws RecognitionException {
		ExecuteContext _localctx = new ExecuteContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_execute);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(K_EXECUTE);
			setState(42);
			procedure();
			setState(44);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << K_NULL) | (1L << NUMERIC_LITERAL) | (1L << STRING_LITERAL) | (1L << BLOB_LITERAL))) != 0)) {
				{
				setState(43);
				args();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ProcedureContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PSQLParser.ID, 0); }
		public ProcedureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedure; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterProcedure(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitProcedure(this);
		}
	}

	public final ProcedureContext procedure() throws RecognitionException {
		ProcedureContext _localctx = new ProcedureContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_procedure);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgsContext extends ParserRuleContext {
		public List<LiteralContext> literal() {
			return getRuleContexts(LiteralContext.class);
		}
		public LiteralContext literal(int i) {
			return getRuleContext(LiteralContext.class,i);
		}
		public ArgsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterArgs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitArgs(this);
		}
	}

	public final ArgsContext args() throws RecognitionException {
		ArgsContext _localctx = new ArgsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_args);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(48);
			literal();
			}
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(49);
				match(T__0);
				setState(50);
				literal();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldsContext extends ParserRuleContext {
		public TerminalNode STAR() { return getToken(PSQLParser.STAR, 0); }
		public List<TerminalNode> ID() { return getTokens(PSQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(PSQLParser.ID, i);
		}
		public FieldsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fields; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterFields(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitFields(this);
		}
	}

	public final FieldsContext fields() throws RecognitionException {
		FieldsContext _localctx = new FieldsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_fields);
		int _la;
		try {
			setState(65);
			switch (_input.LA(1)) {
			case STAR:
				enterOuterAlt(_localctx, 1);
				{
				setState(56);
				match(STAR);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(57);
				match(ID);
				setState(62);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__0) {
					{
					{
					setState(58);
					match(T__0);
					setState(59);
					match(ID);
					}
					}
					setState(64);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QtableContext extends ParserRuleContext {
		public TableContext table() {
			return getRuleContext(TableContext.class,0);
		}
		public SchemaContext schema() {
			return getRuleContext(SchemaContext.class,0);
		}
		public QtableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qtable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterQtable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitQtable(this);
		}
	}

	public final QtableContext qtable() throws RecognitionException {
		QtableContext _localctx = new QtableContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_qtable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				{
				setState(67);
				schema();
				setState(68);
				match(T__1);
				}
				break;
			}
			setState(72);
			table();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SchemaContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PSQLParser.ID, 0); }
		public SchemaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_schema; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterSchema(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitSchema(this);
		}
	}

	public final SchemaContext schema() throws RecognitionException {
		SchemaContext _localctx = new SchemaContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_schema);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(74);
			match(ID);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(PSQLParser.ID, 0); }
		public TableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterTable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitTable(this);
		}
	}

	public final TableContext table() throws RecognitionException {
		TableContext _localctx = new TableContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_table);
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(76);
			match(ID);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public Signed_numberContext signed_number() {
			return getRuleContext(Signed_numberContext.class,0);
		}
		public TerminalNode NUMERIC_LITERAL() { return getToken(PSQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(PSQLParser.STRING_LITERAL, 0); }
		public TerminalNode BLOB_LITERAL() { return getToken(PSQLParser.BLOB_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(PSQLParser.K_NULL, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitLiteral(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_literal);
		try {
			setState(83);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(78);
				signed_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(79);
				match(NUMERIC_LITERAL);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(80);
				match(STRING_LITERAL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(81);
				match(BLOB_LITERAL);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(82);
				match(K_NULL);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Signed_numberContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(PSQLParser.NUMERIC_LITERAL, 0); }
		public Signed_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signed_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).enterSigned_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof PSQLListener ) ((PSQLListener)listener).exitSigned_number(this);
		}
	}

	public final Signed_numberContext signed_number() throws RecognitionException {
		Signed_numberContext _localctx = new Signed_numberContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_signed_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if (_la==T__2 || _la==T__3) {
				{
				setState(85);
				_la = _input.LA(1);
				if ( !(_la==T__2 || _la==T__3) ) {
				_errHandler.recoverInline(this);
				} else {
					consume();
				}
				}
			}

			setState(88);
			match(NUMERIC_LITERAL);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\23]\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\3\2\3\2\5\2\35\n\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\5\4*\n\4\3\5\3\5\3\5\5\5/\n\5\3\6\3\6\3\7\3\7\3\7\7\7\66\n\7"+
		"\f\7\16\79\13\7\3\b\3\b\3\b\3\b\7\b?\n\b\f\b\16\bB\13\b\5\bD\n\b\3\t\3"+
		"\t\3\t\5\tI\n\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\f\3\f\3\f\5\fV\n\f"+
		"\3\r\5\rY\n\r\3\r\3\r\3\r\2\2\16\2\4\6\b\n\f\16\20\22\24\26\30\2\3\3\2"+
		"\5\6\\\2\34\3\2\2\2\4 \3\2\2\2\6#\3\2\2\2\b+\3\2\2\2\n\60\3\2\2\2\f\62"+
		"\3\2\2\2\16C\3\2\2\2\20H\3\2\2\2\22L\3\2\2\2\24N\3\2\2\2\26U\3\2\2\2\30"+
		"X\3\2\2\2\32\35\5\6\4\2\33\35\5\b\5\2\34\32\3\2\2\2\34\33\3\2\2\2\35\36"+
		"\3\2\2\2\36\37\7\2\2\3\37\3\3\2\2\2 !\7\23\2\2!\"\b\3\1\2\"\5\3\2\2\2"+
		"#$\7\7\2\2$%\5\16\b\2%&\7\t\2\2&)\5\20\t\2\'(\7\n\2\2(*\7\f\2\2)\'\3\2"+
		"\2\2)*\3\2\2\2*\7\3\2\2\2+,\7\b\2\2,.\5\n\6\2-/\5\f\7\2.-\3\2\2\2./\3"+
		"\2\2\2/\t\3\2\2\2\60\61\7\21\2\2\61\13\3\2\2\2\62\67\5\26\f\2\63\64\7"+
		"\3\2\2\64\66\5\26\f\2\65\63\3\2\2\2\669\3\2\2\2\67\65\3\2\2\2\678\3\2"+
		"\2\28\r\3\2\2\29\67\3\2\2\2:D\7\r\2\2;@\7\21\2\2<=\7\3\2\2=?\7\21\2\2"+
		"><\3\2\2\2?B\3\2\2\2@>\3\2\2\2@A\3\2\2\2AD\3\2\2\2B@\3\2\2\2C:\3\2\2\2"+
		"C;\3\2\2\2D\17\3\2\2\2EF\5\22\n\2FG\7\4\2\2GI\3\2\2\2HE\3\2\2\2HI\3\2"+
		"\2\2IJ\3\2\2\2JK\5\24\13\2K\21\3\2\2\2LM\7\21\2\2M\23\3\2\2\2NO\7\21\2"+
		"\2O\25\3\2\2\2PV\5\30\r\2QV\7\16\2\2RV\7\17\2\2SV\7\20\2\2TV\7\13\2\2"+
		"UP\3\2\2\2UQ\3\2\2\2UR\3\2\2\2US\3\2\2\2UT\3\2\2\2V\27\3\2\2\2WY\t\2\2"+
		"\2XW\3\2\2\2XY\3\2\2\2YZ\3\2\2\2Z[\7\16\2\2[\31\3\2\2\2\13\34).\67@CH"+
		"UX";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
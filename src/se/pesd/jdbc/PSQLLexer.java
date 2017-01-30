// Generated from PSQL.g4 by ANTLR 4.5.3
package se.pesd.jdbc;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, K_SELECT=5, K_EXECUTE=6, K_FROM=7, K_WHERE=8, 
		K_NULL=9, WHERE=10, STAR=11, NUMERIC_LITERAL=12, STRING_LITERAL=13, BLOB_LITERAL=14, 
		ID=15, WS=16, UNEXPECTED_CHAR=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "K_SELECT", "K_EXECUTE", "K_FROM", "K_WHERE", 
		"K_NULL", "WHERE", "STAR", "NUMERIC_LITERAL", "STRING_LITERAL", "BLOB_LITERAL", 
		"ID", "WS", "UNEXPECTED_CHAR", "DIGIT", "A", "B", "C", "D", "E", "F", 
		"G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
		"U", "V", "W", "X", "Y", "Z"
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


	public PSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "PSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23\u0118\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\r\6\r\u008a"+
		"\n\r\r\r\16\r\u008b\3\r\3\r\7\r\u0090\n\r\f\r\16\r\u0093\13\r\5\r\u0095"+
		"\n\r\3\r\3\r\5\r\u0099\n\r\3\r\6\r\u009c\n\r\r\r\16\r\u009d\5\r\u00a0"+
		"\n\r\3\r\3\r\6\r\u00a4\n\r\r\r\16\r\u00a5\3\r\3\r\5\r\u00aa\n\r\3\r\6"+
		"\r\u00ad\n\r\r\r\16\r\u00ae\5\r\u00b1\n\r\5\r\u00b3\n\r\3\16\3\16\3\16"+
		"\3\16\7\16\u00b9\n\16\f\16\16\16\u00bc\13\16\3\16\3\16\3\17\3\17\3\17"+
		"\3\17\6\17\u00c4\n\17\r\17\16\17\u00c5\3\20\3\20\3\20\3\20\6\20\u00cc"+
		"\n\20\r\20\16\20\u00cd\3\20\3\20\3\20\7\20\u00d3\n\20\f\20\16\20\u00d6"+
		"\13\20\5\20\u00d8\n\20\3\21\6\21\u00db\n\21\r\21\16\21\u00dc\3\21\3\21"+
		"\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30"+
		"\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\37\3\37"+
		"\3 \3 \3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\3*\3"+
		"*\3+\3+\3,\3,\3-\3-\2\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f"+
		"\27\r\31\16\33\17\35\20\37\21!\22#\23%\2\'\2)\2+\2-\2/\2\61\2\63\2\65"+
		"\2\67\29\2;\2=\2?\2A\2C\2E\2G\2I\2K\2M\2O\2Q\2S\2U\2W\2Y\2\3\2$\4\2--"+
		"//\3\2))\5\2\62;CHch\3\2$$\4\2C\\c|\5\2\62;C\\c|\5\2\13\f\17\17\"\"\3"+
		"\2\62;\4\2CCcc\4\2DDdd\4\2EEee\4\2FFff\4\2GGgg\4\2HHhh\4\2IIii\4\2JJj"+
		"j\4\2KKkk\4\2LLll\4\2MMmm\4\2NNnn\4\2OOoo\4\2PPpp\4\2QQqq\4\2RRrr\4\2"+
		"SSss\4\2TTtt\4\2UUuu\4\2VVvv\4\2WWww\4\2XXxx\4\2YYyy\4\2ZZzz\4\2[[{{\4"+
		"\2\\\\||\u010f\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3"+
		"\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2"+
		"\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3"+
		"\2\2\2\2#\3\2\2\2\3[\3\2\2\2\5]\3\2\2\2\7_\3\2\2\2\ta\3\2\2\2\13c\3\2"+
		"\2\2\rj\3\2\2\2\17r\3\2\2\2\21w\3\2\2\2\23}\3\2\2\2\25\u0082\3\2\2\2\27"+
		"\u0086\3\2\2\2\31\u00b2\3\2\2\2\33\u00b4\3\2\2\2\35\u00bf\3\2\2\2\37\u00d7"+
		"\3\2\2\2!\u00da\3\2\2\2#\u00e0\3\2\2\2%\u00e2\3\2\2\2\'\u00e4\3\2\2\2"+
		")\u00e6\3\2\2\2+\u00e8\3\2\2\2-\u00ea\3\2\2\2/\u00ec\3\2\2\2\61\u00ee"+
		"\3\2\2\2\63\u00f0\3\2\2\2\65\u00f2\3\2\2\2\67\u00f4\3\2\2\29\u00f6\3\2"+
		"\2\2;\u00f8\3\2\2\2=\u00fa\3\2\2\2?\u00fc\3\2\2\2A\u00fe\3\2\2\2C\u0100"+
		"\3\2\2\2E\u0102\3\2\2\2G\u0104\3\2\2\2I\u0106\3\2\2\2K\u0108\3\2\2\2M"+
		"\u010a\3\2\2\2O\u010c\3\2\2\2Q\u010e\3\2\2\2S\u0110\3\2\2\2U\u0112\3\2"+
		"\2\2W\u0114\3\2\2\2Y\u0116\3\2\2\2[\\\7.\2\2\\\4\3\2\2\2]^\7\60\2\2^\6"+
		"\3\2\2\2_`\7-\2\2`\b\3\2\2\2ab\7/\2\2b\n\3\2\2\2cd\5K&\2de\5/\30\2ef\5"+
		"=\37\2fg\5/\30\2gh\5+\26\2hi\5M\'\2i\f\3\2\2\2jk\5/\30\2kl\5U+\2lm\5/"+
		"\30\2mn\5+\26\2no\5O(\2op\5M\'\2pq\5/\30\2q\16\3\2\2\2rs\5\61\31\2st\5"+
		"I%\2tu\5C\"\2uv\5? \2v\20\3\2\2\2wx\5S*\2xy\5\65\33\2yz\5/\30\2z{\5I%"+
		"\2{|\5/\30\2|\22\3\2\2\2}~\5A!\2~\177\5O(\2\177\u0080\5=\37\2\u0080\u0081"+
		"\5=\37\2\u0081\24\3\2\2\2\u0082\u0083\7\63\2\2\u0083\u0084\7?\2\2\u0084"+
		"\u0085\7\62\2\2\u0085\26\3\2\2\2\u0086\u0087\7,\2\2\u0087\30\3\2\2\2\u0088"+
		"\u008a\5%\23\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u0094\3\2\2\2\u008d\u0091\7\60\2\2\u008e"+
		"\u0090\5%\23\2\u008f\u008e\3\2\2\2\u0090\u0093\3\2\2\2\u0091\u008f\3\2"+
		"\2\2\u0091\u0092\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0094"+
		"\u008d\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u009f\3\2\2\2\u0096\u0098\5/"+
		"\30\2\u0097\u0099\t\2\2\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099"+
		"\u009b\3\2\2\2\u009a\u009c\5%\23\2\u009b\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a0\3\2\2\2\u009f"+
		"\u0096\3\2\2\2\u009f\u00a0\3\2\2\2\u00a0\u00b3\3\2\2\2\u00a1\u00a3\7\60"+
		"\2\2\u00a2\u00a4\5%\23\2\u00a3\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a3\3\2\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00b0\3\2\2\2\u00a7\u00a9\5/"+
		"\30\2\u00a8\u00aa\t\2\2\2\u00a9\u00a8\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa"+
		"\u00ac\3\2\2\2\u00ab\u00ad\5%\23\2\u00ac\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\3\2\2\2\u00b0"+
		"\u00a7\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u0089\3\2"+
		"\2\2\u00b2\u00a1\3\2\2\2\u00b3\32\3\2\2\2\u00b4\u00ba\7)\2\2\u00b5\u00b9"+
		"\n\3\2\2\u00b6\u00b7\7)\2\2\u00b7\u00b9\7)\2\2\u00b8\u00b5\3\2\2\2\u00b8"+
		"\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2\2\u00ba\u00bb\3\2"+
		"\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7)\2\2\u00be"+
		"\34\3\2\2\2\u00bf\u00c0\7\62\2\2\u00c0\u00c3\5U+\2\u00c1\u00c2\t\4\2\2"+
		"\u00c2\u00c4\t\4\2\2\u00c3\u00c1\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c3"+
		"\3\2\2\2\u00c5\u00c6\3\2\2\2\u00c6\36\3\2\2\2\u00c7\u00cb\7$\2\2\u00c8"+
		"\u00cc\n\5\2\2\u00c9\u00ca\7$\2\2\u00ca\u00cc\7$\2\2\u00cb\u00c8\3\2\2"+
		"\2\u00cb\u00c9\3\2\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00cb\3\2\2\2\u00cd\u00ce"+
		"\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf\u00d8\7$\2\2\u00d0\u00d4\t\6\2\2\u00d1"+
		"\u00d3\t\7\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3\2\2\2\u00d4\u00d2\3\2"+
		"\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3\2\2\2\u00d7"+
		"\u00c7\3\2\2\2\u00d7\u00d0\3\2\2\2\u00d8 \3\2\2\2\u00d9\u00db\t\b\2\2"+
		"\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da\3\2\2\2\u00dc\u00dd"+
		"\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\b\21\2\2\u00df\"\3\2\2\2\u00e0"+
		"\u00e1\13\2\2\2\u00e1$\3\2\2\2\u00e2\u00e3\t\t\2\2\u00e3&\3\2\2\2\u00e4"+
		"\u00e5\t\n\2\2\u00e5(\3\2\2\2\u00e6\u00e7\t\13\2\2\u00e7*\3\2\2\2\u00e8"+
		"\u00e9\t\f\2\2\u00e9,\3\2\2\2\u00ea\u00eb\t\r\2\2\u00eb.\3\2\2\2\u00ec"+
		"\u00ed\t\16\2\2\u00ed\60\3\2\2\2\u00ee\u00ef\t\17\2\2\u00ef\62\3\2\2\2"+
		"\u00f0\u00f1\t\20\2\2\u00f1\64\3\2\2\2\u00f2\u00f3\t\21\2\2\u00f3\66\3"+
		"\2\2\2\u00f4\u00f5\t\22\2\2\u00f58\3\2\2\2\u00f6\u00f7\t\23\2\2\u00f7"+
		":\3\2\2\2\u00f8\u00f9\t\24\2\2\u00f9<\3\2\2\2\u00fa\u00fb\t\25\2\2\u00fb"+
		">\3\2\2\2\u00fc\u00fd\t\26\2\2\u00fd@\3\2\2\2\u00fe\u00ff\t\27\2\2\u00ff"+
		"B\3\2\2\2\u0100\u0101\t\30\2\2\u0101D\3\2\2\2\u0102\u0103\t\31\2\2\u0103"+
		"F\3\2\2\2\u0104\u0105\t\32\2\2\u0105H\3\2\2\2\u0106\u0107\t\33\2\2\u0107"+
		"J\3\2\2\2\u0108\u0109\t\34\2\2\u0109L\3\2\2\2\u010a\u010b\t\35\2\2\u010b"+
		"N\3\2\2\2\u010c\u010d\t\36\2\2\u010dP\3\2\2\2\u010e\u010f\t\37\2\2\u010f"+
		"R\3\2\2\2\u0110\u0111\t \2\2\u0111T\3\2\2\2\u0112\u0113\t!\2\2\u0113V"+
		"\3\2\2\2\u0114\u0115\t\"\2\2\u0115X\3\2\2\2\u0116\u0117\t#\2\2\u0117Z"+
		"\3\2\2\2\26\2\u008b\u0091\u0094\u0098\u009d\u009f\u00a5\u00a9\u00ae\u00b0"+
		"\u00b2\u00b8\u00ba\u00c5\u00cb\u00cd\u00d4\u00d7\u00dc\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
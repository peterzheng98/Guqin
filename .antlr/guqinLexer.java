// Generated from /root/Guqin/guqin.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class guqinLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, ID=16, LINE_COMMENT=17, 
		BLOCK_COMMENT=18, CHAR=19, STRING_VALUE=20, VOID=21, BOOL=22, INT=23, 
		STRING=24, NEW=25, CLASS=26, NULL=27, TRUE=28, FALSE=29, THIS=30, IF=31, 
		ELSE=32, FOR=33, WHILE=34, BREAK=35, CONTINUE=36, RETURN=37, PRINT=38, 
		PRINTLN=39, PRINTINT=40, GETSTRING=41, GETINT=42, TOSTRING=43, ADD=44, 
		MINUS=45, MUL=46, DIV=47, MOD=48, AND=49, OR=50, NOT=51, GEQ=52, LEQ=53, 
		EQ=54, UEQ=55, RSH=56, RLH=57, SAD=58, SMI=59, BAN=60, BOR=61, XOR=62, 
		BNO=63, LE=64, GE=65, ASS=66, INT_VALUE=67, WS=68;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "ID", "LINE_COMMENT", 
			"BLOCK_COMMENT", "CHAR", "STRING_VALUE", "VOID", "BOOL", "INT", "STRING", 
			"NEW", "CLASS", "NULL", "TRUE", "FALSE", "THIS", "IF", "ELSE", "FOR", 
			"WHILE", "BREAK", "CONTINUE", "RETURN", "PRINT", "PRINTLN", "PRINTINT", 
			"GETSTRING", "GETINT", "TOSTRING", "ADD", "MINUS", "MUL", "DIV", "MOD", 
			"AND", "OR", "NOT", "GEQ", "LEQ", "EQ", "UEQ", "RSH", "RLH", "SAD", "SMI", 
			"BAN", "BOR", "XOR", "BNO", "LE", "GE", "ASS", "INT_VALUE", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "','", "'('", "')'", "'{'", "'}'", "'()'", "';'", "'.'", "'['", 
			"']'", "'?'", "':'", "'f'", "'\"'", "'[]'", null, null, null, null, null, 
			"'void'", "'bool'", "'int'", "'string'", "'new'", "'class'", "'null'", 
			"'true'", "'false'", "'this'", "'if'", "'else'", "'for'", "'while'", 
			"'break'", "'continue'", "'return'", "'print'", "'println'", "'printlinInt'", 
			"'getString'", "'getInt'", "'toString'", "'+'", "'-'", "'*'", "'/'", 
			"'%'", "'&&'", "'||'", "'!'", "'>='", "'<='", "'=='", "'!='", "'>>'", 
			"'<<'", "'++'", "'--'", "'&'", "'|'", "'^'", "'~'", "'<'", "'>'", "'='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, "ID", "LINE_COMMENT", "BLOCK_COMMENT", "CHAR", 
			"STRING_VALUE", "VOID", "BOOL", "INT", "STRING", "NEW", "CLASS", "NULL", 
			"TRUE", "FALSE", "THIS", "IF", "ELSE", "FOR", "WHILE", "BREAK", "CONTINUE", 
			"RETURN", "PRINT", "PRINTLN", "PRINTINT", "GETSTRING", "GETINT", "TOSTRING", 
			"ADD", "MINUS", "MUL", "DIV", "MOD", "AND", "OR", "NOT", "GEQ", "LEQ", 
			"EQ", "UEQ", "RSH", "RLH", "SAD", "SMI", "BAN", "BOR", "XOR", "BNO", 
			"LE", "GE", "ASS", "INT_VALUE", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
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


	public guqinLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "guqin.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000D\u01b2\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002"+
		"\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002"+
		"\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002"+
		"\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002"+
		"\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002"+
		"\u001e\u0007\u001e\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007"+
		"!\u0002\"\u0007\"\u0002#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007"+
		"&\u0002\'\u0007\'\u0002(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007"+
		"+\u0002,\u0007,\u0002-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u0007"+
		"0\u00021\u00071\u00022\u00072\u00023\u00073\u00024\u00074\u00025\u0007"+
		"5\u00026\u00076\u00027\u00077\u00028\u00078\u00029\u00079\u0002:\u0007"+
		":\u0002;\u0007;\u0002<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007"+
		"?\u0002@\u0007@\u0002A\u0007A\u0002B\u0007B\u0002C\u0007C\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003"+
		"\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u00ac\b\u000f\n\u000f\f\u000f\u00af\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00b5\b\u0010\n\u0010\f\u0010"+
		"\u00b8\t\u0010\u0001\u0010\u0003\u0010\u00bb\b\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0005\u0011\u00c5\b\u0011\n\u0011\f\u0011\u00c8\t\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0003\u0012\u00d2\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013"+
		"\u0001\u0013\u0005\u0013\u00d8\b\u0013\n\u0013\f\u0013\u00db\t\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0001\u0019\u0001\u001a\u0001\u001a\u0001\u001a\u0001"+
		"\u001a\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001"+
		"\u001b\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001\u001c\u0001"+
		"\u001c\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001\u001d\u0001"+
		"\u001e\u0001\u001e\u0001\u001e\u0001\u001f\u0001\u001f\u0001\u001f\u0001"+
		"\u001f\u0001\u001f\u0001 \u0001 \u0001 \u0001 \u0001!\u0001!\u0001!\u0001"+
		"!\u0001!\u0001!\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001\"\u0001"+
		"#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001#\u0001$\u0001"+
		"$\u0001$\u0001$\u0001$\u0001$\u0001$\u0001%\u0001%\u0001%\u0001%\u0001"+
		"%\u0001%\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001&\u0001"+
		"\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001\'\u0001"+
		"\'\u0001\'\u0001\'\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001(\u0001"+
		"(\u0001(\u0001(\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001)\u0001"+
		"*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001*\u0001+\u0001"+
		"+\u0001,\u0001,\u0001-\u0001-\u0001.\u0001.\u0001/\u0001/\u00010\u0001"+
		"0\u00010\u00011\u00011\u00011\u00012\u00012\u00013\u00013\u00013\u0001"+
		"4\u00014\u00014\u00015\u00015\u00015\u00016\u00016\u00016\u00017\u0001"+
		"7\u00017\u00018\u00018\u00018\u00019\u00019\u00019\u0001:\u0001:\u0001"+
		":\u0001;\u0001;\u0001<\u0001<\u0001=\u0001=\u0001>\u0001>\u0001?\u0001"+
		"?\u0001@\u0001@\u0001A\u0001A\u0001B\u0004B\u01a8\bB\u000bB\fB\u01a9\u0001"+
		"C\u0004C\u01ad\bC\u000bC\fC\u01ae\u0001C\u0001C\u0002\u00b6\u00c6\u0000"+
		"D\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u001a5\u001b7\u001c9\u001d;\u001e=\u001f? A!C\"E#G$I%"+
		"K&M\'O(Q)S*U+W,Y-[.]/_0a1c2e3g4i5k6m7o8q9s:u;w<y={>}?\u007f@\u0081A\u0083"+
		"B\u0085C\u0087D\u0001\u0000\u0005\u0002\u0000AZaz\u0004\u000009AZ__az"+
		"\u0001\u0000\"\"\u0001\u000009\u0003\u0000\t\n\r\r  \u01ba\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u0000"+
		"5\u0001\u0000\u0000\u0000\u00007\u0001\u0000\u0000\u0000\u00009\u0001"+
		"\u0000\u0000\u0000\u0000;\u0001\u0000\u0000\u0000\u0000=\u0001\u0000\u0000"+
		"\u0000\u0000?\u0001\u0000\u0000\u0000\u0000A\u0001\u0000\u0000\u0000\u0000"+
		"C\u0001\u0000\u0000\u0000\u0000E\u0001\u0000\u0000\u0000\u0000G\u0001"+
		"\u0000\u0000\u0000\u0000I\u0001\u0000\u0000\u0000\u0000K\u0001\u0000\u0000"+
		"\u0000\u0000M\u0001\u0000\u0000\u0000\u0000O\u0001\u0000\u0000\u0000\u0000"+
		"Q\u0001\u0000\u0000\u0000\u0000S\u0001\u0000\u0000\u0000\u0000U\u0001"+
		"\u0000\u0000\u0000\u0000W\u0001\u0000\u0000\u0000\u0000Y\u0001\u0000\u0000"+
		"\u0000\u0000[\u0001\u0000\u0000\u0000\u0000]\u0001\u0000\u0000\u0000\u0000"+
		"_\u0001\u0000\u0000\u0000\u0000a\u0001\u0000\u0000\u0000\u0000c\u0001"+
		"\u0000\u0000\u0000\u0000e\u0001\u0000\u0000\u0000\u0000g\u0001\u0000\u0000"+
		"\u0000\u0000i\u0001\u0000\u0000\u0000\u0000k\u0001\u0000\u0000\u0000\u0000"+
		"m\u0001\u0000\u0000\u0000\u0000o\u0001\u0000\u0000\u0000\u0000q\u0001"+
		"\u0000\u0000\u0000\u0000s\u0001\u0000\u0000\u0000\u0000u\u0001\u0000\u0000"+
		"\u0000\u0000w\u0001\u0000\u0000\u0000\u0000y\u0001\u0000\u0000\u0000\u0000"+
		"{\u0001\u0000\u0000\u0000\u0000}\u0001\u0000\u0000\u0000\u0000\u007f\u0001"+
		"\u0000\u0000\u0000\u0000\u0081\u0001\u0000\u0000\u0000\u0000\u0083\u0001"+
		"\u0000\u0000\u0000\u0000\u0085\u0001\u0000\u0000\u0000\u0000\u0087\u0001"+
		"\u0000\u0000\u0000\u0001\u0089\u0001\u0000\u0000\u0000\u0003\u008b\u0001"+
		"\u0000\u0000\u0000\u0005\u008d\u0001\u0000\u0000\u0000\u0007\u008f\u0001"+
		"\u0000\u0000\u0000\t\u0091\u0001\u0000\u0000\u0000\u000b\u0093\u0001\u0000"+
		"\u0000\u0000\r\u0096\u0001\u0000\u0000\u0000\u000f\u0098\u0001\u0000\u0000"+
		"\u0000\u0011\u009a\u0001\u0000\u0000\u0000\u0013\u009c\u0001\u0000\u0000"+
		"\u0000\u0015\u009e\u0001\u0000\u0000\u0000\u0017\u00a0\u0001\u0000\u0000"+
		"\u0000\u0019\u00a2\u0001\u0000\u0000\u0000\u001b\u00a4\u0001\u0000\u0000"+
		"\u0000\u001d\u00a6\u0001\u0000\u0000\u0000\u001f\u00a9\u0001\u0000\u0000"+
		"\u0000!\u00b0\u0001\u0000\u0000\u0000#\u00c0\u0001\u0000\u0000\u0000%"+
		"\u00d1\u0001\u0000\u0000\u0000\'\u00d3\u0001\u0000\u0000\u0000)\u00de"+
		"\u0001\u0000\u0000\u0000+\u00e3\u0001\u0000\u0000\u0000-\u00e8\u0001\u0000"+
		"\u0000\u0000/\u00ec\u0001\u0000\u0000\u00001\u00f3\u0001\u0000\u0000\u0000"+
		"3\u00f7\u0001\u0000\u0000\u00005\u00fd\u0001\u0000\u0000\u00007\u0102"+
		"\u0001\u0000\u0000\u00009\u0107\u0001\u0000\u0000\u0000;\u010d\u0001\u0000"+
		"\u0000\u0000=\u0112\u0001\u0000\u0000\u0000?\u0115\u0001\u0000\u0000\u0000"+
		"A\u011a\u0001\u0000\u0000\u0000C\u011e\u0001\u0000\u0000\u0000E\u0124"+
		"\u0001\u0000\u0000\u0000G\u012a\u0001\u0000\u0000\u0000I\u0133\u0001\u0000"+
		"\u0000\u0000K\u013a\u0001\u0000\u0000\u0000M\u0140\u0001\u0000\u0000\u0000"+
		"O\u0148\u0001\u0000\u0000\u0000Q\u0154\u0001\u0000\u0000\u0000S\u015e"+
		"\u0001\u0000\u0000\u0000U\u0165\u0001\u0000\u0000\u0000W\u016e\u0001\u0000"+
		"\u0000\u0000Y\u0170\u0001\u0000\u0000\u0000[\u0172\u0001\u0000\u0000\u0000"+
		"]\u0174\u0001\u0000\u0000\u0000_\u0176\u0001\u0000\u0000\u0000a\u0178"+
		"\u0001\u0000\u0000\u0000c\u017b\u0001\u0000\u0000\u0000e\u017e\u0001\u0000"+
		"\u0000\u0000g\u0180\u0001\u0000\u0000\u0000i\u0183\u0001\u0000\u0000\u0000"+
		"k\u0186\u0001\u0000\u0000\u0000m\u0189\u0001\u0000\u0000\u0000o\u018c"+
		"\u0001\u0000\u0000\u0000q\u018f\u0001\u0000\u0000\u0000s\u0192\u0001\u0000"+
		"\u0000\u0000u\u0195\u0001\u0000\u0000\u0000w\u0198\u0001\u0000\u0000\u0000"+
		"y\u019a\u0001\u0000\u0000\u0000{\u019c\u0001\u0000\u0000\u0000}\u019e"+
		"\u0001\u0000\u0000\u0000\u007f\u01a0\u0001\u0000\u0000\u0000\u0081\u01a2"+
		"\u0001\u0000\u0000\u0000\u0083\u01a4\u0001\u0000\u0000\u0000\u0085\u01a7"+
		"\u0001\u0000\u0000\u0000\u0087\u01ac\u0001\u0000\u0000\u0000\u0089\u008a"+
		"\u0005,\u0000\u0000\u008a\u0002\u0001\u0000\u0000\u0000\u008b\u008c\u0005"+
		"(\u0000\u0000\u008c\u0004\u0001\u0000\u0000\u0000\u008d\u008e\u0005)\u0000"+
		"\u0000\u008e\u0006\u0001\u0000\u0000\u0000\u008f\u0090\u0005{\u0000\u0000"+
		"\u0090\b\u0001\u0000\u0000\u0000\u0091\u0092\u0005}\u0000\u0000\u0092"+
		"\n\u0001\u0000\u0000\u0000\u0093\u0094\u0005(\u0000\u0000\u0094\u0095"+
		"\u0005)\u0000\u0000\u0095\f\u0001\u0000\u0000\u0000\u0096\u0097\u0005"+
		";\u0000\u0000\u0097\u000e\u0001\u0000\u0000\u0000\u0098\u0099\u0005.\u0000"+
		"\u0000\u0099\u0010\u0001\u0000\u0000\u0000\u009a\u009b\u0005[\u0000\u0000"+
		"\u009b\u0012\u0001\u0000\u0000\u0000\u009c\u009d\u0005]\u0000\u0000\u009d"+
		"\u0014\u0001\u0000\u0000\u0000\u009e\u009f\u0005?\u0000\u0000\u009f\u0016"+
		"\u0001\u0000\u0000\u0000\u00a0\u00a1\u0005:\u0000\u0000\u00a1\u0018\u0001"+
		"\u0000\u0000\u0000\u00a2\u00a3\u0005f\u0000\u0000\u00a3\u001a\u0001\u0000"+
		"\u0000\u0000\u00a4\u00a5\u0005\"\u0000\u0000\u00a5\u001c\u0001\u0000\u0000"+
		"\u0000\u00a6\u00a7\u0005[\u0000\u0000\u00a7\u00a8\u0005]\u0000\u0000\u00a8"+
		"\u001e\u0001\u0000\u0000\u0000\u00a9\u00ad\u0007\u0000\u0000\u0000\u00aa"+
		"\u00ac\u0007\u0001\u0000\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac"+
		"\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad"+
		"\u00ae\u0001\u0000\u0000\u0000\u00ae \u0001\u0000\u0000\u0000\u00af\u00ad"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005/\u0000\u0000\u00b1\u00b2\u0005"+
		"/\u0000\u0000\u00b2\u00b6\u0001\u0000\u0000\u0000\u00b3\u00b5\t\u0000"+
		"\u0000\u0000\u00b4\u00b3\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b7\u0001\u0000\u0000\u0000\u00b6\u00b4\u0001\u0000"+
		"\u0000\u0000\u00b7\u00ba\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b9\u00bb\u0005\r\u0000\u0000\u00ba\u00b9\u0001\u0000\u0000"+
		"\u0000\u00ba\u00bb\u0001\u0000\u0000\u0000\u00bb\u00bc\u0001\u0000\u0000"+
		"\u0000\u00bc\u00bd\u0005\n\u0000\u0000\u00bd\u00be\u0001\u0000\u0000\u0000"+
		"\u00be\u00bf\u0006\u0010\u0000\u0000\u00bf\"\u0001\u0000\u0000\u0000\u00c0"+
		"\u00c1\u0005/\u0000\u0000\u00c1\u00c2\u0005*\u0000\u0000\u00c2\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c3\u00c5\t\u0000\u0000\u0000\u00c4\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c5\u00c8\u0001\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000"+
		"\u0000\u0000\u00c6\u00c4\u0001\u0000\u0000\u0000\u00c7\u00c9\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005*\u0000"+
		"\u0000\u00ca\u00cb\u0005/\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000\u0000"+
		"\u00cc\u00cd\u0006\u0011\u0000\u0000\u00cd$\u0001\u0000\u0000\u0000\u00ce"+
		"\u00cf\u0005\\\u0000\u0000\u00cf\u00d2\u0007\u0002\u0000\u0000\u00d0\u00d2"+
		"\b\u0002\u0000\u0000\u00d1\u00ce\u0001\u0000\u0000\u0000\u00d1\u00d0\u0001"+
		"\u0000\u0000\u0000\u00d2&\u0001\u0000\u0000\u0000\u00d3\u00d9\u0005\""+
		"\u0000\u0000\u00d4\u00d5\u0005\\\u0000\u0000\u00d5\u00d8\u0007\u0002\u0000"+
		"\u0000\u00d6\u00d8\b\u0002\u0000\u0000\u00d7\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d8\u00db\u0001\u0000\u0000\u0000"+
		"\u00d9\u00d7\u0001\u0000\u0000\u0000\u00d9\u00da\u0001\u0000\u0000\u0000"+
		"\u00da\u00dc\u0001\u0000\u0000\u0000\u00db\u00d9\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0005\"\u0000\u0000\u00dd(\u0001\u0000\u0000\u0000\u00de"+
		"\u00df\u0005v\u0000\u0000\u00df\u00e0\u0005o\u0000\u0000\u00e0\u00e1\u0005"+
		"i\u0000\u0000\u00e1\u00e2\u0005d\u0000\u0000\u00e2*\u0001\u0000\u0000"+
		"\u0000\u00e3\u00e4\u0005b\u0000\u0000\u00e4\u00e5\u0005o\u0000\u0000\u00e5"+
		"\u00e6\u0005o\u0000\u0000\u00e6\u00e7\u0005l\u0000\u0000\u00e7,\u0001"+
		"\u0000\u0000\u0000\u00e8\u00e9\u0005i\u0000\u0000\u00e9\u00ea\u0005n\u0000"+
		"\u0000\u00ea\u00eb\u0005t\u0000\u0000\u00eb.\u0001\u0000\u0000\u0000\u00ec"+
		"\u00ed\u0005s\u0000\u0000\u00ed\u00ee\u0005t\u0000\u0000\u00ee\u00ef\u0005"+
		"r\u0000\u0000\u00ef\u00f0\u0005i\u0000\u0000\u00f0\u00f1\u0005n\u0000"+
		"\u0000\u00f1\u00f2\u0005g\u0000\u0000\u00f20\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f4\u0005n\u0000\u0000\u00f4\u00f5\u0005e\u0000\u0000\u00f5\u00f6\u0005"+
		"w\u0000\u0000\u00f62\u0001\u0000\u0000\u0000\u00f7\u00f8\u0005c\u0000"+
		"\u0000\u00f8\u00f9\u0005l\u0000\u0000\u00f9\u00fa\u0005a\u0000\u0000\u00fa"+
		"\u00fb\u0005s\u0000\u0000\u00fb\u00fc\u0005s\u0000\u0000\u00fc4\u0001"+
		"\u0000\u0000\u0000\u00fd\u00fe\u0005n\u0000\u0000\u00fe\u00ff\u0005u\u0000"+
		"\u0000\u00ff\u0100\u0005l\u0000\u0000\u0100\u0101\u0005l\u0000\u0000\u0101"+
		"6\u0001\u0000\u0000\u0000\u0102\u0103\u0005t\u0000\u0000\u0103\u0104\u0005"+
		"r\u0000\u0000\u0104\u0105\u0005u\u0000\u0000\u0105\u0106\u0005e\u0000"+
		"\u0000\u01068\u0001\u0000\u0000\u0000\u0107\u0108\u0005f\u0000\u0000\u0108"+
		"\u0109\u0005a\u0000\u0000\u0109\u010a\u0005l\u0000\u0000\u010a\u010b\u0005"+
		"s\u0000\u0000\u010b\u010c\u0005e\u0000\u0000\u010c:\u0001\u0000\u0000"+
		"\u0000\u010d\u010e\u0005t\u0000\u0000\u010e\u010f\u0005h\u0000\u0000\u010f"+
		"\u0110\u0005i\u0000\u0000\u0110\u0111\u0005s\u0000\u0000\u0111<\u0001"+
		"\u0000\u0000\u0000\u0112\u0113\u0005i\u0000\u0000\u0113\u0114\u0005f\u0000"+
		"\u0000\u0114>\u0001\u0000\u0000\u0000\u0115\u0116\u0005e\u0000\u0000\u0116"+
		"\u0117\u0005l\u0000\u0000\u0117\u0118\u0005s\u0000\u0000\u0118\u0119\u0005"+
		"e\u0000\u0000\u0119@\u0001\u0000\u0000\u0000\u011a\u011b\u0005f\u0000"+
		"\u0000\u011b\u011c\u0005o\u0000\u0000\u011c\u011d\u0005r\u0000\u0000\u011d"+
		"B\u0001\u0000\u0000\u0000\u011e\u011f\u0005w\u0000\u0000\u011f\u0120\u0005"+
		"h\u0000\u0000\u0120\u0121\u0005i\u0000\u0000\u0121\u0122\u0005l\u0000"+
		"\u0000\u0122\u0123\u0005e\u0000\u0000\u0123D\u0001\u0000\u0000\u0000\u0124"+
		"\u0125\u0005b\u0000\u0000\u0125\u0126\u0005r\u0000\u0000\u0126\u0127\u0005"+
		"e\u0000\u0000\u0127\u0128\u0005a\u0000\u0000\u0128\u0129\u0005k\u0000"+
		"\u0000\u0129F\u0001\u0000\u0000\u0000\u012a\u012b\u0005c\u0000\u0000\u012b"+
		"\u012c\u0005o\u0000\u0000\u012c\u012d\u0005n\u0000\u0000\u012d\u012e\u0005"+
		"t\u0000\u0000\u012e\u012f\u0005i\u0000\u0000\u012f\u0130\u0005n\u0000"+
		"\u0000\u0130\u0131\u0005u\u0000\u0000\u0131\u0132\u0005e\u0000\u0000\u0132"+
		"H\u0001\u0000\u0000\u0000\u0133\u0134\u0005r\u0000\u0000\u0134\u0135\u0005"+
		"e\u0000\u0000\u0135\u0136\u0005t\u0000\u0000\u0136\u0137\u0005u\u0000"+
		"\u0000\u0137\u0138\u0005r\u0000\u0000\u0138\u0139\u0005n\u0000\u0000\u0139"+
		"J\u0001\u0000\u0000\u0000\u013a\u013b\u0005p\u0000\u0000\u013b\u013c\u0005"+
		"r\u0000\u0000\u013c\u013d\u0005i\u0000\u0000\u013d\u013e\u0005n\u0000"+
		"\u0000\u013e\u013f\u0005t\u0000\u0000\u013fL\u0001\u0000\u0000\u0000\u0140"+
		"\u0141\u0005p\u0000\u0000\u0141\u0142\u0005r\u0000\u0000\u0142\u0143\u0005"+
		"i\u0000\u0000\u0143\u0144\u0005n\u0000\u0000\u0144\u0145\u0005t\u0000"+
		"\u0000\u0145\u0146\u0005l\u0000\u0000\u0146\u0147\u0005n\u0000\u0000\u0147"+
		"N\u0001\u0000\u0000\u0000\u0148\u0149\u0005p\u0000\u0000\u0149\u014a\u0005"+
		"r\u0000\u0000\u014a\u014b\u0005i\u0000\u0000\u014b\u014c\u0005n\u0000"+
		"\u0000\u014c\u014d\u0005t\u0000\u0000\u014d\u014e\u0005l\u0000\u0000\u014e"+
		"\u014f\u0005i\u0000\u0000\u014f\u0150\u0005n\u0000\u0000\u0150\u0151\u0005"+
		"I\u0000\u0000\u0151\u0152\u0005n\u0000\u0000\u0152\u0153\u0005t\u0000"+
		"\u0000\u0153P\u0001\u0000\u0000\u0000\u0154\u0155\u0005g\u0000\u0000\u0155"+
		"\u0156\u0005e\u0000\u0000\u0156\u0157\u0005t\u0000\u0000\u0157\u0158\u0005"+
		"S\u0000\u0000\u0158\u0159\u0005t\u0000\u0000\u0159\u015a\u0005r\u0000"+
		"\u0000\u015a\u015b\u0005i\u0000\u0000\u015b\u015c\u0005n\u0000\u0000\u015c"+
		"\u015d\u0005g\u0000\u0000\u015dR\u0001\u0000\u0000\u0000\u015e\u015f\u0005"+
		"g\u0000\u0000\u015f\u0160\u0005e\u0000\u0000\u0160\u0161\u0005t\u0000"+
		"\u0000\u0161\u0162\u0005I\u0000\u0000\u0162\u0163\u0005n\u0000\u0000\u0163"+
		"\u0164\u0005t\u0000\u0000\u0164T\u0001\u0000\u0000\u0000\u0165\u0166\u0005"+
		"t\u0000\u0000\u0166\u0167\u0005o\u0000\u0000\u0167\u0168\u0005S\u0000"+
		"\u0000\u0168\u0169\u0005t\u0000\u0000\u0169\u016a\u0005r\u0000\u0000\u016a"+
		"\u016b\u0005i\u0000\u0000\u016b\u016c\u0005n\u0000\u0000\u016c\u016d\u0005"+
		"g\u0000\u0000\u016dV\u0001\u0000\u0000\u0000\u016e\u016f\u0005+\u0000"+
		"\u0000\u016fX\u0001\u0000\u0000\u0000\u0170\u0171\u0005-\u0000\u0000\u0171"+
		"Z\u0001\u0000\u0000\u0000\u0172\u0173\u0005*\u0000\u0000\u0173\\\u0001"+
		"\u0000\u0000\u0000\u0174\u0175\u0005/\u0000\u0000\u0175^\u0001\u0000\u0000"+
		"\u0000\u0176\u0177\u0005%\u0000\u0000\u0177`\u0001\u0000\u0000\u0000\u0178"+
		"\u0179\u0005&\u0000\u0000\u0179\u017a\u0005&\u0000\u0000\u017ab\u0001"+
		"\u0000\u0000\u0000\u017b\u017c\u0005|\u0000\u0000\u017c\u017d\u0005|\u0000"+
		"\u0000\u017dd\u0001\u0000\u0000\u0000\u017e\u017f\u0005!\u0000\u0000\u017f"+
		"f\u0001\u0000\u0000\u0000\u0180\u0181\u0005>\u0000\u0000\u0181\u0182\u0005"+
		"=\u0000\u0000\u0182h\u0001\u0000\u0000\u0000\u0183\u0184\u0005<\u0000"+
		"\u0000\u0184\u0185\u0005=\u0000\u0000\u0185j\u0001\u0000\u0000\u0000\u0186"+
		"\u0187\u0005=\u0000\u0000\u0187\u0188\u0005=\u0000\u0000\u0188l\u0001"+
		"\u0000\u0000\u0000\u0189\u018a\u0005!\u0000\u0000\u018a\u018b\u0005=\u0000"+
		"\u0000\u018bn\u0001\u0000\u0000\u0000\u018c\u018d\u0005>\u0000\u0000\u018d"+
		"\u018e\u0005>\u0000\u0000\u018ep\u0001\u0000\u0000\u0000\u018f\u0190\u0005"+
		"<\u0000\u0000\u0190\u0191\u0005<\u0000\u0000\u0191r\u0001\u0000\u0000"+
		"\u0000\u0192\u0193\u0005+\u0000\u0000\u0193\u0194\u0005+\u0000\u0000\u0194"+
		"t\u0001\u0000\u0000\u0000\u0195\u0196\u0005-\u0000\u0000\u0196\u0197\u0005"+
		"-\u0000\u0000\u0197v\u0001\u0000\u0000\u0000\u0198\u0199\u0005&\u0000"+
		"\u0000\u0199x\u0001\u0000\u0000\u0000\u019a\u019b\u0005|\u0000\u0000\u019b"+
		"z\u0001\u0000\u0000\u0000\u019c\u019d\u0005^\u0000\u0000\u019d|\u0001"+
		"\u0000\u0000\u0000\u019e\u019f\u0005~\u0000\u0000\u019f~\u0001\u0000\u0000"+
		"\u0000\u01a0\u01a1\u0005<\u0000\u0000\u01a1\u0080\u0001\u0000\u0000\u0000"+
		"\u01a2\u01a3\u0005>\u0000\u0000\u01a3\u0082\u0001\u0000\u0000\u0000\u01a4"+
		"\u01a5\u0005=\u0000\u0000\u01a5\u0084\u0001\u0000\u0000\u0000\u01a6\u01a8"+
		"\u0007\u0003\u0000\u0000\u01a7\u01a6\u0001\u0000\u0000\u0000\u01a8\u01a9"+
		"\u0001\u0000\u0000\u0000\u01a9\u01a7\u0001\u0000\u0000\u0000\u01a9\u01aa"+
		"\u0001\u0000\u0000\u0000\u01aa\u0086\u0001\u0000\u0000\u0000\u01ab\u01ad"+
		"\u0007\u0004\u0000\u0000\u01ac\u01ab\u0001\u0000\u0000\u0000\u01ad\u01ae"+
		"\u0001\u0000\u0000\u0000\u01ae\u01ac\u0001\u0000\u0000\u0000\u01ae\u01af"+
		"\u0001\u0000\u0000\u0000\u01af\u01b0\u0001\u0000\u0000\u0000\u01b0\u01b1"+
		"\u0006C\u0000\u0000\u01b1\u0088\u0001\u0000\u0000\u0000\n\u0000\u00ad"+
		"\u00b6\u00ba\u00c6\u00d1\u00d7\u00d9\u01a9\u01ae\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}
grammar guqin;

prog: (classdef | func | global_declarstat)*;
typepair: (INT | BOOL | STRING | ID) ID;
real_type: (INT | BOOL | STRING | ID);
args: (typepair (',' typepair*))?;
func: (INT | BOOL | STRING | VOID | ID) ID '(' args ')' '{' stat* '}';
construct_func: ID '()' '{' stat* '}';
classdef:
	CLASS ID '{' (typepair ';')
	| construct_func
	| func '}';
funcall: ID (expr (',' expr)*)?;
expr:
	INT_VALUE
	| STRING_VALUE
	| TRUE
	| FALSE
	| ID
	| THIS
	| funcall
	| ID '.' ID
	| ID '.' funcall
	| ID '[' INT ']'
	| newexpr
	| expr ADD expr
	| expr MINUS expr
	| expr MUL expr
	| expr DIV expr
	| expr MOD expr
	| '(' expr ')'
	| SAD expr
	| expr SAD
	| SMI expr
	| expr SMI
	| NOT expr
	| BNO expr
	| MINUS expr
	| expr EQ expr
	| expr UEQ expr
	| expr AND expr
	| expr OR expr
	| expr '?' expr ':' expr
	| assignexpr
	| format_string
	| GETSTRING '()'
	| GETINT '()'
	| TOSTRING '()'
	| ID '.' LENGTH '()'
	| ID '.' SUBSTRING '(' expr ',' expr ')'
	| ID '.' PARSEINT '()'
	| ID '.' ORD '()';
assignexpr: ID ASS expr;
format_string:
	'f' '"' (
		('{' (format_string | expr) '}')
		| ( '\\' '"' | ~'"')
	)* '"';
newexpr:
	NEW real_type '[]' '{' expr (',' expr)* '}'
	| NEW real_type ('()')?
	| NEW real_type ('[' INT ']')*;
assignstat: assignexpr ';';
global_declarstat:
	real_type ID ('=' expr)? (',' ID ('=' expr)?)* ';';
local_declarstat:
	real_type ID ('=' expr)? (',' ID ('=' expr)?)* ';';
innercontent: '{' (stat | expr)* '}' | (stat | expr);
conditstat: IF '(' expr ')' innercontent (ELSE innercontent)?;
whilestat: WHILE '{' (stat | expr)* '}';
forstat:
	FOR '(' (expr)? ';' (expr)? ';' (expr)? ')' innercontent;
returnstat: RETURN (expr)? ';';
contistat: CONTINUE ';';
breakstat: BREAK ';';
exprstat: expr ';';
stat:
	assignstat
	| local_declarstat
	| conditstat
	| whilestat
	| forstat
	| returnstat
	| contistat
	| breakstat;
LINE_COMMENT: '//' .*? '\r'? '\n' -> skip;
BLOCK_COMMENT: '/*' .*? '*/' -> skip;
STRING_VALUE: '"' ( '\\' ["] | ~["])* '"';
VOID: 'void';
BOOL: 'bool';
INT: 'int';
STRING: 'string';
NEW: 'new';
CLASS: 'class';
NULL: 'null';
TRUE: 'true';
FALSE: 'false';
THIS: 'this';
IF: 'if';
ELSE: 'else';
FOR: 'for';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
RETURN: 'return';
PRINT: 'print';
PRINTLN: 'println';
PRINTINT: 'printlnInt';
GETSTRING: 'getString';
GETINT: 'getInt';
TOSTRING: 'toString';
LENGTH: 'length';
SUBSTRING: 'substring';
PARSEINT: 'parseInt';
ORD: 'ord';
INT_VALUE: [0-9]+;
ID: [a-zA-Z][a-zA-Z0-9_]*;
AND: '&&';
OR: '||';
NOT: '!';
GEQ: '>=';
LEQ: '<=';
EQ: '==';
UEQ: '!=';
RSH: '>>';
RLH: '<<';
SAD: '++';
SMI: '--';
BAN: '&';
BOR: '|';
XOR: '^';
BNO: '~';
LE: '<';
GE: '>';
ASS: '=';
ADD: '+';
MINUS: '-';
MUL: '*';
DIV: '/';
MOD: '%';
WS: [ \r\n\t]+ -> skip;
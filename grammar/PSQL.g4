grammar PSQL;

parse: (select|execute) EOF;

error: UNEXPECTED_CHAR {throw new RuntimeException("UNEXPECTED_CHAR=" + $UNEXPECTED_CHAR.text);};

select: K_SELECT (fields) K_FROM (qtable) (K_WHERE WHERE)?;

execute: K_EXECUTE procedure args?;

procedure: ID;

args: (literal) (',' literal)*;

fields: STAR | (ID (',' ID)*);

qtable: (schema '.')? table ;

schema: (ID);

table: (ID);


literal
 : signed_number
 | NUMERIC_LITERAL
 | STRING_LITERAL
 | BLOB_LITERAL
 | K_NULL
 ;

signed_number
 : ( '+' | '-' )? NUMERIC_LITERAL
 ;

K_SELECT : S E L E C T;
K_EXECUTE : E X E C U T E;
K_FROM : F R O M;
K_WHERE : W H E R E;
K_NULL : N U L L;

WHERE: '1=0';
STAR: '*';

NUMERIC_LITERAL
 : DIGIT+ ( '.' DIGIT* )? ( E [-+]? DIGIT+ )?
 | '.' DIGIT+ ( E [-+]? DIGIT+ )?
 ;

STRING_LITERAL
 : '\'' ( ~'\'' | '\'\'' )* '\''
 ;


BLOB_LITERAL
 : '0' X ([0-9a-fA-F] [0-9a-fA-F])+
 ;

ID
 : '"' (~'"' | '""')+ '"'
 | [a-zA-Z] [a-zA-Z0-9]* // TODO check: needs more chars in set
 ;


WS : [ \t\r\n]+ -> skip ;
UNEXPECTED_CHAR : .  ;


fragment DIGIT : [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];


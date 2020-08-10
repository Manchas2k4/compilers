package lexer;

public class Tag {
	public final static int
		EOF 	= 65535,
		PROGRAM = 256, 	CONSTANT = 257,	VAR 	= 258,	BEGIN  	= 259,	END 	= 260,
		INTEGER = 261, 	REAL 	= 262,	BOOLEAN = 263,	STRING	= 264,	ASSIGN 	= 265, 	
		WRITELN = 266,	READLN	= 267, 	WHILE	= 268,	DO 		= 269,	REPEAT	= 270,
		UNTIL	= 271, 	FOR		= 272, 	TO 		= 273,	DOWNTO	= 274, 	IF 		= 275,
		THEN	= 276,	ELSE	= 277, 	NOT		= 278,	EQ		= 279,	NEQ		= 280,
		GE		= 281,	LE		= 282, 	FALSE 	= 283,	TRUE	= 284, 	DIV 	= 285,
		MOD		= 286,	AND		= 287, 	OR		= 288,	MINUS	= 289, 	ID		= 290,
		ERROR = 293;
}

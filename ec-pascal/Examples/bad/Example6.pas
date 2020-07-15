Program Example6;
Var 
	bool : Boolean;
	A, B : Integer;

Begin
	A = 10;
	B := False;
	bool := False;
	bool := (A = 10) OR (B = 10);
	Writeln(bool);
	bool := (A = 10) AND (B = 10);
	Writeln(bool);
End.


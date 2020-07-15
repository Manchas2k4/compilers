Program Example7;
Var
	Res : Integer;
	
Procedure Square(Index : Integer; Var Result : Integer);
Begin
	Result := Index * Index;
End;

Begin
	Writeln('The square of 5 is: ');
	Square(5, 10);
	Writeln(Res);
End.

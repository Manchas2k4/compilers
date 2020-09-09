Program Example2;
Var
    Num1, Num2, Sum : Integer;

Begin
	Writeln("Input number 1:");
	Readln(Num1);
	(* This is a Pascal comment *)
	Writeln("Input number 2:");
	Readln(Num2);
	Sum := Num1 + Num2 + 123 + 3.1415;
	Writeln(Sum);
	Readln;
End.

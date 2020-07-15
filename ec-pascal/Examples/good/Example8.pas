Program Lesson7_Program4;
Var SizeA, sizeB : Real;
	YN : String;
	unitS : String;

Function PythagorasFunc(A: Real; B: Real) : Real;
Begin
	PythagorasFunc := SQRT(A * A + B * B);
End;

Begin
	Repeat
		Writeln('Enter the size of side A : ');
		Readln(sizeA);
		Writeln('Enter the size of side B : ');
		Readln(sizeB);

		Repeat
			Writeln('metres or centimetres? Enter : [m or cm] ');
			Readln(unitS);
		Until (unitS = 'm') OR (unitS = 'cm');

		Writeln(PythagorasFunc(sizeA,sizeB),' ',unitS);
		Writeln();
		Writeln('Repeat? ');
		Readln(YN);
	Until (YN = 'N') or (YN = 'n');

End.

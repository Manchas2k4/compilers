Program Exampl5;
Var age : Integer;
             
Begin
	Repeat
		Writeln('Enter age (1 - 100): ');
		Readln(age);
		If (age < 1) 
			Writeln('Age cannot be less than 1...')
		Else If (age > 100) Then
			Writeln('Age cannot be greater than 100...');
	Until (age > 0) && (age <= 100); 
End.     

package lexer;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import errors.EOFException;

public class InputFile {
	public byte data[];
	public int size;
	public int position, lineNumber, columnNumber;
	
	@SuppressWarnings("resource")
	public InputFile(String filename) throws Exception {
		File file = null;
		BufferedInputStream bufferedInputStream = null;
		
		file = new File(filename);
		if (!file.exists() || !file.isFile()) throw new Exception();
		if (file.length() == 0) throw new Exception();
		
		size = (int) file.length();
		data = new byte[size];
		
		bufferedInputStream = new BufferedInputStream(new FileInputStream(file)); 
		bufferedInputStream.read(data, 0, size);
	}
	
	public char getChar() throws EOFException {
		char c;
		
		position++;
		
		if (isEof()) throw new EOFException();
		
		c = (char) data[position];
		
		if (c == '\n') {
			columnNumber = 1;
			lineNumber++;
		} else {
			columnNumber++;
		}
		
		return c;
	}
	
	public char peekChar() throws EOFException {
		if (isEof()) throw new EOFException();
		return ((char) data[position]);
	}
	
	public boolean isEof() {
		return (position >= size);
	}
	
	public boolean isEol() throws EOFException {
		return (isEof()  || peekChar() == '\n');
	}
}

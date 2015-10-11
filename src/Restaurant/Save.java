package Restaurant;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Save {
	private PrintWriter File;
	
	public Save( String file_name ) throws FileNotFoundException{
		this.File = new PrintWriter( file_name );
	}
	
	public void toFile( String input ){
		this.File.println( input );
	}
	
	public void Close(){
		this.File.close();
	}
}

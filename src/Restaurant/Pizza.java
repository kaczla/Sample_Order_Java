package Restaurant;
import java.util.ArrayList;

public class Pizza {
	private String Name;
	private ArrayList <String> Components = new ArrayList <String>();
	private String Out = "Undefined";
	
	public Pizza( String name, String[] components ){
		this.Name = name;
		for( String i: components ){
			this.Components.add( i );
		}		
		this.Out = this.Name + ": ";
		for( String i : this.Components ){
			this.Out += i + "; ";
		}
	}
	
	public Pizza( String name, ArrayList <String> components ){
		this.Name = name;
		for( String i: components ){
			this.Components.add( i );
		}		
		this.Out = this.Name + ": ";
		for( String i : this.Components ){
			this.Out += i + "; ";
		}
	}
	
	public Pizza( Pizza input ){
		this.Name = input.Name;
		this.Components = input.Components;
		this.Out = input.Out;
	}
	
	public String toString(){
		return this.Out;
	}
}

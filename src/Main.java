import Restaurant.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
	private static ArrayList <Pizza> PizzaList = new ArrayList <Pizza>();
	private static ArrayList <Pizza> PizzaUser = new ArrayList <Pizza>();
	private static ArrayList <String> PizzaComponents = new ArrayList <String>();
	private static ArrayList <Pizza> PizzaOrder = new ArrayList <Pizza>();
	private static String FileName = "order.txt"; 
	private static Scanner Input;
	private static String Msg = "";
	
	private static void Set(){
		//Main pizza:
		String name = "Margherita";
		String[] components1 = { "tomato sauce", "mozzarella" };
		PizzaList.add( new Pizza( name, components1 ) );
		
		name = "Funghi";
		String[] components2 = { "tomato sauce", "mozzarella", "mushrooms" };
		PizzaList.add( new Pizza( name, components2 ) );
		
		name = "Peperoni";
		String[] components3 = { "tomato sauce", "mozzarella", "peperoni" };
		PizzaList.add( new Pizza( name, components3 ) );
		
		name = "Napolitana";
		String[] components4 = { "tomato sauce", "anchovies", "olives", "capers" };
		PizzaList.add( new Pizza( name, components4 ) );
		
		name = "Hawaii";
		String[] components5 = { "tomato sauce", "mozzarella", "ham", "pineapple" };
		PizzaList.add( new Pizza( name, components5 ) );
		
		//User pizza:
		name = "User pizza";
		String[] user1 = { "tomato sauce", "mozzarella", "ham", "mushrooms" };
		PizzaUser.add( new Pizza( name, user1 ) );
		
		//Pizza components:
		PizzaComponents.add( "tomato sauce" );
		PizzaComponents.add( "mozzarella" );
		PizzaComponents.add( "mushrooms" );
		PizzaComponents.add( "peperoni" );
		PizzaComponents.add( "ham" );
		PizzaComponents.add( "olives" );
		PizzaComponents.add( "capers" );
		PizzaComponents.add( "pineapple" );
	}
	
	private static void PrintEnter(){
		System.out.println( "\n\n\n\n" );
	}
	
	private static void PrintMsg(){
		System.out.println( Msg );
	}
	
	private static void PrintList(){
		for( Pizza i : PizzaList ){
			System.out.println( "[" + PizzaList.indexOf( i ) + "] " + i );
		}
		for( Pizza i : PizzaUser ){
			System.out.println( "[" + ( PizzaList.size() + PizzaUser.indexOf( i ) ) + "] " + i );
		}
		System.out.println( "[" + ( PizzaList.size() + PizzaUser.size() ) + "] Create yours" );
		System.out.println( "[" + ( PizzaList.size() + PizzaUser.size() + 1 ) + "] EXIT" );
	}
	
	private static void PrintComponents(){
		for( int i = 0; i < PizzaComponents.size(); ++i ){
			System.out.println( "[" + i + "] " + PizzaComponents.get( i ) );
		}
		System.out.println( "[" + PizzaComponents.size() + "] EXIT" );
	}
	
	private static void PrintOrder(){
		if( PizzaOrder.size() > 0 ){
			System.out.println( "    Your order:" );
		}
		for( Pizza i : PizzaOrder ){
			System.out.println( "       " + i );
		}
	}
	
	private static void PrintChoiceText(){
		System.out.print( "Choice: " );
	}
	
	private static int ReadInt(){
		Input = new Scanner( System.in );
		return Integer.parseInt( Input.nextLine() );
	}
	
	private static void SaveIntoFile() throws FileNotFoundException{
		System.out.print( "Save into file: " + FileName );
		Save save = new Save( FileName );
		for( Pizza i : PizzaOrder ){
			save.toFile( i.toString() );
		}
		save.Close();
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		Set();		
		
		int input_choice;
		int input_choice_2;
		
		do{
			PrintEnter();
			PrintMsg();
			PrintList();
			PrintOrder();
			PrintChoiceText();
			input_choice = ReadInt();
			if( input_choice >= 0 ){
				if( input_choice < PizzaList.size() ){
					Msg = "Add pizza number: " + input_choice;
					PizzaOrder.add( new Pizza( PizzaList.get( input_choice ) ) );
				}
				else if( input_choice < ( PizzaList.size() + PizzaUser.size() ) ){
					Msg = "Add user pizza number: " + input_choice;
					PizzaOrder.add( new Pizza( PizzaUser.get( input_choice - PizzaList.size() ) ) );
				}
				else if( input_choice == PizzaList.size() + PizzaUser.size() ){
					Msg = "Choice what you want:";
					ArrayList <String> tmp_components = new ArrayList <String>();
					do{
						PrintEnter();
						PrintMsg();
						PrintComponents();
						if( tmp_components.size() > 0 ){
							System.out.print( "    Your components: " );
							for( int i = 0; i < tmp_components.size(); ++i ){
								System.out.print( tmp_components.get( i ) + "; " );
							}
							System.out.println();
						}
						PrintChoiceText();
						input_choice_2 = ReadInt();
						if( input_choice_2 >= 0 ){
							if( input_choice_2 < PizzaComponents.size() ){
								Msg = "Add components number: " + input_choice_2;
								tmp_components.add( PizzaComponents.get( input_choice_2 ) );
							}
							else if( input_choice_2 == PizzaComponents.size() ){
								Msg = "";
								if( tmp_components.size() > 0 ){
									PizzaUser.add( new Pizza( "User pizza", tmp_components ) );
									PizzaOrder.add( new Pizza( "User pizza", tmp_components ) );
								}
							}
							else{
								Msg = "Wrong number!";
							}
						}
						else{
							Msg = "Wrong number!";
						}
					}while( input_choice_2 != PizzaComponents.size() );
				}
				else if( input_choice == PizzaList.size() + PizzaUser.size() + 1 ){
					Msg = "Exit";
				}
				else{
					Msg = "Wrong number!";
				}
			}
			else{
				Msg = "Wrong number!";
			}
		}while( input_choice != ( PizzaList.size() + PizzaUser.size() + 1 ) );
		//PizzaOrder.add( new Pizza( PizzaList.get( 0 ) ) );
		//System.out.print( PizzaOrder.get( 0 ).toString() );
		
		SaveIntoFile();		
	}
}

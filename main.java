import java.util.Locale;
import java.util.Scanner;
import java.io.*;

public class main {

	private static final Locale A = null;
	private static final Locale a = null;

	private static final Locale S = null;
	private static final Locale s = null;

	private static final Locale G = null;
	private static final Locale g = null;

	private static final Locale E = null;
	private static final Locale e = null;

	String input;
	Scanner reader = new Scanner(System.in);
	String c = reader.next()

	while(input!='a'||input!='A')
	{
		input.toUpperCase(A);
		input.toLowerCase(a);
		switch(input)
		{
		
		System.out.println("Enter filename \n");
		reader.next();
		
		
		System.out.println("Enter Semester and year \n");
		reader.next();
		
		System.out.println("Enter course number \n");
		reader.next();
		
		System.out.println("The number of students whose data is read equals: "+ input);
		reader.next();
	break;	
		}}

	while(input!='s'||input!='S')
	{
		switch(input)
		{
		input.toUpperCase(S);
		input.toLowerCase(s);
		System.out.println("Enter display student ID \n");
		reader.next();

		System.out.println("Enter filename to which data will be exported \n");
		reader.next();
		break;
	}}

	while(input!='g'||input!='G')
	{
		switch(input)
		{
		input.toUpperCase(G);
		input.toLowerCase(g);

		System.out.println("Enter the course number \n");
		reader.next();

		System.out.println("Enter the semester and year \n");
		reader.next();

		System.out.println("Enter e or E to exit the program \n");
		reader.next();
		break;
		}}

	while(input!='e'||input!='E')
	{
		switch(input)
		{
		input.toUpperCase(E);
		input.toLowerCase(e);
		System.out.println("Exiting the program");
		System.exit(0);
		break;
		}}

}

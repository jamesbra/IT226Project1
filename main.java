import java.util.Scanner;
import java.io.*;

public class input {

	public static void main(String[] args){

	// DataIntegration fetchData;
	String input = "";
	char choice = 0;
	Scanner reader = new Scanner(System.in);
	String userID;
	String courseNumber;
	String year;
	String semester;


	while(choice!='e')
	{
		
		while (input.length() > 1 || input.length()==0){
		System.out.println("Please make your selection:");
		System.out.println("S or s: Add data to repository");
		System.out.println("G or g: Export student data to file");
		System.out.println("E or e: Exit program");
		input = reader.next();
		choice = input.toLowerCase().charAt(0);
		}

		

		switch (choice) {

		case 'a':
			System.out.println("Enter file name");
			input = reader.next();

			System.out.println("Enter semester");
			semester = reader.next();

			System.out.println("Enter year");
			year = reader.next();

			System.out.println("Enter course number");
			courseNumber = reader.next();
			//fetchData.compileData(input, semester, year, courseNumber);
			break;

		case 's':
			System.out.println("Enter student ID");
			userID = reader.next();

			System.out.println("Enter output file name");
			input = reader.next();
			//fetchData.outputFile(userID, input);
			break;

		case 'g':
			System.out.println("Enter course number");
			courseNumber = reader.next();

			System.out.println("Enter semester");
			semester = reader.next();

			System.out.println("Enter year");
			year = reader.next();
			int[] gradeList = new int[5];
			//gradeList = fetchData.gradeSearch(courseNumber, semester, year);
			break;

		default:
			break;
		}
	}
	
}
}

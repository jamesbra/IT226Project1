package edu.it226.isu;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		// DataIntegration fetchData;
		String fileName;
		char choice = 0;
		String input;
		String semester;
		String year;
		String userID;
		String courseNumber;
		Scanner kbin = new Scanner(System.in);

		DataIntegration dataPlease = new DataIntegration();
		dataPlease.compileData("437-fall-2002.csv", "fall", "2002");
		
		while (choice != 'e')
		{
			input = "";
			while (input.length() > 1 || input.length() == 0)
			{
				System.out.println("Please make your selection:");
				System.out.println("S or s: Add data to repository");
				System.out.println("G or g: Export student data to file");
				System.out.println("E or e: Exit program");
				input = kbin.next();
				input = input.toLowerCase();
				choice = input.charAt(0);
			}
			// switch statement
			switch (choice)
			{
			case 'a':
				System.out.println("Enter file name");
				fileName = kbin.next();
				System.out.println("Enter semester");
				semester = kbin.next();
				System.out.println("Enter year");
				year = kbin.next();
				System.out.println("Enter course number");
				courseNumber = kbin.next();
				// fetchData.compileData(fileName,semester,year,courseNumber);
				break;

			case 's':
				System.out.println("Enter student ID");
				userID = kbin.next();
				System.out.println("Enter output file name");
				fileName = kbin.next();
				// fetchData.outputFile(userID,fileName);
				break;

			case 'g':
				System.out.println("Enter course number");
				courseNumber = kbin.next();
				System.out.println("Enter semester");
				semester = kbin.next();
				System.out.println("Enter year");
				year = kbin.next();
				int[] gradeList = new int[5];
				// gradeList =
				// fetchData.gradeSearch(courseNumber,semester,year);
				break;

			default:
				break;
			}
		}
	}

}

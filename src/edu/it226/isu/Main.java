package edu.it226.isu;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		DataIntegration fetchData = new DataIntegration();
		String fileName;
		char choice = 0;
		String input;
		String semester;
		String year;
		String userID;
		String courseNumber;
		Scanner kbin = new Scanner(System.in);
		fetchData.compileData("380-fall-2002.csv", "fall", "2002", "it380");
		fetchData.compileData("437-fall-2002.csv", "fall", "2002", "it437");

		while (choice != 'e')
		{
			input = "";
			while (input.length() > 1 || input.length() == 0)
			{
				System.out.println("Please make your selection:");
				System.out.println("A or a: Add data to repository");
				System.out.println("S or s: Export student data to file");
				System.out.println("G or g: Export grade array");
				System.out.println("E or e: Exit program");
				input = kbin.next();
				input = input.toLowerCase();
				choice = input.charAt(0);

			}
			input = "";
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
				fetchData.compileData(fileName, semester, year, courseNumber);
				break;

			case 's':
				System.out.println("Enter student ID");
				userID = kbin.next();
				System.out.println("Enter output file name");
				fileName = kbin.next();
				fetchData.outputFile(userID, fileName);
				break;

			case 'g':
				System.out.println("Enter course number");
				courseNumber = kbin.next();
				System.out.println("Enter semester");
				semester = kbin.next();
				System.out.println("Enter year");
				year = kbin.next();
				if (courseNumber.equals("none") && semester.equals("none") && year.equals("none")){
					return;
				}
				int[] gradeList = new int[5];
				gradeList = fetchData.gradeSearch(courseNumber, semester, year);
				System.out.println("A: " + gradeList[0]);
				System.out.println("B: " + gradeList[1]);
				System.out.println("C: " + gradeList[2]);
				System.out.println("D: " + gradeList[3]);
				System.out.println("E: " + gradeList[4]);

				break;

			default:
				break;
			}
		}
	}

}

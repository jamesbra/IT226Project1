package edu.it226.isu;

import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		DataIntegration fetchData = new DataIntegration();
		String fileName;
		char choice = 0;
		String input = "";
		String semester;
		String year;
		String userID;
		String courseNumber;
		Scanner kbin = new Scanner(System.in);
		fetchData.compileData("380-fall-2002.csv", "fall", "2002", "it380");
		fetchData.compileData("437-fall-2002.csv", "fall", "2002", "it 437");
		fetchData.compileData("437-fall-2003.csv", "fall", "2003", "it 437");

		while (choice != 'e')
		{
			while (input.length() > 1 || input.length() == 0)
			{
				System.out.println("Menu:\n");
				System.out.println("A or a: Add data to repository");
				System.out.println("S or s: Export student data to file");
				System.out.println("G or g: Export grade compilation");
				System.out.println("E or e: Exit program\n");
				System.out.println("Please make your selection:");

				input = kbin.next();
				input = input.toLowerCase();
				choice = input.charAt(0);

			}
			input = "";
			switch (choice)
			{
			case 'a':
				kbin.nextLine();
				System.out.println("Enter file name (With .csv file extension)");
				fileName = kbin.nextLine();
				System.out.println("Enter semester");
				semester = kbin.nextLine();
				System.out.println("Enter year");
				year = kbin.nextLine();
				System.out.println("Enter course number");
				courseNumber = kbin.nextLine();
				fetchData.compileData(fileName, semester, year, courseNumber);
				break;

			case 's':
				kbin.nextLine();
				System.out.println("Enter student ID");
				userID = kbin.nextLine();
				System.out.println("Enter output file name (With .csv file extension)");
				fileName = kbin.nextLine();
				fetchData.outputFile(userID, fileName);
				break;

			case 'g':
				kbin.nextLine();
				System.out.println("Enter course number");
				courseNumber = kbin.nextLine();
				System.out.println("Enter semester");
				semester = kbin.nextLine();
				System.out.println("Enter year");
				year = kbin.nextLine();
				if (courseNumber.equals("none") && semester.equals("none") && year.equals("none")){
					break;
				}
				int[] gradeList = new int[5];
				gradeList = fetchData.gradeSearch(courseNumber, semester, year);
				if (gradeList == (null)){
					break;
				}
				System.out.println("A: " + gradeList[0]);
				System.out.println("B: " + gradeList[1]);
				System.out.println("C: " + gradeList[2]);
				System.out.println("D: " + gradeList[3]);
				System.out.println("F: " + gradeList[4]);

				break;

			default:
				break;
			}
		}
	}

}

package edu.it226.isu;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataIntegration
{
	private ArrayList<Student> studentList;
	private StringTokenizer stk;
	private Scanner readFile;
	private File myFile;

	public void compileData(String fileName, String semester, String year)
	{
		CharSequence ch1 = "id";
		try
		{
			myFile = new File(fileName);
			readFile = new Scanner(myFile);
		}
		catch (Exception e)
		{
			System.out.println("Error opening file: closing program");
			System.exit(1);
		}
		while (readFile.hasNextLine())
		{
			stk = new StringTokenizer(readFile.nextLine(), ",", false);
			while (stk.hasMoreTokens())
			{
				String token = stk.nextToken();

				// if (token.contains("id")))
			}
		}
	}

	public void outputFile(String userID, String fileName)
	{
		PrintWriter out = null;
		try
		{
			myFile = new File(fileName);
			out = new PrintWriter(myFile);
		}
		catch (Exception e)
		{
			System.out.println("Error creating output file: closing program");
			System.exit(1);
		}

		for (int i = 0; i < studentList.size(); i++)
		{
			if (userID.equals(studentList.get(i).getUserID()))
			{
				out.println(studentList.get(i).printCourses());
				break;
			}
			if (i == studentList.size() - 1)
			{
				System.out.println("Could not find student");
			}
		}

		out.close();
	}

	// Need to create logic for if course number is missing and if the
	// semester/year is missing
	public int[] gradeSearch(String course, String semester, String year)
	{
		int[] grades = new int[5];
		for (int i = 0; i < studentList.size(); i++)
		{
			
			if (studentList.get(i).tookCourse(course, semester, year))
			{
				char grade = studentList.get(i)
						.findCourse(course, semester, year).getLetterGrade();
				switch (grade)
				{
				case 'A':
					grades[0]++;
				case 'B':
					grades[1]++;
				case 'C':
					grades[2]++;
				case 'D':
					grades[3]++;
				default:
					grades[4]++;

				}
			}
			else if (course.equals("")){
				studentList.get(i).findCourse(semester, year);
			}
			else if (semester.equals("") && year.equals("")){
					studentList.get(i).findCourse(semester, year);
			}
		
		}
		return grades;

	}

}

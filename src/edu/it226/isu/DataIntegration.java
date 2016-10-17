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

	public void compileData(String fileName)
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

	public int[] gradeSearch(String course, String semester, String year)
	{
		int[] grades = new int[5];
		for (int i = 0; i < studentList.size(); i++)
		{
			if (studentList.get(i).tookCourse(course, semester, year))
			{
				double grade = studentList.get(i)
						.findCourse(course, semester, year).getOverallGrade();
				if (grade >= 90)
				{
					grades[0]++;
				}
				else if (grade >= 80)
				{
					grades[1]++;
				}
				else if (grade >= 70)
				{
					grades[2]++;
				}
				else if (grade >= 60)
				{
					grades[3]++;
				}
				else
				{
					grades[4]++;
				}
			}
		}
		return grades;

	}

}

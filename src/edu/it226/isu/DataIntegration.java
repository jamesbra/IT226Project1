package edu.it226.isu;

import java.io.File;
import java.io.FileNotFoundException;
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
		catch (FileNotFoundException e)
		{
			System.out.println("File not found! Returning to menu");
			return;
		}
		ArrayList<String> master = new ArrayList<String>();
		ArrayList<String> current = new ArrayList<String>();
		stk = new StringTokenizer(readFile.nextLine(), ",", false);

		while (readFile.hasNext())
		{
			master.add(readFile.next());
		}

		while (readFile.hasNextLine())
		{
			stk = new StringTokenizer(readFile.nextLine(), ",", false);
			current = new ArrayList<String>();
			while (stk.hasMoreTokens())
			{
				String token = stk.nextToken();
				current.add(token);

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
			System.out.println("Error creating output file: returning to menu");
			return;
		}

		for (int i = 0; i < studentList.size(); i++)
		{
			if (userID.equals(studentList.get(i).getUserID()))
			{
				out.print(studentList.get(i).printCourses());
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

			if (semester.length() == 0 && year.length() == 0)
			{
				ArrayList<CourseData> coursesForCurrentStudent = studentList
						.get(i).findCourseOverAllSemesters(course);
				for (int j = 0; j < coursesForCurrentStudent.size(); j++)
				{
					char grade = coursesForCurrentStudent.get(j)
							.getLetterGrade();

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

			}
			// ALL COURSES THAT FIT INSIDE YEAR AND SEMESTER ARE RETURNED AND
			// ADDED TO GRADE COUNT
			else if (course.length() == 0)
			{
				ArrayList<CourseData> coursesWithinYearAndSemester = studentList
						.get(i)
						.findCoursesWithinYearAndSemester(semester, year);
				for (int j = 0; j < coursesWithinYearAndSemester.size(); i++)
				{
					char grade = coursesWithinYearAndSemester.get(j)
							.getLetterGrade();

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
			}
			else if (studentList.get(i).tookCourse(course, semester, year))
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

		}
		return grades;

	}

}

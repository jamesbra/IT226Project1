package edu.it226.isu;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class DataIntegration
{
	private ArrayList<Student> studentList;
	private ArrayList<String> masterList;
	private StringTokenizer stk;
	private Scanner readFile;
	private File myFile;
	private int[] commentSpots;
	private int[] assignmentSpots;
	private int[] nameSpots;
	private int idSpot;
	private int total;
	private int letterGrade;

	public DataIntegration()
	{
		masterList = new ArrayList();
		studentList = new ArrayList();
		commentSpots = new int[0];
		assignmentSpots = new int[0];
		nameSpots = new int[0];
		idSpot = -1;
		total = -1;
		letterGrade = -1;

	}

	public void compileData(String fileName, String semester, String year,
			String courseNumber)
	{
		String[] studentArray;
		boolean masterMade = false;

		try
		{
			myFile = new File(fileName);
			readFile = new Scanner(myFile);
		}
		catch (Exception e)
		{
			System.out.println("Error opening file: Returning to menu");
			return;
		}
		while (readFile.hasNextLine())
		{
			String[] tokens = readFile.nextLine()
					.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);

			if (!masterMade)
			{
				createMaster(tokens);
				masterMade = true;
			}

			studentArray = new String[masterList.size()];
			findKeywords();

			while (readFile.hasNextLine())
			{
				tokens = readFile.nextLine()
						.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				for (int i = 0; i < masterList.size(); i++)
				{
					studentArray[i] = tokens[i];
				}
				Student stu = createStudent(studentArray, nameSpots, idSpot);
				CourseData cd = createCourseData(studentArray, assignmentSpots,
						commentSpots, total, letterGrade, semester, year,
						courseNumber);

				stu.addCourse(cd);
				studentList.add(stu);
			}
		}

		masterList = new ArrayList();
		commentSpots = new int[0];
		assignmentSpots = new int[0];
		nameSpots = new int[0];
		idSpot = -1;
		total = -1;
		letterGrade = -1;
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

	public int[] gradeSearch(String course, String semester, String year)
	{
		int[] grades = new int[5];
		for (int i = 0; i < studentList.size(); i++)
		{

			if (semester.equals("none") && year.equals("none"))
			{
				ArrayList<CourseData> coursesForCurrentStudent = studentList
						.get(i).findCourseOverAllSemesters(course);
				if (coursesForCurrentStudent.size() == 0)
				{
					System.out.println(
							"Please enter a valid course number! Returning to menu.\n");
					return null;
				}
				for (int j = 0; j < coursesForCurrentStudent.size(); j++)
				{
					char grade = coursesForCurrentStudent.get(j)
							.getLetterGrade();
					grades = gradeArrayUpdater(grade, grades);
					grade = 0;
				}

			}
			// ALL COURSES THAT FIT INSIDE YEAR AND SEMESTER ARE RETURNED AND
			// ADDED TO GRADE COUNT
			else if (course.equals("none"))
			{
				ArrayList<CourseData> coursesWithinYearAndSemester = studentList
						.get(i)
						.findCoursesWithinYearAndSemester(semester, year);
				if (coursesWithinYearAndSemester.size() == 0)
				{
					System.out.println(
							"Semester and year not found. Please enter a valid semester and year! Returning to menu.\n");
					return null;
				}
				for (int j = 0; j < coursesWithinYearAndSemester.size(); j++)
				{
					char grade = coursesWithinYearAndSemester.get(j)
							.getLetterGrade();
					grades = gradeArrayUpdater(grade, grades);
					grade = 0;
				}
			}
			else if (studentList.get(i).tookCourse(course, semester, year))
			{
				char grade = studentList.get(i)
						.findCourse(course, semester, year).getLetterGrade();
				grades = gradeArrayUpdater(grade, grades);
				grade = 0;
			}
			else
			{
				System.out.println(
						"One or more values provided are not found! Returning to menu.\n");
				return null;
			}

		}
		return grades;
	}

	private int[] gradeArrayUpdater(char grade, int[] grades)
	{
		switch (grade)
		{
		case 'A':
			grades[0]++;
			break;
		case 'B':
			grades[1]++;
			break;
		case 'C':
			grades[2]++;
			break;
		case 'D':
			grades[3]++;
			break;
		default:
			grades[4]++;

		}
		return grades;
	}

	private void createMaster(String[] tokens)
	{
		for (String t : tokens)
		{
			masterList.add(t);
		}
	}

	private void findKeywords()
	{
		String temp;
		int size;
		for (int i = 0; i < masterList.size(); i++)
		{
			temp = masterList.get(i).toLowerCase();

			if (temp.contains("comment"))
			{
				size = commentSpots.length;
				commentSpots = Arrays.copyOf(commentSpots, size + 1);
				commentSpots[size] = i;
			}

			else if (temp.contains("name"))
			{
				size = nameSpots.length;
				if (nameSpots.length == 1)
				{
					if (temp.contains("first"))
					{
						int move;
						nameSpots = Arrays.copyOf(nameSpots, size + 1);
						move = nameSpots[0];
						nameSpots[1] = move;
						nameSpots[0] = i;
					}

					else
					{
						nameSpots = Arrays.copyOf(nameSpots, size + 1);
						nameSpots[size] = i;
					}
				}

				else
				{
					nameSpots = Arrays.copyOf(nameSpots, size + 1);
					nameSpots[size] = i;
				}
			}

			else if (temp.contains("id") && !temp.contains("midterm"))
			{
				idSpot = i;
			}

			else if (temp.contains("total"))
			{
				total = i;
			}

			else if (temp.contains("grade"))
			{
				letterGrade = i;
			}

			else
			{
				size = assignmentSpots.length;
				assignmentSpots = Arrays.copyOf(assignmentSpots, size + 1);
				assignmentSpots[size] = i;
			}
		}
	}

	private Student createStudent(String[] studentArray, int[] nameSpots,
			int id)
	{
		Student stud;

		if (nameSpots.length == 2)
			stud = new Student(studentArray[nameSpots[0]],
					studentArray[nameSpots[1]], studentArray[id]);
		else
			stud = new Student(studentArray[nameSpots[0]], studentArray[id]);

		return stud;
	}

	private CourseData createCourseData(String[] studentArray,
			int[] assignmentSpots, int[] commentSpots, int total,
			int letterGrade, String semester, String year, String courseNumber)
	{
		CourseData cd = new CourseData();
		Assignments assign;
		String assignTitle;
		String assignComment = null;
		double grade;
		cd.setSemester(semester);
		cd.setCourseNumber(courseNumber);
		cd.setYear(year);
		for (int i = 0; i < assignmentSpots.length; i++)
		{
			assignTitle = masterList.get(assignmentSpots[i]);
			grade = Double.parseDouble(studentArray[assignmentSpots[i]]);

			for (int x = 0; x < commentSpots.length; x++)
			{
				if (masterList.get(commentSpots[x]).contains(assignTitle))
				{
					assignComment = studentArray[commentSpots[x]];
				}
			}

			if (assignComment == (null))
			{
				assign = new Assignments(assignTitle, grade);
			}
			else
			{
				assign = new Assignments(assignTitle, assignComment, grade);
			}

			cd.addAssignment(assign);
			assignComment = null;
		}

		cd.addTotalGrade(Double.parseDouble(studentArray[total]));
		cd.addLetterGrade(studentArray[letterGrade].charAt(0));

		return cd;
	}
}

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
	
	public void compileData(String fileName, String semester, String year)
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
			System.out.println("Error opening file: closing program");
			System.exit(1);
		}
		while (readFile.hasNextLine())
		{
			String[] tokens = readFile.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			
			if(!masterMade)
			{
				createMaster(tokens);
				masterMade = true;
			}
			
			studentArray = new String[masterList.size()];
			findKeywords();
			
			while (readFile.hasNextLine())
			{
				tokens = readFile.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				for(int i = 0; i < masterList.size(); i++)
				{
					studentArray[i] = tokens[i];
				}
				Student stu = createStudent(studentArray, nameSpots, idSpot);
				CourseData cd = createCourseData(studentArray, assignmentSpots, commentSpots, total, letterGrade);
				
				stu.addCourse(cd);
				studentList.add(stu);
			}
		}
		
		System.out.println(studentList); //TESTING
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
	
	private void createMaster(String[] tokens)
	{	
		for(String t : tokens)
		{
			masterList.add(t);
		}
	}
	
	private void findKeywords()
	{
		String temp;
		int size;
		for(int i = 0; i < masterList.size(); i++)
		{
			temp = masterList.get(i).toLowerCase();
			
			if(temp.contains("comment"))
			{
				size = commentSpots.length;
				commentSpots = Arrays.copyOf(commentSpots, size + 1);
				commentSpots[size] = i;
			}
			
			else if(temp.contains("name"))
			{
				size = nameSpots.length;
				if(nameSpots.length == 1)
				{
					if(temp.contains("first"))
					{
						int move;
						nameSpots = Arrays.copyOf(nameSpots, size+1);
						move = nameSpots[0];
						nameSpots[1] = move;
						nameSpots[0] = i;
					}
					
					else
					{
						nameSpots = Arrays.copyOf(nameSpots, size+1);
						nameSpots[size] = i;
					}
				}
				
				else
				{
					nameSpots = Arrays.copyOf(nameSpots, size + 1);
					nameSpots[size] = i;
				}
			}
			
			else if(temp.contains("id") && !temp.contains("midterm"))
			{
				idSpot = i;
			}
			
			else if(temp.contains("total"))
			{
				total = i;
			}
			
			else if(temp.contains("grade"))
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
	
	private Student createStudent(String[] studentArray, int[] nameSpots, int id)
	{
		Student stud;
		
		if(nameSpots.length == 2)
			stud = new Student(studentArray[nameSpots[0]], studentArray[nameSpots[1]], studentArray[id]);
		else
			stud = new Student(studentArray[nameSpots[0]], studentArray[id]);
		
		return stud;
	}
	
	private CourseData createCourseData(String[] studentArray, int[]assignmentSpots, int[]commentSpots, int total, int letterGrade)
	{
		CourseData cd = new CourseData();
		Assignments assign;
		String assignTitle;
		String assignComment = null;
		double grade;
		
		for(int i = 0; i < assignmentSpots.length; i++)
		{
			assignTitle = masterList.get(assignmentSpots[i]);
			grade = Double.parseDouble(studentArray[assignmentSpots[i]]);
			
			for(int x = 0; x < commentSpots.length; x++)
			{
				if(masterList.get(commentSpots[x]).contains(assignTitle))
				{
					assignComment = studentArray[commentSpots[x]];
				}
			}
			
			if(assignComment == (null))
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

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
	
	public DataIntegration()
	{
		masterList = new ArrayList();
		studentList = new ArrayList();
	}
	
	public void compileData(String fileName, String semester, String year)
	{
		String[] studentArray;
		int[] commentSpots = new int[0];
		int[] assignmentSpots = new int[0];
		int[] nameSpots = new int[0];
		int idSpot = -1;
		int total = -1;
		int letterGrade = -1;
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
			//stk = new StringTokenizer(readFile.nextLine(), ",", false);
			String[] tokens = readFile.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
			System.out.println(java.util.Arrays.toString(tokens));
			
			if(!masterMade)
			{
				createMaster(tokens);
				masterMade = true;
			}
			
			studentArray = new String[masterList.size()];
			findKeywords(commentSpots, assignmentSpots, nameSpots, idSpot, total, letterGrade);
			
			while (readFile.hasNextLine())
			{
				tokens = readFile.nextLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1);
				for(int i = 0; i < masterList.size(); i++)
				{
					studentArray[i] = tokens[i];
				}
				
				System.out.println(java.util.Arrays.toString(studentArray));
				
				Student stu = createStudent(studentArray, nameSpots, idSpot);
				CourseData cd = createCourseData(studentArray, assignmentSpots, commentSpots, total, letterGrade);
				stu.addCourse(cd);
				studentList.add(stu);
				
				System.out.println(stu);
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
		/*
		String header = stk.nextToken();
		String temp = header;
		while(!temp.toLowerCase().contains("grade"))
		{
			masterList.add(temp);
			header = stk.nextToken();
			temp = header;
		}
		*/
		
		for(String t : tokens)
		{
			masterList.add(t);
		}
		//masterList.add(temp);
		System.out.println(masterList);
		//System.out.println(temp);
	}
	
	private void findKeywords(int[] comment, int[] assignment, int[] name, int id, int total, int letterGrade)
	{
		String temp;
		int size;
		for(int i = 0; i < masterList.size(); i++)
		{
			temp = masterList.get(i).toLowerCase();
			
			if(temp.contains("comment"))
			{
				size = comment.length;
				comment = Arrays.copyOf(comment, size + 1);
				comment[size] = i;
			}
			
			else if(temp.contains("name"))
			{
				size = name.length;
				if(name.length == 1)
				{
					if(temp.contains("first"))
					{
						int move;
						name = Arrays.copyOf(name, size+1);
						move = name[0];
						name[1] = move;
						name[0] = i;
					}
					
					else
					{
						name = Arrays.copyOf(name, size+1);
						name[size] = i;
					}
				}
				
				else
				{
					name = Arrays.copyOf(name, size + 1);
					name[size] = i;
				}
			}
			
			else if(temp.contains("id"))
			{
				id = i;
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
				size = assignment.length;
				assignment = Arrays.copyOf(assignment, size + 1);
				assignment[size] = i;
			}
		}
	}
	
	private Student createStudent(String[] studentArray, int[] nameSpots, int id)
	{
		Student stud;
		System.out.println(java.util.Arrays.toString(nameSpots));
		int x = nameSpots[0];
		
		if(nameSpots.length == 2)
			stud = new Student(masterList.get(nameSpots[0]), masterList.get(nameSpots[1]), studentArray[id]);
		else
			stud = new Student(masterList.get(nameSpots[0]), studentArray[id]);
		
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
				if(masterList.get(commentSpots[i]).contains(assignTitle))
				{
					assignComment = studentArray[commentSpots[i]];
				}
			}
			
			if(assignComment.equals(null))
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

package edu.it226.isu;

import java.util.ArrayList;

public class CourseData
{

	private String courseName;
	private String semester;
	private String year;
	private char letterGrade;
	private double overallGrade;
	private ArrayList<Assignments> assignmentData;

	public CourseData()
	{
		assignmentData = new ArrayList();
	}
	
	public String getCourse()
	{
		return courseName;
	}

	public String getSemester()
	{
		return semester;
	}

	public String getYear()
	{
		return year;
	}

	public char getLetterGrade()
	{
		return letterGrade;
	}

	/**
	 * This method will return the assignment title and grade
	 */
	@Override
	public String toString()
	{
		String output = "";
		for (int i = 0; i < assignmentData.size(); i++)
		{
			output += assignmentData.get(i).getTitle() + "," + assignmentData.get(i).getGrade() + ",";
		}
		return output;
	}

	/**
	 * This method will create the top columns for saving student data or pressing 'S'
	 * @return
	 */
	public String constructColumns()
	{
		String output = "";
		for (int i = 0; i < assignmentData.size(); i++){
			output += courseName + "-" + semester + "-" + year + "-" + assignmentData.get(i).getTitle() + ",";
		}
		output += courseName + "-" + semester + "-" + year + "-" + "Letter Grade" + ",";
		return output;
	}
	
	public void addAssignment(Assignments assign)
	{
		assignmentData.add(assign);
	}
	
	public void addTotalGrade(double total)
	{
		overallGrade = total;
	}
	
	public void addLetterGrade(char grade)
	{
		letterGrade = grade;
	}
}

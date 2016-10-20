package edu.it226.isu;

import java.util.ArrayList;

public class CourseData
{

	private String courseName;
	private String semester;
	private String year;
	private char letterGrade;
	private double overallGrade;
	private ArrayList<Assignments> assignmentList;

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
	public String assignmentGradeOutput()
	{
		String output = "";
		for (int i = 0; i < assignmentList.size(); i++)
		{
			output += assignmentList.get(i).getGrade() + ",";
		}
		output += letterGrade + "\n";
		return output;
	}

	/**
	 * This method will create the top columns for saving student data or
	 * pressing 'S'
	 * 
	 * @return
	 */
	public String constructColumns()
	{
		String output = "";
		for (int i = 0; i < assignmentList.size(); i++)
		{
			output += courseName + "-" + semester + "-" + year + "-"
					+ assignmentList.get(i).getTitle() + ",";
		}
		output += courseName + "-" + semester + "-" + year + "-"
				+ "Letter Grade" + ",";
		return output;
	}

}

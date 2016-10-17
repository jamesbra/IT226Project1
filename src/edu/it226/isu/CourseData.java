package edu.it226.isu;

import java.util.ArrayList;

public class CourseData
{

	private String courseName;
	private String semester;
	private String year;
	private double overallGrade;
	private ArrayList<Assignments> assignmentData;
	
	public String getCourse()
	{
		return courseName;
	}
	public String getSemester()
	{
		return semester;
	}
	public String getYear(){
		return year;
	}
	public double getOverallGrade()
	{
		return overallGrade;
	}
	@Override
	public String toString(){
		return courseName;
		
	}

}

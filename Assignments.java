package edu.it226.isu;

public class Assignments
{
	private String comment;
	private String title;
	private double grade;
	
	public Assignments()
	{
		
	}
	
	public Assignments(String title, String comment, double grade)
	{
		this.title = title;
		this.comment = comment;
		this.grade = grade;
	}
	
	public Assignments(String title, double grade)
	{
		this.title = title;
		this.grade = grade;
	}
	
	public String getTitle(){
		return title;
	}
	public double getGrade(){
		return grade;
	}
	@Override
	public String toString()
	{
		return printData();
	}

	private String printData()
	{
		return null;
	}
}

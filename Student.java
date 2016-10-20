package edu.it226.isu;

import java.util.ArrayList;

public class Student
{
	private ArrayList<CourseData> coursesTaken;
	private String name;
	private String userID;

	public Student()
	{
		coursesTaken = new ArrayList();
	}

	public Student(String firstName, String lastName, String userID)
	{
		name = lastName + ", " + firstName;
		this.userID = userID;
		coursesTaken = new ArrayList();
	}
	
	public Student(String name, String userID)
	{
		this.name = name;
		this.userID = userID;
		coursesTaken = new ArrayList();
	}
	
	public void addCourse(CourseData courseName)
	{
		coursesTaken.add(courseName);
	}

	public String getName()
	{
		return name;
	}

	public String getUserID()
	{
		return userID;
	}

	public String printCourses()
	{
		String data = userID;
		for (int i = 0; i< coursesTaken.size(); i++){
			data += coursesTaken.get(i).constructColumns() + ",";
		}
		data += "\n";
		for (int i = 0; i < coursesTaken.size(); i++)
		{
			data += coursesTaken.get(i).toString()
					+ "\n";
		}
		return data;
	}

	public boolean tookCourse(String courseName, String semester, String year)
	{
		for (int i = 0; i < coursesTaken.size(); i++)
		{
			if (courseName.equals(coursesTaken.get(i).getCourse())
					&& semester.equals(coursesTaken.get(i).getSemester())
					&& year.equals(coursesTaken.get(i).getYear()))
			{
				return true;
			}
		}
		return false;
	}

	public CourseData findCourse(String courseName, String semester, String year)
	{
		for (int i = 0; i < coursesTaken.size(); i++)
		{
			if (courseName.equals(coursesTaken.get(i).getCourse())
					&& semester.equals(coursesTaken.get(i).getSemester())
					&& year.equals(coursesTaken.get(i).getYear()))
			{
				return coursesTaken.get(i);
			}
		}
		return null;
	}

	public boolean findCourse(String semester, String year)
	{
		// TODO Auto-generated method stub
		return false;
	}
	
	public String toString()
	{
		return name + " " + userID;
	}
}

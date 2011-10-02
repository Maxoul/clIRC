package irc;

import java.util.Vector;

public class Alias
{
	public Alias(String name, String color)
	{
		this.color = color;
		this.name = name;
	}
	
	public void addMessage(Message msg)
	{
		messageList.add(msg);
	}
	
	public boolean delCriteria(Criteria c)
	{
		return(criteriaList.remove(c));
	}
	
	public boolean addCriteria(Criteria c)
	{
		return(criteriaList.add(c));
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public Vector<Criteria> getCriteriaList()
	{
		return this.criteriaList;
	}
	
	private Vector<Criteria> criteriaList;
	private Vector<Message> messageList;
	private String name;
	private String color;
}

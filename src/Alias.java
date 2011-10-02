package irc;

import java.util.Vector;

public class Alias
{
	public Alias(String name, String color)
	{
		this.color = color;
		this.name = name;
		this.isHide = false;
		this.criteriaList = new Vector<Criteria>();
		this.messageList = new Vector<Message>();
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
	
	public Vector<Message> getMsgList()
	{
		return this.messageList;
	}
	
	public boolean isHide()
	{
		return this.isHide;
	}
	
	public void hide()
	{
		this.isHide = true;
	}
	
	public void show()
	{
		this.isHide = false;
	}
	
	private Vector<Criteria> criteriaList;
	private Vector<Message> messageList;
	private String name;
	private String color;
	private boolean isHide;
}

package irc;

import irc.Criteria.eCriteriaType;

public class Message
{
	public Message(eCriteriaType type, String from, String to, String content, String action)
	{
		this.type = type;
		this.from = from;
		this.to = to;
		this.content = content;
		this.action = action;
		this.isRead = false;
	}

	public boolean isRead()
	{
		return isRead;
	}
	
	public void setRead()
	{
		this.isRead = true;
	}
	
	public eCriteriaType getType()
	{
		return type;
	}
	
	public String getTo()
	{
		return to;
	}
	
	public String getFrom()
	{
		return from;
	}
	
	public String getContent()
	{
		return content;
	}

	public String getAction() 
	{
		return action;
	}
	private eCriteriaType type;
	private String from;
	private String to;
	private String content;
	private boolean isRead;
	private String action;
}

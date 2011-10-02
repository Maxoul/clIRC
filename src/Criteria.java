package irc;

public class Criteria
{
	public Criteria(eCriteriaType type, String value)
	{
		this.type = type;
		this.value = value;
	}
	
	enum eCriteriaType
	{
		none,
		query,
		chan,
		server,
		all_query,
		all_chan,
		all_server;
	}
	
	public eCriteriaType getType()
	{
		return type;
	}
	
	public String getValue()
	{
		return value;
	}
	
	private eCriteriaType type;
	private String value;
}

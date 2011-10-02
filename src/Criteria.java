package irc;

public class Criteria
{
	public Criteria(eCriteriaType type, String value)
	{
		this.type = type;
		this.value = value;
	}
	
	public Criteria(eCriteriaType type)
	{
		assert (type == eCriteriaType.all_chan || type == eCriteriaType.all_server || type == eCriteriaType.all_query);
		this.type = type;
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

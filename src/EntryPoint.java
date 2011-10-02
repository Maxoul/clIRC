package irc;

import irc.Criteria.eCriteriaType;

import java.io.IOException;
import java.net.UnknownHostException;

public class EntryPoint
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		MainIRC irc = new MainIRC("irc.epita.fr");
		
		Alias test = new Alias("chan epita2016", "pink");
		test.addCriteria(new Criteria(eCriteriaType.chan, "epita2016"));
		
		Alias test2 = new Alias("serveur", "pink");
		test2.addCriteria(new Criteria(eCriteriaType.all_server));
		test2.hide();
		
		Alias test3 = new Alias("my queries", "pink");
		test3.addCriteria(new Criteria(eCriteriaType.all_query));
		
		Alias test4 = new Alias("tout mes chans", "pink");
		test4.addCriteria(new Criteria(eCriteriaType.chan, "clirc"));
		test4.addCriteria(new Criteria(eCriteriaType.chan, "epita2016"));
		
		irc.getAliasList().add(test);
		irc.getAliasList().add(test2);
		irc.getAliasList().add(test3);
		irc.getAliasList().add(test4);
		
		irc.setNickname("clIRC3");
		irc.login();
		irc.joinChannel("#clirc");
		irc.joinChannel("#test");
//		irc.joinChannel("#epita2016");
		irc.handle();
	}

}

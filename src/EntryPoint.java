package irc;

import irc.Criteria.eCriteriaType;

import java.io.IOException;
import java.net.UnknownHostException;

public class EntryPoint
{

	public static void main(String[] args) throws UnknownHostException, IOException
	{
		String t = ":Max!maxoul@RZ-dfa258cf.196.99.84.rev.sfr.net PRIVMSG #clirc :!!Je m'explique: non. il dit: oui !";
		String u = ":Max!maxoul@RZ-dfa258cf.196.99.84.rev.sfr.net PRIVMSG clIRC :test micro";
		String s = ":irc.epita.rezosup.org NOTICE AUTH :*** Checking Ident";
		String v = ":irc.epita.rezosup.org NOTICE clIRC :*** Notice -- This server runs";
		String v2 = ":aurag!aurag@RZ-90.46.165.2de18a71 QUIT :Ping timeout";
		String v3 = ":Alicendre!ali@RZ-b691c367.fbx.proxad.net QUIT :Ping timeout";
		String v4 = ":Alicendre|3!ali@RZ-b691c367.fbx.proxad.net JOIN :#epita2016";
		String v5 = ":Alicendre|3!ali@RZ-b691c367.fbx.proxad.net NICK :Alicendre";
		
		//Debug.debugMsg(t);
		
		//Message m = new Message(chan, toto, "tata");

		MainIRC irc = new MainIRC("irc.epita.fr");
		irc.setNickname("clIRC");
		irc.login();
		irc.joinChannel("#epita2016");
		irc.handle();
		
	}

}

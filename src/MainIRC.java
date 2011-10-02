package irc;

import irc.Criteria.eCriteriaType;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Vector;

// /hide [query | chan | server | @query | @chan | @server | alias | @alias] <value>
// /show [query | chan | server | @query | @chan | @server | alias | @alias] value [all | unread] (nb_of_line)
// /alias new <alias_name> <color>
// /alias add <alias_name> [query | chan | server | @query | @chan | @alias ] <value> (file)
// /alias del [query | chan | server | @query | @chan | @alias ] <value> <alias_name>
// /alias set <alias_name> color <color>
// /alias set <alias_name> visibility [ visible | hidden ]
// /alias set <alias_name> file <file> <mode>

public class MainIRC
{

	MainIRC(String host) throws UnknownHostException, IOException
	{
		this(host, 6667);
	}

	MainIRC(String host, int port) throws UnknownHostException, IOException
	{
		this.host = host;
		this.port = port;
		this.line = null;
		this.aliasList = new Vector<Alias>();

		socket = new Socket(host, port);
		writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	}

	void login() throws IOException
	{
		writer.write("NICK " + nickname + "\r\n");
		writer.write("USER " + nickname + " 8 * : clIRC\r\n");
		writer.flush();

		while ((line = reader.readLine()) != null)
		{
			 if (line.toLowerCase( ).startsWith("ping "))
			 {
				 writer.write("PONG " + line.substring(5) + "\r\n");
				 writer.flush( );
			 }

			if (line.indexOf("004") >= 0)
			{
				System.out.println("Success login.");
				break;
			} 
			else
				if (line.indexOf("433") >= 0)
				{
					System.out.println("Nickname is already in use.");
					return;
				}
		}
	}

	void joinChannel(String channel) throws IOException
	{
		writer.write("JOIN " + channel + "\r\n");
		writer.flush();
	}

	void handle() throws IOException
	{	
		while ((line = reader.readLine()) != null)
		{
			if (line.toLowerCase().startsWith("ping "))
			{
				writer.write("PONG " + line.substring(5) + "\r\n");
				writer.flush();
			} 
			else
			{
				assignMsg(createMsg(line));
				showMsg();
			}
		}
	}
	
	void showMsg()
	{
		for (Alias alias: aliasList)
			if (!alias.isHide())
			{
				for (Message msg: alias.getMsgList())
					if (!msg.isRead())
					{
						String out = "[" + alias.getName() + "]";
						
						if (msg.getType() == eCriteriaType.chan)
							out += "[#" + msg.getTo() + "] <" + msg.getFrom() + ">";
						if (msg.getType() == eCriteriaType.query)
							out += "[QUERY] <" + msg.getFrom() + ">";
						if (msg.getType() == eCriteriaType.server)
							out += "[" + msg.getAction() + "]";
						
						out += " " + msg.getContent();
						System.out.println(out);
						msg.setRead();
					}
			}
	}
	
	void assignMsg(Message msg)
	{
		for (Alias alias: aliasList)
			for (Criteria criteria: alias.getCriteriaList())
			{
				if ((msg.getType() == eCriteriaType.chan) && (msg.getType() == criteria.getType()) && (msg.getTo().equals(criteria.getValue())) ||
						(msg.getType() == eCriteriaType.query) && (msg.getType() == criteria.getType()) && (msg.getFrom().equals(criteria.getValue())) ||
						(msg.getType() == eCriteriaType.server) && (msg.getType() == criteria.getType()) ||
						((msg.getType() == eCriteriaType.chan) && criteria.getType() == eCriteriaType.all_chan) ||
						((msg.getType() == eCriteriaType.server) && criteria.getType() == eCriteriaType.all_server) ||
						((msg.getType() == eCriteriaType.query) && criteria.getType() == eCriteriaType.all_query))
					alias.addMessage(msg);
			}
		
	}
	
	Message createMsg(String msg)
	{
		String from = "";
		String to = "";
		String content = "";
		String action = "";
		eCriteriaType type = eCriteriaType.none;
	
		if (msg.split(":").length > 2)
		{
			for (int i = 0; i < msg.split(":").length - 2; i++)
				content = content + ":" + msg.split(":")[i + 2];
			content = content.substring(1);
		}
		
		String withoutContent = msg.split(":")[1];
		from = withoutContent.split("!")[0];
	
		if (withoutContent.contains("PRIVMSG #"))
		{
			type = eCriteriaType.chan;
			to = withoutContent.split("#")[1].trim();
		}
		else if (withoutContent.contains("PRIVMSG"))
		{
			type = eCriteriaType.query;
			to = withoutContent.split("PRIVMSG ")[1].trim();
		}
		else if (withoutContent.contains("QUIT"))
		{
			from = withoutContent.split("!")[0];
			action = "QUIT";
		}
		else if (withoutContent.contains("NOTICE"))
		{
			from = withoutContent.split(" NOTICE")[0].trim();
			action = "NOTICE";
		}
		else if (withoutContent.contains("JOIN"))
		{
			from = withoutContent.split("!")[0];
			action = "JOIN";
		}
		else if (withoutContent.contains("NICK"))
		{
			from = withoutContent.split("!")[0];
			action = "NICK";
		}
		else
		{
			content = msg;
			action = "error";
		}
		if (type == eCriteriaType.none)
			type = eCriteriaType.server;
		
		return (new Message(type, from, to, content, action));
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Vector<Alias> getAliasList()
	{
		return this.aliasList;
	}
	
	private String line;
	private int port;
	private BufferedWriter writer;
	private BufferedReader reader;
	private Socket socket;
	private String host;
	private String nickname;
	private Vector<Alias> aliasList;
	
}
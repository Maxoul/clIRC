package irc;

import irc.Criteria.eCriteriaType;

public class Debug {

	static void debugMsg(String msg) {
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

		if (withoutContent.contains("PRIVMSG #")) {
			type = eCriteriaType.chan;
			to = withoutContent.split("#")[1].trim();
		} else if (withoutContent.contains("PRIVMSG")) {
			type = eCriteriaType.query;
			to = withoutContent.split("PRIVMSG ")[1].trim();
		} else if (withoutContent.contains("QUIT")) {
			from = withoutContent.split("!")[0];
			action = "QUIT";
		} else if (withoutContent.contains("NOTICE")) {
			from = withoutContent.split(" NOTICE")[0].trim();
			action = "NOTICE";
		} else if (withoutContent.contains("JOIN")) {
			from = withoutContent.split("!")[0];
			action = "JOIN";
		} else if (withoutContent.contains("NICK")) {
			from = withoutContent.split("!")[0];
			action = "NICK";
		} else {
			content = msg;
			from = "error ! I dont understand this command !";
		}
		if (type == eCriteriaType.none)
			type = eCriteriaType.server;

		System.out.println("From: <" + from + ">");
		System.out.println("To: <" + to + ">");
		System.out.println("Content: <" + content + ">");
		System.out.println("Action: <" + action + ">");
		System.out.println("Type: <" + type + ">");
		System.out.println("BRUT<" + msg + ">");
	}

}

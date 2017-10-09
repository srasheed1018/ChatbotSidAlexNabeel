package chatbotProject;

public class NabeelChatbot implements Topic {

	private String[] keywords = { "apps", "app", "facebook", "Twitter", "Snapchat" };;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	private boolean typeOfPhone;
	private String[] phoneTypes = { "android", "iphone", "windows" };
	private int j;
	private boolean done;
	private String[] gameRec = {
			"Clash of clans is a fun strategy game where you built you own base and attack others.",
			"Implosion is a hack and stash shooting game that has an epic storyline.",
			"Interlocked is a creativity game that tests your ingenuity." };
	private String[] socialRec = { "Facebook is the most used social media platform",
			"Snapchat is an instant video/picture sharing app", "Instragram is used predominantly to post pictures" };
	private String[] productRec = {
			"Google Drive is one of the best apps that allows you to not only store your information but also edit and make docs, execel sheets, and other things.",
			"Todoist is a great app for setting reminders and making lists of things to do",
			"Appblock is an app that blocks unwanted apps from disturbing you when your busy." };
	private int selection = 1;
	private boolean facebook = false;
	private int next = 0;
	private boolean snapchat = false;
	private boolean news = false;
	private boolean limitless=false;
	private boolean limit=false;

	public NabeelChatbot() {
		String[] temp = {};
		goodbyeWord = "Go back";
		secretWord = "Best ";
		j = -1;
	}

	public boolean isTriggered(String response) {
		for (int i = 0; i < keywords.length; i++) {
			if (ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
				return true;
		}
		return false;
	}

	public void startChatting(String response) {
		determineUserPhoneType(response);
		chatting = true;
		while (chatting) {
			response = ChatbotMain.getInput();
			if (ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			} else if (ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				ChatbotMain.print(" WOW1 Yuo guessed my favorite thing!");
			} else
				ChatbotMain.print(" Huh! I don't understand! Try again!");
		}
	}

	public void determineUserPhoneType(String response) {
		ChatbotMain.print("Hi!!! My name is Neko");
		ChatbotMain.print("What type of phone do you have?");
		done = false;
		int blowUpCounter = 0;
		while (done == false) {
			response = ChatbotMain.getInput().toLowerCase();
			boolean phoneType = false;
			for (int i = 0; i < phoneTypes.length; i++) {
				if (response.contains(phoneTypes[i])) {
					j = i;
					phoneType = true;
					break;
				}
			}
			if (j != -1) {
				done = true;
			} else if (phoneType == false && blowUpCounter < 3) {
				ChatbotMain.print("That is not a phone type. Type something else");
				blowUpCounter += 1;
			} else if (blowUpCounter == 3) {
				ChatbotMain.print("My patience is running short answer my question!!!");
				blowUpCounter += 1;
			} else if (blowUpCounter == 4) {
				ChatbotMain.print("You aren't the smartest are you? Answer corretly or I will kick you off my chat!!!");
				blowUpCounter += 1;
			} else {
				ChatbotMain.print("GET OFF");
				ChatbotMain.chatbot.startTalking();
			}

		}
		if (j == 0 || j == 1 || j == 2)
			phoneSetUp(response);
	}

	public void phoneSetUp(String response) {
		// boolean boo = false;
		ChatbotMain.print("So you have an " + phoneTypes[j]
				+ " phone I got some great app recommendations from you. If you wanna hear them say yes or if you wanna talk about something else say no");
		response = ChatbotMain.getInput().toLowerCase();
		if (response.contains("recommendations") || response.contains("yes") || response.contains("recs")
				|| response.contains("recommandation")) {
			ChatbotMain.print("Would you like to talk about games, social media or productivity apps?");
			while (selection == 1) {
				response = ChatbotMain.getInput().toLowerCase();
				if (response.contains("games") || response.contains("game")) {
					ChatbotMain.print(printOut(gameRec));
					selection = 3;
				}

				else if (response.contains("social media") || response.contains("social")
						|| response.contains("media")) {
					ChatbotMain.print(printOut(socialRec));
					selection = 2;
				} else if (response.contains("productivity")) {
					ChatbotMain.print(printOut(productRec));
					selection = 4;
				} else
					ChatbotMain.print(
							"I don't really understand can you try using only one of the topics above or if you want to exit and talk about something else say 'talk about something else'");

			}
			if (selection > 1) {
				nextPart(response);
			}
		} else if (!(response.contains("recommendations") || response.contains("yes") || response.contains("recs")
				|| response.contains("recommandation"))) {
			nextPart(response);
			// boo = nextPart(response);
		}
		/*
		 * if(boo == true) ChatbotMain.print("dkfnsjdnfjsdnfjsdnfjknd");
		 */
	}

	public void nextPart(String response) {
		ChatbotMain.print("Well what else would you like to talk about?");
		response = ChatbotMain.getInput().toLowerCase();
		if (response.contains("facebook")) {
			facebook = true;
			ChatbotMain.print("What about Facebook do you want to talk about?");
			response = ChatbotMain.getInput().toLowerCase();
			chatSocial(response);
		} else if (response.contains("snapchat")) {
			snapchat = true;
			ChatbotMain.print("What about SnapChat do you want to talk about?");
			response = ChatbotMain.getInput().toLowerCase();
			chatSocial(response);
		} else if (response.contains("games") || response.contains("game")) {
			ChatbotMain.print("Cool games. Which ones?");
			response = ChatbotMain.getInput().toLowerCase();
			chatGame(response);
		} else {
			ChatbotMain.print("Hey I dont know much about this topic, can you specify what type of app it is?");
			response = ChatbotMain.getInput().toLowerCase();
			determineWhatTypeOfApp(response);

		}
	}

	public void determineWhatTypeOfApp(String response) {
		int i = 0;
		boolean in = true;
		while (i == 0) {
			if (in == false) {
				response = ChatbotMain.getInput().toLowerCase();
			}
			if (response.contains("games") || response.contains("game") || response.contains("fun")) {
				if (selection == 3) {
					ChatbotMain.print("Do you play any of the games that I recommended above?");
					response = ChatbotMain.getInput().toLowerCase();
					if (response.contains("yes") || response.contains("ya")) {
						ChatbotMain.print(
								"Awesome, those are some of my favorite games, I'm glad that you have tried them");
						ChatbotMain.print("Do you wanna talk about those apps or another game?");
						response = ChatbotMain.getInput().toLowerCase();
						selection = 5;
						chatGame(response);
						i = 2;
					}
				} else {
					ChatbotMain.print(
							"What games do you wanna talk about? I can talk about shooting games or racing games.");
					response = ChatbotMain.getInput().toLowerCase();
					chatGame(response);
					i = 2;
				}
			} else if (response.contains("social") || response.contains("social media")) {
				ChatbotMain.print("So you want to talk about a social media app? Which one do you use the most?");
				response = ChatbotMain.getInput().toLowerCase();
				chatGame(response);
				i = 2;
			} else {
				ChatbotMain.print("Sorry I can't talk about that. Please pick between games apps and social media apps.");
				in = false;
			}
		}

	}

	public void chatSocial(String response) {

	}

	public void chatGame(String response) {
		if(selection == 5) {
			if(response.contains("yes") || response.contains("ya")) {
				ChatbotMain.print("Which one did you like the best?") ;
				response = ChatbotMain.getInput().toLowerCase();
				if(response.contains("clash") || response.contains("clans")) {
					ChatbotMain.print("I guess you like strategy games. To bad i don't (except this one) and will never bring it up again so don't even try it!");
				}else if(response.contains("implosion") || response.contains("implode")) {
					ChatbotMain.print("I guess you like shooting games huh.");
				}else if(response.contains("interlocked") || response.contains("interlock")) {
					ChatbotMain.print("I guess that you like relaxing games, huh");
				}else if(response.contains("break") || response.contains("stop") || response.contains("talk about something else") || response.contains("exit") || response.contains("next")) {
					ChatbotMain.print("Looks like you wanna leave, hold on.");
					selection = 10; 
					chatGame("starting again");
				}else {
					ChatbotMain.print("Honestly you've been a pain in the ass, why can't you just answer the question. For the next question pick one answer and show that you can follow directions.");
				}
			}
		}else {
			ChatbotMain.print("Well thats too bad");
		}
		if(limitless == false && limit == false) {
		ChatbotMain.print("Whats you favorite? Shooting or racing (Choose one)");
		response = ChatbotMain.getInput().toLowerCase();
	}
		if(response.contains("shooting")){
			ChatbotMain.print("I see that you like shooting games, have you ever played any of these games: Left for dead, );
		}else if(response.contains("racing")) {
			ChatbotMain.print("I love racing, its my favorite type of gaming, what do you like about it?");
			response = ChatbotMain.getInput().toLowerCase();
			if(response.contains("like") || response.contains("best") || response.contains("love")) {
				ChatbotMain.print("That is really cool. I often play Asphalt 8 its has one ofnthe best graphics for any game, not just racing.");
				ChatbotMain.print("Well you can pick to talk about other types of games or social media or news apps");
				response = ChatbotMain.getInput().toLowerCase();
				limitless =true;
				determineNexPart(response);

			}
			else if (!(response.contains("like") || response.contains("best") || response.contains("love"))){
			ChatbotMain.print("Don't like racing huh? Well thats to bad. Lets move on to other things");
			ChatbotMain.print("Well you can pick to talk about other types of games or social media or news apps");
			response = ChatbotMain.getInput().toLowerCase();
			limitless = true;
			determineNexPart(response);
			}	
		}else {
			ChatbotMain.print("WOW, can't even do this simple thing really? GET OFF!!");
			ChatbotMain.chatbot.startTalking();
				}
					
	}

	public void determineNexPart(String response) {
				if(response.contains("shooting") || response.contains("racing"))
					chatGame("nothing");
				else if(response.contains("social") || response.contains("media"))
					chatSocial("nada");
	}
	public String printOut(String[] itemRec) {
		String result = "";
		for (int i = 0; i < itemRec.length; i++) {
			result += itemRec[i] + System.lineSeparator();
		}
		return result;
	}
}

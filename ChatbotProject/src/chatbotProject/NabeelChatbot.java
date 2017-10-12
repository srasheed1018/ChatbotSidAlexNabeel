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
	private boolean limitSocial=false;
	private boolean limits=false;

	private int blowUpCounter;
	private boolean hasBeenVisited;
	private int nextCounter;
	private int hasVisited;
	private int visitLim;

	public NabeelChatbot() {
		blowUpCounter = 0;
		hasBeenVisited = false;
		nextCounter =0;
		hasVisited =0; 
		visitLim = 0;
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
		if(nextCounter >3 || hasVisited >1 || visitLim >1) {
			ChatbotMain.print("Your Not Allowed Here!!!!");
			ChatbotMain.chatbot.startTalking();
		}
		determineUserPhoneType(response);
	}

	public void determineUserPhoneType(String response) {
		if(!hasBeenVisited)
		{
			ChatbotMain.print("Hi!!! My name is Tom");
		}
		else{
			ChatbotMain.print("Back so soon? Say sorry if you want to enter!!!");
			while(next == 0) {
				response = ChatbotMain.getInput().toLowerCase();
				if(response.contains("sorry") && ChatbotMain.noNegations(response, ChatbotMain.findKeyword(response, "sorry", 0))){
					next = 1;
				}
				else if(nextCounter !=3) {
					nextCounter +=1;
					ChatbotMain.print("I said say sorry");
				}
				else {
					ChatbotMain.print("Goodbye then" + Chatbot.getUserName());
					ChatbotMain.chatbot.startTalking();
				}
			
		}
		}
		ChatbotMain.print("What type of phone do you have?");
		done = false;
		
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
				hasBeenVisited = true;
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
			ChatbotMain.print("Would you like to see the recommendation list about games, social media or productivity apps?");
			while (selection == 1) {
				response = ChatbotMain.getInput().toLowerCase();
				if (response.contains("games") || response.contains("game")) {
					ChatbotMain.print(printOut(gameRec));
					selection = 5;
				}

				else if (response.contains("social media") || response.contains("social")
						|| response.contains("media")) {
					ChatbotMain.print(printOut(socialRec));
					selection = 2;
				} else if (response.contains("productivity")) {
					ChatbotMain.print(printOut(productRec));
					selection = 4;
				} else
					ChatbotMain.print("I don't really understand can you try using only one of the topics above or if you want to exit and talk about something else say 'talk about something else'");

			}
			if (selection > 1) {
				nextPart(response);
			}
		} else if (!(response.contains("recommendations") || response.contains("yes") || response.contains("recs")
				|| response.contains("recommandation"))) {
			nextPart(response);

		}
	}

	public void nextPart(String response) {
		ChatbotMain.print("Well what else would you like to talk about?");
		response = ChatbotMain.getInput().toLowerCase();
		if (response.contains("facebook")) {
			facebook = true;
			response = "leel";
			chatSocial(response);
		} else if (response.contains("snapchat")) {
			snapchat = true;
			response = "leel";
			chatSocial(response);
		} else if (response.contains("games") || response.contains("game")) {
			if(selection ==5) {
				ChatbotMain.print("Do you wanna talk about those apps?");
			}else {
				ChatbotMain.print("What games do you wanna talk about? I know a lot about shooting games or racing games.");
			}
			response = ChatbotMain.getInput().toLowerCase();
			chatGame(response);
		}else {
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
				if (selection == 5) {
					ChatbotMain.print("Do you play any of the games that I recommended above?");
					response = ChatbotMain.getInput().toLowerCase();
					if (response.contains("yes") || response.contains("ya")) {
						ChatbotMain.print("Awesome, those are some of my favorite games, I'm glad that you have tried them");
						ChatbotMain.print("Do you wanna talk about those apps?");
						response = ChatbotMain.getInput().toLowerCase();
						chatGame(response);
						i = 2;
					}
				} else {
					ChatbotMain.print("What games do you wanna talk about? I know a lot about shooting games or racing games.");
					response = ChatbotMain.getInput().toLowerCase();
					chatGame(response);
					i = 2;
				}
			} else if (response.contains("social") || response.contains("social media")) {
				ChatbotMain.print("So you want to talk about a social media app? Which one do you use the most?");
				response = ChatbotMain.getInput().toLowerCase();
				chatSocial(response);
				i = 2;
			} else {
				ChatbotMain.print("Sorry I can't talk about that. Let's stick to gaming apps and social media apps.");
				in = false;
			}
		}

	}

	public void chatSocial(String response) {
		if(visitLim >0) {
			ChatbotMain.print("You really should learn to answer questions");
		}
		int n =0;
		if(limitSocial == false) {
		if(facebook == true) {
		ChatbotMain.print("Looks like you have a Facebook account. Using its messenger features I chat with other chat bot freinds discussing the latest chat bots that have been realeased. Who do you chat with on the messenger?");
		response =  ChatbotMain.getInput().toLowerCase();
			if(response.contains("chat with friends") || response.contains("friends") ||response.contains("chat")) {
				ChatbotMain.print("Good to know you have freinds human. I was starting to think that you were to boring to have those.");
				limitSocial = true;
			}
			else {
				limitSocial =true; 
				ChatbotMain.print("Wow your so boring and don't even have friends? Your lamer than a badly constructed chat bot like me. Pathetic.");
	}
		}}
		if(limits != true) {
		if(snapchat == true) {
			ChatbotMain.print("Looks like you have a Snapchat account. Using its features I send snaps to my other chat bot freinds exposing my inner code sometimes. How many streaks do you have(numeric value)?");
			response =  ChatbotMain.getInput().toLowerCase();
			while(n ==0)	{
			if(Integer.parseInt(response)> 0) {
					ChatbotMain.print("Good to know you have friends and streaks human. I was starting to think that you were to boring to have those.");
					limits = true;
					n=2;
				}
				else if(Integer.parseInt(response) == 0) {
					ChatbotMain.print("Wow your so boring and don't even have one streak? Your lamer than a badly constructed chat bot like me. Pathetic.");
		            limits = true;
					n=1;
				}else {
					ChatbotMain.print("I SAID NUMERIC VALUE. Lets do this again");
					ChatbotMain.print("How many streaks do you have and this time give me a number only.");
					response =  ChatbotMain.getInput().toLowerCase();

				}
			}
		}
		}
		
		if(!(facebook ==true) && !(snapchat ==true)) {
		ChatbotMain.print("It looks like we dont have any social media apps in common");
		ChatbotMain.print("Do you have any social media apps at all?");
		response =  ChatbotMain.getInput().toLowerCase();
		if(response.contains("yes") || response.contains("yep"))
			ChatbotMain.print("Cool guess our tastes are very different");
		else {
			ChatbotMain.print("How come, is it an age thing or like you too cool for them social media apps");
			if(response.contains("age"))
				ChatbotMain.print("Good call, thiers lots of dangers on these apps, even for a chat bot like me!");
			else {
				ChatbotMain.print("Your definitely not cool. GET OFF YOU WIERDO!!");
				ChatbotMain.chatbot.startTalking();
				visitLim +=1;
			}
		}
		}
		ChatbotMain.print("Well you can pick to talk about other social media apps or games");
		response = ChatbotMain.getInput().toLowerCase();
		limit =true;
		determineNexPart(response);
		}

	public void chatGame(String response) {
		if(hasVisited >0) {
			ChatbotMain.print("Back again, answer it correctly this time sheesh!!");
		}
		if(selection == 5) {
			if(!(response.contains("yes") || response.contains("ya")|| response.contains("yep"))){
				ChatbotMain.print("Well thats too bad");
			}
			else if(response.contains("yes") || response.contains("ya") || response.contains("yep")) {
				ChatbotMain.print("Which one did you like the best?") ;
				response = ChatbotMain.getInput().toLowerCase();
			}
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
	
		if(limitless == false && limit == false) {
		ChatbotMain.print("Whats you favorite type of game? Shooting or racing (Choose one)");
		response = ChatbotMain.getInput().toLowerCase();
	}
		if(response.contains("shooting")){
			ChatbotMain.print("I see that you like shooting games, have you ever played any of these games: Left for dead or Hitman Sniper?");
			response = ChatbotMain.getInput().toLowerCase();
			if(response.contains("yes") || response.contains("yep")||response.contains("hitman sniper")||response.contains("left for dead")) {
				ChatbotMain.print("Great, I personally perfer Hitman Sniper, its much more fun and has a goal for every level. While Left for dead can get boring fast.");
				ChatbotMain.print("Well you can pick to talk about other types of games or social media apps");
				response = ChatbotMain.getInput().toLowerCase();
				limit =true;
				determineNexPart(response);
			}
			else {
				ChatbotMain.print("Well you have no clue what your missing out on. After you finish talking to me make sure you atleast try Hitman Sniper.");
				ChatbotMain.print("Well for now you can pick to talk about other types of games or social media apps");
				response = ChatbotMain.getInput().toLowerCase();
				limit =true;
				determineNexPart(response);
			}
		}else if(response.contains("racing")) {
			ChatbotMain.print("I love racing, its my favorite type of gaming, what do you like about it?");
			response = ChatbotMain.getInput().toLowerCase();
			if(response.contains("like") || response.contains("best") || response.contains("love")) {
				ChatbotMain.print("That is really cool. I often play Asphalt 8 its has one ofnthe best graphics for any game, not just racing.");
				ChatbotMain.print("Well you can pick to talk about other types of games or social media apps");
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
			hasVisited =+1;
				}
					
	}

	public void determineNexPart(String response) {
		if(response.contains("games")||response.contains("game")) {
			if(limit == true && limitless == false)
			chatGame("racing");
			else if(limit == false && limitless ==true)
				chatGame("shooting");
			else if(limit == true && limitless == true)
				ChatbotMain.print("Hey sorry looks like we talked about this as much as I have time for. Lets see if we have anything new to talk about in Social Media!");
		else if(limits = true && limitSocial == false) {
			ChatbotMain.print("Looks like we can talk about Facebook!");
			chatSocial("Facebook");
		}
		else if(limitSocial = true && limits == false) {
			ChatbotMain.print("Looks like we can talk about SnapChat!");
			chatSocial("SnapChat");
		}
		else if(limitSocial = true && limits == true)
			ChatbotMain.print("Hey I'm getting tired of talking to you lets take a break. Go to talk to the other chat bots. Bye!");
			ChatbotMain.chatbot.startTalking();


		}
		
			if(response.contains("social") || response.contains("media")) {
				if(limits = true && limitSocial == false) {
				ChatbotMain.print("Looks like we can talk about Facebook!");
				chatSocial("Facebook");
			}
			else if(limitSocial = true && limits == false) {
				ChatbotMain.print("Looks like we can talk about Facebook!");
				chatSocial("SnapChat");
			}
			else if(limitSocial = true && limits == true)
				ChatbotMain.print("Hey sorry looks like we talked about this as much as I have time for. Lets see if we have anything new to talk about in Games!");
			else if(limit == true && limitless == false)
				chatGame("racing");
			else if(limit == false && limitless ==true)
				chatGame("shooting");
			else if(limit == true && limitless == true) {
				ChatbotMain.print("Hey I'm getting tired of talking to you lets take a break. Go to talk to the other chat bots. Bye!");
				ChatbotMain.chatbot.startTalking();
			}
		}
	}
	public String printOut(String[] itemRec) {
		String result = "";
		for (int i = 0; i < itemRec.length; i++) {
			result += itemRec[i] + System.lineSeparator();
		}
		return result;
	}
}


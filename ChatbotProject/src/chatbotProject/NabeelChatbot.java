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
	private String[] gameRec = { "Angry Birds is a popular game.",
			"Clash of clans is a fun strategy game where you built you own base and attack others.",
			"Implosion is a hack and stash shooting game that has an epic storyline.",
			"Interlocked is a creativity game that tests your ingenuity." };
	private String[] socialRec = { "" };
	private String[] productRec = { "" };
	private int selection = 1;

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
			}
			else if (phoneType == false && blowUpCounter < 3) {
				ChatbotMain.print("That is not a phone type. Type something else");
				blowUpCounter += 1;
			}
			else if (blowUpCounter == 3) {
					ChatbotMain.print("My patience is running short answer my question!!!");
					blowUpCounter += 1;
			}
			else if (blowUpCounter == 4) {
					ChatbotMain.print(
							"You aren't the smartest are you? Answer corretly or I will kick you off my chat!!!");
					blowUpCounter += 1;
			} 
			else {
					ChatbotMain.print("GET OFF");
					ChatbotMain.chatbot.startTalking();
			}

		}
		if (j == 0 || j == 1 || j == 2)
			phoneSetUp(response);
	}

	private void phoneSetUp(String response) {
		//boolean boo = false;
		ChatbotMain.print("So you have an " + phoneTypes[j]
				+ " phone I got some great app recommendations from you. If you wanna hear them say yes or if you wanna talk about something else let me know");
		response = ChatbotMain.getInput().toLowerCase();
		if (response.contains("recommendations") || response.contains("yes") || response.contains("recs")
				|| response.contains("recommandation")) {
			ChatbotMain.print("Would you like to talk about games, social media or productivity apps?");
			while (selection == 1) {
				response = ChatbotMain.getInput().toLowerCase();
					if (response.contains("games") || response.contains("game")) {
						ChatbotMain.print(printOut(gameRec));
						selection = 2;
					}

					else if (response.contains("social media") || response.contains("social")
							|| response.contains("media")) {
						ChatbotMain.print(socialRec.toString());
						selection = 2;
					} else if (response.contains("productivity")) {
						ChatbotMain.print(productRec.toString());
						selection = 2;
					} else
						ChatbotMain.print("I don't really understand can you try using only one of the topics above or if you want to exit and talk about something else say 'talk about something else'");

			}
			if (selection == 2)
				nextPart(response);
		} else if(!(response.contains("recommendations") || response.contains("yes") || response.contains("recs")
				|| response.contains("recommandation"))){
			
			//boo = nextPart(response);
		}
		/*if(boo == true)
			ChatbotMain.print("dkfnsjdnfjsdnfjsdnfjknd");*/
	}

	private void nextPart(String response) {
		

	}

	private String printOut(String[] itemRec) {
		String result = "";
		for (int i = 0; i < itemRec.length; i++) {
			result += itemRec[i] + System.lineSeparator();
		}
		return result;
	}
}

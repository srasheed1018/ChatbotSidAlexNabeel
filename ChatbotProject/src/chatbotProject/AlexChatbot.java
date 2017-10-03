package chatbotProject;

public class AlexChatbot implements Topic {
	
	private String[] keywords;
	private String[] goodbyeWords;
	private String[] feelingWords;
	private String[] conversationType;
	private boolean chatting;
	
	private String secretWord;
	
	public AlexChatbot() {
		String[] temp = {"cases","chargers","docks","headphones","earphones","speakers","cable"};
		keywords = temp;
		String[] temp2 = {"bye","goodbye","stop"};
		goodbyeWords = temp2;
		String[] temp3 = {"love","hate","like","don't like","cool","not good"};
		feelingWords = temp3;
		String[] temp4 = {"learn","buy","purchase","talk", "discuss","view","look at","pictures","get a"};
		conversationType = temp4;
		
		secretWord = "pug";
	}

	@Override
	public boolean isTriggered(String response) {
		// TODO Auto-generated method stub
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				ChatbotMain.print("What do you want to know about " + keywords[i] + "  ?");
			}
		}
		return false;
	}

	@Override
	public void startChatting(String response) {
		// TODO Auto-generated method stub
		ChatbotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more!");
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			for(int i =0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				ChatbotMain.print("Oh my goodness! You guessed my favorite thing ever. We are friends now.");
			}else {
				ChatbotMain.print("Huh. I don't really get you. Tell me something else?");
			}
			}
		}
	}

}

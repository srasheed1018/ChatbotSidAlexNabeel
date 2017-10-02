package chatbotProject;

public class chatbotAlexLeon implements Topic {
	
	private String[] keywords;
	private String[] goodbyeWords;
	private String secretWord;
	
	public chatbotAlexLeon() {
		String[] temp = {"food","entertainment","Internet","video games",""};
		keywords = temp;
		String[] temp2 = {"bye","goodbye","stop"};
		goodbyeWords = temp2;
		secretWord = "pug";
	}

	@Override
	public boolean isTriggered(String response) {
		// TODO Auto-generated method stub
		for(int i = 0; i < keywords.length; i++) {
			if(chatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
			
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
			response = chatbotMain.getInput();
			if(chatbotMain.findKeyword(response, goodbyeWords, 0) >= 0) {
				chatting = false;
				chatbotMain.chatbot.startTalking();
			}else if(chatbotMain.findKeyword(response, secretWord, 0) >= 0) {
				chatbotMain.print("Oh my goodness! You guessed my favorite thing ever. We are friends now.");
			}else {
				chatbotMain.print("Huh. I don't really get you. Tell me something else?");
			}
		}
	}

}

package chatbotProject;

public class NabeelChatbot implements Topic{
	
	private String[] keywords;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	
	public NabeelChatbot() {
		String[] temp = {};
		keywords = temp;
		goodbyeWord = "nabill";
		secretWord = "";
	}
	
	
	
	
	
	
	
	public boolean isTriggered(String response) {
		for(int i=0; i< keywords.length; i++) {
			if(ChatBotMain.findKeyword(response, keywords[i], 0) >= 0)
				return true;
		}
		return false;
	}

	public void startChatting(String response) {
		ChatBotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more!");
		chatting = true;
		while(chatting) {
			response = ChatBotMain.getInput();
			if(ChatBotMain.findKeyword(response, goodbyeWord, 0) >= 0){
				chatting = false;
				ChatBotMain.chatbot.startTalking();
			}
			else if(ChatBotMain.findKeyword(response, secretWord, 0) >= 0){
					ChatBotMain.print(" WOW1 Yuo guessed my favorite thing!");
		}
			else
				ChatBotMain.print(" Huh! I don't understand! Try gain!");
		}
	}
}

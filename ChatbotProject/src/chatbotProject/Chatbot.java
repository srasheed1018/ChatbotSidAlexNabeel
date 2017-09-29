package chatbotProject;

public class Chatbot {
	
	private String userName;
	private Topic siddeeq;
	private boolean chatting;
	
	
	public Chatbot() {
		siddeeq = new ChatbotSiddeeq();
		userName = "unknown user";
		chatting = true;
	}
	
	public void startTalking() {
		ChatbotMain.print("Welcome to our chatbot! What is your name?");
		userName = ChatbotMain.getInput();
		chatting = true;
		while (chatting) {
			ChatbotMain.print("What do you want to talk about?");
			String response = ChatbotMain.getInput();
			if (siddeeq.isTriggered(response)) {
				chatting = false;
				siddeeq.startChatting(response);
			}
			else {
				ChatbotMain.print("I'm sorry, I don't understand.");
			}
		}
	}
}

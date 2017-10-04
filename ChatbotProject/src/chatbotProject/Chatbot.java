package chatbotProject;

public class Chatbot {
	
	private String userName;
	private Topic siddeeq;
	private Topic alex;
	private Topic nabeel;
	private boolean chatting;
	
	
	public Chatbot() {
		siddeeq = new SiddeeqChatbot();
		userName = "unknown user";
		chatting = true;
	}
	
	public void startTalking() {
		ChatbotMain.print("Welcome to our chatbot! What is your name?");
		userName = ChatbotMain.getInput();
		chatting = true;
		while (chatting) {
			ChatbotMain.print("We offer discussions on types of phones, applications, and accesories. What do you want to talk about?");
			String response = ChatbotMain.getInput();
			if (siddeeq.isTriggered(response)) {
				chatting = false;
				siddeeq.startChatting(response);
			}
			else {
				if (nabeel.isTriggered(response)) {
					chatting = false;
					nabeel.startChatting(response);
				}
				else {
					if (alex.isTriggered(response)) {
						chatting = false;
						alex.startChatting(response);
					}
				}		
			}
		}
	}
}

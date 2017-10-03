package chatbotProject;

public class SiddeeqChatbot implements Topic {

	private String[] keywords = {"type","battery","batteries","resolution","resolutions","iphone","iphones","samsung","samsungs","lg","htc","motorola","motorolas","moto"};
	private String[] goodbyeWords = {"bye", "goodbye", "stop"};
	private String previousResponse;
	private boolean chatting;
	private int repetition = 0;
	
	public SiddeeqChatbot() {
	}

	@Override
	public boolean isTriggered(String response) {
		for (int i = 0; i<keywords.length; i++)
		{
			if (ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
			{
				return true;
			}
		}
		return false;
	}

	@Override
	public void startChatting(String response) {
		ChatbotMain.print("Looks like you want to discuss types of devices! What would you like to talk about?");
		chatting = true;
		while (chatting) 
		{
			response = ChatbotMain.getInput();
			boolean exit = false;
			for (int i = 0; i<goodbyeWords.length; i++)
			{
				if (ChatbotMain.findKeyword(response, goodbyeWords[i], 0) >= 0)
				{
					exit = true;
					break;
				}
			}
			if (exit)
			{
				ChatbotMain.print("See ya around!");
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}
			else
			{
				if(previousResponse.equals(response))
				{
					repetition++;
					if(repetition<2)
					ChatbotMain.print("You just said that. Can you say something else?");
					else {
						if (repetition>5)
						{
							ChatbotMain.print("I'm done, goodbye.");
							chatting = false;
						}
						else
						ChatbotMain.print("You've said this "+repetition+" times. Please say something else.");
					}	
				}
				if (ChatbotMain.findKeyword(response, "battery", 0) >= 0 || ChatbotMain.findKeyword(response, "batteries", 0) >= 0)
				{
					ChatbotMain.print("I can rate the kind of battery! What");
				}
				else
				{
					ChatbotMain.print("Huh. I don't really get you. Tell me something else?");
				}
			}
			previousResponse = response;
		}
	}
}

package chatbotProject;

public class SiddeeqChatbot implements Topic {

	private String[] keywords = {"type","battery","batteries","resolution","resolutions","iphone","iphones","samsung","samsungs","lg","htc","motorola","motorolas","moto"};
	private String[] goodbyeWords = {"bye", "goodbye", "stop"};
	private String previousResponse = "";
	private String chatTopic = "";
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
	
	public String makeFunOf(String s)
	{
		String temp = "";
		boolean toggle = true;
		for (int i=0; i<s.length(); i++)
		{
			if (toggle)
			{
			temp = temp+s.substring(i,i+1).toLowerCase();
			toggle = false;
			}
			else
			{
			temp = temp+s.substring(i,i+1).toUpperCase();
			toggle = true;
			}
		}
		return temp;
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
								ChatbotMain.print(makeFunOf(response));
						}	
				}
				else 
				{
					if (chatTopic=="")
					{
						if (ChatbotMain.findKeyword(response, "battery", 0) >= 0 || ChatbotMain.findKeyword(response, "batteries", 0) >= 0)
						{
							chatTopic = "battery";
							ChatbotMain.print("I can rate the kind of battery! Do you know what milliamp hours (mAh) are?");
						}
						else
						{
							ChatbotMain.print("Huh. I don't really get you. Tell me something else?");
						}
					}
					else
					{
						if (chatTopic=="battery")
						{
							if (!(response.toLowerCase()=="yes" || response.toLowerCase()=="no"))
							{
								ChatbotMain.print("Please answer yes or no.");
							}
							else
							{
								if (response.toLowerCase()=="no")
								{
									ChatbotMain.print("mAh means milliamp Hour and is a unit that measures (electric) power over time. Your phone and any other electronic device has a battery with some amount of mAh. Do you understand?");
								}
								else
								{
									ChatbotMain.print("Great! I can give you an idea of how much screen-on time you can get with your phone. Please tell me the size of your phone's battery in mAh.");
									chatTopic="batterysize";
								}
							}
						}
						if (chatTopic=="batterysize")
						{
							try
							{
								int temp = Integer.parseInt(response)/850;
								String result = Integer.toString(temp);
								ChatbotMain.print(result);
							}
							catch (NumberFormatException e)
							{
								if (repetition>1)
								ChatbotMain.print("Just input a number...");
								else
								ChatbotMain.print("Please input a number.");
							}
						}
					}
				}
			}
			previousResponse = response;
		}
	}
}

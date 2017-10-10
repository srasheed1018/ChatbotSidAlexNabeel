package chatbotProject;

public class SiddeeqChatbot implements Topic {

	private String[] keywords = {"types","battery","batteries","camera","cameras","iphone","iphones","samsung","samsungs","lg","htc","motorola","motorolas","moto","android","androids"};
	private String[] goodbyeWords = {"bye", "goodbye", "stop"};
	private String[] yesWords = {"yes", "indeed", "okay", "please do", "go ahead","please", "why not", "sure", "alright"};
	private String[] noWords = {"no","never","not really","nah"};
	private String[] cameraFacts = {"Major manufacturers of cameras for phones include Toshiba, ST Micro, Sharp, Omnivision, and Aptina","Phones like  Apple’s iPhone 7 Plus and the new OnePlus 5 feature two rear facing cameras to enhance their zooming capabilities.","The original iPhone and the HTC Dream (T-Mobile G1) were the first smartphones, in the modern sense of the word, and both arrived with cameras onboard."};
	private int camFactPsn = cameraFacts.length - 1;
	private String previousResponse = "";
	private String chatTopic = "";
	private boolean chatting;
	private int repetition = 0;
	private String iphoneLike;
	private String androidLike;
	private String iphoneHate;
	private String androidHate;
	
	public SiddeeqChatbot() {
	}

	@Override
	public boolean isTriggered(String response) 
	{
		for (int i = 0; i<keywords.length; i++)
		{
			if (ChatbotMain.findKeyword(response.toLowerCase(), keywords[i], 0) >= 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isYes(String s)
	{
		for (int i = 0; i<yesWords.length; i++)
		{
			if (ChatbotMain.findKeyword(s.toLowerCase(), yesWords[i], 0) >= 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isNo(String s)
	{
		for (int i = 0; i<noWords.length; i++)
		{
			if (ChatbotMain.findKeyword(s.toLowerCase(), noWords[i], 0) >= 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public String makeFunOf(String s)
	{
		String temp = "\"";
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
		temp = temp+"\"";
		return temp;
	}
	public String fetchCameraFact()
	{
		if (camFactPsn<0)
		{
			return null;
		}
		else
		{
			
			if (camFactPsn==2)
			{
				camFactPsn--;
				return cameraFacts[2];
			}
			else
			{
				if (camFactPsn==1)
				{
					camFactPsn--;
					return cameraFacts[1];
				}
				else
				{
						camFactPsn--;
						return cameraFacts[0];	
				}
			}
		}
	}

	@Override
	public void startChatting(String response) 
	{
		ChatbotMain.print("Looks like you want to discuss types of devices! What would you like to talk about? (keywords include battery life, cameras, and phone brands.)");
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
								if (Math.random()>0.5)
								ChatbotMain.print(makeFunOf(response));
								else
								ChatbotMain.print("Say something else!! >:(");
						}	
				}
				else 
				{
					if (chatTopic=="")
					{
						boolean relevant = false;
						for (int i=0; i < keywords.length; i++)
						{
							response = response.toLowerCase();
							if (ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
							{
								relevant = true;
								break;
							}
						}
						if (relevant)
						{
							if (ChatbotMain.findKeyword(response, "battery", 0) >= 0 || ChatbotMain.findKeyword(response, "batteries", 0) >= 0)
							{
								chatTopic = "battery";
								ChatbotMain.print("I can rate the kind of battery you have for screen-on time! Do you know what milliamp hours (mAh) are?");
							}
							else
							{
								if (ChatbotMain.findKeyword(response, "camera", 0) >= 0 || ChatbotMain.findKeyword(response, "cameras", 0) >= 0)
								{
									chatTopic = "camera";
									ChatbotMain.print("Would you like to hear some facts about smartphone cameras?");
								}
								else
								{
									if (ChatbotMain.findKeyword(response, "iphone", 0) >= 0 || ChatbotMain.findKeyword(response, "iphones", 0) >= 0)
									{
										chatTopic = "iphone";
										ChatbotMain.print("Interested in iPhones, are we? Whats your favorite thing about them?");
									}
									else
									{
										if (ChatbotMain.findKeyword(response, "samsung", 0) >= 0 || ChatbotMain.findKeyword(response, "samsungs", 0) >= 0 || ChatbotMain.findKeyword(response, "android", 0) >= 0 || ChatbotMain.findKeyword(response, "androids", 0) >= 0 || ChatbotMain.findKeyword(response, "lg", 0) >= 0 || ChatbotMain.findKeyword(response, "htc", 0) >= 0 || ChatbotMain.findKeyword(response, "moto", 0) >= 0 || ChatbotMain.findKeyword(response, "motorola", 0) >= 0 || ChatbotMain.findKeyword(response, "motorolas", 0) >= 0)
										{
											chatTopic = "android";
											ChatbotMain.print("Interested in Androids, are we? Whats your favorite thing about them?");
										}
									}
								}
							}
						}
						else
						{
							if (repetition==0)
							{
								ChatbotMain.print("I'm afraid I'm unfamiliar with that, tell me something else please.");
							}
							if (repetition==1 || repetition==2)
							{
								ChatbotMain.print("Excuse me?");
							}
							if (repetition>2)
							{
								ChatbotMain.print("Oh my LORD please start making sense...");
							}
						}
					}
					else
					{
						if (chatTopic=="battery")
						{
							if (isYes(response.toLowerCase()) || isNo(response.toLowerCase()))
							{
								if (isNo(response.toLowerCase()))
								{
									ChatbotMain.print("mAh means milliamp Hour and is a unit that measures (electric) power over time. Your phone and any other electronic device has a battery with some amount of mAh. Do you understand?");
								}
								else
								{
									ChatbotMain.print("Great! I can give you an idea of how much screen-on time you can get with your phone. Please tell me the size of your phone's battery in mAh.");
									chatTopic="batterysize";
								}
							}
							else
							{
								ChatbotMain.print("Please answer \"yes\" or \"no\"");
							}
						}
						if (chatTopic=="batterysize")
						{
							try
							{
								int temp = Integer.parseInt(response)/850;
								String result = Integer.toString(temp);
								ChatbotMain.print("You can expect around "+result+" hours of screen on time with that "+response+" mAh battery. Enough about that though, let's talk about soemthing else about devices.");
								chatTopic = "";
							}
							catch (NumberFormatException e)
							{
								if (repetition>1)
								ChatbotMain.print("Just input a number...");
								else
								ChatbotMain.print("Please input a number.");
							}
						}
						if (chatTopic=="androidHate")
						{
							if (ChatbotMain.findKeyword(response, "hate", 0) >=0)
							{
								androidHate = response.substring(ChatbotMain.findKeyword(response, "hate", 0), ChatbotMain.findKeyword(response, "hate", 0)+5);
							}
							else
							{
								androidHate = response;
								
							}
							ChatbotMain.print("I know, I agree! "+androidHate+"! If only they were like certain OTHER devices. Everyone likes a phone when "+iphoneLike+", right? Lets talk about something else.");
							iphoneLike = "";
							iphoneHate = "";
							androidHate = "";
							androidLike = "";
							chatTopic = "";
						}
						if (chatTopic=="iphoneHate")
						{
							if (ChatbotMain.findKeyword(response, "hate", 0) >=0)
							{
								iphoneHate = response.substring(ChatbotMain.findKeyword(response, "hate", 0), ChatbotMain.findKeyword(response, "hate", 0)+5);
							}
							else
							{
								iphoneHate = response;
								
							}
							ChatbotMain.print("I know, I hate  how "+iphoneHate+" too! If only they were like certain OTHER devices. Everyone likes a phone when "+androidLike+", right? Lets talk about something else.");
							iphoneLike = "";
							iphoneHate = "";
							androidHate = "";
							androidLike = "";
							chatTopic = "";
						}
						if (chatTopic=="iphone")
						{
							if (ChatbotMain.findKeyword(response, "like", 0) >=0)
							{
								iphoneLike = response.substring(ChatbotMain.findKeyword(response, "like", 0)+5);
							}
							else
							{
								iphoneLike = response;
								
							}
							ChatbotMain.print("Yeah, "+iphoneLike+" ! What do you hate about androids?");
							chatTopic = "androidHate";
						}
						if (chatTopic=="android")
						{
							if (ChatbotMain.findKeyword(response, "like", 0) >=0)
							{
								androidLike = response.substring(ChatbotMain.findKeyword(response, "like", 0)+5);
							}
							else
							{
								androidLike = response;
								
							}
							ChatbotMain.print("Yeah "+iphoneLike+" ! What do you hate about iphones?");
							chatTopic = "iphoneHate";
						}
						if (chatTopic=="camera")
						{
							if (isYes(response))
							{
								if (camFactPsn>0)
								{
									ChatbotMain.print(fetchCameraFact()+" Would you like to hear another?");
								}
								else
								{
									ChatbotMain.print("I've told you all that I know about phone cameras. Lets talk about someting else.");
									chatTopic = "";
								}
							}
							else
							{
								ChatbotMain.print("Alright, what else do you wanna discuss?");
								chatTopic = "";
							}
						}
					}
				}
			}
			previousResponse = response;
		}
	}
}

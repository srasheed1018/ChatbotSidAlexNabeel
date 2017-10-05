package chatbotProject;

public class NabeelChatbot implements Topic{
	
	private String[] keywords ={"apps", "facebook", "Twitter","Snapchat"};;
	private String goodbyeWord;
	private String secretWord;
	private boolean chatting;
	private boolean typeOfPhone;
	private String[] phoneTypes = {"android", "iphone", "windows"};
	private int j;
	private boolean done;
	private String[] gameRec = {"Angry Birds is a popular", "Clash of clans is a fun strategy game where you built you own base and attack others", "Implosion is a hack and stash shooting game that has an epic storyline",""};
	private String[] socialRec = {""};
	private String[] productRec = {""};
	private int selection = 1;

	public NabeelChatbot() {
		String[] temp = {}; 
		goodbyeWord = "Go back";
		secretWord = "Best ";
		j=-1; 
	}
	
	
	
	
	
	
	
	public boolean isTriggered(String response) {
		for(int i=0; i< keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0)
				return true;
		}
		return false;
	}

	public void startChatting(String response) {
		determineUserPhoneType(response);
		chatting = true;
		while(chatting) {
			response = ChatbotMain.getInput();
			if(ChatbotMain.findKeyword(response, goodbyeWord, 0) >= 0){
				chatting = false;
				ChatbotMain.chatbot.startTalking();
			}
			else if(ChatbotMain.findKeyword(response, secretWord, 0) >= 0){
					ChatbotMain.print(" WOW1 Yuo guessed my favorite thing!");
		}
			else
				ChatbotMain.print(" Huh! I don't understand! Try again!");
		}
	}



	public void determineUserPhoneType(String response) {
		ChatbotMain.print("What type of phone do you have?");
		response = ChatbotMain.getInput();
		response = response.toLowerCase();
		done = false;
		int blowUpCounter = 0; 
		while(done == false) {
			for(int i =0; i< response.length();i++) {
				if(response.contains(phoneTypes[i])) {
					j = i;
					break;
				}
			}
			if(j != -1)
				done = true;
			else if(blowUpCounter != 3) {
				ChatbotMain.print("This is not a phone type");
				blowUpCounter += 1;
			}
			else if(blowUpCounter ==3) {
				ChatbotMain.print("My patience is running short answer my question!!!");
				blowUpCounter +=1;
			}
			else if(blowUpCounter == 4)
				ChatbotMain.print("You aren't the smartest are you? Answer corretly or I will kick you off my chat!!!");
			else {
				ChatbotMain.print("GET OFF");
				ChatbotMain.chatbot.startTalking();
			}
				
		}
		if(j<0)
			phoneSetUp(response);
		}






	private void phoneSetUp(String response) {
		response= ChatbotMain.getInput().toLowerCase();
		ChatbotMain.print("So you have an Iphone? I got some great app recomandations from you. If you wanna hear them say yes or if you wanna talk about something else let me know"); 
			if(response.contains("recommendations")) {
				ChatbotMain.print("Would you like to talk about games, social media or productivity apps?");
				while(selection == 1) {
				if(response.contains("games")) {
					ChatbotMain.print(gameRec.toString());
					selection = 2;
				}
				
				else if(response.contains("social media")){
					ChatbotMain.print(socialRec.toString());
					selection = 2;
				}
				else if(response.contains("productivity")) {
					ChatbotMain.print(productRec.toString());
					selection = 2;
				}
				else
					ChatbotMain.print("I don't really understand can you try using only one of the topics above or if you want to exit and talk about something else say 'talk about something else'");
					
			}
		}
			else
			startTalking(response);
			
		
	}







	private void socialRecs() {
		// TODO Auto-generated method stub
		
	}







	private void startTalking(String response) {
		// TODO Auto-generated method stub
		
	}







	private void gamesForPhones() {
		// TODO Auto-generated method stub
		
	}
}


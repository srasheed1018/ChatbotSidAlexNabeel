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
		
		ChatbotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more!");
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
			else if(blowUpCounter != 4) {
				ChatbotMain.print("This is not a phone type");
				blowUpCounter += 1;
			}
			else if(blowUpCounter == 4)
				ChatbotMain.print("You aren't the smartest are you? Answer corretly or I will kick you off my chat!!!");
			else {
				ChatbotMain.print("GET OFF");
				ChatbotMain.chatbot.startTalking();
			}
				
		}
		if(j == 0 || j == 2) {
			typeOfPhone = true;
			androidSetUp();
		}
		else {
			typeOfPhone = false;
			iphoneSetUp();
		}
		}
sfsfsdsd






	private void iphoneSetUp() {
		// TODO Auto-generated method stub
		
	}







	private void androidSetUp() {
		// TODO Auto-generated method stub
	}
}

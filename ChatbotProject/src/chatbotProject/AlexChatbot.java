package chatbotProject;

public class AlexChatbot implements Topic {
	
	private String selectedkeyword = "accessories";
	private String[] keywords;
	private String[] goodbyeWords;
	private String[] feelingWords;
	private String[] conversationType;
	private String[] descriptionWords;
	private String[] brandsWords;
	private String[] affirmingWords;
	private String[] declineWords;
	
	private String[] casesInfo;
	private String[] chargersInfo;
	private String[] headphonesInfo;
	private String[] earphonesInfo;
	private String[] speakersInfo;
	private boolean chatting;
	
	public AlexChatbot() {
		// for the arrays containing info on the accessories, make sure each index has is related to the same index in a different array.
		// 1st index : types
		// 2nd index : what makes keyword[i] good?
		// 3rd index : best companies to purchase one
		String[] temp = {"cases","chargers","headphones","earphones","speakers"};
		keywords = temp;
		String[] temp2 = {"bye","goodbye","stop"};
		goodbyeWords = temp2;
		
		String[] temp7 = {"yeah","yes","ofc","of course","definitely","obviously"};
		affirmingWords = temp7;
		String[] temp8 = {"no","nah","nope"};
		declineWords = temp8;
		
		String[] temp3 = {"love","hate","like","don't like"};
		feelingWords = temp3;
		String[] temp4 = {"learn","buy","talk","purchase", "discuss","get a"};
		conversationType = temp4;
		String[] temp5 = {"big","red","small","black","tiny","blue","large","grey"};
		descriptionWords = temp5;
		
		String[] temp9 = {"Popular cases include Otterbox, Speck, Griffin, and Caseology.","When purchasing a case for your smartphone, you should take size, style, and strength into consideration.","The best smartphone cases are the customizable one made by Otterbox -- military grade."};
		casesInfo = temp9;
		String[] temp10 = {"Chargers come in many different forms nowadays : bases, docks, and battery boxes.","A good charge is portable, will charge your phone in a short period of time, and will last a long time too.","The best chargers are made by Jackery."};
		chargersInfo = temp10;	
		String[] temp11 = {"Headphones come in three types : in-ear, over-ear, and on-ear.","A good pair of headphones fits the consumer nicely and comfortably.","The best headphones are made by Beats."};
		headphonesInfo = temp11;
		String[] temp12 = {"Earphones can be wired or wireless.","A great pair of earphones are portable and don't wear too easily.","The best pair of earphones are given to Apple users for free."};
		earphonesInfo = temp12;
		String[] temp13 = {"Speakers come in all sizes and colors.","A good speakers, as with all accessories is portable, but can also be brought to a party.","There isn't a company who has managed to dominate the speaker market."};
		speakersInfo = temp13;
		
		
	}

	@Override
	public boolean isTriggered(String response) {
		// TODO Auto-generated method stub
		String selectedKeyword;
		for(int i = 0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				selectedKeyword = keywords[i];
				return true;
			}
		}
		return false;
	}
	@Override
	public void startChatting(String response) {
		// Nockles : refer back to previous Chatbot opinions AND have the chatbot get more excited
		// TODO Auto-generated method stub
		ChatbotMain.print("Hey! It sounds like you and I have a common interest! Let's talk some more! I can answer three questions regarding any of the following accessories : cases,chargers,headphones,earphones,speakers.");
		chatting = true;
		int questionNum = parseInt(response);
		while(chatting) {
			response = ChatbotMain.getInput().toLowerCase();
			//String qNumber = ChatbotMain.getInput();
			//int questionNumber = parseInt(qNumber);
			for(int i =0; i < keywords.length; i++) {
			if(ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				ChatbotMain.print("What do you want to know about" + keywords[i] + "?" + " 1.Types of " + keywords[i] + "." + " 2.Qualities that make good " + keywords[i] + " or 3.the best company to purchase " + keywords[i] + "from?" + "To ask any of these questions, type in the number for the corresponding question.");
				if(response!= "1" && response!= "2" && response!= "3") {
					ChatbotMain.print("Please type the numbers 1,2, or 3 to ask a question about" + keywords[i]);
				}
				else {
					if(keywords[i] == "cases") {
						ChatbotMain.print(casesInfo[questionNum]);
					}
					if(keywords[i] == "chargers") {
						ChatbotMain.print(chargersInfo[questionNum]);
					}
					if(keywords[i] == "headphones") {
						ChatbotMain.print(headphonesInfo[questionNum]);
					}
					if(keywords[i] == "earphones") {
						ChatbotMain.print(earphonesInfo[questionNum]);
					}
					if(keywords[i] == "speakers") {
						ChatbotMain.print(speakersInfo[questionNum]);
					}
					// return the proper index of the proper string
				}
			}
			
			if(ChatbotMain.findKeyword(response, goodbyeWords[i], 0) >= 0) {
			
			}
			if(ChatbotMain.findKeyword(response, feelingWords[i], 0) >= 0) {
			
			}
			if(ChatbotMain.findKeyword(response, conversationType[i], 0) >= 0) {
				
			}
			if(ChatbotMain.findKeyword(response, descriptionWords[i], 0) >= 0) {
				
			}
			if(ChatbotMain.findKeyword(response, brandsWords[i], 0) >= 0) {
				
			}
			if(ChatbotMain.findKeyword(response, affirmingWords[i], 0) >= 0) {
				
			}
			if(ChatbotMain.findKeyword(response, declineWords[i], 0) >= 0) {
				
			}
			if(ChatbotMain.findKeyword(response, casesInfo[i], 0) >= 0) {
				
			}
			
			else if(ChatbotMain.findKeyword(response, feelingWords[i], 0) >= 0 && ChatbotMain.findKeyword(response, keywords[i], 0) >= 0) {
				ChatbotMain.print("What a coincidence." + "I " + feelingWords[i] + keywords[i] + "too.");
			}else {
				ChatbotMain.print("Huh. I don't really get you. Tell me something else?");
			}
			}
		}
		/*
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
		*/
	}

	private int parseInt(String lowerCase) {
		// TODO Auto-generated method stub
		return 0;
	}

}



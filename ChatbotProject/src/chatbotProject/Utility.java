/**
 *Note: I wrote this class without a compiler, quickly. There are probably errors. I apologize. When I get Internet access with my own computer, I will revise.
 */
package chatbotProject;

import java.util.Scanner;

public class Utility{

	private static Scanner inputSource = new Scanner(System.in);
	private static String[] words= {"good","great","okay"}; 

	public static void main(String[] args){
		//this main method is designed to help you test your keywordIsIsolated method and noNegations method
		if( noNegations("i'm not good", 8)) {
			print("You passed all the keywordIsIsolated tests.");
		}else {
			print("You failed some of the keywordIsIsolated tests.");
		}
		if(!noNegations("I am not great, but I am okay", 9) && noNegations("I am not great, but I am okay", 25) && noNegations("okay", 0)){
			print("You passed all the noNegations tests.");
		}
		else {
			print("You failed some of the NoNegation tests.");}
	}
	

	/**
	 * This method returns the index of keyword in the searchString (after startPsn)
	 *  where keyword is isolated and has noNegations. It returns -1 if the
	 *  keyword is not found
	 */
	public static int findKeyword(String searchString, String keyword, int startPsn) {
		//makes lowercase
		searchString = searchString.toLowerCase();
		keyword = keyword.toLowerCase();
		//find the first position after the startPsn
		int psn = searchString.indexOf(keyword, startPsn);
		
		//keep searching until keyword is found (noNegations and isolated)
		while(psn >= 0) {
			
			if(keywordIsIsolated(psn, keyword, searchString) && noNegations(searchString, psn)) {
				return psn;
			}else {
				//look for the next occurrence
				psn = searchString.indexOf(keyword, psn+1);
			}
		}
		return -1;
	}
	
	
	public static boolean keywordIsIsolated(int psn, String keyword, String s){
		if(psn == 0 && (s.charAt(psn + keyword.length()-1)== s.length() || psn + keyword.length() ==' '))
			return true;
		else if(psn == 0 && !(s.charAt(psn + keyword.length()-1)== s.length() || psn + keyword.length() ==' '))
			return false;
		else if(s.charAt(psn -1) != ' ')
			return false;
		else if(psn + keyword.length() == s.length())
			return true;
		else
			return false;
		}
	

		/**"helper method" a method that contributes 
		 * functionality to another method. Helper methods are
		 * very common when you need to do the same thing repeatedly
		 * They also help make methods more readable
		 * This method is private because it is only used 
		 * by the method it is helping
		 */
		private static boolean noNegations(String phrase, 
				int index){
			//check for word "NO " (3 characters)
			//check to see if there is space for the word
			//"NO " to be in front of the index
			if(index - 3 >=0  && 
					phrase.substring(index-3,index).equals("no ")){
				return false;
			}
			//check for "not "
			if(index - 4 >=0  && 
					phrase.substring(index-4,index).equals("not ")){
				return false;
			}
			//check for "never "
			if(index - 6 >=0  && 
					phrase.substring(index-6,index).equals("never ")){
				return false;
			}
			//check for "n't "
			if(index - 4 >=0  && 
					phrase.substring(index-4,index).equals("n't ")){
				return false;
			}
			return true;
		}


	public static String getInput(){
		return inputSource.nextLine();
	}

	public static void print(String s){
		multiLinePrint(s);
	}

	public static void multiLinePrint(String s){
		String printString = "";
		int cutoff = 25;
		//this while loop last as long as there are words left in the original String
		while(s.length() > 0){

			String currentCut = "";
			String nextWord = "";

			//while the current cut is still less than the line length 
			//AND there are still words left to add
			while(currentCut.length()+nextWord.length() < cutoff && s.length() > 0){

				//add the next word
				currentCut += nextWord;

				//remove the word that was added from the original String
				s = s.substring(nextWord.length());

				//identify the following word, exclude the space
				int endOfWord = s.indexOf(" ");

				//if there are no more spaces, this is the last word, so add the whole thing
				if(endOfWord == -1) {
					endOfWord = s.length()-1;//subtract 1 because index of last letter is one les than length
				}

				//the next word should include the space
				nextWord = s.substring(0,endOfWord+1);
			}

			printString +=currentCut+"\n";

		}
		System.out.print(printString);
	}

	public static int getIntegerInput() {
		print("Please enter an integer.");
		String integerString = getInput();
		boolean isInteger = false;
		int value = 0;
		while(!isInteger){
			try{
				value = Integer.parseInt(integerString);
				//will not continue if an error above is thrown
				isInteger = true;//exits loop if entry is valid
			}catch(NumberFormatException e){
				print("You must enter an integer. You better try again.");
				integerString = getInput();
			}
		}
		return value;
	}

}

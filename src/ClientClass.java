import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class ClientClass {
	private SpellChecker bst;
	private SpellChecker trie;
	private Scanner input;
	private ArrayList<String> splitArray;
	
	public ClientClass(){
		bst = new BSTSpellChecker();
		trie = new TrieSpellChecker();
		input = new Scanner(System.in);
		splitArray = new ArrayList<>();
	}
	
	public void wordCorrection(){
		
		System.out.println("Please choose your favorite SpellChecker:\nSpell Checkers:");
		System.out.println("   a. Unbalanced BST\n   b. Balanced BST\n   c. Trie");
		
		//try{
			System.out.print("Choose: ");
			String choice = input.nextLine();
		//}catch()
		
		switch(choice){
			case "a":
				System.out.println("System will take more than a minute to load with this option!");
				((BSTSpellChecker)bst).addToTreeInOrder(); 
				correctWords(bst);
				break;
			case "b":
				System.out.println("System will take about few seconds to load...!");
				((BSTSpellChecker)bst).addTreeInBalanced(); 
				correctWords(bst);
				break;
			case "c":
				System.out.println("System will take about few seconds to load!");
				((TrieSpellChecker)trie).addToTrie(); 
				correctWords(trie);
				break;
			default:
	
		}
	
	}
	
	private void correctWords(SpellChecker o){
		String answer = "";
		do{
			boolean isError = false;
			System.out.println("Enter your paragraph: ");
			String paragraph = input.nextLine();
			paragraph = paragraph.toLowerCase();
			
			//String[] splitArray = paragraph.split("\\s+");
			splitWords(paragraph);
			/*for(String s : splitArray)
				System.out.println(s);*/
				
			for(int i = 0; i < splitArray.size(); i++){
				//System.out.println(splitArray.get(i));
				Set<String> test = o.closeMatches(splitArray.get(i));
				if(test != null){
					System.out.println("\n+++++++++++++++++++++++++++++++++\n" + splitArray.get(i) + " is not correct!");
					System.out.println("Suggestion: ");
					// Iterator method
					/*Iterator<String> iter = test.iterator();
					while(iter.hasNext())
						System.out.print(iter.next());*/
					
					// Loop method
					for(String s : test)
						System.out.println(s);
					
					test.clear();
					isError = true;
				}
			
			}
		
			if(!isError)
				System.out.println("Correct Grammar. No Errors to Be Corrected");
			
			System.out.print("Continues?...Press Y||Exit?...Press X: ");
			answer = input.nextLine();
		}while(!answer.equals("X") && !answer.equals("x"));
		
		System.out.println("Bye! See you again...");
	}
	
	private void splitWords(String s){
		String t = "";
		splitArray = new ArrayList<>(); // Reset the array
		int count = 0;
		char c = ' ';
		for(int i = 0; i < s.length() + 1; i++){
			
			if(i < s.length())
				c = s.charAt(i);
			else
				c = ' ';
			
			if(c >= 'a' && c <= 'z'){
				t += c;
				count++;
			}
			else if( !(c >= 'a' && c <= 'z') && count > 0){
				splitArray.add(t);
				count = 0;
				t = "";
			}
				
		}
	}
	public static void main(String[] args){
		ClientClass client = new ClientClass();
		client.wordCorrection();
	}
}

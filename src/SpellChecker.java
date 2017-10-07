import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public abstract class SpellChecker {
	protected Scanner inFile;// = new Scanner("");
	protected Set<String> set; 
	
	public SpellChecker(){
		inFile = new Scanner("");
		set = new HashSet<String>();
	}
	
	protected void fileReading(){
		try{
			File inputFile = new File("Project3_wordlist.txt");
			//File inputFile = new File("test.txt");
			inFile = new Scanner(inputFile);
		}catch(FileNotFoundException e){
			System.out.println("File not found: Project3_wordlist.txt" );
			System.out.println("Program terminated");
			System.exit(0);
		}
	}
	
	protected abstract void add(String s);
	
	protected abstract boolean contains(String s);
	
	protected Set<String> closeMatches(String s){
		if(contains(s))
			return null;
		
		/*1. Inserting a new character at any position in the word*/
		StringBuilder tempS = new StringBuilder(s);
		for(int l = 0; l < 26; l++){
			String newLetter = "" + (char)(l + 'a');
			tempS = tempS.append(newLetter); // Insert to the end of the string
			
			if(contains(tempS.toString()))
				set.add(tempS.toString());
				
			tempS = new StringBuilder(s);
			
			// Insert a character to all the position in the word except the end
			for(int count = 0; count < s.length(); count++){
				int beginIndex = 0;
				int endIndex = count;
				String d = tempS.substring(beginIndex, endIndex) + newLetter + tempS.substring(endIndex );
				if(contains(d))
					set.add(d);
					
				tempS = new StringBuilder(s);

			}
			
			/*2. Replacing the character at each position in the word with a new character*/
			for(int w = 0; w < s.length(); w++){
				int start = w;
				int end = w + 1;
				tempS.replace(start,end,newLetter);
				//System.out.println(tempS.toString());
				if(contains(tempS.toString()))
					set.add(tempS.toString());
				tempS = new StringBuilder(s);
			
			}

		}
		
		// Loop through the string s
		String c ="";
		int countPair = 2; // Pointer points to the 2nd position/3rd character in the string skips first 2
		for(int i = 0; i < s.length(); i++){
			c += s.charAt(i);
			int beginIndex = 0;
			int endIndex = i;
		/*3. Swapping any pair of adjacent characters in the word. */
			if(countPair < s.length()){ //Swapping adjacent character in the string except the end
				
				String first = "" + s.charAt(i) ;
				String second = "" + s.charAt(i + 1);
				String swap = s.substring(beginIndex,endIndex) + second + first + s.substring(countPair);
				countPair++;
				if(contains(swap))
					set.add(swap);
			}
			if(i == s.length() - 2){ // Swapping adjacent character at the end of the string
				String first = "" + s.charAt(i) ;
				String second = "" + s.charAt(i + 1);
				String swap = s.substring(beginIndex,endIndex) + second + first;
				if(contains(swap))
					set.add(swap);
			}
			
		/*4. Deleting a character at any position in the word. */
			
			String d = s.substring(beginIndex, endIndex) + s.substring(endIndex + 1);
			if(!d.equals(" "))
				if(contains(d))
					set.add(d);
				
			/*5. Inserting a space between any pair of adjacent characters in the word, 
			to see if two smaller words can be formed. */
			
			if(contains(c)){ // If found the words same as the word in file then save it to set
				
				//Check if the String s is made of 2 separate words
				String c2 = "";
				for(int j = i + 1; j < s.length(); j++) // Save the rest of s to new String c2
					c2 += s.charAt(j); // 
				if(contains(c2)) // Check if c2 is in the file
					set.add(c + " " + c2);
				/*else
					set.add(c);*/
			}
			
			
		}
		
		return set;
		
	}
}

import java.util.Map;
import java.util.HashMap;

public class TrieSearchTree {
	
	private static class TrieNode{
		private Map<Character, TrieNode> children;
		private boolean endOfWord;
		
		private TrieNode(){
			children = new HashMap<>();
			endOfWord = false;
		}
	}
	
	private TrieNode root = new TrieNode();
	
	/*This method add words to the TrieSearchTree, characters and references to next
	TrieNode will be stored in the Map/children of the TrieNode*/
	public void insert(String s){
		
		/*TrieNode link = root;
		TrieNode newNode = new TrieNode();
		if(!root.children.containsKey(s.charAt(0)))
			root.children.put(s.charAt(0), newNode);
		link = root.children.get(s.charAt(0));
		
		
			for(int i = 1; i < s.length(); i++){
				char c = s.charAt(i);
				TrieNode temp = link;
				
					if(!temp.children.containsKey(c)){
						temp.children.put(c, newNode);
					}
					link = temp.children.get(c);
			}
			
			link.endOfWord = true;*/
		// Better way for the insertion, the code above does work a well
		TrieNode link = root;
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			TrieNode newNode = link.children.get(c); // Get the next node/value using key
													// return null if the value can not found
			if(newNode == null){
				newNode = new TrieNode();
				link.children.put(c, newNode);
			}
			link = newNode;
		}
		link.endOfWord = true;
	}
	/*This find method take input string and check if the string is included inside the TrieSearchTree
	 * if not then return null, if found then return that string*/
	public String find(String s){
		TrieNode current = root;
		String returnS = "";
		for(int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			
			if(current.children.containsKey(c)){
				current = current.children.get(c);
				returnS += c;
			}
			else
				return null;
				
		}
		if(current.endOfWord)
			return returnS;
		else
			return null;
	}
	//public String toString(){
		/*String s = "";
		TrieNode link = root;
		while(link != null){
			TrieNode temp = link;
			char c = ' ';
		 for( Character key :  temp.children.keySet()){
		 	s += key + "\n";
		 	c = key;
		 	link = temp.children.get(c);
		 	temp = link;
		 }
		 link = temp.children.get(c);
		}
		 
		return s;*/
	//	return toString(root,"");
	//}
	
	/*private String toString(TrieNode where, String s){
		if(where == null)
			return "null";
		else{
			char c = ' ';
			for( Character key :  where.children.keySet()){
			 	s += key + toString(where.children.get(c),s) + "\n";
			 	c = key;
			}
			return s  ;
		}
	}*/
	public static void main(String[] args){
		TrieSearchTree trie = new TrieSearchTree();
		trie.insert("abcd");
		trie.insert("ade");
		trie.insert("cdea");
		System.out.println(trie.find("ade"));
		System.out.println(trie.find("abcd"));
		
		System.out.println(trie.find("adesd"));
		System.out.println(trie.find("a"));
		System.out.println(trie.find("cdea"));
	}
}

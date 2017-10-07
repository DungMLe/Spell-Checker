import java.util.Set;

public class TrieSpellChecker extends SpellChecker {
	private TrieSearchTree trie;
	
	public TrieSpellChecker(){
		super();
		trie = new TrieSearchTree();
	}
	public void add(String s){
		trie.insert(s);
	}
	
	public boolean contains(String s){
		if(trie.find(s) == null)
			return false;
		else
			return true;
	}
	
	public void addToTrie(){
		super.fileReading();
		while(inFile.hasNext()){
			String s = inFile.nextLine();
			//System.out.println(s);
			add(s);
		}
		
	}
	public Set<String> closeMatches(String s){
		return super.closeMatches(s);
	}
	
	public static void main(String[] args){
		TrieSpellChecker checker = new TrieSpellChecker();
		checker.addToTrie();
		Set<String> set = checker.closeMatches("cop");
		if(set == null)
			System.out.println("Word is found!!!...");
		else{
			for(String t : set)
				System.out.println(t);
		}
	}
}

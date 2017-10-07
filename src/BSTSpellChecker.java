import java.util.ArrayList;
import java.util.Set;

public class BSTSpellChecker extends SpellChecker{
	private BinarySearchTree<String> bst ; 
	private ArrayList<String> storage ;
	
	public BSTSpellChecker(){
		super();
		bst = new BinarySearchTree<>();
		storage = new ArrayList<>();		
	}
	
	public void add(String s){
		bst.interationAdd(s);
	}
	
	public boolean contains(String s){
		if( bst.iterationFind(s) == null)
			return false;
		else
			return true;
	}
	
	public void addToTreeInOrder(){
		super.fileReading();
		while(inFile.hasNext()){
			String s = inFile.nextLine();
			add(s);
		}
	}
	
	public void addTreeInBalanced(){
		super.fileReading();
		while(inFile.hasNext()){
			String s = inFile.nextLine();
			storage.add(s);
		}
		storage.trimToSize();
		
	
		addRecursively(storage.size() - 1, 0);
	}
	
	public void addRecursively(int high, int low){
		
		if(high >= low){
			int mid = (high + low)/2;
			add(storage.get(mid));
			addRecursively(mid - 1,low);
			addRecursively(high,mid + 1);
		}
		
		
	}
	
	public Set<String> closeMatches(String s){
		return super.closeMatches(s);
	}
	
	
	public static void main(String[] args){
		BSTSpellChecker checker = new BSTSpellChecker();
		//checker.addToTreeInOrder();
		checker.addTreeInBalanced();
		Set<String> test = checker.closeMatches("am");
		//test.clear();
		//test = checker.closeMatches("i");
		if(test == null)
			System.out.println("The word is correct! Good Job Buddy! Clap Clap Clap...");
		else{
			System.out.println("No Words Matched!\n");
			System.out.println("Suggestions: ");
			for(String s : test)
				System.out.println(s);
		}
	}
}

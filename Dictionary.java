
import java.util.ArrayList;

/**
 * The class represent a dictionary of words. 
 * It provides a way of searching through the dictionary.
 * It also can produce a dictionary in which the words are limited
 * to a particular length. 
 * 
 * @author Joanna Klukowska
 * @author Suebin Kim Edited - 12/2/15
 *
 */
public class Dictionary extends BST<String> implements DictionaryInterface{
	//actual storage for the words
	private ArrayList < String > words;

	
	
	/**
	 * Creates an empty Dictionary object (no words).
	 */
	public Dictionary () {
		super();
		//words = new ArrayList < String > () ;
	}
	
	/**
	 * Creates a Dictionary object containing all words from the 
	 * listOfWords passed as a parameter and stores it as a BST
	 * 
	 * @param listOfWords the list of words to be stored in the newly created 
	 * Dictionary object
	 */
	public Dictionary ( ArrayList < String > listOfWords ) {
		super(listOfWords.get((listOfWords.size()-1)/2));
		
		//store words list as binary tree
		//starts with left side then with right
		int start = 0;
		int end = listOfWords.size()-1;
		int mid = (start+end)/2;
		makeTree(listOfWords, start, mid-1);
		makeTree(listOfWords, mid+1, end);
		
		
		/**old code written by Professor below
		 * 
		 */
		//words = listOfWords;
		//if (null == words) {
		//	words = new ArrayList <String> ();
		//}
		

		
	}
	
	private BSTNode<String> makeTree(ArrayList<String> listOfWords, int start, int end) {
		if (start > end) {
			return null;
		}
		
		int mid = (start+end)/2;
		//creates new node to insert into the tree
		BSTNode <String> curNode = new BSTNode<String>(listOfWords.get(mid));
		this.insert(curNode.getData());
		//sets the nodes left and right references
		curNode.setLeft(makeTree(listOfWords, start, mid-1));
		curNode.setRight(makeTree(listOfWords, mid+1, end));
		
		return curNode;
			
			
		
	}
	
	public int getSize() {
		return this.words.size();
	}
	
	
	
	/**
	 * Creates a new Dictionary object from this Dictionary object that 
	 * contains words of a specified size.
	 * @param size length of the words that should be included in the new 
	 * Dictionary object
	 * @return a new Dictionary object containing only the words of specified 
	 * size
	 */
	public Dictionary getWordsBySize ( int size ) {
		ArrayList <String> wordsBySize = new ArrayList<String> ();
		for (int i = 0; i < words.size(); i++)
			if (words.get(i).length() == size)
				wordsBySize.add(words.get(i));
		return new Dictionary (wordsBySize);
	}
	
	 
	/**
	 * Performs (binary) search in this Dictionary object for a given word.
	 * @param word  the word to look for in this Dictionary object. 
	 * @return true if the word is in this Dictionary object, false otherwise
	 */
	public boolean findWord ( String word ) {
		if(this.contains(word))
			return true;
		else 
			return false;
		
		/**old code written by Professor below
		 * 
		 */
		//return isWordInDictionaryRecursive( word, 0, words.size()-1);

	}
	
	/* METHOD NOT USED AFTER BST IS MADE ** 
	 * The actual method providing recursive implementation of the binary search.
	 * @param word the word to look for in this Dictionary object
	 * @param tree of words to look through
	 * @return  true if the word is in this Dictionary object, false otherwise
	 */
	private boolean isWordInDictionaryRecursive ( String word, int begin, int end ) {
		if (begin > end )
			return false;
		
		int half = (begin+end+1) / 2;
		int comparison = words.get(half).compareToIgnoreCase(word);
		if ( comparison < 0 )
			return isWordInDictionaryRecursive( word, half + 1, end );
		else if ( comparison > 0 )
			return isWordInDictionaryRecursive( word, begin, half - 1);
		else
			return true;
	}
	
	/**
	 * Performs (binary) search in this Dictionary object for a given prefix.
	 * @param prefix  the prefix to look for in this Dictionary object. 
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	public boolean findPrefix (String prefix ) {
		
		
		return isPrefixInDictionaryRecursive (prefix, this.root );
	}

	/*
	 * The actual method providing recursive implementation of the binary search
	 * for the prefix. 
	 * @param prefix the prefix to look for in this Dictionary object.
	 * @param node to make comparison with
	 * @return true if at least one word with the specified prefix exists 
	 * in this Dictionary object, false otherwise
	 */
	private boolean isPrefixInDictionaryRecursive(String prefix, BSTNode<String> n) {
		if (n.getData() == null){
			return false;
		}
		
		int comparison = n.getData().compareToIgnoreCase(prefix);
		boolean isPrefix = n.getData().startsWith(prefix);
		if (isPrefix) 
			return true;
		
		//makes sure the new parameters are not null references
		if (comparison < 0 && n.getLeft() != null)
			return isPrefixInDictionaryRecursive( prefix, n.getLeft());
		else if ( comparison > 0 && n.getRight() != null)
			return isPrefixInDictionaryRecursive( prefix, n.getRight());
		else  //this case should never happen
			return true;
		
		/**old code written by Professor below
		 */
		
		//if (begin > end )
		//return false;
		
		//int half = (begin+end+1) / 2;
		//int comparison = words.get(half).compareToIgnoreCase(prefix);
		//boolean isPrefix = words.get(half).startsWith(prefix);
		//if (isPrefix) 
			//return true;
		
		//if (comparison < 0 )
			//return isPrefixInDictionaryRecursive( prefix, half + 1, end );
		//else if ( comparison > 0 )
			//return isPrefixInDictionaryRecursive( prefix, begin, half - 1);
		//else  //this case should never happen
			//return true;
	}

	
	
}

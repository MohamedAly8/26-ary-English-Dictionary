import java.util.ArrayList;

public class WordTree {
	private TreeNode root;
	
	public WordTree() {
		root = new TreeNode(); 
	}
	
	public int numOfWords(){
		return numOfWordsRec(root);
	}
	
	private int numOfWordsRec(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int childrenWords = 0;
		for(int i = 0; i < 26; i++) {
			childrenWords += numOfWordsRec(node.children[i]);
		}
		if(node.isWord) {
			return 1 + childrenWords;
		}
		return childrenWords;
	}
	
	public void insertWord(String word) {
		insertWordRec(root, word);
	}
	
	private void insertWordRec(TreeNode node, String word) {
		char firstChar = word.charAt(0);
		if(word.length() == 1) {
			if(node.children[letterToInt(firstChar)] == null) {
				node.children[letterToInt(firstChar)] = new TreeNode();
				node.children[letterToInt(firstChar)].isWord = true;
			}
			else {
				node.children[letterToInt(firstChar)].isWord = true;
			}
		}
		else {
			String restOfWord = word.substring(1);
			if(node.children[letterToInt(firstChar)] == null) {
				node.children[letterToInt(firstChar)] = new TreeNode();
				insertWordRec(node.children[letterToInt(firstChar)], restOfWord);
			}
			else {
				insertWordRec(node.children[letterToInt(firstChar)], restOfWord);
			}
		}
	}
	
	public boolean contains(String word) {

		return containsRec(root,word);
	}

	private boolean containsRec(TreeNode Node, String word) {
		char firstChar = word.charAt(0);

		if (word.length() == 1){
			if (Node.children[letterToInt(firstChar)] == null){
				return false;
			}
			else{
				return Node.children[letterToInt(firstChar)].isWord;
			}
		}
		else{
			String RemainingWord = word.substring(1);
			if (Node.children[letterToInt(firstChar)] == null){
				return false;
			}
			else{
				return containsRec(Node.children[letterToInt(firstChar)],RemainingWord)
			}
		}
 	}
	
	public ArrayList<String> suggestCorrections(String word, int offBy) {
		
		ArrayList<String> list_of_words = WordEndings(root, "");
		ArrayList<String> Suggested_Words = new ArrayList<>();

		int error_margin = 0;
		
		for (int i = 0; i < list_of_words.size(); i++) {
			error_margin = 0;
			if (list_of_words.get(i).length() == word.length()) {
				for (int j = 0; j < list_of_words.get(i).length(); j++) {
					if (list_of_words.get(i).charAt(j) != word.charAt(j)) {
						error_margin++;
					}
				}
				if (error_margin <= offBy) {
					Suggested_Words.add(list_of_words.get(i));
				}
			}
		}
		return Suggested_Words;
	}
	
	public ArrayList<String> suggestAutoCompletes(String prefix){
		ArrayList<String> list_of_words = suggestAutoCompleteRec(prefix,root);


		char[] char_in_prefix = new char[prefix.length()];
		for (int i = 0; i < prefix.length(); i++) {
			char_in_prefix[i] = prefix.charAt(i);
		}
			for (int i = prefix.length() - 1; i >= 0; i--){
			list_of_words = addToEach(char_in_prefix[i],list_of_words);
		}


		try {
			if (contains(prefix)){
				list_of_words.add(0,prefix);
			}
		}catch (StringIndexOutOfBoundsException e) {

		}
		return list_of_words;
	}

	private ArrayList<String> suggestAutoCompleteRec(String prefix, TreeNode node) {
		ArrayList<String> list_of_words = new ArrayList<>();


		if (prefix.isEmpty()){
			list_of_words = WordEndings(node, prefix);
		}
		else{
			TreeNode PREFIX_NODE = Find_prefixNode(node, prefix);
			if (PREFIX_NODE == null){
				return list_of_words;
			}
			list_of_words = WordEndings(PREFIX_NODE, "");
		}
		return list_of_words;
	}


	// GETS ALL WORDS UNDER THE NODE AND ADDS THE PREFIX TO IT
	private ArrayList<String> WordEndings(TreeNode node, String prefix){

		ArrayList<String> end = new ArrayList<>();
		for(int i = 0; i < 26; i++) {
			if (node.children[i] != null){
				prefix = prefix + intToLetter(i);

				if (node.children[i].isWord) {
					end.add(prefix);
				}
					end.addAll(WordEndings(node.children[i], prefix));
					prefix = prefix.substring(0, prefix.length() - 1);
				}
			}
		return end;
	}

		private TreeNode Find_prefixNode(TreeNode root, String prefix){
		char First_Char = prefix.charAt(0);
		TreeNode found = root.children[letterToInt(First_Char)];

		if (root.children[letterToInt(First_Char)] != null){
			prefix = prefix.substring(1);
			if (prefix.isEmpty() == false)
				found = Find_prefixNode(root.children[letterToInt(First_Char)],prefix);
		}
		else {
			return null;
		}
		return found;
		}


	public ArrayList<String> addToEach(char c, ArrayList<String> words) {

		ArrayList<String> added = new ArrayList<>();

		for (String word : words){
			word = c + word;
			added.add(word);
		}
		return added;
	}


	public int letterToInt(char c) {
		return c - 97;
	}
	
	public char intToLetter(int i) {
		return (char)(i + 97);
	}
}

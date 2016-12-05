package structures;

/**
 * Created by xuanwang on 12/1/16.
 */
public class AddAndSearchWordWithWildCard {
    // add / search: O(wordLength) time; O(numOfTrieNode * 26) = O(numOfWords * wordLength * 26) space
    class TrieNode {
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[26];//new TrieNode[26], not new TrieNode() !!!!
            isWord = false;
        }
    }

    public class WordDictionary {
        TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }

        // Adds a word into the data structure.
        public void addWord(String word) {
            TrieNode node = root;
            for (int i = 0; i < word.length(); i++) {
                int j = word.charAt(i) - 'a';
                if (node.children[j] == null) {
                    node.children[j] = new TrieNode();
                }
                node = node.children[j];
            }
            node.isWord = true;
        }

        // Returns if the word is in the data structure. A word could
        // contain the dot character '.' to represent any one letter.
        public boolean search(String word) {
            return find(word, root, 0);
        }

        private boolean find(String word, TrieNode node, int index) {
            if (index == word.length()) {//word found
                return node.isWord;//return node.isWord, not true !!!!
            }
            if (word.charAt(index) == '.') {//if whatever char can be matched
                for (TrieNode temp : node.children) {
                    if (temp != null && find(word, temp, index + 1)) {//if word found, return true
                        return true;
                    }
                }
                return false;
            } else {
                int j = word.charAt(index) - 'a';
                TrieNode temp = node.children[j];
                return temp != null && find(word, temp, index + 1);
            }
        }
    }

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
}

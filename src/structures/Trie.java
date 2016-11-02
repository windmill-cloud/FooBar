import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }


    //O(n)
    // Inserts a word into the trie.
    public void insert(String word) {
        char[] c = word.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < c.length; i++) {
            if (!temp.next.containsKey(c[i])) {
                temp.next.put(c[i], new TrieNode(c[i]));
            }
            temp = temp.next.get(c[i]);
        }
        temp.isEnd = true;
    }


    //O(n)
    // Returns if the word is in the trie.
    public boolean search(String word) {
        char[] c = word.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < c.length; i++) {
            if (!temp.next.containsKey(c[i])) return false;
            temp = temp.next.get(c[i]);
        }
        return temp.isEnd;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        char[] c = prefix.toCharArray();
        TrieNode temp = root;
        for (int i = 0; i < c.length; i++) {
            if (!temp.next.containsKey(c[i])) return false;
            temp = temp.next.get(c[i]);
        }
        return true;
    }
}


class TrieNode {
    // Initialize your data structure here.

    char val;
    boolean isEnd = false;
    Map<Character, TrieNode> next = new HashMap<>();

    public TrieNode() {

    }

    public TrieNode(char val) {
        this.val = val;
    }
}

// Your Trie object will be instantiated and called as such:
// Trie trie = new Trie();
// trie.insert("somestring");
// trie.search("key");
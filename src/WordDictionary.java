import java.util.HashMap;
import java.util.Map;

public class WordDictionary {
    boolean isEnd = false;
    Map<Character, WordDictionary> next = new HashMap<>();
    // Adds a word into the data structure.
    public void addWord(String word) {
        char[] c = word.toCharArray();
        WordDictionary curr = this;
        for (int i = 0; i < c.length; i++) {
            if (!curr.next.containsKey(c[i])) {
                curr.next.put(c[i], new WordDictionary());
            }
            curr = curr.next.get(c[i]);
        }
        curr.isEnd = true;
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        return search(this, word.toCharArray(), 0);
    }

    private boolean search(WordDictionary curr, char[] word, int left) {
        for (int i = left; i < word.length; i++) {
            if (word[i] != '.' && (curr.next == null || !curr.next.containsKey(word[i]))
                    || word[i] == '.' && curr.next == null) {
                return false;
            }

            if (word[i] != '.') {
                if (!curr.next.containsKey(word[i])) {
                    return false;
                }
                curr = curr.next.get(word[i]);
            } else {
                for (WordDictionary nextDic:curr.next.values()) {
                    if (search(nextDic, word, i + 1)) return true;
                }
                return false;
            }
        }
        return curr.isEnd;
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");
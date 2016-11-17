package strings;

import java.util.HashMap;

/**
 * Created by xuanwang on 11/16/16.
 */
public class RemoveDuplicatesCharactersFromString {
    //1. remove duplicate characters in a string. ex "abcba", return "c".


    public String remove(String input) {
        HashMap<Character, Integer> charToIndex = new HashMap<>();
        Character[] result = new Character[input.length()];
        for (int i = 0; i < input.length(); i++) {
            char letter = input.charAt(i);
            if (charToIndex.containsKey(letter)) {
                int index = charToIndex.get(letter);
                if (index >= 0) {
                    result[index] = null;
                    charToIndex.put(letter, -1);
                }
            }
            else {
                result[i] = letter;
                charToIndex.put(letter, i);
            }
        }
        StringBuilder output = new StringBuilder();
        for (Character letter : result) {
            if (letter != null) {
                output.append(letter);
            }
        }
        return output.toString();
    }
}

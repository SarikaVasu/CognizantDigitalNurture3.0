import java.util.*;
public class Solution {
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> ans = new ArrayList<>();
        HashSet<String> per = new HashSet<>();
        List<String> wordList = new ArrayList<String>(Arrays.asList(words));
        findAllPer(wordList, new ArrayList<String>(), per);
        // System.out.println(per);
        for(String word: per) {
                // System.out.println(word + " " + s);
                List<Integer> list = findAllOcc(s,word);
                ans.addAll(list);
                // ans.add(s.indexOf(word));
        }
        Collections.sort(ans);
        return ans;
    }
    private static List<Integer> findAllOcc(String s, String w) {
        List<Integer> indexes = new ArrayList<Integer>();
        int index = 0;
        while(index != -1) {
            index = s.indexOf(w, index);
            if(index != -1) {
                indexes.add(index);
                index++;
            }
        }
        return indexes;
    }
    //permutation algo
    //since arraylist is used change is reflected in future calls also so it is important to do backtracking to undo the changes
    private static void findAllPer(List<String> wordList, List<String> word, HashSet<String> per) {
        if(wordList.isEmpty()) {
            String ans = "";
            for(int i=0; i<word.size(); i++) {
                ans += word.get(i);
            }
            per.add(ans);
            return;
        }
        String st = wordList.remove(0);
        for(int i = 0; i<=word.size(); i++) {
            word.add(i, st);
            System.out.println(word);
            findAllPer(wordList, word, per);
            word.remove(i);  
        }
        wordList.add(0, st);
    }
    public static void main(String[] args) {
        findSubstring("foobarfoobar", new String[] {"foo", "bar"});
    }
    
}
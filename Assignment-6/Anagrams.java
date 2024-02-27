import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
    final Integer[] primes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
    Map<Character, Integer> letterTable;
    Map<Long, ArrayList<String>> anagramTable;

    public Anagrams() {
        buildLetterTable();
        anagramTable = new HashMap<Long, ArrayList<String>>();
    }

    private void buildLetterTable() {
        letterTable= new HashMap<Character,Integer>();
        Character[] letter= {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for(int i = 0; i < 26; i++) {
            letterTable.put(letter[i], primes[i]);
        }
    }

    private void addWord (String s) {
        long hashcode = myHashCode(s);
        if(anagramTable.get(hashcode) != null) {
            anagramTable.get(hashcode).add(s);
        }else{
            ArrayList<String> new_string = new ArrayList<String>();
            new_string.add(s);
            anagramTable.put(hashcode, new_string);
        }
    }

    private Long myHashCode(String s) {
        long hashcode = 1;
        for (int i = 0; i < s.length(); i++) {
            hashcode = hashcode * letterTable.get(s.charAt(i));
        }
        return hashcode;
    }

    private void processFile(String s) throws IOException {
        FileInputStream fStream = new FileInputStream(s);
        BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
        String strLine;
        while ((strLine = br.readLine()) != null) {
            this.addWord(strLine);
        }
        br.close();
    }

    private ArrayList<Map.Entry<Long, ArrayList<String>>> getMaxEntries() {
        int max = 0;
        ArrayList<Map.Entry<Long, ArrayList<String>>> result = new ArrayList<Map.Entry<Long, ArrayList<String>>>();

        for(Map.Entry<Long, ArrayList<String>> temp : anagramTable.entrySet()) {
            if(temp.getValue().size() == max){
                result.add(temp);
            } else if(temp.getValue().size() > max) {
                max = temp.getValue().size();
                result.clear();
                result.add(temp);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Anagrams a = new Anagrams();
        final long startTime = System.nanoTime();

        try {
            a.processFile ("words_alpha.txt");
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries ();
        int anagram_length = maxEntries.get(0).getValue().size();
        long key_max = maxEntries.get(0).getKey();
        final long estimatedTime = System.nanoTime() - startTime ;
        final double seconds = ((double)estimatedTime/1000000000);
        System.out.println("Elapsed  Time : " + seconds);
        System.out.println("Key of max anagrams : " + key_max);
        System.out.println("List of max anagrams : " + maxEntries.get(0).getValue());
        System.out.println("Length of max anagrams : " + anagram_length);
    }

}

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class TweetsKeywords {
    public static void main(String[] args) throws Exception {
        BufferedReader tweets = new BufferedReader(new FileReader(args[0]));
        String line = null;

        HashMap<String, Integer> result = new HashMap<String, Integer>();
        result.put("hackathon", 0);
        result.put("dec", 0);
        result.put("chicago", 0);
        result.put("java", 0);

        while((line = tweets.readLine()) != null) {
            String[] words = line.toLowerCase().trim().split("\\W+");

            HashMap<String, Boolean> found = new HashMap<String, Boolean>();
            found.put("hackathon", false);
            found.put("dec", false);
            found.put("chicago", false);
            found.put("java", false);

            for (String word : words) {
                if (result.containsKey(word) && found.get(word) == false)  {
                    int temp = result.get(word);
                    result.put(word, ++temp);
                    found.put(word, true);
                }
            }
        }

        for (String word : result.keySet()) {
            System.out.println(word + result.get(word));
        }


    }
}

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;
import java.util.Arrays;

public class NGram {
    private int n;
    private int nSation;
    private ArrayList<String> knowledgeLines;

    HashMap<Unit, Integer> units;
    public NGram(int n, ArrayList<String> knowledgeLines){
        this.n = n;
        this.nSation = n;
        units = new HashMap<>();
        this.knowledgeLines = knowledgeLines;

        for (String line : knowledgeLines) {
            String[] words = line.split(" ");
            int totalWords = words.length;

            for (int i = 0; i <= totalWords - n; i++){
                Unit u = new Unit(n);
                for (int j = 0; j < n; j++){
                    u.addWord(words[i + j]);
                }

                if (!units.containsKey(u)) {
                  units.put(u, 1);
                } else {
                    units.put(u, units.get(u)+1);
                }
            }

        }
        // int totalWords = units.size();

        // for (Map.Entry<Unit, Integer> pair : units.entrySet()){
        //     pair.getKey().setProbability((double) units.get(pair.getKey()) / (totalWords- (n-1)));
        //     System.out.println(pair.getKey() + ": " + units.get(pair.getKey()) + ", " + pair.getKey().getProbability());
        // }
        //
        // double total = 0.0;
        // for (Map.Entry<Unit, Integer> pair : units.entrySet()){
        //     total += pair.getKey().getProbability();
        // }
        // System.out.println("total: " + total);
    }

    public void reconstruct(int n) {
        this.n = n;
        units = new HashMap<>();

        for (String line : knowledgeLines) {
            String[] words = line.split(" ");
            int totalWords = words.length;

            for (int i = 0; i <= totalWords - n; i++){
                Unit u = new Unit(n);
                for (int j = 0; j < n; j++){
                    u.addWord(words[i + j]);
                }

                if (!units.containsKey(u)) {
                  units.put(u, 1);
                } else {
                    units.put(u, units.get(u)+1);
                }
            }

        }
    }

    public String generateSentence(int words, String[] initialPrompt){
        ArrayList<String> finalSentence = new ArrayList<String>();
        for (int i = 0; i < initialPrompt.length; i++) {
            finalSentence.add(initialPrompt[i]);
        }
        for (int i = 0; i <= words; i++){
            reconstruct(nSation);
            String[] prompt = new String[n - 1];
            int k = 0;
            for (int j = n - 2; j >= 0; j--, k++) {
                prompt[k] = finalSentence.get(finalSentence.size() - 1 - j);
            } 
            System.out.println("PROMPT");
            for (int a = 0; a < prompt.length; a++) {
                System.out.print(prompt[a]);
                System.out.print(" ");
            }
            System.out.println();

            String nextWord = getNextWord(prompt);
            finalSentence.add(nextWord);
        }
        StringBuilder sb = new StringBuilder();
        for (String s : finalSentence) {
            sb.append(s);
            sb.append(" ");
        }
        return sb.toString();
    }

    private String getNextWord(String[] prompt) {
      System.out.println("gnw "+n);
      // for (Unit u : units.keySet()) {
      //     for (int i = 0; i < u.getContent().length; i++) {
      //      System.out.print(u.getContent()[i]);
      //       System.out.print(" ");
      //     }
      //   System.out.println();
      // }
      // System.out.println();
      // for (int i = 0; i < prompt.length; i++) {
      //     System.out.print(prompt[i]);
      //     System.out.print(" ");
      // }

      if (prompt.length == 0)
          return getRandomWord();
      HashMap<String, Integer> subUnits = new HashMap<>();
      
      for (Map.Entry<Unit, Integer> me : units.entrySet()) {
          if (me.getKey().nEquals(prompt)){
              // System.out.println("nEquals");
              subUnits.put(me.getKey().getContent()[me.getKey().getContent().length - 1], me.getValue());
          }
      }
      
      int subTotal = 0;
      for (Integer i : subUnits.values()) {
          subTotal += i;
      }

      double r = Math.random();
      double total = 0.0;
      for (Map.Entry<String, Integer> me : subUnits.entrySet()) {
          System.out.println(me.getKey() + " " + (double)me.getValue() / subTotal);
          total += (double)me.getValue() / subTotal;
          if (r <= total)
              return me.getKey();
      }
      reconstruct(n-1);
      return getNextWord(Arrays.copyOfRange(prompt, 1, prompt.length)); //försök gå ner i n när inget hittas.
    }

    private String getRandomWord() {
      int num = (int) (Math.random() * units.size());
      for(Unit u: units.keySet()) 
          if (--num < 0){
              int num2 = (int) (Math.random() * u.getContent().length);
              for (String s : u.getContent())
                  if (--num2 < 0)
                      return s;
          }
    return "gog";
    }
}

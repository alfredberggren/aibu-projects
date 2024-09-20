import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ArrayList;

public class NGram {
    private int n;

    HashMap<Unit, Integer> units;
    public NGram(int n, ArrayList<String> knowledgeLines){
        this.n = n;
        units = new HashMap<>();

        for (String line : knowledgeLines) {
            String[] words = line.split(" ");
            int totalWords = words.length;

            for (int i = 0; i < totalWords - n - 1; i++){
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
        int totalWords = units.size();

        for (Map.Entry<Unit, Integer> pair : units.entrySet()){
            pair.getKey().setProbability((double) units.get(pair.getKey()) / (totalWords- (n-1)));
            System.out.println(pair.getKey() + ": " + units.get(pair.getKey()) + ", " + pair.getKey().getProbability());
        }

        double total = 0.0;
        for (Map.Entry<Unit, Integer> pair : units.entrySet()){
            total += pair.getKey().getProbability();
        }
        System.out.println("total: " + total);
    }


    public String generateSentence(int words, String[] initialPrompt){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> finalSentence = new ArrayList<String>();
        for (int i = 0; i < initialPrompt.length; i++) {
            finalSentence.add(initialPrompt[i]);
        }
        System.out.println(finalSentence);
        for (int i = 0; i <= words; i++){
            String[] prompt = new String[n - 1];
            int k = 0;
            for (int j = n - 2; j >= 0; j--, k++) {
                prompt[k] = finalSentence.get(finalSentence.size() - 1 - j);
            } 

            String nextWord = getNextWord(prompt);
            finalSentence.add(nextWord);

            sb.append(nextWord);
            sb.append(" ");

        }
        return sb.toString();
    }

    private String getNextWord(String[] prompt) {
      HashMap<String, Integer> subUnits = new HashMap<>();
      
      for (Map.Entry<Unit, Integer> me : units.entrySet()) {
          if (me.getKey().nEquals(prompt))
              subUnits.put(me.getKey().getContent()[me.getKey().getContent().length - 1], me.getValue());
              
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
      return "poop"; //todo: försök gå ner i n när inget hittas.
    }

    private Unit getRandomUnit(){
        double r = Math.random();
        double total = 0.0;
        for (Map.Entry<Unit, Integer> pair : units.entrySet()){
            total += pair.getKey().getProbability();
            if (r <= total)
                return pair.getKey();
        }
        return null;
    }

    //TODO: skapa en riktig getNext"Random"Unit....
}

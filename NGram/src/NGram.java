import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class NGram {

    HashMap<Unit, Integer> units;
    public NGram(int n, String knowledgeBase){
        units = new HashMap<>();

        String[] words = knowledgeBase.split(" ");
        int totalWords = words.length;


        for (int i = 0; i < totalWords - (n); i++){
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


    public String generateSentence(int words){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <= words; i++){
            sb.append(getRandomUnit());
            sb.append(" ");
        }
        return sb.toString();
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

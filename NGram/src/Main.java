import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
public class Main {
    public static void main(String[] args){
        int wordCount = Integer.parseInt(args[0]);
        String[] prompt = Arrays.copyOfRange(args, 1, args.length);
        int n = prompt.length + 1;

        try {
            FileWriter writer = new FileWriter("words.txt", true);
            writer.write("\n");
            for (int i = 0; i < prompt.length; i++) {
                writer.write(prompt[i]);
                writer.write(" ");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> knowledgeLines = new ArrayList<>();
        try {
            FileReader reader = new FileReader("words.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                knowledgeLines.add(line);
            }
            reader.close();
 
        } catch (IOException e) {
            e.printStackTrace();
        }

        NGram ngram = new NGram(n, knowledgeLines);

        System.out.println(ngram.generateSentence(wordCount, prompt));

    }
}

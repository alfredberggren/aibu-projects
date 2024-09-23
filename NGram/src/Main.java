import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Scanner;

// Todo: ignore case, punctuation, exclamation marks etc. (?)

public class Main {
    public static void main(String[] args){
        int wordCount = Integer.parseInt(args[0]);
        Scanner in = new Scanner(System.in);
        //String[] prompt = Arrays.copyOfRange(args, 1, args.length);
        //int n = prompt.length + 1;



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

        try {
            FileWriter writer = new FileWriter("words.txt", true);
            String p;
            System.out.println("Say>");
            String l = in.nextLine();
            String[] prompt = l.split(" ");
            int n = prompt.length + 1;
            NGram ngram = new NGram(n, knowledgeLines);
            writer.write(l + '\n');
            while (true) {
                System.out.println(ngram.generateSentence(wordCount, prompt));
                System.out.println("Say>");
                p = in.nextLine();
                if (p.equals("q"))
                    break;
                prompt = p.split(" ");
                ngram.reconstruct(prompt.length + 1);
                writer.write(p + "\n");
            }
           
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //NGram ngram = new NGram(n, knowledgeLines);

        //System.out.println(ngram.generateSentence(wordCount, prompt));

    }
}

import java.util.Arrays;

public class Unit {
    private String[] content; 
    private double probability;
    private int index;

    public Unit(int n){
        content = new String[n];
        this.probability = probability;
        index = 0;
    }

    public String[] getContent() {
        return content;
    }


    public double getProbability() {
        return probability;
    }

    public void setProbability(double probability) {
        this.probability = probability;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < content.length; i++) {
            sb.append(content[i]);
            if (i != content.length - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object other){
        if (other == null) {
            return false;
        }

        if (other instanceof Unit u) {
            return Arrays.equals(this.content, u.content);
        }
        return false;

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(content);
    }

    public void addWord(String word) {
        if (index >= content.length)
            throw new IndexOutOfBoundsException("You bastard stop it");

        content[index] = word;
        index++;
    }
}

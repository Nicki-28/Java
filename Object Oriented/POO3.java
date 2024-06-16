//DESCRIPTION 
//Implement a method that, given a line of text, returns the longest word it contains. In the text, in addition to words, there may be spaces, punctuation symbols (period, comma, colon, semicolon), and numbers (a number does not count as a word).


import acm.program.CommandLineProgram;
import java.util.StringTokenizer;

public class POO3 extends CommandLineProgram {

    public void run() {
        String line = readLine("Enter a phrase: "); 
        String word = longestWord(line);
        println("The longest word is: " + word); 
    }

    public String longestWord(String line) {
        StringTokenizer tokens = new StringTokenizer(line, ".,;:1234567890 ");
        int longest = 0;
        String word = "";

        while (tokens.hasMoreTokens()) {
            String next = tokens.nextToken(); // more efficient method, not to make a variable for the length of words
            int chars = next.length(); // string current= tokenizer.nextToler();
                                      // if(current.length()>longest.length())
            if (longest <= chars) { // longest=current;
                longest = chars;
                word = next; // update words
            }
        }
        return word;
    }

    public static void main(String[] args) {
        new POO3().start(args);
    }
}

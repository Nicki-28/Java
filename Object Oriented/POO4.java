import acm.program.CommandLineProgram;

import java.util.StringTokenizer;

public class POO4 extends CommandLineProgram {

    public void run() {
        String line= readLine("Introduce una frase: ");
        String word= longestWord(line);
        println("La palabra más larga es: "+ word);
    }

    public String longestWord(String line) {
        StringTokenizer tokens= new StringTokenizer(line,".,;:1234567890 ");
        int longest=0;
        String word= "";

        while(tokens.hasMoreTokens()){
            String next= tokens.nextToken();    //metodo mas eficiente, no hacer variable para largo de palabras
            int chars= next.length();   // string current= tokenizer.nextToler();
                                        //if(current.length()>longest.length())
                if(longest<=chars){     //longest=current;
                    longest=chars;
                    word= next; //actualizar palabras
                }
        }
        return word;
    }

    public static void main(String[] args) {
        new POO4().start(args);
    }
}
//DESCRIPTION

//An acronym consists of taking the initial letters of a text, converting them to uppercase, and separating them by dots. For example, "Escuela Politécnica Superior" is converted to "E.P.S.".

//What is requested is a method that, given a line of text, converts it into an acronym

import acm.program.CommandLineProgram;
import java.util.StringTokenizer;

public class POO4 extends CommandLineProgram{

    public void run(){
        String line= readLine("Introduce lo que quieras convertir en acrónimo: ");
        String acronym= makeAcronym(line);
        for(int i=0; i<acronym.length(); i++){
            print(acronym.charAt(i));
            print(".");
        }

    }
    public String makeAcronym(String line){
        //nombre de tokenizer es la referencia
        String acronym="";
        StringTokenizer tokenizer= new StringTokenizer(line, "., "); // Type variable name = new object's name
        while(tokenizer.hasMoreTokens()){
            String token= tokenizer.nextToken(); //guardamos los tokens que pasaran
            if(token.length()>1 && Character.isUpperCase(token.charAt(0))){
                String letter= String.valueOf(token.toUpperCase().charAt(0));
                acronym = acronym.concat(letter);
            }
        }
        return acronym ;
    }
    public static void main(String[] args) {
        new POO4().start(args);
    }
}

import acm.program.CommandLineProgram;

public class Problema6 extends CommandLineProgram {

    public void run(){
        String str= readLine("Introduce una cadena de caracteres:");
        String new_word= removeExtrems(str);
        println("La cadena resultante es= "+ new_word);
    }

    public String removeExtrems (String str){
        char [] caracters = str.toCharArray();
        char [] elementos = new char[caracters.length-2];

        for(int i=1; i< caracters.length-1; i++){

            elementos[i-1]=caracters[i];

        }

        String new_word= new String(elementos,0, elementos.length);
        return new_word;
    }
    public static void main(String[] args) {
        new Problema6().start(args);
    }
}
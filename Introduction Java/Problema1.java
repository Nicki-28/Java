import acm.program.CommandLineProgram;

public class Problema1 extends CommandLineProgram {

    public void run(){
        int n;
        n=readInt("Inserta un entero mayor a 1:");

        if(n<=1){
            println("Error");
        }else{
            if(isPerfect(n)) {
                println("Es perfecto");
            }else{
                println("No es perfecto");
            }
        }
    }
    public boolean isPerfect(int n) {
        int i = 1;
        boolean perfect;
        int divisores = 0;
        while (i < n) {
            if (n % i == 0) {
                divisores += i;
            }
            i++;
        }


        if (divisores == n) {
            perfect = true;
        }else{
            perfect=false;
        }

        return perfect;
    }

    public static void main(String[] args) {
        new Problema1().start(args);
    }
}


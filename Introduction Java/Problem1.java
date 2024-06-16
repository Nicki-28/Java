//DESCRIPTION

// Ask the user for an integer greater than 1 and, in response, indicate whether the given integer is perfect or not. If the entered number is not greater than 1, an error message will be displayed.
//A number is perfect if it is the sum of all its divisors (except itself).


import acm.program.CommandLineProgram;

public class Problema1 extends CommandLineProgram {

    public void run(){
        int n;
        n=readInt("Introduce a number greater than 1:");

        if(n<=1){
            println("Error");
        }else{
            if(isPerfect(n)) {
                println("Is perfect");
            }else{
                println("Is not perfect");
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


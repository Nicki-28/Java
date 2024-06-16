//DESCRIPTION

//As an input parameter, receive an array of characters and as a result, return the array consisting of the same elements and in the same order as the original array except for the first and last elements.


import acm.program.CommandLineProgram;

public class Problem3 extends CommandLineProgram {

    public void run() {
        char[] chars = new char[]{'a', 'b', 'c', 'd', 'e'};
        char[] noExtremos = removeExtremos(chars);
        println(noExtremos);
    }

    public void println(char[] chars) {
        print("{");
        for (int i = 0; i < chars.length; ++i) {
            print(chars[i]);
            if (i != chars.length - 1) {
                print(", ");
            }
        }
        println("}");
    }

    public char[] removeExtremos(char[] chars) {
        if (chars.length < 3) {
            return new char[0]; //Empty array
        }
        char[] noExtremos = new char[chars.length - 2];
        for (int i = 1; i < chars.length - 1; i++) {
            noExtremos[i - 1] = chars[i];
        }
        return noExtremos;
    }

    public static void main(String[] args) {
        new Problem3().start(args);
    }
}

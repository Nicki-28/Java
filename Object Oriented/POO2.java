// DESCRIPTION

//One of the simplest encryption systems is called the Caesar Cipher, which involves replacing each letter in a message with another letter that is a fixed distance forward (in alphabetical order) from the initial letter. For example, if the distance is 3, 'A' would be replaced by 'D', 'B' by 'E', 'C' by 'F', 'X' by 'A', 'Y' by 'B', and so on.

//What is requested is a method such that, given a string and an integer, it returns the corresponding encrypted string obtained by encrypting the initial string using the integer as the distance. Take into account the following:

//Characters that are not letters remain unchanged.
//Uppercase letters translate to uppercase letters.
//Lowercase letters translate to lowercase letters.
//If the integer is negative, instead of substituting with characters further ahead in the alphabet, do it with characters that are earlier in the same alphabet.

import acm.program.CommandLineProgram;

public class POO2 extends CommandLineProgram {

    public void run() {
        int dist = readInt("Enter a distance: "); // Introduce una distancia

        String word = readLine("Enter word to encrypt: "); // Introduce palabra a cifrar

        char[] chars = word.toCharArray(); // It will have the index of position k

        char[] cifrado = cifrarMayus(chars, dist);

        String cifradoString1 = new String(cifrado, 0, cifrado.length);
        println("The encrypted word is: " + cifradoString1);
        // Show me the answer if the distance were reversed

        dist = -dist;

        char[] cifrado_inv = cifrarMayus(chars, dist);
        String cifradoString2 = new String(cifrado_inv, 0, cifrado_inv.length);
        println("If we change the distance symbol the result is= " + cifradoString2);
    }

    // Encrypt uppercase letters
    public char[] cifrarMayus(char[] chars, int dist) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] Mabc = abc.toCharArray();

        char[] cifrado = new char[chars.length];

        if (dist <= 0) {
            dist += 26; // In case dist is a negative number
        }

        for (int k = 0; k < chars.length; k++) {
            // if the character is uppercase
            if (Character.isUpperCase(chars[k])) {
                for (int i = 0; i < Mabc.length; i++) { // It will have the index of position i (uppercase abc array)
                    if (chars[k] == Mabc[i]) { // if the uppercase letter is found in the alphabet
                        cifrado[k] = Mabc[(i + dist) % 26];
                        break; // Exit the loop once the letter is encrypted
                    }
                }
            } else if (Character.isLowerCase(chars[k])) {
                // if it is lowercase
                String min = abc.toLowerCase();
                char[] mabc = min.toCharArray(); // It will have the index of position j (lowercase abc array)
                for (int j = 0; j < mabc.length; j++) {
                    if (chars[k] == mabc[j]) {
                        cifrado[k] = mabc[(j + dist) % 26];
                        break;
                    }
                }
            }

        }
        return cifrado;
    }

    public static void main(String[] args) {
        new POO2().start(args);
    }
}

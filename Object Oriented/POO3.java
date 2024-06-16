import acm.program.CommandLineProgram;

public class POO3 extends CommandLineProgram {

    public void run() {
        int dist = readInt("Introduce una distancia: ");

        String word = readLine("Introduce palabra a cifrar: ");

        char[] chars = word.toCharArray(); //tendra indice de posicion k

        char[] cifrado = cifrarMayus(chars, dist);

        String cifradoString1 = new String(cifrado,0, cifrado.length);
        println("La palabra cifrada es:" + cifradoString1);
        //Enseñame la respuesta si la distancia fuera a la inversa

        dist=-dist;

        char[] cifrado_inv = cifrarMayus(chars,dist);
        String cifradoString2 = new String(cifrado_inv,0, cifrado_inv.length);
        println("Si cambiamos el simbolo de distancia el resultado es= "  + cifradoString2);

    }

    // Cifrar las letras mayúsculas
    public char[] cifrarMayus(char[] chars, int dist) {
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        char[] Mabc = abc.toCharArray();

        char[] cifrado = new char[chars.length];

        if(dist<=0){
            dist+=26; // En el caso de que dist sea un número negativo
        }

        for (int k = 0; k < chars.length; k++) {
            //si el caracter es mayuscula

            if (Character.isUpperCase(chars[k])) {
                for (int i = 0; i < Mabc.length; i++) {//tendrá indice de posicion i (array abc mayus)
                    if (chars[k] == Mabc[i]) { //si la letra en mayuscula se encuentra en el abecedario
                        cifrado[k] = Mabc[(i + dist) % 26];
                        break; // Salir del bucle una vez cifrada la letra
                    }
                }
            }else if(Character.isLowerCase(chars[k])) {
                //si es minuscula
                String min = abc.toLowerCase();
                char[] mabc = min.toCharArray(); //tendrá indice de posicion j (array abc min)
                for (int j = 0; j < mabc.length; j++) {
                    if (chars[k] == mabc[j]) {
                        cifrado[k] = mabc[(j+dist) % 26];
                        break;
                    }
                }
            }

        }
        return cifrado;
    }

    public static void main(String[] args) {
        new POO3().start(args);
    }
}
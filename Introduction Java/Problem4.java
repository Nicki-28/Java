//DESCRIPTION

//Design and implement a method to multiply integer matrices. The method will receive two matrices as parameters and return another matrix as a result. That is:

//    - Simplifications and hints:

//  Within the function, you can assume that the matrices are of the correct dimensions (the number of columns in the left matrix must match the number of rows in the right matrix).
//  Create a main program that creates a pair of constant matrices (ensure that they are of the correct dimensions).


import acm.program.CommandLineProgram;
public class Problem4 extends CommandLineProgram{

    public void run(){
        int[][]right={{1,2,3},{4,5,6},{7,8,9}};
        int[][]left={{9,7,8},{6,5,4},{3,2,1}};
        int [][] result= matrixMultiplication(left,right);

        for (int i = 0; i < result.length; i++) {
            print("{");
                for (int j = 0; j < result[i].length; j++) {
                    print(result[i][j]);
                    if (j != result[i].length - 1) {
                        print(", ");
                    }
                }
                println("}");
            }
        }



    public int[][] matrixMultiplication(int [][] left, int [][] right){

        int filasL= left.length;
        int colR= right[0].length;
        int colL= left[0].length;

        int [][] result= new int [filasL][colR];

        if(colR==filasL){
            for(int i=0; i < filasL; i++){
                for(int j=0; j < colR;j++){
                    for(int k=0; k < filasL;k++){
                        result[i][j] += left[i][k] * right[k][j];
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        new Problem4().start(args);
    }
}

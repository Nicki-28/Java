
import acm.program.CommandLineProgram;

public class Problema2 extends CommandLineProgram {
    public void run () {
        int[] nums = new int[]{4, 12, -5, 3};
        int max = max(nums);
        println("El resultado es " + max);
    }

    public int max(int[] nums) {
        int i = 1;
        int maximo = nums[0];
        while (i <= nums.length - 1) {
            if (maximo < nums[i]) {
                maximo = nums[i];
            }
            i++;
        }
        return maximo;
    }
    public static void main(String[]args){
        new Problema2().start(args);
    }
}
/* It is correct because the statement indicates that there is at least one element inside the array.
   Otherwise, we would have to consider if the array is empty or if the first position is occupied by a null element */

